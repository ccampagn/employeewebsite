package servlet;

	import java.io.*; 
import java.util.Random;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.catalina.Session;
import org.jasypt.util.password.BasicPasswordEncryptor;

import modelcontroller.UserDataController;


import user.Email;
import user.RandomPassword;
import user.UserData;

import com.mysql.jdbc.Connection;

import database.DB;
import org.jasypt.util.password.PasswordEncryptor;



	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/forgetpassword")
	public class ForgetPasswordServlet extends HttpServlet {
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	@Override
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		request.getRequestDispatcher("/ForgetPassword.jsp").forward(request, response);
	  }
	 public void doPost(HttpServletRequest request,
             HttpServletResponse response)
throws ServletException, IOException {
		 Email emails=new Email();
		 BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		 RandomPassword randpass=new RandomPassword();
		 DB db=new DB();
		 boolean checkemail=false;
		 String email=request.getParameter("email");
		if(email==""||email.contains(" ")||email.contains("'")||email.contains(" ")||email.contains("'")){
			request.setAttribute("error", "Email is invalid");
			request.getRequestDispatcher("/ForgetPassword.jsp").forward(request, response);
		}
		 try {
			checkemail=db.checkifcontainemail(email);
			if(checkemail==true){
				int id=db.getidemail(email);
				String random=randpass.generateRandomString();
				String user=db.getuserbyid(id);
				String encrytion=passwordEncryptor.encryptPassword(random);
				db.updateattempt(user, false,encrytion);
				emails.sendemail(email,"New Password","Your new password is " +random);
				request.setAttribute("error", "Change Password Success");
				request.getRequestDispatcher("/Index.jsp").forward(request, response);
				
			}else{
				request.setAttribute("error", "Email not in system");
				request.getRequestDispatcher("/ForgetPassword.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 

	}
	
package servlet;

	import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;

import user.Email;
import user.Password;
import user.PositionInfo;
import user.RandomPassword;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/email")
	public class EmailServlet extends HttpServlet {
	  /**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	
	@Override
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null || !request.isRequestedSessionIdValid() )
		{
			request.setAttribute("error", "Invalid Request");
			request.getRequestDispatcher("/Index.jsp").forward(request, response); 
			
		}else{
			if(session.getAttribute("userinfo")==null){
				request.setAttribute("error", "Invalid Request");
				request.getRequestDispatcher("/Index.jsp").forward(request, response); 
			}else{
		DB db=new DB();
		String mail = null;
		int emaila=Integer.parseInt(request.getParameter("email"));
		String company = null;
		try {
			company = db.getcompanyid(emaila);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(!company.equals(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany())){
			request.getRequestDispatcher("/Index.jsp").forward(request, response);
		}else{
		
		try {

		mail=db.getemail(emaila);
		session.setAttribute("mail", mail);
		UserData user=db.getemployeeinfo(emaila);
		System.out.print(user.getFirstname());
		session.setAttribute("usermail", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/Email.jsp").forward(request, response); 
		
		}
		}
	}
	}
	  public void doPost(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		 HttpSession session = request.getSession(false);
		 if(session==null || !request.isRequestedSessionIdValid() )
			{
				request.setAttribute("error", "Invalid Request");
				request.getRequestDispatcher("/Index.jsp").forward(request, response); 
				
			}else{
				if(session.getAttribute("userinfo")==null||((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
					request.setAttribute("error", "Invalid Request");
					request.getRequestDispatcher("/Index.jsp").forward(request, response); 
				}else{
		Email email=new Email();
		String message=request.getParameter("message");
		email.sendemail(session.getAttribute("mail").toString(),"Message from "+((WorkInfo)session.getAttribute("userinfo")).getUserData().getFirstname()+((WorkInfo)session.getAttribute("userinfo")).getUserData().getLastname(),message);
		request.setAttribute("error", "Sucessful sent email");
		request.getRequestDispatcher("/EmployeeDirectory.jsp").forward(request, response);
				}
			}
	  }
	}
package servlet;

	import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;

import user.Email;
import user.Message;
import user.NewStories;
import user.Password;
import user.PositionInfo;
import user.RandomPassword;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/index")
	public class IndexServlet extends HttpServlet {
	  /**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	
	@Override
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		  request.getRequestDispatcher("/Index.jsp").forward(request, response);
	  }
	  @Override
	  public void doPost(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		  HttpSession session = request.getSession(true);
		  session.setMaxInactiveInterval(1000);
		  Email email=new Email();
		  BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		 RandomPassword randpass=new RandomPassword();
		 DB db=new DB();
		 boolean contain=false;
		 String username=request.getParameter("username");
		 String password=request.getParameter("password");
		  try {
			contain=db.checkifcontain(username);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		  if(username==""||password==""){
			  request.setAttribute("error", "You Have Empty Fields");
			  request.getRequestDispatcher("/Index.jsp").forward(request, response); 
		  }
		  else if(password.contains(" ")||password.contains("'")||username.contains(" ")||username.contains("'")){
			  request.setAttribute("error", "You Password or Username Have Invaild Character");
			  request.getRequestDispatcher("/Index.jsp").forward(request, response); 
		  }
		  else if(contain==false){
			 request.setAttribute("error", "That user name doesn't exist");
			 request.getRequestDispatcher("/Index.jsp").forward(request, response);
			}
		  else{
			  	Password pw = null;
				try {
					pw=db.getuserlogin(username);
					if(passwordEncryptor.checkPassword(password,pw.getPassword())){
						String encrytion=passwordEncryptor.encryptPassword(password);
						db.updateattempt(username,false,encrytion);
						WorkInfo work=db.getemployeestring(pw.getID());
						session.setAttribute("userinfo",work);
						ArrayList<Message> mess=db.getMessage(pw.getID());
						ArrayList<NewStories> news=db.getNews(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),((WorkInfo)session.getAttribute("userinfo")).getPositionInfo());
						session.setAttribute("message",mess);
						session.setAttribute("newstories",news);
						request.getRequestDispatcher("/Home.jsp").forward(request, response);
					}else{
						if(pw.getAttempt()==3){
							request.setAttribute("error", "Account Lock");
							String random=randpass.generateRandomString();
							String encrytion=passwordEncryptor.encryptPassword(random);
							email.sendemail(db.getemail(pw.getID()),"Account Lock","Your new password is " +random);		
							db.updateattempt(username,false,encrytion);
							request.getRequestDispatcher("/Index.jsp").forward(request, response);
					}
						else{
							String encrytion=passwordEncryptor.encryptPassword(password);
							db.updateattempt(username,true,encrytion);
							request.setAttribute("error", "Incorrect Password");
							request.getRequestDispatcher("Index.jsp").forward(request, response);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
					
				
			
				}
				  
			  
				 
				
				 
			  
		  
		  
		 
		  
	         
	        
	  }
	
	  
	  
	
	  
	}


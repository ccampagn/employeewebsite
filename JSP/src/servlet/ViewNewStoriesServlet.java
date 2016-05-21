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


	@WebServlet("/viewnewstories")
	public class ViewNewStoriesServlet extends HttpServlet {
	  /**
		 * 
		 */
	private static final long serialVersionUID = 1L;

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
						NewStories news = null;
						try {
							news=db.getNews(request.getParameter("upvote"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						session.setAttribute("viewnew",news);
						request.getRequestDispatcher("/NewStories.jsp").forward(request, response);
				}
				  
			  
				 
				
				 
			  
		  
		  
		 
		  
	         
	        
	  }
	
	  
	  
	
	  
	



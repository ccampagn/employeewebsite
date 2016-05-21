package servlet;

	import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;

import user.Break;
import user.Email;
import user.NewStories;
import user.Password;
import user.PositionInfo;
import user.RandomPassword;
import user.Shift;
import user.Training;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/AddNewStories")
	public class AddNewStoriesServlet extends HttpServlet {
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
					if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
						request.getRequestDispatcher("/Home.jsp").forward(request, response); 
					}
					else{
		  request.getRequestDispatcher("/AddStory.jsp").forward(request, response);
	  }
				}
			}
	}
	  @SuppressWarnings("deprecation")
	@Override
	  public void doPost(HttpServletRequest request,
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
					String location=request.getParameter("location");
					String sublocation=request.getParameter("sublocation");
					String dept=request.getParameter("dept" );
					String position=request.getParameter("position");
					String title=request.getParameter("title");
					String story=request.getParameter("story" );
					System.out.println(location);
					System.out.println(dept);
					System.out.println(sublocation);
					System.out.println(position);
					DB db=new DB();
					try {
						int companyid=db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany());
						NewStories news=new NewStories(0,companyid,location,dept,sublocation,position,null,title,story);
						db.insertnews(news);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.getRequestDispatcher("/Home.jsp").forward(request, response);
	         
	        
	  }
	
	  
	  
	
	  
	


	  }

	  }
	}
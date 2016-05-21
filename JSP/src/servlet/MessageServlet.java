package servlet;

	import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import user.Shift;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/Message")
	public class MessageServlet extends HttpServlet {
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
		
		  request.getRequestDispatcher("/Message.jsp").forward(request, response);
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
		if(session.getAttribute("userinfo")==null){
			request.setAttribute("error", "Invalid Request");
			request.getRequestDispatcher("/Index.jsp").forward(request, response); 
		}else{
DB db=new DB();
String company=null;
int id=1;
try {
	if(isNumeric(request.getParameter("to"))!=true){
		id=db.getid(request.getParameter("to"));
	}
	else{
		id=Integer.parseInt(request.getParameter("to"));
	}
	company=db.getacompany(id);
	if(company!=null){
		if(company.equals(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany())){
Message message=new Message(0,db.getMaxSeriesID()+1,((WorkInfo)session.getAttribute("userinfo")).getUserData().getID(),id,request.getParameter("message"),null,null,null,null);

	db.insertMessage(message);
		}
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
ArrayList<Message> mess = null;
ArrayList<NewStories> news = null;
try {
	mess = db.getMessage(((WorkInfo)session.getAttribute("userinfo")).getUserData().getID());
	news=db.getNews(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),((WorkInfo)session.getAttribute("userinfo")).getPositionInfo());
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
session.setAttribute("newstories",news);
session.setAttribute("message",mess);
request.getRequestDispatcher("/Home.jsp").forward(request, response);
}
}
}
	  public static boolean isNumeric(CharSequence cs) {
		    if (cs == null || cs.length() == 0) {
		        return false;
		     }
		     int sz = cs.length();
		     for (int i = 0; i < sz; i++) {
		         if (Character.isDigit(cs.charAt(i)) == false) {
		              return false;
		         }
		      }
		    return true;
		 }
	}
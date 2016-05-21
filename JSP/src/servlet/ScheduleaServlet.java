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


	@WebServlet("/Schedulea")
	public class ScheduleaServlet extends HttpServlet {
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
		try {
			//db.deleteshift();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int id=((WorkInfo)session.getAttribute("userinfo")).getUserData().getID();
		ArrayList<Shift> shift = null;
		try {
			shift=db.getschedule(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("schedule",shift);
		
		  request.getRequestDispatcher("/Schedule.jsp").forward(request, response);
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
try {
	//db.deleteshift();
	db.updateshifttrade(request.getParameter("upvote"));
} catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
int id=((WorkInfo)session.getAttribute("userinfo")).getUserData().getID();
ArrayList<Shift> shift = null;
try {
	shift=db.getschedule(id);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
session.setAttribute("schedule",shift);
request.getRequestDispatcher("/Schedule.jsp").forward(request, response);
}
}
}
	}
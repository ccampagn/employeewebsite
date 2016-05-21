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


	@WebServlet("/messageid")
	public class MessageIDServlet extends HttpServlet {
	  /**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	
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
		String id=request.getParameter("message");
		DB db=new DB();
		String name = null;
		try {
			name = db.getName(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("messageid",id);
		session.setAttribute("messagename",name);
		  request.getRequestDispatcher("/MessageID.jsp").forward(request, response);
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
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

import user.Attendance;
import user.Email;
import user.Password;
import user.PositionInfo;
import user.RandomPassword;
import user.Shift;
import user.ShiftInfo;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/deleteattendence")
	public class DeleteAttendanceServlet extends HttpServlet {
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
DB db=new DB();
ArrayList<Attendance> req;
try {
	db.deleteattendance(request.getParameter("upvote"));
	req=db.getallattendence(0);
	session.setAttribute("attendance", req);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
request.getRequestDispatcher("/Attendance.jsp").forward(request, response);
}
}
}
	}

package servlet;

	import java.io.IOException;
import java.util.ArrayList;
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
import user.Password;
import user.PositionInfo;
import user.RandomPassword;
import user.Shift;
import user.ShiftInfo;
import user.Training;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/changepoints")
	public class ChangePointsServlet extends HttpServlet {
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

 
try {
	db.updatepoints(session.getAttribute("edituser").toString(),request.getParameter("points"));
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
ArrayList<ShiftInfo> shift = null;
try {
	if((session.getAttribute("editreturn").toString()).equals("clockin")){
	shift=db.getscheduleclockin(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
	}else{
	shift=db.getscheduleclockincomplete(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
	}
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
if((session.getAttribute("editreturn").toString()).equals("clockin")){
	session.setAttribute("clockin", shift);
request.getRequestDispatcher("/ClockIn.jsp").forward(request, response);
}else{
	session.setAttribute("completeshift", shift);
	request.getRequestDispatcher("/CompletedShifts.jsp").forward(request, response);
}
}
}
}
	}

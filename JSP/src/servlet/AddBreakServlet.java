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
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/addbreak")
	public class AddBreakServlet extends HttpServlet {
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
String startday=request.getParameter("startdate").substring(6, 10)+"-"+request.getParameter("startdate").substring(0, 3)+"-"+request.getParameter("startdate").substring(3, 5);
String endday=request.getParameter("enddate").substring(6, 10)+"-"+request.getParameter("enddate").substring(0, 3)+"-"+request.getParameter("enddate").substring(3, 5);
String starttime=request.getParameter("starttime")+":00";
String endtime=request.getParameter("endtime")+":00";
Break breaks=new Break(session.getAttribute("break").toString(),startday,endday,starttime,endtime);
try {
	db.addbreak(breaks);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
if((session.getAttribute("addbreak").toString()).equals("clockin")){
request.getRequestDispatcher("/ClockIn.jsp").forward(request, response);
}else{
	request.getRequestDispatcher("/CompletedShifts.jsp").forward(request, response);
}
}
}
}
	}
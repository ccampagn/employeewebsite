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


	@WebServlet("/Editshifttimechange")
	public class EditShiftTimeChange extends HttpServlet {
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
String startday,endday,starttime,endtime;
if(request.getParameter("enddate")==null){
	endday=null;
	endtime=null;
}
else{
	 endday=request.getParameter("enddate").substring(6,10)+"-"+request.getParameter("enddate").substring(0, 2)+"-"+request.getParameter("enddate").substring(3, 5);
	 endtime=request.getParameter("endtime")+":00";
	
}
if(request.getParameter("startdate")==null){
	startday=null;
	starttime=null;
}
else{
	startday=request.getParameter("startdate").substring(6, 10)+"-"+request.getParameter("startdate").substring(0, 2)+"-"+request.getParameter("startdate").substring(3, 5);
	starttime=request.getParameter("starttime")+":00";
}
 
 

Break breaks=new Break(((Break) session.getAttribute("editshifttime")).getid(),startday,endday,starttime,endtime);
try {
	db.updateclockin(breaks);
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

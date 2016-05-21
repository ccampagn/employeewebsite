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


	@WebServlet("/clockintime")
	public class ClockInTime extends HttpServlet {
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
	int companyid=db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany());
	int id=db.getshiftid(request.getParameter("upvote"));
	boolean clockin=db.checkifclockin(id);
	ShiftInfo shift=db.getshiftind(request.getParameter("upvote"));
	if(clockin==false){
	db.updateshiftclockin(request.getParameter("upvote"));
	db.updateshiftclockinlate(request.getParameter("upvote"));
	int points=db.addpoints(shift);
	db.addactualpoints(points,shift);
	int point=db.getpointsnsnc(companyid);
	db.notshownocall(companyid,point);
	}
} catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
ArrayList<ShiftInfo> shift = null;
try {
	shift=db.getscheduleclockin(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
	session.setAttribute("clockin", shift);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
//session.setAttribute("schedule",shift);
request.getRequestDispatcher("/ClockIn.jsp").forward(request, response);
}
}
}
}
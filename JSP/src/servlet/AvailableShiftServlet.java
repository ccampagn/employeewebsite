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
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */



	@WebServlet("/AvailableShifts")
	public class AvailableShiftServlet extends HttpServlet {
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
		ArrayList<Shift> shift = null;
		try {
			shift=db.getavailableschedule(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("availableshift",shift);
		//for(int x=0;x<((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getTraining().size();x++){
			//System.out.println(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getTraining().get(x).getTraining());
		//}
		  request.getRequestDispatcher("/AvailableShift.jsp").forward(request, response);
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
int id=((WorkInfo)session.getAttribute("userinfo")).getUserData().getID();
//String shift=request.getParameter("upvote");
ArrayList<Shift> shift = null;
try {
	//db.updateshift(id, shift);
	shift=db.getavailableschedulesearch(0, Integer.parseInt(request.getParameter("search")), request.getParameter("field"));
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
session.setAttribute("availableshift",shift);
request.getRequestDispatcher("/AvailableShift.jsp").forward(request, response);
	}
	}

}
	}
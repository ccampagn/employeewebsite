package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.WorkInfo;
import user.timeoffrequest;

import database.DB;

@WebServlet("/timeoffrequest")
public class TimeOffRequestServlet extends HttpServlet {
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
		db.deletetor();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	ArrayList<timeoffrequest>req = null;
	//get all possible time off request for user
	try {
		req=db.gettor(((WorkInfo)session.getAttribute("userinfo")).getUserData().getID());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	session.setAttribute("timeoff",req);
	  request.getRequestDispatcher("/TimeOffRequest.jsp").forward(request, response);
  }
	}
}
public void doPost(HttpServletRequest request,
        HttpServletResponse response)
throws ServletException, IOException {
	DB db=new DB();
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
	String month=request.getParameter("month");
	String day=request.getParameter("day");
	String year=request.getParameter("year");
	String month2=request.getParameter("month2");
	String day2=request.getParameter("day2");
	String year2=request.getParameter("year2");
	String reason=request.getParameter("reason");
	String starttime=request.getParameter("starttime");
	String endtime=request.getParameter("endtime");
	if(year.length()!=4||year2.length()!=4||!isNumeric(year)||!isNumeric(year2)){
		request.getRequestDispatcher("/Home.jsp").forward(request, response);
	}
	else{
	ArrayList<timeoffrequest>req = null;
	try {
		db.inserttor(year,month,day,year2,month2,day2,reason,starttime,endtime,((WorkInfo)session.getAttribute("userinfo")).getUserData().getID());
		req=db.gettor(((WorkInfo)session.getAttribute("userinfo")).getUserData().getID());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	session.setAttribute("timeoff",req);
	request.setAttribute("error","Sucess Time Off Request");
	request.getRequestDispatcher("/TimeOffRequest.jsp").forward(request, response);
	
}
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
 }}

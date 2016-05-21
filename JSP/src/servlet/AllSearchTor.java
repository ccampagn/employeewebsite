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

import user.PositionInfo;
import user.TimeOffInfo;
import user.WorkInfo;
import user.timeoffrequest;

import database.DB;

@WebServlet("/allsearchtor")
public class AllSearchTor extends HttpServlet {
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
			if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
				request.getRequestDispatcher("/Home.jsp").forward(request, response); 
			}
			else{
	DB db=new DB();
	try {
		db.deletetor();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	int search=Integer.parseInt(request.getParameter("search"));
	String field=request.getParameter("field").toLowerCase();
	ArrayList<TimeOffInfo>req = null;
	try {
		req=db.getalltorsearch(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),search,field);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   session.setAttribute("alltimeoff",req);
	  request.getRequestDispatcher("/AllTimeOffRequest.jsp").forward(request, response);
}
}
	}
}
}
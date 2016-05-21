package servlet;

import java.io.IOException;
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

@WebServlet("/alltimeoffrequest")
public class AllTimeOffRequestServlet extends HttpServlet {
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
		if(session.getAttribute("userinfo")==null||((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
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
	ArrayList<TimeOffInfo>req = null;

	//get all possible time off request for user
	try {
		req=db.getalltor(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	session.setAttribute("alltimeoff",req);
	
	  request.getRequestDispatcher("/AllTimeOffRequest.jsp").forward(request, response);
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
		request.setAttribute("error", "");
		
	}else{
		if(session.getAttribute("userinfo")==null||((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
			request.setAttribute("error", "Invalid Request");
			request.getRequestDispatcher("/Index.jsp").forward(request, response); 
			request.setAttribute("error", "");
		}else{
	DB db=new DB();
	ArrayList<TimeOffInfo>req =((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff"));
	String field=request.getParameter("field");
	int search=0;
	if(field=="All"){
		search=0;
	}
	else{
	search=Integer.parseInt(request.getParameter("search"));
	}
	for(int x=0;x<req.size();x++){
		int id=req.get(x).gettor().getId();
	String p=request.getParameter("status"+id);
	try {
		db.updatetor(p,id);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
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
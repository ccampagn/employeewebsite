package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.ShiftInfo;
import user.WorkInfo;
import database.DB;

@WebServlet("/completeshift")
public class CompletedShiftServlet extends HttpServlet {
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
			if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
				request.getRequestDispatcher("/Home.jsp").forward(request, response); 
			}
			else{
			DB db=new DB();
			ArrayList<ShiftInfo> shift = null;
			try {
				shift=db.getscheduleclockincomplete(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
				session.setAttribute("completeshift", shift);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	request.getRequestDispatcher("/CompletedShifts.jsp").forward(request, response);
		}
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
				if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
					request.getRequestDispatcher("/Home.jsp").forward(request, response); 
				}
				else{

		int search=Integer.parseInt(request.getParameter("search"));
		String field=request.getParameter("field").toLowerCase();
		
		DB db=new DB();
		try {
			
			ArrayList<ShiftInfo> user=db.getsearchschedulecomp(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),request.getParameter("search"),request.getParameter("field"));
		
			session.setAttribute("completeshift",user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
request.getRequestDispatcher("/CompletedShifts.jsp").forward(request, response);
}

}
}
}
}
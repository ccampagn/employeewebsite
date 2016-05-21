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

@WebServlet("/deletetimeoffrequest")
public class DeletetorServlet extends HttpServlet {
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
	String s=request.getParameter("upvote");
	DB db=new DB();
	ArrayList<timeoffrequest>req = null;
	try {
		db.deleteator(s);
		req=db.gettor(((WorkInfo)session.getAttribute("userinfo")).getUserData().getID());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	session.setAttribute("timeoff",req);
	request.setAttribute("error", "Sucess Delete Time Off Request");
	request.getRequestDispatcher("/TimeOffRequest.jsp").forward(request, response);
}
}
}
}
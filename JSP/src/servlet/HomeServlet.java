package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DB;

import user.Message;
import user.NewStories;
import user.WorkInfo;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
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
			ArrayList<Message> mess = null;
			ArrayList<NewStories> news=null;
			try {
				mess = db.getMessage(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getID());
				news = db.getNews(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),((WorkInfo)session.getAttribute("userinfo")).getPositionInfo());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("message",mess);
			session.setAttribute("newstories",news);
	request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
	}
	
	
  }

}
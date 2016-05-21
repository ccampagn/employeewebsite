package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


	@WebServlet("/logout")
	public class LogOutServlet extends HttpServlet {
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final String CHAR_LIST = 
		        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
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
				session.invalidate();
				request.setAttribute("error", "Thanks for Logging Out");
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
			}
		}
		
		
	  }

}

package servlet;

	import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/index")
	public class IndexServlet extends HttpServlet {
	  @Override
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		  
		  request.getRequestDispatcher("/Index.jsp").forward(request, response);
	  }
	  @Override
	  public void doPost(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		  String username=request.getParameter("id");
		  String password=request.getParameter("password");
		  System.out.print(username);
		  System.out.print(password);
		  
			  request.getRequestDispatcher("/Index.jsp").forward(request, response);
		  
	         
	        
	  }
	}


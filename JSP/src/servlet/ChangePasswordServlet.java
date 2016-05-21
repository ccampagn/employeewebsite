package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;

import user.WorkInfo;

import database.DB;
@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet  {
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
		request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
			}
		}
	  }
	@Override
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
throws ServletException, IOException {
	 BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
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
				
	 String password=request.getParameter("password");
	 String confirmpassword=request.getParameter("password2");
	 if(!password.equals(confirmpassword)||password.length()<6||password.contains(" ")||password.contains("'")){
		 request.setAttribute("error", "New password is invaild");
			request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
			request.setAttribute("error", "");
	 }else{
		String encrytion=passwordEncryptor.encryptPassword(password);
		String user=((WorkInfo)session.getAttribute("userinfo")).getPassword().getUsername();
		try {
			db.updateattempt(user,false,encrytion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("error", "Change Password Success");
		request.getRequestDispatcher("/Home.jsp").forward(request, response);
}
}

}
	}
}
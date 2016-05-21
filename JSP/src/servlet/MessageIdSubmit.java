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

import org.jasypt.util.password.BasicPasswordEncryptor;

import user.Email;
import user.Message;
import user.NewStories;
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


	@WebServlet("/MessageSubmit")
	public class MessageIdSubmit extends HttpServlet {
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
DB db=new DB();
String company=null;

try {
	//company=db.getacompany(session.getAttribute("messageid"));
Message message=new Message(0,db.getMaxSeriesID()+1,((WorkInfo)session.getAttribute("userinfo")).getUserData().getID(),Integer.parseInt((String) session.getAttribute("messageid")),request.getParameter("message"),null,null,null,null);

	db.insertMessage(message);
		
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
ArrayList<Message> mess = null;
ArrayList<NewStories> news = null;
try {
	mess = db.getMessage(((WorkInfo)session.getAttribute("userinfo")).getUserData().getID());
	news=db.getNews(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),((WorkInfo)session.getAttribute("userinfo")).getPositionInfo());
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
session.setAttribute("newstories",news);
session.setAttribute("message",mess);
request.getRequestDispatcher("/Home.jsp").forward(request, response);
}
}
}

	}

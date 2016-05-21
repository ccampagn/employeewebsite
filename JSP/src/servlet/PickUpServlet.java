package servlet;

	import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.jasypt.util.password.BasicPasswordEncryptor;

import user.Email;
import user.Password;
import user.PositionInfo;
import user.RandomPassword;
import user.Shift;
import user.Training;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/PickUp")
	public class PickUpServlet extends HttpServlet {
	  /**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	
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
int id=((WorkInfo)session.getAttribute("userinfo")).getUserData().getID();
String shift=request.getParameter("upvote");
int shiftnum=Integer.parseInt(shift);
ArrayList<Shift> shifta = null;

try {

	if(checkingtraining(shift,session)==true){
	db.updateshift(id, shift);
	shifta=db.getavailableschedule(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
	

session.setAttribute("availableshift",shifta);
request.setAttribute("error","Sucess Pick Up");
request.getRequestDispatcher("/AvailableShift.jsp").forward(request, response);
	}
	else{
		request.setAttribute("error","Pick Up Error"); 
		request.getRequestDispatcher("/AvailableShift.jsp").forward(request, response);  
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	}

}
	 public boolean checkingtraining(String shift, HttpSession session) throws Exception{
		 ArrayList<Training> training = new ArrayList<Training>();
		 DB db=new DB();
		 training=db.gettraining(shift);
		 ArrayList<String> trainingstring = new ArrayList<String>();
		for(int x=0;x<((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getTraining().size();x++){
			trainingstring.add(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getTraining().get(x).getTraining());
		}
			for(int x=0;x<training.size();x++){
				
		 		if(!trainingstring.contains(training.get(x).getTraining())){
		 			
		 			return false;
		 		}
		 	}
		
			return true;
		
		 
	 }
	}

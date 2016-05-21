package servlet;

	import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;

import user.Email;
import user.Password;
import user.PositionInfo;
import user.RandomPassword;
import user.Training;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/profile")
	public class ProfileServlet extends HttpServlet {
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
		String mail = null;
		int emaila=Integer.parseInt(request.getParameter("profile"));
		String company = null;
		try {
			company = db.getcompanyid(emaila);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(!company.equals(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany())){
			request.getRequestDispatcher("/Index.jsp").forward(request, response);
		}else{
		try {
			WorkInfo work=db.getemployee(request.getParameter("profile").toString());
			session.setAttribute("change",work);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Profile.jsp").forward(request, response);
	  }
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


		 DB db=new DB();
		 int id=((WorkInfo)session.getAttribute("change")).getUserData().getID();
		 String username=((WorkInfo)session.getAttribute("change")).getPassword().getUsername();
		 String password=((WorkInfo)session.getAttribute("change")).getPassword().getPassword();
		 String firstname=request.getParameter("firstname");
		 String lastname=request.getParameter("lastname");
		 String email=request.getParameter("email");
		 String phonenumber=request.getParameter("phonenumber");
		 String address=request.getParameter("address");
		 String city=request.getParameter("city");
		 String state=request.getParameter("state");
		 String country=request.getParameter("country");
		 String zipcode=request.getParameter("zipcode");
		 String month=request.getParameter("month");
		 String day=request.getParameter("day");
		 String year=request.getParameter("year");
		 String sex=request.getParameter("sex");
		 String location=request.getParameter("location");
		 String dept=request.getParameter("dept");
		 String position=request.getParameter("position");
		 String comment=request.getParameter("comment");
		 String rights=request.getParameter("right");
		 String sublocation=request.getParameter("sublocation");
		 String company=request.getParameter("company");
		 if(firstname==""||lastname==""||email==""||phonenumber==""||address==""||city==""||country==""||year==""||location==""||dept==""||position==""||comment==""||sublocation==""){
		 	  request.setAttribute("error", "You Have Blank Fields");
		 	  request.getRequestDispatcher("/Profile.jsp").forward(request, response); 
		 	 
		 }
		 else if(zipcode.length()!=5||isNumeric(zipcode)==false){
		 	  request.setAttribute("error", "Zip Code is Invaild");
		 	  request.getRequestDispatcher("/Profile.jsp").forward(request, response);
		 	 
		 }
		 else if(isNumeric(phonenumber)==false||phonenumber.length()!=10){
		 	  request.setAttribute("error", "Phone Number is Invaild");
		 	  request.getRequestDispatcher("/Profile.jsp").forward(request, response);
		 }
		 else if(!email.contains("@")||email.contains(" ")){
		 	  request.setAttribute("error", "Email is Invaild");
		 	  request.getRequestDispatcher("/Profile.jsp").forward(request, response);
		 }
		 else if(isNumeric(year)==false||year.length()!=4){
		 	  request.setAttribute("error", "Year is Invaild");
		 	  request.getRequestDispatcher("/Profile.jsp").forward(request, response); 
		 }
		 else{
		 	boolean gender=false;
		 	boolean admin=false;
		 	if(sex.equals("male")){
		 		gender=true;
		 	}
		 	if(rights.equals("true")){
		 		admin=true;
		 	}
		 	ArrayList<Training> training=null;
		 	UserData user=new UserData(id,firstname,lastname,email,phonenumber,address,city,state,zipcode,Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),gender,country);
		 	PositionInfo pos=new PositionInfo(id,company,location,dept,position,comment,admin,sublocation,training,0);
		 	Password pw=new Password(id,username,password,0);
		 	WorkInfo work=new WorkInfo(user,pos,pw);	
			try {
				db.updateuser(user);
				db.updateposition(pos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WorkInfo workA = null;
			try {
				workA = db.getemployeestring(((WorkInfo)session.getAttribute("change")).getUserData().getID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("change",workA);	
			request.setAttribute("error","Successful Updated");
request.getRequestDispatcher("/Profile.jsp").forward(request, response);

}
		 	}
		 }
}
		 public static boolean isNumeric(CharSequence cs) {
			    if (cs == null || cs.length() == 0) {
			        return false;
			     }
			     int sz = cs.length();
			     for (int i = 0; i < sz; i++) {
			         if (Character.isDigit(cs.charAt(i)) == false) {
			              return false;
			         }
			      }
			    return true;
			 }
	
	 }
	
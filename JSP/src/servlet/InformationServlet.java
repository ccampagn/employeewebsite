package servlet;

	import java.io.*; 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.catalina.Session;

import modelcontroller.UserDataController;


import user.Password;
import user.PositionInfo;
import user.UserData;
import user.WorkInfo;

import com.mysql.jdbc.Connection;

import database.DB;




	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/information")
	public class InformationServlet extends HttpServlet {
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
				
		request.getRequestDispatcher("/Information.jsp").forward(request, response);
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
int id=((WorkInfo)session.getAttribute("userinfo")).getUserData().getID();
String username=((WorkInfo)session.getAttribute("userinfo")).getPassword().getUsername();
String password=((WorkInfo)session.getAttribute("userinfo")).getPassword().getPassword();
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
String company=request.getParameter("company");
String location=request.getParameter("location");
String dept=request.getParameter("dept");
String position=request.getParameter("position");
String comment=request.getParameter("comment");
String rights=request.getParameter("right");
String sublocation=request.getParameter("sublocation");
if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
	rights="false";
	if(((WorkInfo)session.getAttribute("userinfo")).getUserData().getSex()==true){
	sex="male";
	} else{
	sex="female";	
	}
	month=String.valueOf(((WorkInfo)session.getAttribute("userinfo")).getUserData().getMonth());
	day=String.valueOf(((WorkInfo)session.getAttribute("userinfo")).getUserData().getDay());
}
if(firstname==""||lastname==""||email==""||phonenumber==""||address==""||city==""||country==""||year==""||location==""||dept==""||position==""||comment==""||sublocation==""){
	  request.setAttribute("error", "You Have Blank Fields");
	  request.getRequestDispatcher("/Information.jsp").forward(request, response);
	  
}
else if(zipcode.length()!=5||isNumeric(zipcode)==false){
	  request.setAttribute("error", "Zip Code is Invaild");
	  request.getRequestDispatcher("/Information.jsp").forward(request, response); 
	  
}
else if(isNumeric(phonenumber)==false||phonenumber.length()!=10){
	  request.setAttribute("error", "Phone Number is Invaild");
	  request.getRequestDispatcher("/Information.jsp").forward(request, response);
	  
}
else if(!email.contains("@")||email.contains(" ")){
	  request.setAttribute("error", "Email is Invaild");
	  request.getRequestDispatcher("/Information.jsp").forward(request, response); 
	  
}
else if(isNumeric(year)==false||year.length()!=4){
	  request.setAttribute("error", "Year is Invaild");
	  request.getRequestDispatcher("/Information.jsp").forward(request, response); 
	  
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
	UserData user=new UserData(id,firstname,lastname,email,phonenumber,address,city,state,zipcode,Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),gender,country);
	PositionInfo pos=new PositionInfo(id,company,location,dept,position,comment,admin,sublocation,null,0);
	Password pw=new Password(id,username,password,0);
	WorkInfo work=new WorkInfo(user,pos,pw);		
	session.setAttribute("userinfo",work);




try {
	db.updateuser(user);
	db.updateposition(pos);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
request.setAttribute("error", "Sucessful Update");
request.getRequestDispatcher("/Information.jsp").forward(request, response);

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
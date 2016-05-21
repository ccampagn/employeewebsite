package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

import database.DB;

import user.Password;
import user.PositionInfo;
import user.UserData;
import user.WorkInfo;

@WebServlet("/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {
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
	request.getRequestDispatcher("/AddEmployee.jsp").forward(request, response);
			}
			}
			
	}
	
  }
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
		}
	else{
		if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
			request.setAttribute("error", "Invalid Request");
			request.getRequestDispatcher("/Home.jsp").forward(request, response); 
		}else{
	DB db=new DB();
	int id = 0;
	int idcompany = 0;
	  String username=request.getParameter("username");
	  String password=request.getParameter("password");
	  String confirmpassword=request.getParameter("password2");
	  String firstname=request.getParameter("firstname");
	  String lastname=request.getParameter("lastname");
	  String email=request.getParameter("email");
	  String phonenumber=request.getParameter("phonenumber");
	  String address=request.getParameter("address");
	  String city=request.getParameter("city");
	  String state=request.getParameter("state");
	  String zipcode=request.getParameter("zipcode");
	  String month=request.getParameter("month");
	  String day=request.getParameter("day");
	  String year=request.getParameter("year");
	  String company=(String) session.getAttribute("company");
	  String dept=request.getParameter("dept");
	  String location=request.getParameter("location");
	  String sublocation=request.getParameter("sublocation");
	  String position=request.getParameter("position");
	  String comment=request.getParameter("comment");
	  String sex=request.getParameter("sex");
	  String country=request.getParameter("country");
	  String right=request.getParameter("right");
	  int years= 0,months = 0,days= 0;
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
		   years=Integer.parseInt(year);
		   months=Integer.parseInt(month);
		   days=Integer.parseInt(day);  
	  
	  boolean gender=true;
	  boolean userrepeat=false;
	  if(sex.equals("male")){
		gender=true;  
		System.out.print("is male");
	  }else{
		gender=false;
		System.out.print("is female");
	  }
	  try { 
		userrepeat=db.checkifcontain(username);
		id=db.getMaxUserId()+1;
		idcompany=db.getcompanyid(company);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  if(!password.equals(confirmpassword)){
		  request.setAttribute("error", "Invaild Fields");
			request.getRequestDispatcher("AddEmployee.jsp").forward(request, response);
	  }
	  else{
	  if(userrepeat==false){
		  PositionInfo pos;
			Password pw=new Password(id,username,password,0); 
			UserData user=new UserData(id,firstname,lastname,email,phonenumber,address,city,state,zipcode,years,months,days,gender,country);
			if(right.equals("true")){
			pos=new PositionInfo(id,company,location,dept,position,comment,true,sublocation,null,0);
			}else{
			pos=new PositionInfo(id,company,location,dept,position,comment,false,sublocation,null,0);
			}
			try {
				db.adduserinfo(user);
				db.addpasswordinfo(pw);
				db.addpositioninfo(pos,idcompany);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("error", "Sucessful Added Employee");
			request.getRequestDispatcher("AddEmployee.jsp").forward(request, response);	
		}

		else{
			request.setAttribute("error", "Invaild Fields");
			request.getRequestDispatcher("AddEmployee.jsp").forward(request, response);
		}
				    
		}
	}}
		}}
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
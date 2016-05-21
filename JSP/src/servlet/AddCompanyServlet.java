package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DB;

import user.Password;
import user.PositionInfo;
import user.UserData;

@WebServlet("/AddCompany")
public class AddCompanyServlet extends HttpServlet {
  /**
	 * 
	 */
private static final long serialVersionUID = 1L;

@Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  request.getRequestDispatcher("/AddCompany.jsp").forward(request, response);
  }
@Override
public void doPost(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException, IOException {
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
	  String company=request.getParameter("company");
	  String dept=request.getParameter("dept");
	  String location=request.getParameter("location");
	  String position=request.getParameter("position");
	  String comment=request.getParameter("comment");
	  String sex=request.getParameter("sex");
	  String country=request.getParameter("country");
	  String sublocation=request.getParameter("sublocation");
	  boolean gender=true;
	  boolean companyrepeat=false;
	  boolean userrepeat=false;
	  if(sex.equals("male")){
		gender=true;  
	  }else{
		gender=false;
	  }
	  try {
		companyrepeat=db.checkifcontaincompany(company); 
		userrepeat=db.checkifcontain(username);
		id=db.getMaxUserId()+1;
		idcompany=db.getCompanyMaxUserId()+1;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if(firstname==""||lastname==""||email==""||phonenumber==""||address==""||city==""||country==""||year==""||location==""||dept==""||position==""||comment==""||sublocation==""||company==""){
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
		  if(companyrepeat==false&&userrepeat==false){
	Password pw=new Password(id,username,password,0); 
	UserData user=new UserData(id,firstname,lastname,email,phonenumber,address,city,state,zipcode,Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),gender,country);
	PositionInfo pos=new PositionInfo(id,company,location,dept,position,comment,true,sublocation,null,0);
	try {
		db.adduserinfo(user);
		db.addpasswordinfo(pw);
		db.addcompanyinfo(company,idcompany);
		db.addpositioninfo(pos,idcompany);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	request.setAttribute("error", "Company Added");
	request.getRequestDispatcher("Index.jsp").forward(request, response);
}

else{
	request.setAttribute("error", "Invaild Fields");
	request.getRequestDispatcher("AddCompany.jsp").forward(request, response);
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

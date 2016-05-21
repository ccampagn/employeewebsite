package servlet;

	import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;

import user.Break;
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


	@WebServlet("/scheduleemployee")
	public class ScheduleEmployeeServlet extends HttpServlet {
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
		  request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response);
	  }
				}
			}
	}
	  @SuppressWarnings("deprecation")
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
					if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
						request.getRequestDispatcher("/Home.jsp").forward(request, response); 
					}
					else if(request.getParameter("location").equals("")||request.getParameter("dept").equals("")||request.getParameter("sublocation").equals("")||request.getParameter("position").equals("")){
						request.setAttribute("error","Input Error"); 
						request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response);  
					}
					else if(request.getParameter("starttime").length()!=5||request.getParameter("endtime").length()!=5){
						request.setAttribute("error","Input Error"); 
						request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response);  
					}
					else if(request.getParameter("starttime").charAt(2)!=':'){
						request.setAttribute("error","Input Error"); 
						request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response); 
					}
					else if(request.getParameter("endtime").charAt(2)!=':'){
						request.setAttribute("error","Input Error"); 
						request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response); 
					}
					else if(!(request.getParameter("startdate").charAt(2)=='/')||!(request.getParameter("startdate").charAt(5)=='/')){
						request.setAttribute("error","Input Error"); 
						request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response); 
					}
					else if(!(request.getParameter("enddate").charAt(2)=='/')||!(request.getParameter("enddate").charAt(5)=='/')){
						request.setAttribute("error","Input Error"); 
						request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response);  
					}
					else if(request.getParameter("startdate").length()!=10||request.getParameter("enddate").length()!=10){
						request.setAttribute("error","Input Error"); 
						request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response);  
					}
					else{
		  DB db=new DB();
		  String company=null;
		  int id=1;
		  try {
			if(isNumeric(request.getParameter("id"))!=true){
				id=db.getid(request.getParameter("id"));
			}
			else{
				id=Integer.parseInt(request.getParameter("id"));
			}
			company=db.getacompany(id);
			if(id==0){
				company=((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany();
			}
			if(company!=null){
			if(company.equals(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany())){
				  
		ArrayList<Training> training = new ArrayList<Training>();
		  String[] splitString = request.getParameter("training").split(",");
		  for(int x=0;x<splitString.length;x++){
			  java.sql.Date date=new java.sql.Date(2020-01-23);
			  training.add(new Training(splitString[x],"2099-01-01"));
		  }
		  ArrayList<Break> brek=null;
		  Date startdate = null;
		  Date enddate = null;
			startdate = new Date(114,02,15);
			enddate = new Date(114,02,15);
	
		  
			String starttime=request.getParameter("startdate").substring(6, 10)+"-"+request.getParameter("startdate").substring(0, 3)+"-"+request.getParameter("startdate").substring(3, 5)+" "+request.getParameter("starttime")+":00";
			  String endtime=request.getParameter("enddate").substring(6, 10)+"-"+request.getParameter("enddate").substring(0, 3)+"-"+request.getParameter("enddate").substring(3, 5)+" "+request.getParameter("endtime")+":00";
		  Shift shift = null;
	
			shift = new Shift(db.getShiftMaxUserId()+1,id,db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),request.getParameter("location"),request.getParameter("dept"),request.getParameter("sublocation"),request.getParameter("position"),startdate,enddate,starttime,endtime,0,0,brek,0,null,null,null,null,training);
		 //check if employee have training splitString vs 
			boolean checktraining=db.getusertraining(splitString,id);
			if(checktraining==true ||id==0){
			db.insertshift(shift);
			db.inserttraining(shift);
			request.setAttribute("error","Sucessfully Schedule");
			request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response);
			}
			else{
				request.setAttribute("error","Training Error"); 
				request.getRequestDispatcher("/ScheduleEmployee.jsp").forward(request, response);  
			}
		  
	         
	        
	  }
	
	  
	  
	
	  
	

	  
			else{
				request.getRequestDispatcher("/Index.jsp").forward(request, response);
			}
			}else{
				request.getRequestDispatcher("/Index.jsp").forward(request, response);
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
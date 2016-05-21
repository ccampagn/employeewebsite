
	package servlet;

	import java.io.IOException;
import java.util.ArrayList;
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
import user.ShiftInfo;
import user.Training;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/availableeditshift")
	public class EditAvailableShiftsServlet extends HttpServlet {
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
					
				}else{
					if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
						request.getRequestDispatcher("/Home.jsp").forward(request, response); 
					}
					else{
					 
						 DB db=new DB();
							session.setAttribute("shiftnumber", request.getParameter("upvote"));
							System.out.println(request.getParameter("upvote"));
							ArrayList<Training> training = new ArrayList<Training>();
							try {
								training=db.gettraining(((ArrayList<Shift>) session.getAttribute("availableshift")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String s="";
							for(int x=0;x<training.size();x++){
								s=s+training.get(x).getTraining()+",";
							}
							//System.out.println(s);
							session.setAttribute("training", s);
					request.getRequestDispatcher("/AvailableEditShift.jsp").forward(request, response); 
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
					if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==false){
						request.getRequestDispatcher("/Home.jsp").forward(request, response); 
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
		  ArrayList<Shift> shifta = null;
			shift = new Shift(((ArrayList<Shift>)session.getAttribute("availableshift")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift(),id,db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()),request.getParameter("location"),request.getParameter("dept"),request.getParameter("sublocation"),request.getParameter("position"),startdate,enddate,starttime,endtime,0,0,brek,0,null,null,null,null,training);
			
			
			db.updateshift(shift);
			db.deletetraining(shift.getShift());
			db.inserttraining(shift);
			shifta=db.getavailableschedule(db.getcompanyid(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getCompany()));
			session.setAttribute("availableshift",shifta);
			request.setAttribute("error", "Sucess Edit Shift");
			request.getRequestDispatcher("/AvailableShift.jsp").forward(request, response);
		 
		  
	         
	        
	  
		
		 
		  
	         
	        
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
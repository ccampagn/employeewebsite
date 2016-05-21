package servlet;

	import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import user.Training;
import user.UserData;
import user.WorkInfo;
import database.DB;
	/** Very simplistic servlet that generates plain text.
	 *  Run it with the address given in web.xml. 
	 */


	@WebServlet("/Training")
	public class TrainingServlet extends HttpServlet {
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
						
		  request.getRequestDispatcher("/Training.jsp").forward(request, response);
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
								@SuppressWarnings("deprecation")
								String date=request.getParameter("date");
								//java.sql.Date date=new java.sql.Date(dat);
									Training training=new Training(request.getParameter("training"), date.substring(6, 10)+"-"+date.subSequence(0, 3)+"-"+date.substring(3, 5));	  
							
							
						
							  
								
						
								
							 
								db.inserttraining(training,id);
								db.deletetraining();
								request.setAttribute("error","Sucessfully Training");
								request.getRequestDispatcher("/Training.jsp").forward(request, response);
								
							  
						         
						        
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
	
							  
						
						
					
			
	
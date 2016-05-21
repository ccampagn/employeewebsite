package database;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

import org.jasypt.util.password.BasicPasswordEncryptor;

import modelcontroller.UserDataController;

import user.Attendance;
import user.Break;
import user.Message;
import user.NewStories;
import user.Password;
import user.PositionInfo;
import user.Shift;
import user.ShiftInfo;
import user.TimeOffInfo;
import user.Training;
import user.UserData;
import user.WorkInfo;
import user.timeoffrequest;




public class DB{
     
       
        	 private Connection connect = null;
        	 private Statement statement = null;
        	 private PreparedStatement preparedStatement = null;
        	  private ResultSet resultSet = null;
        	   public int getMaxUserId() throws Exception {
        		   int id = 0;
        			Connection con=connect();
                 	 preparedStatement = con.prepareStatement("SELECT MAX(id) FROM EMPLOYEE.WORKERS");
                 	 ResultSet resultSet = preparedStatement.executeQuery();
                 	while (resultSet.next()) {
                		id = resultSet.getInt(1);
               	}
                 	con.close();
                 	System.out.print(id);
					return id;
      
                   
           }
         	   public int getCompanyMaxUserId() throws Exception {
        		   int id = 0;
        			Connection con=connect();
                 	 preparedStatement = con.prepareStatement("SELECT MAX(idcompany) FROM EMPLOYEE.COMPANY");
                 	 ResultSet resultSet = preparedStatement.executeQuery();
                 	while (resultSet.next()) {
                		id = resultSet.getInt(1);
               	}
                 	con.close();
                 	System.out.print(id);
					return id;
      
                   
           }
         	  public int getShiftMaxUserId() throws Exception {
       		   int id = 0;
       			Connection con=connect();
                	 preparedStatement = con.prepareStatement("SELECT MAX(id) FROM EMPLOYEE.SHIFT");
                	 ResultSet resultSet = preparedStatement.executeQuery();
                	while (resultSet.next()) {
               		id = resultSet.getInt(1);
              	}
                	con.close();
                	System.out.print(id);
					return id;
     
                  
          }
            public boolean checkifcontain(String user) throws Exception {
            	Connection con=connect();
              	 preparedStatement = con.prepareStatement("SELECT username FROM employee.login WHERE username = ? ");
              	preparedStatement.setString(1,user);
              	 ResultSet resultSet = preparedStatement.executeQuery();
              	int rowCount = 0;  
              	while ( resultSet.next() )  
              	{  
              	    // Process the row.  
              	    rowCount++;  
              	} 
              	 if(rowCount==0){

              		return false;
              	 }else{
              		 return true;
              	 }
              	
              		
              	
              	
              }
            public boolean checkifcontaincompany(String company) throws Exception {
            	Connection con=connect();
              	 preparedStatement = con.prepareStatement("SELECT company FROM employee.company WHERE company = ? ");
              	preparedStatement.setString(1,company);
              	 ResultSet resultSet = preparedStatement.executeQuery();
              	int rowCount = 0;  
              	while ( resultSet.next() )  
              	{  
              	    // Process the row.  
              	    rowCount++;  
              	} 
              	con.close();
              	 if(rowCount==0){

              		return false;
              	 }else{
              		 return true;
              	 }
              	
              		
              	
              	
              }
            public Connection connect() throws Exception{
            	 Class.forName("com.mysql.jdbc.Driver");
             	Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?"+ "user=root&password=root");
				return connect;
            }
            public Password getuserlogin(String user) throws Exception {
            	Connection con=connect();
            	preparedStatement = con.prepareStatement("SELECT * FROM employee.login WHERE username =?");    
            	preparedStatement.setString(1,user);
            	resultSet = preparedStatement.executeQuery();
            	String password=null;
            	int id = 0;
            	String username = null;
            	int attempt = 0;
            	while (resultSet.next()) {
            		id = resultSet.getInt(1);
            		username= resultSet.getString(2);
            		password = resultSet.getString(3);
            		attempt = resultSet.getInt(4);
           	}
           	
           	Password pw=new Password(id,username,password,attempt);
           	con.close();
           		return pw;
           	
           }
         
		public UserData getemployeeinfo(int i) throws Exception {
			// TODO Auto-generated method stub
			UserData user=null;
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("select * from EMPLOYEE.WORKERS where id=?");
			 pstmt.setInt(1, i);
             resultSet = pstmt.executeQuery();
             while (resultSet.next()) {
                 // It is possible to get the columns via name
                 // also possible to get the columns via the column number
                 // which starts at 1
                 // e.g. resultSet.getSTring(2);
                 int id = resultSet.getInt(1);
                 String firstname = resultSet.getString(2);
                 String lastname = resultSet.getString(3);
                 String email= resultSet.getString(4);
                 String phonenumber = resultSet.getString(5);
                 String address= resultSet.getString(6);
                 String state = resultSet.getString(8);
                 String city = resultSet.getString(7);
                 String zipcode = resultSet.getString(9);
                 Date date=resultSet.getDate(10);
                 @SuppressWarnings("deprecation")
   			int month = date.getMonth()+1;
                @SuppressWarnings("deprecation")
   			int day = date.getDate();
                @SuppressWarnings("deprecation")
   			int year = date.getYear()+1900;
                boolean sex=resultSet.getBoolean(11);
                String country=resultSet.getString(12);

                user=new UserData(id,firstname,lastname,email,phonenumber,address,city,state,zipcode,year,month,day,sex,country);
               }
             con.close();
   			return user;
             
		}
		public PositionInfo getpositioninfo(int i) throws Exception {
			// TODO Auto-generated method stub
			DB db=new DB();
			PositionInfo info=null;
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("SELECT companyid,location,dept,position,comment,rights,sublocation FROM employee.positioninfo  where id=?");
			 pstmt.setInt(1, i);
             resultSet = pstmt.executeQuery();
             while (resultSet.next()) {
            	 String company=db.getcompany(resultSet.getInt(1));
                  String location=resultSet.getString(2);
                  String dept=resultSet.getString(3);
                  String position=resultSet.getString(4);
                  String comment=resultSet.getString(5);
                  boolean rights=resultSet.getBoolean(6);
                  String sublocation=resultSet.getString(7);
                  info=new PositionInfo(i,company,location,dept,position,comment,rights,sublocation,null,0);
               }
             con.close();
   			return info;
             
		}
		public String getcompany(int i) throws Exception {
			String company = null;
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("SELECT company FROM employee.company  where idcompany=?");
			 pstmt.setInt(1, i);
             resultSet = pstmt.executeQuery();
             while (resultSet.next()) {
             company=resultSet.getString(1);
               }
			return company;
		}
		public String getcompanyid(int i) throws Exception {
			DB db=new DB();
			String company = null;
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("SELECT companyid FROM employee.positioninfo  where id=?");
			 pstmt.setInt(1, i);
             resultSet = pstmt.executeQuery();
             while (resultSet.next()) {
             company=db.getcompany(resultSet.getInt(1));
             System.out.print(company);
               }
			return company;
		}
		public int getid(String username) throws Exception {
			int i = 1;
			Connection con=connect();con=connect();
			 statement = con.createStatement();
            PreparedStatement pstmt=con.prepareStatement("select id from EMPLOYEE.LOGIN where username=?");
			 pstmt.setString(1, username);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
            	 i=resultSet.getInt(1);
            }
            con.close();
            return i;
		}
		public int getidemail(String email) throws Exception {
			int i = 0;
			Connection con=connect();con=connect();
			 statement = con.createStatement();
            // Result set get the result of the SQL query
			 PreparedStatement pstmt=con.prepareStatement("select id from EMPLOYEE.WORKERS where email=?");
			 pstmt.setString(1, email);
			 ResultSet resultSet=pstmt.executeQuery();
			 while (resultSet.next()) {
            	 i=resultSet.getInt(1);
            	 System.out.print(i);
            }
			 con.close();
            return i;
		}
		public void updatepassword(String username,String password) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.login SET password=? where username=?");
			 pstmt.setString(1,password);
			 pstmt.setString(2, username);
			 pstmt.executeUpdate();
			 con.close();
		}
		public void updatepasswordid(int id,String password) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.login SET password=? where id=?");
			 pstmt.setString(1,password);
			 pstmt.setInt(2, id);
			 pstmt.executeUpdate();
			 con.close();
		}
		public void updateattempt(String username,boolean plus,String encrytion) throws Exception {
			Connection con=connect();
			 if(plus==true){
				 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.login SET password=? , loginattempt=(loginattempt+1) where username=?");
				 pstmt.setString(1,encrytion);
				 pstmt.setString(2,username);
				 pstmt.executeUpdate();
			 }
			 else {
				 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.login SET password=? , loginattempt=0 where username=?");
			 pstmt.setString(1,encrytion);
				 pstmt.setString(2,username);
				 pstmt.executeUpdate();
			 }
			 
			 con.close();
			 }
		public String getemail(int id) throws Exception {
			// TODO Auto-generated method stub
			Connection con=connect();
			 statement = con.createStatement();
			 String email = null;
			 resultSet = statement.executeQuery("select email from EMPLOYEE.WORKERS where id="+id);
			 while (resultSet.next()) {
            	 email=resultSet.getString(1);
            	 System.out.println(email);
            }
			 con.close();
			 return email;
		}
		 public boolean checkifcontainemail(String email) throws Exception {
         	Connection con=connect();
           	 preparedStatement = con.prepareStatement("SELECT email FROM employee.workers WHERE email = ?");
           	preparedStatement.setString(1,email);
           	 ResultSet resultSet = preparedStatement.executeQuery();
           	int rowCount = 0;  
           	while ( resultSet.next() )  
           	{  
           	    // Process the row. 
           		
           	    rowCount++;  
           	} 
           	con.close();
           	 if(rowCount==0){
           		return false;
           	 }else{
           		 return true;
           	 }
           	
           		
           	
           }
			public void adduserinfo(UserData user) throws Exception {
				Connection con=connect();con=connect();
				 statement = con.createStatement();
				 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.WORKERS values (?,?,?,?,?,?,?,?,?,?,?,?)");
				

				 pstmt.setInt(1,user.getID());
				 pstmt.setString(2,user.getFirstname());
				 pstmt.setString(3,user.getLastname());
				 pstmt.setString(4,user.getEmail());
				 pstmt.setString(5,user.getPhonenumber());
				 pstmt.setString(6,user.getAddress());
				 pstmt.setString(7,user.getCity());
				 pstmt.setString(8,user.getState());
				 pstmt.setString(9,user.getZipcode());
				 pstmt.setString(10,user.getYear()+"-"+user.getMonth()+"-"+user.getDay());
				 pstmt.setBoolean(11,user.getSex());
			
				 pstmt.setString(12,user.getCountry());
				 pstmt.executeUpdate();
				 con.close();
			}
			public void addpasswordinfo(Password password) throws Exception {
				 BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
				 String encrytion=passwordEncryptor.encryptPassword(password.getPassword());
				Connection con=connect();con=connect();
				 statement = con.createStatement();
				 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.LOGIN values (?,?,?,?)");
				 pstmt.setInt(1,password.getID());
				 pstmt.setString(2,password.getUsername());
				 pstmt.setString(3,encrytion);
				 pstmt.setInt(4,password.getAttempt());
				 pstmt.executeUpdate();
				 con.close();
			}
			public void addpositioninfo(PositionInfo pos,int company) throws Exception {
				Connection con=connect();con=connect();
				 statement = con.createStatement();
				 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.POSITIONINFO values (?,?,?,?,?,?,?,?)");
				 pstmt.setInt(1,pos.getID());
				 pstmt.setInt(2,company);
				 pstmt.setString(3,pos.getLocation());
				 pstmt.setString(4,pos.getDept());
				 pstmt.setString(5,pos.getPosition());
				 pstmt.setString(6,pos.getComment());
				 pstmt.setBoolean(7,pos.getRight());
				 pstmt.setString(8,pos.getSublocation());
				 pstmt.executeUpdate();
				 con.close();
			}
			public void addcompanyinfo(String company, int id) throws Exception {
				Connection con=connect();con=connect();
				 statement = con.createStatement();
				 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.COMPANY values (?,?)");
				 pstmt.setInt(1,id);
				 pstmt.setString(2,company);
				 pstmt.executeUpdate();
				 con.close();
			}
		public String getuserbyid(int id) throws Exception {
			Connection con=connect();
			String user = null;
          	 preparedStatement = con.prepareStatement("SELECT username FROM employee.login WHERE id = ?");
          	preparedStatement.setInt(1,id);
          	 ResultSet resultSet = preparedStatement.executeQuery();
         	while ( resultSet.next() )  
           	{  
           	    user=resultSet.getString(1);  
           	} 
         	con.close();
          	 return user;
		}
		
		public void updateuser(UserData user) throws Exception {
			Connection con=connect();
			
				 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.workers SET firstname=? , lastname=? , email=? , phonenumber=? , address=? , city=? , state=? , zipcode=? , birthdate=? , sex=? , country=? where id=?");
				 pstmt.setString(1,user.getFirstname());
				 pstmt.setString(2,user.getLastname());
				 pstmt.setString(3,user.getEmail());
				 pstmt.setString(4,user.getPhonenumber());
				 pstmt.setString(5,user.getAddress());
				 pstmt.setString(6,user.getCity());
				 pstmt.setString(7,user.getState());
				 pstmt.setString(8,user.getZipcode());
				 pstmt.setString(9, user.getYear()+"-"+user.getMonth()+"-"+user.getDay());
				pstmt.setBoolean(10,user.getSex());
				pstmt.setString(11,user.getCountry());
				 pstmt.setInt(12,user.getID());
				 pstmt.executeUpdate();
				 con.close();
		}
		public void inserttor(String year, String month, String day,String year2,String month2,String day2,
				String reason,String starttime, String endtime, int i) throws Exception {
			Connection con=connect();
         	 preparedStatement = con.prepareStatement("insert into  employee.timeoffrequest values (default,?, ?,?,?, ? , 0, ?,0)");
         	String date=year+"-"+month+"-"+day;
         	String date2=year2+"-"+month2+"-"+day2;
         	preparedStatement.setString(1,date);
         	preparedStatement.setString(2,date2);
         	preparedStatement.setString(3,starttime);
        	 preparedStatement.setString(4,endtime);
         	 preparedStatement.setString(5,reason);
         	preparedStatement.setInt(6,i);
         	preparedStatement.executeUpdate();
			
		}
		public void updateposition(PositionInfo pos) throws Exception {
			Connection con=connect();
			
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.positioninfo SET location=? , dept=? , position=? , comment=? , rights=? , sublocation=? where id=?");
			 pstmt.setString(1,pos.getLocation());
			 pstmt.setString(2,pos.getDept());
			 pstmt.setString(3,pos.getPosition());
			 pstmt.setString(4,pos.getComment());
			 pstmt.setBoolean(5,pos.getRight());
			 pstmt.setString(6,pos.getSublocation());
			 pstmt.setInt(7,pos.getID());
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public int getcompanyid(String company) throws Exception {
			Connection con=connect();
			int companyid = 0;
          	 preparedStatement = con.prepareStatement("SELECT idcompany FROM employee.company where company=?");
          	preparedStatement.setString(1,company);
          	 ResultSet resultSet = preparedStatement.executeQuery();
         	while ( resultSet.next() )  
           	{  
           	    companyid=resultSet.getInt(1);  
           	} 
         	con.close();
         	return companyid;
			
		}
		@SuppressWarnings("null")
		public ArrayList<WorkInfo> getemployeedirectory(int i) throws Exception {

			Connection con=connect();
			DB db=new DB();
			//int i=db.getcompanyid(company);
          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=?;");
          	preparedStatement.setInt(1,i);
          	 ResultSet resultSet = preparedStatement.executeQuery();
          	
          	ArrayList<WorkInfo> person = new ArrayList<WorkInfo>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettrainingemployee(resultSet.getInt(1));
         		//SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id;
           	 UserData user=new UserData(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),Integer.parseInt(resultSet.getString(10).substring(0,4)),Integer.parseInt(resultSet.getString(10).substring(5,7)),Integer.parseInt(resultSet.getString(10).substring(8,10)),resultSet.getBoolean(11),resultSet.getString(12));
           	 PositionInfo pos=new PositionInfo( resultSet.getInt(13), db.getcompany(resultSet.getInt(14)), resultSet.getString(15),resultSet.getString(16),resultSet.getString(17),resultSet.getString(18), resultSet.getBoolean(19),resultSet.getString(20),training,db.gettotalpoints(resultSet.getInt(1)));
           	 Password password=new Password(resultSet.getInt(21),resultSet.getString(22),resultSet.getString(23),resultSet.getInt(24));
           	 WorkInfo work=new WorkInfo(user,pos,password);
           	 person.add(work);	
           	
           	} 
         	con.close();
			return person;
			
		}
		private int gettotalpoints(int int1) throws Exception {
			Connection con=connect();
			int points = 0;
          	 preparedStatement = con.prepareStatement("SELECT time FROM employee.shift where idemployee=? AND time>0");
          	preparedStatement.setInt(1,int1);
          	 ResultSet resultSet = preparedStatement.executeQuery();
         	while ( resultSet.next() )  
           	{  
           	    points=points+resultSet.getInt(1);  
           	} 
         	con.close();
         	return points;
			
		}
		private ArrayList<Training> gettrainingemployee(int int1) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT training,date FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id join employee.trainingemployee on employee.workers.id=employee.trainingemployee.employeeid where employee.positioninfo.id=?;");
			 pstmt.setInt(1,int1);
			 
			
          	 ResultSet resultSet = pstmt.executeQuery();
          
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		Training tran=new Training(resultSet.getString(1),resultSet.getString(2));
         		training.add(tran);
         		
           	} 
         	con.close();
			return training;
		}
		private ArrayList<Training> gettrainingemployeecompany(int int1) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT training,date FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id join employee.trainingemployee on employee.workers.id=employee.trainingemployee.employeeid where employee.positioninfo.companyid=?;");
			 pstmt.setInt(1,int1);
			 
			
          	 ResultSet resultSet = pstmt.executeQuery();
          
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		Training tran=new Training(resultSet.getString(1),resultSet.getString(2));
         		training.add(tran);
         		
           	} 
         	con.close();
			return training;
		}
		public ArrayList<WorkInfo> getemployeefielddirectory(int i,int search,String field) throws Exception {
			Connection con=connect();
			DB db=new DB();
			//int i=db.getcompanyid(company);
			if(search==0){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=?;");
				}
			if(search==1){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.id=?;");
				}
			if(search==2){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.login.username=?;");
				}
			if(search==3){
          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.firstname=?;");
			}
			if(search==4){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.lastname=?;");
				}
 
			if(search==5){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.sex=?;");
				}
			if(search==6){
         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.address=?;");
			}
			if(search==7){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.city=?;");
				}
			if(search==8){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.state=?;");
				}
			if(search==9){
        	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.zipcode=?;");
			}
			if(search==10){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.country=?;");
				}
			if(search==11){
	        	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.phonenumber=?;");
				}
				if(search==12){
		          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.email=?;");
					}
				if(search==13){
		          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.workers.birthdate=?;");
					}
				if(search==14){
		          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.positioninfo.location=?;");
					}
				if(search==15){
		          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.positioninfo.dept=?;");
					}
				if(search==16){
		          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.positioninfo.position=?;");
					}
				if(search==17){
		          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.positioninfo.companyid=? AND employee.positioninfo.comment=?;");
					}
				if(search==18){
		          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id join employee.trainingemployee on employee.workers.id=employee.trainingemployee.employeeid where employee.positioninfo.companyid=? AND employee.trainingemployee.training=?;");
					}
				if(search==5){
				if(field.equals("male")){	
					preparedStatement.setInt(1,i);
					preparedStatement.setInt(2,1);
				
					
				}
				else if(field.equals("female")){
					preparedStatement.setInt(1,i);
					preparedStatement.setInt(2,0);
				}
				else{
					preparedStatement.setInt(1,1000);
					preparedStatement.setInt(2,0);
				}
				}
				else if(search==13){
					System.out.println(field.substring(6, 9)+"-"+field.subSequence(0, 1)+"-"+field.substring(3, 4));
					preparedStatement.setInt(1,i);
					preparedStatement.setString(2,(String) field.substring(6, 10)+"-"+field.subSequence(0, 3)+"-"+field.substring(3, 5));
				}
				else{
				preparedStatement.setInt(1,i);
				if(search!=0){
				preparedStatement.setString(2,field);
				}
				}
          	 ResultSet resultSet = preparedStatement.executeQuery();
          	
          	ArrayList<WorkInfo> person = new ArrayList<WorkInfo>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettrainingemployee(resultSet.getInt(1));
         		//SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id;
           	 UserData user=new UserData(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),1991,6,16,resultSet.getBoolean(11),resultSet.getString(12));
           	 PositionInfo pos=new PositionInfo( resultSet.getInt(13), db.getcompany(resultSet.getInt(14)), resultSet.getString(15),resultSet.getString(16),resultSet.getString(17),resultSet.getString(18), resultSet.getBoolean(19),resultSet.getString(20),training,db.gettotalpoints(resultSet.getInt(1)));
           	 Password password=new Password(resultSet.getInt(21),resultSet.getString(22),resultSet.getString(23),resultSet.getInt(24));
           	 WorkInfo work=new WorkInfo(user,pos,password);
           	 person.add(work);		
           	} 
         	con.close();
			return person;
		}
		public WorkInfo getemployee(String string) throws Exception {
			WorkInfo work = null;
			Connection con=connect();
			DB db=new DB();
          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.workers.id=?;");
          	preparedStatement.setString(1,string);
          	 ResultSet resultSet = preparedStatement.executeQuery();
          	
         
          	ArrayList<Training> training = new ArrayList<Training>();
          	training=db.gettrainingemployeeinfo(string);
         	while ( resultSet.next() )  
           	{  
         		//SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id;
           	 UserData user=new UserData(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),1991,6,16,resultSet.getBoolean(11),resultSet.getString(12));
           	 
			PositionInfo pos=new PositionInfo( resultSet.getInt(13), db.getcompany(resultSet.getInt(14)), resultSet.getString(15),resultSet.getString(16),resultSet.getString(17),resultSet.getString(18), resultSet.getBoolean(19),resultSet.getString(20),training,0);
           	 Password password=new Password(resultSet.getInt(21),resultSet.getString(22),resultSet.getString(23),resultSet.getInt(24));
           	 work=new WorkInfo(user,pos,password);		
           	} 
         	con.close();
			return work;
			
		}
		private  ArrayList<Training> gettrainingemployeeinfo(int id) throws Exception {
			Connection con=connect();
        	preparedStatement = con.prepareStatement("SELECT * FROM employee.trainingemployee where employeeid=?;");    
        	preparedStatement.setInt(1,id);
        	resultSet = preparedStatement.executeQuery();
        	ArrayList<Training> training = new ArrayList<Training>();
        	while (resultSet.next()) {
        		training.add(new Training(resultSet.getString(2),resultSet.getString(3)));
       	}
       	
       
       	con.close();
       		return training;
       	
			
		}
		private  ArrayList<Training> gettrainingemployeeinfo(String id) throws Exception {
			Connection con=connect();
        	preparedStatement = con.prepareStatement("SELECT * FROM employee.trainingemployee where employeeid=?;");    
        	preparedStatement.setString(1,id);
        	resultSet = preparedStatement.executeQuery();
        	ArrayList<Training> training = new ArrayList<Training>();
        	while (resultSet.next()) {
        		training.add(new Training(resultSet.getString(2),resultSet.getString(3)));
        		
       	}
       	
       
       	con.close();
       		return training;
       	
			
		}
		public WorkInfo getemployeestring(int id) throws Exception {
			WorkInfo work = null;
			Connection con=connect();
			DB db=new DB();
          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id where employee.workers.id=?;");
          	preparedStatement.setInt(1,id);
          	 ResultSet resultSet = preparedStatement.executeQuery();
          	ArrayList<Training> training = new ArrayList<Training>();
          	training=db.gettrainingemployeeinfo(id);
         
          	 
         	while ( resultSet.next() )  
           	{  
         		//SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id;
           	 UserData user=new UserData(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getDate(10).getYear()+1900,1+resultSet.getDate(10).getMonth(),resultSet.getDate(10).getDate(),resultSet.getBoolean(11),resultSet.getString(12));
           	 PositionInfo pos=new PositionInfo( resultSet.getInt(13), db.getcompany(resultSet.getInt(14)), resultSet.getString(15),resultSet.getString(16),resultSet.getString(17),resultSet.getString(18), resultSet.getBoolean(19),resultSet.getString(20),training,0);
           	 Password password=new Password(resultSet.getInt(21),resultSet.getString(22),resultSet.getString(23),resultSet.getInt(24));
           	 work=new WorkInfo(user,pos,password);		
           	} 
         	con.close();
			return work;
		}
		public ArrayList<timeoffrequest> gettor(int i) throws Exception {
			Connection con=connect();
			DB db=new DB();
          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest where employee=? order by date;");
          	preparedStatement.setInt(1,i);
          	 ResultSet resultSet = preparedStatement.executeQuery();
          	
          	ArrayList<timeoffrequest> req = new ArrayList<timeoffrequest>();

         	while ( resultSet.next() )  
           	{  
         		//SELECT * FROM employee.workers JOIN employee.positioninfo on employee.workers.id=employee.positioninfo.id JOIN employee.login on employee.login.id=employee.workers.id;
           	timeoffrequest request=new timeoffrequest(resultSet.getInt(1),resultSet.getDate(2).getYear()+1900,resultSet.getDate(2).getMonth()+1,resultSet.getDate(2).getDate(),resultSet.getDate(3).getYear()+1900,resultSet.getDate(3).getMonth()+1,resultSet.getDate(3).getDate(),resultSet.getTime(4),resultSet.getTime(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9));
           	
           	 req.add(request);	
           	
           	} 
         	con.close();
			return req;
		}
		
		public void updatetor(String p,int id) throws Exception {
			Connection con=connect();
			
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.timeoffrequest SET status=? where id=?");
			 pstmt.setString(1,p);
			 pstmt.setInt(2,id);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public ArrayList<TimeOffInfo> getalltor(int i) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;

			 pstmt= con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? order by date;");
			 pstmt.setInt(1,i);

		
          	 ResultSet resultSet = pstmt.executeQuery();
          	
          	ArrayList<TimeOffInfo> req = new ArrayList<TimeOffInfo>();
         	while ( resultSet.next() )  
           	{  
         		
           	timeoffrequest request=new timeoffrequest(resultSet.getInt(1),resultSet.getDate(2).getYear()+1900,resultSet.getDate(2).getMonth()+1,resultSet.getDate(2).getDate(),resultSet.getDate(3).getYear()+1900,resultSet.getDate(3).getMonth()+1,resultSet.getDate(3).getDate(),resultSet.getTime(4),resultSet.getTime(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9));
           	PositionInfo info=new PositionInfo(resultSet.getInt(10),db.getcompany(resultSet.getInt(11)),resultSet.getString(12),resultSet.getString(13),resultSet.getString(14),resultSet.getString(15),resultSet.getBoolean(16),resultSet.getString(17),null,0);
           	UserData data=new UserData(resultSet.getInt(18),resultSet.getString(19),resultSet.getString(20),resultSet.getString(21),resultSet.getString(22),resultSet.getString(23),resultSet.getString(24),resultSet.getString(25),resultSet.getString(26),resultSet.getDate(27).getYear()+1900,resultSet.getDate(27).getMonth()+1,resultSet.getDate(27).getDay(),resultSet.getBoolean(28),resultSet.getString(29));
           	TimeOffInfo to=new TimeOffInfo(request,info,data);
           	
           	
           	req.add(to);	
           	
           	} 
         	con.close();
			return req;
		}
		public ArrayList<TimeOffInfo> getalltorsearch(int i,int search,String field) throws Exception {
			Connection con=connect();
			DB db=new DB();
			System.out.println(search);
			if(search==0){
	         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? order by date");
				}
			if(search==1){
         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND employee.workers.id=? order by date");
			}
			if(search==2){
	         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND firstname=? order by date");
				}
			if(search==3){
	         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND lastname=? order by date");
				}
			if(search==4){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND location=? order by date;");
				}
			if(search==5){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND dept=? order by date;");
				}
			if(search==6){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND sublocation=? order by date;");
				}
			if(search==7){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND position=? order by date;");
				}
			if(search==8){
				if(field.equals("Approve")){
					field="2";
				}
				else if(field.equals("Decline")){
					field="1";
				}
				else{
					field="0";
				}
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND status=?;");
				}
			if(search==9){
	          	 preparedStatement = con.prepareStatement("SELECT * FROM employee.timeoffrequest JOIN employee.positioninfo on employee.timeoffrequest.employee=employee.positioninfo.id JOIN employee.workers on employee.workers.id=employee.positioninfo.id where companyid=? AND ? between date and enddate;");
				}
				preparedStatement.setInt(1,i);
				
				if(search!=0){
					if(search!=9){
				preparedStatement.setString(2,field);
				System.out.println(field);
					}
					else{
						preparedStatement.setString(2,(String) field.substring(6, 10)+"-"+field.subSequence(0, 3)+"-"+field.substring(3, 5));
					}
				}
          	 ResultSet resultSet = preparedStatement.executeQuery();
          	
          	ArrayList<TimeOffInfo> req = new ArrayList<TimeOffInfo>();
         	while ( resultSet.next() )  
           	{  
         		
           	timeoffrequest request=new timeoffrequest(resultSet.getInt(1),resultSet.getDate(2).getYear()+1900,resultSet.getDate(2).getMonth()+1,resultSet.getDate(2).getDate(),resultSet.getDate(3).getYear()+1900,resultSet.getDate(3).getMonth()+1,resultSet.getDate(3).getDate(),resultSet.getTime(4),resultSet.getTime(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9));
           	PositionInfo info=new PositionInfo(resultSet.getInt(10),db.getcompany(resultSet.getInt(11)),resultSet.getString(12),resultSet.getString(13),resultSet.getString(14),resultSet.getString(15),resultSet.getBoolean(16),resultSet.getString(17),null,0);
           	UserData data=new UserData(resultSet.getInt(18),resultSet.getString(19),resultSet.getString(20),resultSet.getString(21),resultSet.getString(22),resultSet.getString(23),resultSet.getString(24),resultSet.getString(25),resultSet.getString(26),resultSet.getDate(27).getYear()+1900,resultSet.getDate(27).getMonth()+1,resultSet.getDate(27).getDay(),resultSet.getBoolean(28),resultSet.getString(29));
           	TimeOffInfo to=new TimeOffInfo(request,info,data);
           	
           	
           	req.add(to);	
           	
           	} 
         	con.close();
			return req;
}
		public ArrayList<Shift> getschedule(int id) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
		
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where idemployee=? AND DATE(starttime)>=CURDATE() AND (clockin=0 or clockin=1 or clockin=2 or clockin=3) order by starttime;");
				 pstmt.setInt(1,id);
			
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(10),0,br,0,null,null,null,null,training);
         		
         		
         		
           
           	req.add(shift);	
           	
           	} 
         	con.close();
			return req;
			
		}
		public ArrayList<Shift> getavailableschedule(int id) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where ((idcompany=? AND idemployee=0) AND DATE(starttime)>=CURDATE()) OR ((clockin=1 AND idcompany=?) AND DATE(starttime)>=CURDATE()) order by starttime;");
			 pstmt.setInt(1,id);
			 pstmt.setInt(2,id);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(10),0,br,0,null,null,null,null,training);
         		
         		
         		
           	
           	req.add(shift);	
           	
           	} 
         	con.close();
			return req;
			
		}
		public ArrayList<Training> gettraining(int int1) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT training,date FROM employee.shift  join employee.trainingshift on employee.shift.id=employee.trainingshift.shiftid where shiftid=?;");
			 
			 pstmt.setInt(1,int1);
			
          	 ResultSet resultSet = pstmt.executeQuery();
          
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		Training tran=new Training(resultSet.getString(1),resultSet.getString(2));
         		training.add(tran);
         		
           	} 
         	con.close();
			return training;
		}
		public ArrayList<Shift> getavailableschedulesearch(int id,int search,String field) throws Exception {
			Connection con=connect();
			DB db=new DB();
			
			PreparedStatement pstmt = null;
			if(search==0){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where ((idcompany=? AND idemployee=0) OR (clockin=1 AND idcompany=?)) AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==1){
			 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where ((idcompany=? AND idemployee=0) OR (clockin=1 AND idcompany=?)) AND location=? AND DATE(starttime)>=CURDATE()  order by starttime;");
			}
			if(search==2){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where ((idcompany=? AND idemployee=0) OR (clockin=1 AND idcompany=?))  AND dept=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			if(search==3){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where ((idcompany=? AND idemployee=0) OR (clockin=1 AND idcompany=?))  AND sublocation=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			if(search==4){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where ((idcompany=? AND idemployee=0) OR (clockin=1 AND idcompany=?))  AND position=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			if(search==5){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where ((idcompany=? AND idemployee=0) OR (clockin=1 AND idcompany=?))  AND DATE(starttime)=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			if(search==6){
				pstmt= con.prepareStatement("SELECT * FROM employee.shift join employee.trainingshift on employee.shift.id=shiftid where ((idcompany=? AND idemployee=0) OR (clockin=1 AND idcompany=?)) AND training=? order by starttime");
				}
			 pstmt.setInt(1,id);
			 pstmt.setInt(2,id);
			 if(search!=5&&search!=0){
				 
			 pstmt.setString(3,field);
			 }
				 if(search==5){
			 pstmt.setString(3,(String) field.substring(6, 10)+"-"+field.subSequence(0, 3)+"-"+field.substring(3, 5));
				 }
			 
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
          	
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining(resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),0,0,br,0,null,null,null,null,training);
         		
           	
           	req.add(shift);	
           	
           	} 
         	con.close();
			return req;
			
		}
		
		public ArrayList<Shift> getschedulesearch(int id,int search,String field) throws Exception {
			Connection con=connect();
			DB db=new DB();
			
			PreparedStatement pstmt = null;
			if(search==0){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where (idemployee=? OR clockin=1) AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==1){
			 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where (idemployee=? OR clockin=1) AND location=? AND DATE(starttime)>=CURDATE()  order by starttime;");
			}
			if(search==2){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where (idemployee=?  OR clockin=1)  AND dept=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			if(search==3){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where (idemployee=?  OR clockin=1)  AND sublocation=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			if(search==4){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where (idemployee=?  OR clockin=1)  AND position=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			if(search==5){
				 pstmt= con.prepareStatement("SELECT * FROM employee.shift  where (idemployee=?  OR clockin=1)  AND DATE(starttime)=? AND DATE(starttime)>=CURDATE()  order by starttime;");
				}
			 pstmt.setInt(1,id);
			 if(search!=5&&search!=0){
				 
			 pstmt.setString(2,field);
			 }
				 if(search==5){
			 pstmt.setString(2,(String) field.substring(6, 10)+"-"+field.subSequence(0, 3)+"-"+field.substring(3, 5));
				 }
			 
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
          	
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining(resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),0,0,br,0,null,null,null,null,training);
         		
           	
           	req.add(shift);	
           	
           	} 
         	con.close();
			return req;
			
		}
		public void updateshift(int shift,String shift2) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET idemployee=? , clockin=0 where id=?");
			 pstmt.setInt(1,shift);
			 pstmt.setString(2,shift2);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void updateshifttrade(String string) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET clockin=1 where id=?");
			 pstmt.setString(1,string);
			 pstmt.executeUpdate();
			 con.close();
			
		}
	
		public void deletetor() throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.timeoffrequest WHERE employee.timeoffrequest.date< CURDATE();");
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void deleteator(String s) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.timeoffrequest WHERE id=? AND (status=2 or status=0);");
			 pstmt.setString(1,s);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void insertshift(Shift shift) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.SHIFT values (DEFAULT,?,?,?,?,?,?,?,?,?,null,null,0)");
			 pstmt.setInt(1,shift.getID());
			 pstmt.setInt(2,shift.getCompany());
			 pstmt.setString(3,shift.getLocation());
			 pstmt.setString(4,shift.getDept());
			 pstmt.setString(5,shift.getSublocation());
			 pstmt.setString(6,shift.getPosition());
			 pstmt.setString(7,shift.getStarttime());
			 pstmt.setString(8,shift.getEndtime());
			 pstmt.setInt(9,0);

			 pstmt.executeUpdate();
			 con.close();
		}
		public String getacompany(int i) throws Exception {
			String company = null;
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("SELECT company FROM employee.positioninfo join employee.company on employee.positioninfo.companyid=employee.company.idcompany  where id=?");
			 pstmt.setInt(1, i);
             resultSet = pstmt.executeQuery();
             while (resultSet.next()) {
             company=resultSet.getString(1);
               }
			return company;
		}
		public ArrayList<ShiftInfo> getallschedule(int company) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND DATE(starttime)>=CURDATE() order by starttime;");
			 pstmt.setInt(1,company);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<ShiftInfo> info = new ArrayList<ShiftInfo>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),0,br,0,resultSet.getDate(12),resultSet.getDate(13),null,null,training);
         		ShiftInfo infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           	
           	info.add(infoa);	
           	
           	} 
         	con.close();
			return info;
			
		}
		public ArrayList<ShiftInfo> getallshiftsearch(int company,int search,String field) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			if(search==0){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==1){
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and workers.id=? AND DATE(starttime)>=CURDATE() order by starttime;");
			}
			if(search==2){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and firstname=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==3){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and lastname=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==4){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and location=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==5){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and dept=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==6){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and sublocation=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==7){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and position=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==8){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=?  AND DATE(starttime)=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			if(search==9){
				//training
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? and clockin=? AND DATE(starttime)>=CURDATE() order by starttime;");
				}
			pstmt.setInt(1,company);
			if(search!=0){
			 pstmt.setString(2,field);
			 if(search==8){
				 pstmt.setString(2,(String) field.substring(6, 10)+"-"+field.subSequence(0, 2)+"-"+field.substring(3, 5));
					 }
			}
			 
			  	 ResultSet resultSet = pstmt.executeQuery();
		          	ArrayList<Shift> req = new ArrayList<Shift>();
		          	ArrayList<ShiftInfo> info = new ArrayList<ShiftInfo>();
		          	ArrayList<Break> br = new ArrayList<Break>();
		          	ArrayList<Training> training = new ArrayList<Training>();
		         	while ( resultSet.next() )  
		           	{  
		         		training=db.gettraining(resultSet.getInt(1));
		         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(12),0,br,0,null,null,null,null,training);
		         		ShiftInfo infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
		           	
		           	info.add(infoa);	
		           	
		           	} 
		         	con.close();
					return info;
			
		}
		public void deleteshift(String id) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.shift WHERE employee.shift.id=?;");
			 pstmt.setString(1,id);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void updateshift(Shift shift) throws Exception {
			Connection con=connect();
			
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET idemployee=? , location=?,dept=? ,sublocation=?, position=? , starttime=? , endtime=?  where id=?");
			 pstmt.setInt(1,shift.getID());
			 pstmt.setString(2,shift.getLocation());
			 pstmt.setString(3,shift.getDept());
			 pstmt.setString(4,shift.getSublocation());
			 pstmt.setString(5,shift.getPosition());
			 pstmt.setString(6,shift.getStarttime());
			 pstmt.setString(7,shift.getEndtime());
			 pstmt.setInt(8,shift.getShift());
			 pstmt.executeUpdate();
			 con.close();
			
		}
		@SuppressWarnings("deprecation")
		public void inserttraining(Shift shift) throws Exception {
			Connection con=connect();
			for(int x=0;x<shift.getTraining().size();x++){
			 statement = con.createStatement();
			 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.TRAININGSHIFT values (DEFAULT,?,?,?)");
			 pstmt.setInt(3,shift.getShift());
			 pstmt.setString(1,shift.getTraining().get(x).getTraining());
			 pstmt.setString(2,shift.getTraining().get(x).getDate());
			 pstmt.executeUpdate();
			}
			con.close();
		}
		public void inserttraining(Training training,int user) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.TRAININGEMPLOYEE values (DEFAULT,?,?,?)");
			 
			 pstmt.setString(1,training.getTraining());
			 pstmt.setString(2, training.getDate());
			 pstmt.setInt(3,user);
			 pstmt.executeUpdate();
			
			con.close();
			
		}
		public boolean getusertraining(String[] splitString,int id) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT training FROM employee.trainingemployee where employeeid=?;");
			 pstmt.setInt(1,id);
			 ResultSet resultSet = pstmt.executeQuery();
			 ArrayList<String> training = new ArrayList<String>();
			 String s;
         	while ( resultSet.next() )  
           	{  
         		s=resultSet.getString(1);
         		training.add(s);
         		
         		
         		
           	} 
         	con.close();

         	for(int x=0;x<splitString.length;x++){
         		if(!training.contains(splitString[x])){
         			
         			return false;
         		}
         	}
			return true;
		}
		public ArrayList<Training> gettraining(String shiftid) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT training,date FROM employee.shift  join employee.trainingshift on employee.shift.id=employee.trainingshift.shiftid where shiftid=?;");
			 
			 pstmt.setString(1,shiftid);
			
          	 ResultSet resultSet = pstmt.executeQuery();
          
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		Training tran=new Training(resultSet.getString(1),resultSet.getString(2));
         		training.add(tran);
         		
           	} 
         	con.close();
			return training;
		}
		public void deletetraining(int id) throws Exception {
			Connection con=connect();
			
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.trainingshift where shiftid=?");
			 pstmt.setInt(1,id);
			 
			 
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void deletetraining() throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.trainingemployee WHERE employee.trainingemployee.date< CURDATE();");
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void updateshiftclockin(String parameter) throws Exception {
			//only clock in if ontimke
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET clockin=2,actstart=starttime where id=? AND starttime>NOW();");
			 pstmt.setString(1,parameter);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void updateshiftclockinlate(String parameter) throws Exception {
			//only clock in if ontimke
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET clockin=2,actstart=NOW() where id=? AND starttime<NOW();");
			 pstmt.setString(1,parameter);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public ArrayList<ShiftInfo> getscheduleclockincomplete(int company) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=4 or clockin=3 or clockin=5) order by starttime");
			 pstmt.setInt(1,company);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<ShiftInfo> info = new ArrayList<ShiftInfo>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),resultSet.getInt(15),br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		ShiftInfo infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           	
           	info.add(infoa);	
           	
           	} 
         	con.close();
			return info;
			
		}
		public ArrayList<ShiftInfo> getscheduleclockin(int company) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where (idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2 AND idcompany=?) order by starttime");
			 pstmt.setInt(1,company);
			 pstmt.setInt(2,company);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<ShiftInfo> info = new ArrayList<ShiftInfo>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),resultSet.getInt(15),br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		ShiftInfo infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           	
           	info.add(infoa);	
           	
           	} 
         	con.close();
			return info;
			
		}
		public ArrayList<ShiftInfo> getsearchschedulecomp(int company,String string,String field) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			if(string.equals("0")){
				pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) order by starttime");
			}
			if(string.equals("1")){
				pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) AND idemployee=? order by starttime");
				}
			 if(string.equals("2")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) AND firstname=? order by starttime");
				}
			 if(string.equals("3")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) AND lastname=? order  by starttime");
				}
			 if(string.equals("4")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5)  AND location=? order by starttime");
				}
			 if(string.equals("5")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) AND dept=? order by starttime");
				}
			 if(string.equals("6")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) AND sublocation=? order by starttime");
				}
			 if(string.equals("7")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) AND position=? order by starttime");
				}
			 if(string.equals("8")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee join employee.trainingshift on employee.shift.id=shiftid where idcompany=? AND (clockin=3 or clockin=4 or clockin=5) AND training=? order by starttime");
 
				}
			
			 
			 
			 pstmt.setInt(1,company);
			 if(!string.equals("0")){
			 pstmt.setString(2,field);
			 }
			
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<ShiftInfo> info = new ArrayList<ShiftInfo>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),resultSet.getInt(15),br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		ShiftInfo infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           	
           	info.add(infoa);	
           	
           	} 
         	con.close();
			return info;
			
		}
		public ArrayList<ShiftInfo> getsearchscheduleclockin(int company,String string,String field) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			if(string.equals("0")){
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where (idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2 AND idcompany=?) order by starttime");
			}
			if(string.equals("1")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where ((idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND ((clockin=0 or clockin=1))OR( clockin=2))) AND idemployee=? AND idcompany=? order by starttime");
				}
			 if(string.equals("2")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where (idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND ((clockin=0 or clockin=1))OR( clockin=2)) AND firstname=? AND idcompany=? order by starttime");
				}
			 if(string.equals("3")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where ((idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2)) AND lastname=? AND idcompany=? order by starttime");
				}
			 if(string.equals("4")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where (idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2)) AND location=? AND idcompany=? order by starttime");
				}
			 if(string.equals("5")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where ((idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2)) AND dept=? AND idcompany=? order by starttime");
				}
			 if(string.equals("6")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where (idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2)) AND sublocation=? AND idcompany=? order by starttime");
				}
			 if(string.equals("7")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where (idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2)) AND position=?  AND idcompany=? order by starttime");
				}
			 if(string.equals("8")){
				 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin,time FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee join employee.trainingshift on employee.shift.id=shiftid  where ((idcompany=? AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2) )AND training=?  AND idcompany=? order by starttime;");
 
				}
			
			 
			 
			 pstmt.setInt(1,company);
			 if(!string.equals("0")){
			 pstmt.setString(2,field);
			 pstmt.setInt(3,company);
			 }
			 else{
				 pstmt.setInt(2,company); 
			 }
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ArrayList<ShiftInfo> info = new ArrayList<ShiftInfo>();
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),resultSet.getInt(15),br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		ShiftInfo infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           	
           	info.add(infoa);	
           	
           	} 
         	con.close();
			return info;
			
		}
	     public boolean checkifclockin(int id) throws Exception {
         	Connection con=connect();
           	 preparedStatement = con.prepareStatement("SELECT * FROM employee.shift where idemployee=? AND clockin=2 ");
           	preparedStatement.setInt(1,id);
           	 ResultSet resultSet = preparedStatement.executeQuery();
           	int rowCount = 0;  
           	while ( resultSet.next() )  
           	{  
           	    // Process the row.  
           	    rowCount++;  
           	} 
           	con.close();
           	 if(rowCount==0){

           		return false;
           	 }else{
           		 return true;
           	 }
           	
           		
           	
           	
           }
	     public int getshiftid(String id) throws Exception {
				Connection con=connect();
				int employeeid = 0;
	          	 preparedStatement = con.prepareStatement("SELECT idemployee FROM employee.shift where id=?;");
	          	preparedStatement.setString(1,id);
	          	 ResultSet resultSet = preparedStatement.executeQuery();
	         	while ( resultSet.next() )  
	           	{  
	           	    employeeid=resultSet.getInt(1);  
	           	} 
	         	con.close();
	         	return employeeid;
				
			}
		public void updateshiftclockout(String parameter) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET clockin=3,actend=NOW() where id=? AND clockin=2;");
			 pstmt.setString(1,parameter);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void addbreak(Break breaks) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt = con.prepareStatement("insert into  EMPLOYEE.BREAK values (DEFAULT,?,?,?,?)");
			 
			 pstmt.setString(1,breaks.getid());
			 pstmt.setString(2,breaks.getDatestart()+" "+breaks.getTimestart());
			 pstmt.setString(3,breaks.getDateend()+" "+breaks.getTimeend());
			 pstmt.setInt(4,0);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		
		public void updateclockin(Break breaks) throws Exception {
			// TODO Auto-generated method stub
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt = con.prepareStatement("UPDATE employee.shift SET actstart=? ,actend=? where id=? ");
			 
			 pstmt.setString(3,breaks.getid());
			 pstmt.setString(1,breaks.getDatestart()+" "+breaks.getTimestart());
			 if(breaks.getDateend()!=null){
			 pstmt.setString(2,breaks.getDateend()+" "+breaks.getTimeend());
			 }
			 else{
				 pstmt.setString(2,null);
			 }
			 pstmt.executeUpdate();
			 con.close();
		}
		 public Break getShiftEdit(String shift) throws Exception {
  			Connection con=connect();
           	 preparedStatement = con.prepareStatement("SELECT actstart,actend FROM employee.shift where id=?;");
           	preparedStatement.setString(1,shift);
           	 ResultSet resultSet = preparedStatement.executeQuery();
           	 
           	 String startdate = null,starttime = null,enddate = null,endtime = null;
           	while (resultSet.next()) {
          		startdate=resultSet.getDate(1).toString();
          		starttime=resultSet.getTime(1).toString();
          		if(resultSet.getString(2)==null){
          		}else{
          		enddate=resultSet.getDate(2).toString();
          		endtime=resultSet.getTime(2).toString();
          		}
         	}
           	Break breaks=new Break(shift,startdate,enddate,starttime,endtime);
           	con.close();
           
				return breaks;
		
		
}
		public ShiftInfo getshiftind(String parameter) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where shift.id=? order by starttime");
			 pstmt.setString(1,parameter);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ShiftInfo infoa = null;
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		br=db.getbreak(resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),0,br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           		
           	
           	} 
         	con.close();
			return infoa;
		}
		public ShiftInfo getshiftind(int parameter) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where (shift.id=?  AND (NOW()+interval 2 HOUR)>starttime AND (NOW()-interval 4 hour)<starttime AND (clockin=0 or clockin=1))OR( clockin=2 AND shift.id=?) order by starttime");
			 pstmt.setInt(1,parameter);
			 pstmt.setInt(2,parameter);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ShiftInfo infoa = null;
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		br=db.getbreak(resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),0,br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           		
           	
           	} 
         	con.close();
			return infoa;
		}
		public ShiftInfo getshiftinda(String parameter) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where shift.id=?");
			 pstmt.setString(1,parameter);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ShiftInfo infoa = null;
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		br=db.getbreak(resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),0,br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           		
           	
           	} 
         	con.close();
			return infoa;
		}
		public ShiftInfo getshiftindcomp(int parameter) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where ( clockin=3 AND shift.id=?) order by starttime");
			 pstmt.setInt(1,parameter);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ShiftInfo infoa = null;
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		br=db.getbreak(resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),0,br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           		
           	
           	} 
         	con.close();
			return infoa;
		}
		public ShiftInfo getshiftindcomp(String parameter) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT shift.id,idemployee,idcompany,location,dept,sublocation,position,starttime,endtime,firstname,lastname,actstart,actend,clockin FROM employee.shift join employee.workers on employee.workers.id=employee.shift.idemployee where ( clockin=3 AND shift.id=?) order by starttime");
			 pstmt.setString(1,parameter);
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Shift> req = new ArrayList<Shift>();
          	ShiftInfo infoa = null;
          	ArrayList<Break> br = new ArrayList<Break>();
          	ArrayList<Training> training = new ArrayList<Training>();
         	while ( resultSet.next() )  
           	{  
         		training=db.gettraining( resultSet.getInt(1));
         		br=db.getbreak(resultSet.getInt(1));
         		Shift shift=new Shift(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getDate(9),resultSet.getTime(8).toString(),resultSet.getTime(9).toString(),resultSet.getInt(14),0,br,0,resultSet.getDate(12),resultSet.getDate(13),resultSet.getString(12),resultSet.getString(13),training);
         		infoa=new ShiftInfo(shift,resultSet.getString(10),resultSet.getString(11));
           		
           	
           	} 
         	con.close();
			return infoa;
		}
		public ArrayList<Break> getbreak(int int1) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT id,DATE(starttime),DATE(endtime),TIME(starttime),TIME(endtime) FROM employee.break where shiftid=?;");
			 pstmt.setInt(1,int1);
			 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Break> br = new ArrayList<Break>();
         	while ( resultSet.next() )  
           	{  
         		Break breaks=new Break(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
           	
           br.add(breaks);	
           	
           	} 
         	con.close();
			return br;
		}
		public Break getbreak(String int1) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT id,DATE(starttime),DATE(endtime),TIME(starttime),TIME(endtime) FROM employee.break where id=?;");
			 pstmt.setString(1,int1);
			 ResultSet resultSet = pstmt.executeQuery();
			 Break breaks=null;
         	while ( resultSet.next() )  
           	{  
         		breaks=new Break(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
           	
        	
           	
           	} 
         	con.close();
			return breaks;
		}
		public void deletebreak(String parameter) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.break WHERE id=?;");
			 pstmt.setString(1,parameter);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void updatebreak(Break breaks) throws Exception {
			Connection con=connect();
			
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.break SET starttime=? , endtime=? where id=?");
			 pstmt.setString(1,breaks.getDatestart()+" "+breaks.getTimestart());
			 pstmt.setString(2,breaks.getDateend()+" "+breaks.getTimeend());
			 pstmt.setString(3,breaks.getid());
			 pstmt.executeUpdate();
			 con.close();
		}
		public ArrayList<Attendance> getallattendence(int i) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT * FROM employee.attendance WHERE companyid=?;");
			 pstmt.setInt(1,i);
			
          	 ResultSet resultSet = pstmt.executeQuery();
          	ArrayList<Attendance> req = new ArrayList<Attendance>();
         	while ( resultSet.next() )  
           	{  
         	Attendance atten=new Attendance(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getString(7));
         		
           	
           req.add(atten);	
           	
           	} 
         	con.close();
			return req;
			
		}
		public void deleteattendance(String parameter) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.attendance WHERE id=?;");
			 pstmt.setString(1,parameter);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public Attendance getattendance(String parameter) throws Exception {
			Connection con=connect();
			DB db=new DB();
			PreparedStatement pstmt = null;
			 pstmt= con.prepareStatement("SELECT * FROM employee.attendance WHERE id=?;");
			 pstmt.setString(1,parameter);
			
          	 ResultSet resultSet = pstmt.executeQuery();
          	Attendance atten=null;
         	while ( resultSet.next() )  
           	{  
         	 atten=new Attendance(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getString(7));
         		
           	
          	
           	
           	} 
         	con.close();
			return atten;
		}
		public void updateattendance(Attendance atten) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.attendance SET startrange=? , endrange=? , points=?, type=? where id=?");
			 pstmt.setString(1,atten.getStartrange());
			 pstmt.setString(2,atten.getEndrange());
			 pstmt.setInt(3,atten.getPoints());
			 pstmt.setInt(4,atten.getType());
			 pstmt.setInt(5,atten.getid());
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public void insertattendance(Attendance atten) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("INSERT INTO employee.attendance values (default,?,?,?,?,?,?)");
			 pstmt.setInt(1,atten.getType());
			 pstmt.setInt(2,atten.getCompanyid());
			 pstmt.setString(3,atten.getStartrange());
			 pstmt.setString(4,atten.getEndrange());
			 pstmt.setInt(5,atten.getPoints());
			 pstmt.setString(6,atten.getDesc());
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public int addpoints(ShiftInfo shift) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("SELECT points from employee.attendance where type=0 and startrange<(SUBTIME(CURTIME(),TIME(?))) AND endrange>(SUBTIME(CURTIME(),TIME(?)))");
			 pstmt.setString(1,shift.getShift().getStarttime());
			 pstmt.setString(2,shift.getShift().getStarttime());
			 ResultSet resultSet = pstmt.executeQuery();
			 int points=0;
	         	while ( resultSet.next() )  
	           	{  
	         	points=resultSet.getInt(1);
	         		
	           	
	          	
	           	
	           	} 
			 con.close();
			return points;
			
		}
		public void addactualpoints(int points, ShiftInfo shift) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET time=? where id=?");
			 pstmt.setInt(1,points);
			 pstmt.setInt(2,shift.getShift().getShift());
			 pstmt.executeUpdate();
			
		}
		public void addactualpointscallout(int points, ShiftInfo shift) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET time=? , clockin=5 where id=?");
			 pstmt.setInt(1,points);
			 pstmt.setInt(2,shift.getShift().getShift());
			 pstmt.executeUpdate();
			
		}
		public int updatecallout(ShiftInfo shift,int companyid) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("SELECT points from employee.attendance where type=1 AND startrange<(TIMEDIFF(?,NOW())) AND endrange>(TIMEDIFF(?,NOW())) AND companyid=?;");
			 pstmt.setString(1,shift.getShift().getStartdate()+" "+shift.getShift().getStarttime());
			 pstmt.setString(2,shift.getShift().getStartdate()+" "+shift.getShift().getStarttime());
			 pstmt.setInt(3,companyid);
			 ResultSet resultSet = pstmt.executeQuery();
			 int points=0;
	         	while ( resultSet.next() )  
	           	{  
	         	points=resultSet.getInt(1);
	           	} 
			 con.close();
			return points;
			
		}
	
		public void notshownocall(int companyid,int point) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET time=?, clockin=4 where idcompany=? AND DATE_ADD(starttime,INTERVAL 1 DAY)<NOW() and (clockin=1 or clockin=0); ");
			 pstmt.setInt(1,point);
			 pstmt.setInt(2,companyid);
			 pstmt.executeUpdate();
			
		}
		public int getpointsnsnc(int companyid) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("SELECT points from employee.attendance where type=3 and companyid=?");
			 pstmt.setInt(1,companyid);
			 ResultSet resultSet = pstmt.executeQuery();
			 int points=0;
	         	while ( resultSet.next() )  
	           	{  
	         	points=resultSet.getInt(1);
	         		
	           	
	          	
	           	
	           	} 
			 con.close();
			return points;
		}
		public int getPoints(String parameter) throws Exception {
			Connection con=connect();
          	 preparedStatement = con.prepareStatement("SELECT time FROM employee.shift where id=?;");
          	preparedStatement.setString(1,parameter);
          	 ResultSet resultSet = preparedStatement.executeQuery();
          	 
          	int points=0;
          	while (resultSet.next()) {
         		points=resultSet.getInt(1);
         		
         		}
          	con.close();
            
			return points;
        	}
		public void updatepoints(String id, String points) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("UPDATE employee.shift SET time=? where id=?");
			 pstmt.setString(1,points);
			 pstmt.setString(2, id);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public ArrayList<Message> getMessage(int id) throws Exception {
			Connection con=connect();
         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.message join employee.workers on employee.message.from=employee.workers.id where employee.message.to=? order by date DESC;");
         	preparedStatement.setInt(1,id);
         	 ResultSet resultSet = preparedStatement.executeQuery();
         	 
         	ArrayList<Message> message = new ArrayList<Message>();
         	while (resultSet.next()) {
        		Message mess=new Message(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getDate(6).toString(),resultSet.getTime(6).toString(),resultSet.getString(8),resultSet.getString(9));
        		message.add(mess);
        		}
         	con.close();
           
			return message;
		}
		public void insertMessage(Message message) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("INSERT INTO employee.message values (default,?,?,?,?,NOW())");
			 pstmt.setInt(1,message.getSeriesID());
			 pstmt.setInt(2,message.getFrom());
			 pstmt.setInt(3,message.getTo());
			 pstmt.setString(4,message.getMessage());
			 pstmt.executeUpdate();
			 con.close();
         	 
         	
         
           
			
		}
		public int getMaxSeriesID() throws Exception {
			   int id = 0;
   			Connection con=connect();
            	 preparedStatement = con.prepareStatement("SELECT MAX(seriesid) FROM EMPLOYEE.MESSAGE");
            	 ResultSet resultSet = preparedStatement.executeQuery();
            	while (resultSet.next()) {
           		id = resultSet.getInt(1);
          	}
            	con.close();
            	System.out.print(id);
				return id;
		}
		public void deletemessage(String parameter) throws Exception {
			Connection con=connect();con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("DELETE FROM employee.message WHERE id=?;");
			 pstmt.setString(1,parameter);
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public Message getmessageinfo(String parameter) throws Exception {
			Connection con=connect();
        	 preparedStatement = con.prepareStatement("SELECT * FROM employee.message join employee.workers on employee.message.from=employee.workers.id where employee.message.id=?;");
        	preparedStatement.setString(1,parameter);
        	 ResultSet resultSet = preparedStatement.executeQuery();
        	 
        	Message mess = null;
        	while (resultSet.next()) {
       		mess=new Message(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getDate(6).toString(),resultSet.getTime(6).toString(),resultSet.getString(8),resultSet.getString(9));
        	
        	}
        	con.close();
          
			return mess;
		}

		public void insertrely(Message message,String mess) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("INSERT INTO employee.message values (default,?,?,?,?,NOW())");
			 pstmt.setInt(1,message.getSeriesID());
			 pstmt.setInt(2,message.getTo());
			 pstmt.setInt(3,message.getFrom());
			 pstmt.setString(4,mess);
			 pstmt.executeUpdate();
			 con.close();
        	 
			
		}
		public ArrayList<Message> getMessageSeriesInfo(String parameter) throws Exception {
			Connection con=connect();
        	 preparedStatement = con.prepareStatement("SELECT * FROM employee.message join employee.workers on employee.message.from=employee.workers.id where employee.message.seriesid=?;");
        	preparedStatement.setString(1,parameter);
        	 ResultSet resultSet = preparedStatement.executeQuery();
        	 
        	ArrayList<Message> message = new ArrayList<Message>();
        	while (resultSet.next()) {
       		Message mess=new Message(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getDate(6).toString(),resultSet.getTime(6).toString(),resultSet.getString(8),resultSet.getString(9));
       		message.add(mess);
       		}
        	con.close();
          
			return message;
		}
		public ArrayList<Message> getSentMessage(int id) throws Exception {
			Connection con=connect();
        	 preparedStatement = con.prepareStatement("SELECT * FROM employee.message join employee.workers on employee.message.to=employee.workers.id where employee.message.from=? order by date DESC;");
        	preparedStatement.setInt(1,id);
        	 ResultSet resultSet = preparedStatement.executeQuery();
        	 
        	ArrayList<Message> message = new ArrayList<Message>();
        	while (resultSet.next()) {
       		Message mess=new Message(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getDate(6).toString(),resultSet.getTime(6).toString(),resultSet.getString(8),resultSet.getString(9));
       		message.add(mess);
       		}
        	con.close();
          
			return message;
		}
		public void insertnews(NewStories news) throws Exception {
			Connection con=connect();
			 statement = con.createStatement();
			 PreparedStatement pstmt=con.prepareStatement("INSERT INTO employee.newstories values (default,?,?,?,?,?,NOW(),?,?)");
			 pstmt.setInt(1,news.getcompanyid());
			 pstmt.setString(2,news.getlocation());
			 pstmt.setString(3,news.getdept());
			 pstmt.setString(4,news.getsublocation());
			 pstmt.setString(5,news.getposition());
			 pstmt.setString(6,news.gettitle());
			 pstmt.setString(7,news.getstory());
			 pstmt.executeUpdate();
			 con.close();
			
		}
		public ArrayList<NewStories> getNews(int id,PositionInfo info) throws Exception {
			Connection con=connect();
			//if(info.getLocation().equals("")&&info.getDept().equals("")&&info.getSublocation().eq&&info.getPosition()=="")
         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.newstories where companyid=? AND (location=? OR location=?) AND (dept=? OR dept=?)  AND (sublocation=? OR sublocation=?) AND (position=? OR position=?)  ;");
         	preparedStatement.setInt(1,id);
         	preparedStatement.setString(2,info.getLocation());
         	preparedStatement.setString(3," ");
         	preparedStatement.setString(4,info.getDept());
         	preparedStatement.setString(5," ");
         	preparedStatement.setString(6,info.getSublocation());
         	preparedStatement.setString(7," ");
         	preparedStatement.setString(8,info.getPosition());
         	preparedStatement.setString(9," ");
         	 ResultSet resultSet = preparedStatement.executeQuery();
         	 
         	ArrayList<NewStories> news = new ArrayList<NewStories>();
         	while (resultSet.next()) {
        		NewStories story=new NewStories(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
        		news.add(story);
        		}
         	con.close();
           
			return news;
		}
		public NewStories getNews(String id) throws Exception {
			Connection con=connect();
         	 preparedStatement = con.prepareStatement("SELECT * FROM employee.newstories where id=?;");
         	preparedStatement.setString(1,id);
         	 ResultSet resultSet = preparedStatement.executeQuery();
         	 
         	NewStories news = null;
         	while (resultSet.next()) {
        		news=new NewStories(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
        		
        		}
         	con.close();
           
			return news;
		}
		public String getName(String id) throws Exception {
			Connection con=connect();
        	 preparedStatement = con.prepareStatement("SELECT firstname,lastname FROM employee.workers where id=?;");
        	preparedStatement.setString(1,id);
        	 ResultSet resultSet = preparedStatement.executeQuery();
        	 
        	String name = null;
        	while (resultSet.next()) {
       		name=resultSet.getString(1)+" "+resultSet.getString(2);
       		
       		}
        	con.close();
          
			return name;
		}
          
          	
		
}

			

	


     




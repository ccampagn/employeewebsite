<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.UserData,user.Attendance;" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="stylesheet.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="navbar">
			<a id=menu href="home">Home</a>
			<a id=menu href="information">Information</a>
			<a id=menu href="EmployeeDirectory">Employee Directory</a>
			<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true){
				%><a id=menu href="AddEmployee">Add Employee</a>			
				<% 
			}
				%>
			<a id=menu href="Schedulea">Schedule</a>
			<a id=menu href="timeoffrequest">Time Off Request</a>
			<a id=menu href="Message">Message</a>
			<a id=menu href="Training">Training</a>
			<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true){
				%>
					<a id=menu href="Attendance">Attendance</a>				
				<% 
			}%>
			<a id=menu href="ChangePassword">Change Password</a>	
			<a id=menu href="logout">Log Out</a>			
			</div>
			<div id="scheduleemployee">
<form name="attendence" method="post" action="attendence">
<table>
<tr>
<td>Type:</td><td> <select name="type">
<option value="0">Late</option>
<option value="1">Call out</option>
<option value="3">No Call No Show</option>
</select>
</td>
</tr>
<tr><td>Start Range:</td><td> <input type="text" name="startrange"></td></tr>
<tr><td>End Range:</td><td><input type="text" name="endrange"></td></tr>
<tr><td>Points:</td><td><input type="text" name="points"></td></tr>
<tr><td>Description:</td><td><input type="text" name="points"></td></tr>
<tr><td><input type="submit" value="Add" /></td></tr>
</table>
${error}
</form>
</div>
<div id=attendance>
<table border="1">
<tr>
<td>Type</td>
<td>Start Range</td>
<td>End Range</td>
<td>Points</td>
<td>Descr</td>
</tr>
<% for(int x=0;x<((ArrayList<Attendance>)session.getAttribute("attendance")).size();x++){%>
<tr>
<td>
<%if((((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getType())==0){%>
Late
<%} %>
<%if((((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getType())==1){%>
Call Out
<%} %>
<%if((((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getType())==3){%>
No Show No Call
<%} %>
</td>
<td>
<%out.println((((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getStartrange()));%>
</td>
<td>
<%out.println((((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getEndrange()));%>
</td>
<td>
<%out.println((((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getPoints()));%>
</td>
<td>
<%out.println((((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getDesc()));%>
</td>
<td>
<form action = "deleteattendence" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getid());%>>Delete</button>
    </form> 
    </td>
<td>
<form action = "editattendence" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Attendance>)session.getAttribute("attendance")).get(x).getid());%>>Edit</button>
    </form> 
    </td>



	
</tr>
<%} %>
</table>
</div>
</body>
</html>
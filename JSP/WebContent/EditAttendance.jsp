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
<form name="attendence" method="post" action="editattendenceupdate">
<table>
<tr>
<%if(((Attendance)session.getAttribute("editattendance")).getType()==0){ %>
<td>Type:</td><td> <select name="type" >
<option value="0" selected>Late</option>
<option value="1">Call out</option>
<option value="3">No Show No Call</option>
</select>
</td>
</tr>
<%} %>
<%if(((Attendance)session.getAttribute("editattendance")).getType()==1){ %>
<td>Type:</td><td> <select name="type" >
<option value="0" >Late</option>
<option value="1" selected>Call out</option>
<option value="3">No Show No Call</option>
</select>
</td>
</tr>
<%} %>
<%if(((Attendance)session.getAttribute("editattendance")).getType()==3){ %>
<td>Type:</td><td> <select name="type" >
<option value="0" >Late</option>
<option value="1" >Call out</option>
<option value="3" selected>No Show No Call</option>
</select>
</td>
</tr>
<%} %>
<tr><td>Start Range:</td><td> <input type="text" name="startrange" value="<%=(((Attendance)session.getAttribute("editattendance")).getStartrange()).substring(0,5)%>"></td></tr>
<tr><td>End Range:</td><td><input type="text" name="endrange" value="<%=(((Attendance)session.getAttribute("editattendance")).getEndrange()).substring(0,5)%>"></td></tr>
<tr><td>Points:</td><td><input type="text" name="points" value="<%=((Attendance)session.getAttribute("editattendance")).getPoints()%>"></td></tr>
<tr><td><input type="submit" value="Add" /></td></tr>
</table>
${error}
</form>
</div>


</body>
</html>
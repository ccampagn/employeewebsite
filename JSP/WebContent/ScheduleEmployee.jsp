<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.UserData;" %>
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
<form name="scheduleemployee" method="post" action="scheduleemployee">
<table>
<tr><td>ID: </td><td><input type="text" name="id"></td></tr>
<tr><td>Location: </td><td><input type="text" name="location"></td></tr>
<tr><td>Dept: </td><td><input type="text" name="dept"></td></tr>
<tr><td>Sublocation: </td><td><input type="text" name="sublocation"></td></tr>
<tr><td>Position: </td><td><input type="text" name="position"></td></tr>
<tr><td>Start Date:</td><td> <input type="text" name="startdate"></td></tr>
<tr><td>End Date: </td><td><input type="text" name="enddate"></td></tr>
<tr><td>Start Time:</td><td> <input type="text" name="starttime"></td></tr>
<tr><td>End Time: </td><td><input type="text" name="endtime"></td></tr>
<tr><td>Training: </td><td><input type="text" name="training"></td></tr>
<tr><td><input type="submit" value="Add" /></td></tr>
</table>
${error}
</form>
</div>
</body>
</html>
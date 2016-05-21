<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo;" %>
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
<div id=addemployee>
<form name="newstory" method="post" action="AddNewStories">
<table>
<tr>
<td>Location:</td><td> <INPUT TYPE=TEXT NAME=location SIZE=20 ></td>
</tr>
<tr>
<td>Dept:</td><td> <INPUT TYPE=TEXT NAME=dept SIZE=20 ></td>
</tr>
<tr>
<td>Sub Location:</td><td> <INPUT TYPE=TEXT NAME=sublocation SIZE=20 ></td>
</tr>
<tr>
<td>Position:</td><td> <INPUT TYPE=TEXT NAME=position SIZE=20 ></td>
</tr>
<tr>
<td>Title:</td><td> <INPUT TYPE=TEXT NAME=title SIZE=20 ></td>
</tr>
<tr>
<td>Story: </td><td><textarea  cols="20" rows="20" name="story"></textarea></td>
</tr>
</table>
<input type="submit" value="Add" />
${error}
</form>

</div>
</body>
</html>
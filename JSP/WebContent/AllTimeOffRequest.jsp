<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.TimeOffInfo,user.timeoffrequest,user.PositionInfo,user.WorkInfo;" %>
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

<form name="AllTimeOffRequesta" method="post" action="alltimeoffrequest">
<div id="searchtor">
<table>
<tr>
<td>Search:<INPUT TYPE=TEXT NAME=field  VALUE=" " SIZE=20></td>


<td>
<select name="search">
<option value="0" selected>All</option>
<option value="1">ID</option>
<option value="2">First Name</option>
<option value="3">Last Name</option>
<option value="4">Location</option>
<option value="5">Dept</option>
<option value="6">Sub-Location</option>
<option value="7">Position</option>
<option value="8">Status</option>
<option value="9">Date</option>
</select>
</td>
</tr>
</table>

</div>

<div id="torlist">
<table border="1">
<tr>
<td>ID</td>
<td>Start Date</td>
<td>End Date</td>
<td>Start Time</td>
<td>End Time</td>
<td>Reason</td>
<td>First Name</td>
<td>Last Name</td>
<td>Location</td>
<td>Dept</td>
<td>SubLocation</td>
<td>Position</td>
<td>Reason</td>
<td>Status</td>
</tr>
<% for(int x=0;x<((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).size();x++){%>
<tr>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getEmployee());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getMonth()+"/"+((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getDay()+"/"+((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getYear());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getMonth2()+"/"+((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getDay2()+"/"+((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getYear2());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getStartTime());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getEndTime());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getReason());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).getUserData().getFirstname());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).getUserData().getLastname());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).getPositionInfo().getLocation());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).getPositionInfo().getDept());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).getPositionInfo().getSublocation());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).getPositionInfo().getPosition());%> 
</td>
<td>
<%out.println(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getReason());%> 
</td>
<td>
<%out.println("<select name=status"+((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getId()+">");%>
<%if(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getStatus()==2){%>
<option value="2" selected>Yes</option> 
<option value="1">No</option>
<%}
else if(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getStatus()==1){%>
<option value="1" selected>No</option>
<option value="2">Yes</option>
<%} 
else if(((ArrayList<TimeOffInfo>)session.getAttribute("alltimeoff")).get(x).gettor().getStatus()==0){%>
<option value="0" selected>Pending</option>
<option value="1">No</option>
<option value="2">Yes</option>
<%}%>
</select>
</td>
</tr>
<%} %>

</table>
<input type="submit" value="Search" />
</div>

</form>
${error}
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.timeoffrequest,user.Shift,user.PositionInfo,user.ShiftInfo;" %>
<html>

<head>
<link type="text/css" rel="stylesheet" href="stylesheet.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head><body>
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
			<div id="availshiftsearch">
<form name="availableshifts" method="post" action="AvailableShifts">
<table>
<tr>
<td>Search:<INPUT TYPE=TEXT NAME=field SIZE=20></td>


<td>
<select name="search">
<option value="0" selected>All</option>
<option value="1">Location</option>
<option value="2">Dept</option>
<option value="3">Sublocation</option>
<option value="4">Position</option>
<option value="5">Date</option>
<option value="6">Training</option>
</select>
</td>
</tr>
</table>
<input type="submit" value="Search" />
${error}
</form>
</div>
<div id="availshift">
<table border="1">
<tr>
<td>Date</td>
<td>Start Time</td>
<td>End Time</td>
<td>Location</td>
<td>Dept</td>
<td>Sub-Location</td>
<td>Position</td>
<td>Training</td>
<td>Pick Up</td>
<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true)
	{%>
<td>Edit</td>
<td>Delete</td>
<%} %>
</tr>
<% for(int x=0;x<((ArrayList<Shift>)session.getAttribute("availableshift")).size();x++){%>
<tr>
<td>
<%out.println((((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getStartdate().getMonth()+1)+"/"+((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getStartdate().getDate()+"/"+(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getStartdate().getYear()+1900));%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getStarttime());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getEndtime());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getLocation());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getDept());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getSublocation());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getPosition());%>
</td>
<td>

<%for(int y=0;y<((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getTraining().size();y++){
	out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getTraining().get(y).getTraining());
}
%>
</td>
<td>
  <form action = "PickUp" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getShift());%>>Pick Up</button>
    </form> 
</td>
	<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true)
	{
		%>

<td>
<form action = "availableeditshift" method = "get">
<button type="submit" name="upvote" value=<% out.println(x);%>>Edit</button>
    </form> 
    </td>
<td>
<form action = "deleteavailableshift" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Shift>)session.getAttribute("availableshift")).get(x).getShift());%>>Delete</button>
    </form> 
    </td>
    <%} %>
</tr>
<%} %>
</table>
</div>
</body>
</html>
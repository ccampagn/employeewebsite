<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.timeoffrequest,user.Shift,user.PositionInfo,user.Training;" %>
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
			<div id="schedule">
<table border="1">
<tr>
<td>Date</td>
<td>Start Time</td>
<td>End Time</td>
<td>Location</td>
<td>Dept</td>
<td>Sub-Location</td>
<td>Position</td>
<td>Trade</td>
<td>Call Out</td>
<td>Status</td>
<td>Training</td>
</tr>
<% for(int x=0;x<((ArrayList<Shift>)session.getAttribute("schedule")).size();x++){%>
<tr>
<td>
<%out.println((((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getStartdate().getMonth()+1)+"/"+((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getStartdate().getDate()+"/"+(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getStartdate().getYear()+1900));%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getStarttime());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getEndtime());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getLocation());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getDept());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getSublocation());%>
</td>
<td>
<%out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getPosition());%>
</td>

<td>
<form action = "Schedulea" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getShift());%>>Trade</button>
    </form> 
    </td>
    <td>
    <%if((((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getClockin()==1)||(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getClockin()==0)){%>
<form action = "callout" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getShift());%>>Call Out</button>
    </form> 
    <%}else{ %>
    <form action = "callout" method = "post">
<button type="submit" disabled name="upvote" value=<% out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getShift());%>>Call Out</button>
    </form> 
    <%} %>
    </td>
    <td>
<%if(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getClockin()==0){
	out.println("None");
}else{
	out.println("Pending");
}%>
</td>
<td>

<%for(int y=0;y<((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getTraining().size();y++){
	out.println(((ArrayList<Shift>)session.getAttribute("schedule")).get(x).getTraining().get(y).getTraining());
}
%>
</td>
</tr>
<%} %>
</table>
</div>
<div id="schedulebar">
	<a id=menu href="Schedulea">Schedule</a><br>
	<a id=menu href="AvailableShifts">Available Shifts</a><br>
	<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true){
				%>
	<a id=menu href="schedulesearch">All Schedule</a><br>
	<a id=menu href="clockin">Clock In</a><br>
	<a id=menu href="scheduleemployee">Schedule Employee</a><br>
<%} %>
</div>
</body>
</html>
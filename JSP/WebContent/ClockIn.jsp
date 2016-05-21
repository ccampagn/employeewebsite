<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.timeoffrequest,user.Shift,user.PositionInfo,user.ShiftInfo;" %>
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
			<div id=search>
<form name="schedulesearch" method="post" action="clockin">
<table>
<tr>
<td>Search:<INPUT TYPE=TEXT NAME=field SIZE=20></td>


<td>
<select name="search">
<option value="0">All</option>
<option value="1">id</option>
<option value="2">First Name</option>
<option value="3">Last Name</option>
<option value="4">Location</option>
<option value="5">Dept</option>
<option value="6">Sublocation</option>
<option value="7">Position</option>
<option value="8">Training</option>
</select>
</td>
</tr>
</table>
<input type="submit" value="Search" />
${error}
</form>




</div>
			<div id="schedule">
<table border="1">
<tr>
<td>ID</td>
<td>First Name</td>
<td>Last Name</td>
<td>Date</td>
<td>Start Time</td>
<td>End Time</td>
<td>Location</td>
<td>Dept</td>
<td>Sub-Location</td>
<td>Position</td>
<td>Training</td>
<td>Clock In Time</td>
<td>Clock Out Time</td>
<td>Points</td>
<td>Clock In</td>
<td>Clock Out</td>
<td>Break</td>
<td>Edit</td>
<td>Shift Info</td>
</tr>
<% for(int x=0;x<((ArrayList<ShiftInfo>)session.getAttribute("clockin")).size();x++){%>
<tr>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getID());%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getFirstname());%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getLastname());%>
</td>
<td>
<%out.println((((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getStartdate().getMonth()+1)+"/"+((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getStartdate().getDate()+"/"+(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getStartdate().getYear()+1900));%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getStarttime());%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getEndtime());%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getLocation());%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getDept());%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getSublocation());%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getPosition());%>
</td>
<td>

<%for(int y=0;y<((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getTraining().size();y++){
	out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getTraining().get(y).getTraining());
}
%> 
</td>
<td>
<%if(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getActStarttime()==null){%>
<%out.println("N/A");
}
else{
	out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getActStarttime());
}
%>
</td>
<td>
<%if(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getActEndtime()==null){%>
<%out.println("N/A");}
else{
	out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getActEndtime());
}
%>
</td>
<td>
<%out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getPoints());%>
</td>
<td>
  <form action = "editpoints" method = "post">
  <%if(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getClockin()==2){%>
  <%session.setAttribute("editreturn", "clockin"); %>
<button type="submit" name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Edit Points</button>
   <%}else{ %>
  <button type="submit" disabled name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Edit Points</button> 
   <%} %>
    </form> 
</td>
<td>
  <form action = "clockintime" method = "post">
  <%if(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getClockin()==2){%>
<button type="submit"  disabled name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Clock In</button>
   <%}else{%>
   <button type="submit" name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Clock In</button>
   <%} %>
    </form> 
</td>
<td>
  <form action = "clockouttime" method = "post">
  <%if(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getClockin()==2){%>
<button type="submit"  name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Clock Out</button>
   <%}else{%>
   <button type="submit" disabled name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Clock Out</button>
   <%} %>

    </form> 
</td>
<td>
  <form action = "breaktime" method = "post">
  <%if(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getClockin()==2){%>
   <%session.setAttribute("addbreak", "clockin"); %>
<button type="submit" name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Add Break</button>
   <%}else{ %>
   <button type="submit" disabled name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Add Break</button>
   <%} %>
    </form> 
</td>
<td>
  <form action = "edittime" method = "post">
  <%if(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getClockin()==2){%>
  <%session.setAttribute("editreturn", "clockin"); %>
<button type="submit" name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Edit Times</button>
   <%}else{ %>
  <button type="submit" disabled name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Edit Times</button> 
   <%} %>
    </form> 
</td>
<td>
  <form action = "shifts" method = "post">
   <%session.setAttribute("shiftinfo", "clockin"); %>
   <button type="submit" name="upvote" value=<% out.println(((ArrayList<ShiftInfo>)session.getAttribute("clockin")).get(x).getShift().getShift());%>>Shift Info</button>
 </form> 
</td>
</tr>
<%} %>
</table>

</div>
</body>
</html>
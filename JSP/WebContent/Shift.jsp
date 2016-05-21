<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.timeoffrequest,user.Shift,user.PositionInfo,user.ShiftInfo,user.Break;" %>
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
<td>Status</td>
</tr>

<tr>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getID());%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getFirstname());%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getLastname());%>
</td>
<td>
<%out.println((((ShiftInfo)session.getAttribute("shift")).getShift().getStartdate().getMonth()+1)+"/"+(((ShiftInfo)session.getAttribute("shift")).getShift().getStartdate().getDate()+"/"+(((ShiftInfo)session.getAttribute("shift")).getShift().getStartdate().getYear()+1900)));%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getStarttime());%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getEndtime());%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getLocation());%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getDept());%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getSublocation());%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getPosition());%>
</td>
<td>

<%for(int y=0;y<((ShiftInfo)session.getAttribute("shift")).getShift().getTraining().size();y++){
	out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getTraining().get(y).getTraining());
}
%> 
</td>
<td>
<%if(((ShiftInfo)session.getAttribute("shift")).getShift().getActStarttime()==null){%>
<%out.println("N/A");
}
else{
out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getActStarttime());
}
%>
</td>
<td>
<%if(((ShiftInfo)session.getAttribute("shift")).getShift().getActEndtime()==null){%>
<%out.println("N/A");}
else{
	//out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getActEndtime());
}
%>
</td>
<td>
<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getClockin());%>
</td>


</tr>
</table>
</div>
<div id=break>
<table border="1">
<tr>
<td>Date</td>
<td>Start Time</td>
<td>End Time</td>
<td>Delete Break</td>
<td>Edit Break</td>
</tr>

<tr>
<%for(int y=0;y<((ShiftInfo)session.getAttribute("shift")).getShift().getBreak().size();y++){%>
	<tr>
	<td>
	<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getBreak().get(y).getDatestart());%>
	</td>
	<td>
	<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getBreak().get(y).getTimestart());%>
	</td>
	
	<td>
	<%out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getBreak().get(y).getTimeend());%>
	</td>
	<td>
	<form action = "deletebreak" method = "post">
   <button type="submit" name="upvote" value=<% out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getBreak().get(y).getid());%>>Delete Break</button>
 </form> 
 </td>
 	<td>
	<form action = "editbreak" method = "post">
	  <%session.setAttribute("editreturn", "shift"); %>
   <button type="submit" name="upvote" value=<% out.println(((ShiftInfo)session.getAttribute("shift")).getShift().getBreak().get(y).getid());%>>Edit Break</button>
 </form> 
 </td>
	</tr>
	<% 
}
%> 

<td></td>
</tr>
</table>
</div>
</body>
</html>
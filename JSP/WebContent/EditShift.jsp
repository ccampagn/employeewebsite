<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.UserData,user.ShiftInfo,user.Shift;" %>
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
<form name="editemployee" method="post" action="editshift">
<table>
<tr><td>ID: </td><td><input type="text" name="id" value="<%=((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getID()%>"></td></tr>
<tr><td>Location: </td><td><input type="text" name="location" value="<%=((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getLocation()%>"></td></tr>
<tr><td>Dept: </td><td><input type="text" name="dept" value="<%=((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getDept()%>"></td></tr>
<tr><td>Sublocation: </td><td><input type="text" name="sublocation" value="<%=((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getSublocation()%>"></td></tr>
<tr><td>Position: </td><td><input type="text" name="position" value="<%=((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getPosition()%>"></td></tr>
<tr><td>Start Date:</td><td> <input type="text" name="startdate" value="<%=(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getEnddate().toString()).substring(5,7)+"/"+(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getStartdate().toString()).substring(8,10)+"/"+(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getStartdate().toString()).substring(0,4)%>"></td></tr>
<tr><td>End Date: </td><td><input type="text" name="enddate" value="<%=(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getStartdate().toString()).substring(5,7)+"/"+(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getEnddate().toString()).substring(8,10)+"/"+(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getEnddate().toString()).substring(0,4)%>"></td></tr>
<tr><td>Start Time:</td><td> <input type="text" name="starttime" value="<%=(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getStarttime()).substring(0,5)%>"></td></tr>
<tr><td>End Time: </td><td><input type="text" name="endtime" value="<%=(((ArrayList<ShiftInfo>)session.getAttribute("schedulesearch")).get(Integer.parseInt(session.getAttribute("shiftnumber").toString())).getShift().getEndtime()).substring(0,5)%>"></td></tr>
<tr><td>Training: </td><td><input type="text" name="training" value="<%=(session.getAttribute("training"))%>"></td></tr>
<tr><td><input type="submit" value="Add" /></td></tr>
</table>
</form>
</div>
</body>
</html>
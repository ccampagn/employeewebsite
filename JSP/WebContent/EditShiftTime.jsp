<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.UserData,user.ShiftInfo,user.Break;" %>
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
<form name="Editshifttimechange" method="post" action="Editshifttimechange">
<table>

<tr><td>Start Date:</td><td> <input type="text" name="startdate" value="<%=(((Break)session.getAttribute("editshifttime")).getDatestart()).substring(5,7)+"/"+(((Break)session.getAttribute("editshifttime")).getDatestart()).substring(8,10)+"/"+(((Break)session.getAttribute("editshifttime")).getDatestart()).substring(0,4)%>" ></td></tr>
<tr><td>Start Time: </td><td><input type="text" name="starttime" value="<%=(((Break)session.getAttribute("editshifttime")).getTimestart()).substring(0,5)%>"></td></tr>
<%if((((Break)session.getAttribute("editshifttime")).getDateend())!=null){ %>
<tr><td>End Date:</td><td> <input type="text" name="enddate" value="<%=(((Break)session.getAttribute("editshifttime")).getDateend()).substring(5,7)+"/"+(((Break)session.getAttribute("editshifttime")).getDateend()).substring(8,10)+"/"+(((Break)session.getAttribute("editshifttime")).getDateend()).substring(0,4)%>" ></td></tr>
<tr><td>End Time: </td><td><input type="text" name="endtime" value="<%=(((Break)session.getAttribute("editshifttime")).getTimeend()).substring(0,5)%>"></td></tr>
<%} %>
<tr><td><input type="submit" value="Add" /></td></tr>
</table>
${error}
</form>
</div>
</body>
</html>
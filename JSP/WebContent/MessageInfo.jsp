<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.UserData,user.Message;" %>
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
			
		${error}
		<div id="message">
		<table border="1">
	<tr>Messages </tr>	
<tr>
<td>First Name</td>
<td>Last Name</td>
<td>Message</td>
<td>Date</td>
<td>Time</td>
</tr>

<% for(int x=0;x<((ArrayList<Message>)session.getAttribute("message")).size();x++){%>
	<tr>
	
	<td>
	<%out.println(((ArrayList<Message>)session.getAttribute("message")).get(x).getFirstname());%>
	</td>
	<td>
	<%out.println(((ArrayList<Message>)session.getAttribute("message")).get(x).getLastname());%>
	</td>
	<td>
	<%out.println(((ArrayList<Message>)session.getAttribute("message")).get(x).getMessage());%>
	</td>
	<td>
	<%out.println((((ArrayList<Message>)session.getAttribute("message")).get(x).getDate()).substring(5,7)+"/"+(((ArrayList<Message>)session.getAttribute("message")).get(x).getDate()).substring(8,10)+"/"+(((ArrayList<Message>)session.getAttribute("message")).get(x).getDate()).substring(0,4));%>
	</td>
	<td>
	<%out.println((((ArrayList<Message>)session.getAttribute("message")).get(x).getTime()).substring(0,5));%>
	</td>



	</tr>
<% }%>
</table>
		</div>
			
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.timeoffrequest;" %>

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
<div id="torupdate">
<form name="torForm" method="post" action="timeoffrequest">

Start Month:

<select name="month"  >


<option value="1">January</option>
<option value="2">February</option>
<option value="3">March</option>
<option value="4">April</option>
<option value="5">May</option>
<option value="6">June</option>
<option value="7">July</option>
<option value="8">August</option>
<option value="9">September</option>
<option value="10">October</option>
<option value="11">November</option>
<option value="12">December</option>





</select> 

Day:
<select name="day">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>

</select>


Year: <INPUT TYPE=TEXT NAME=year SIZE=20><br>
End Month:
<select name="month2"  >


<option value="1">January</option>
<option value="2">February</option>
<option value="3">March</option>
<option value="4">April</option>
<option value="5">May</option>
<option value="6">June</option>
<option value="7">July</option>
<option value="8">August</option>
<option value="9">September</option>
<option value="10">October</option>
<option value="11">November</option>
<option value="12">December</option>





</select> 
Day:
<select name="day2">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>

</select>


Year: <INPUT TYPE=TEXT NAME=year2 SIZE=20><br>
Start Time:
<select name="starttime"  >


<option value="00:00">12 AM</option>
<option value="01:00">1 AM</option>
<option value="02:00">2 AM</option>
<option value="03:00">3 AM</option>
<option value="04:00">4 AM</option>
<option value="05:00">5 AM</option>
<option value="06:00">6 AM</option>
<option value="07:00">7 AM</option>
<option value="08:00">8 AM</option>
<option value="09:00">9 AM</option>
<option value="10:00">10 AM</option>
<option value="11:00">11 AM</option>
<option value="12:00">12 PM</option>
<option value="13:00">1 PM</option>
<option value="14:00">2 PM</option>
<option value="15:00">3 PM</option>
<option value="16:00">4 PM</option>
<option value="17:00">5 PM</option>
<option value="18:00">6 PM</option>
<option value="19:00">7 PM</option>
<option value="20:00">8 PM</option>
<option value="21:00">9 PM</option>
<option value="22:00">10 PM</option>
<option value="23:00">11 PM</option>
</select> 
End Time:
<select name="endtime"  >


<option value="23:59">12 AM</option>
<option value="01:00">1 AM</option>
<option value="02:00">2 AM</option>
<option value="03:00">3 AM</option>
<option value="04:00">4 AM</option>
<option value="05:00">5 AM</option>
<option value="06:00">6 AM</option>
<option value="07:00">7 AM</option>
<option value="08:00">8 AM</option>
<option value="09:00">9 AM</option>
<option value="10:00">10 AM</option>
<option value="11:00">11 AM</option>
<option value="12:00">12 PM</option>
<option value="13:00">1 PM</option>
<option value="14:00">2 PM</option>
<option value="15:00">3 PM</option>
<option value="16:00">4 PM</option>
<option value="17:00">5 PM</option>
<option value="18:00">6 PM</option>
<option value="19:00">7 PM</option>
<option value="20:00">8 PM</option>
<option value="21:00">9 PM</option>
<option value="22:00">10 PM</option>
<option value="23:00">11 PM</option>

</select>
<br> 
Reason: <INPUT TYPE=TEXT NAME=reason SIZE=40>
<P><input type="submit" value="Update" />
<BR>
<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true){
				%><a id=menu href="alltimeoffrequest">All Time Off Request</a>			
				<% 
			}%>
${error}
</FORM>
</div>
<div id="tor">
<table border="1">
<tr>
<td>State Date</td>
<td>End Date</td>
<td>Start Time</td>
<td>End Time</td>
<td>Reason</td>
<td>Status</td>
<td>Delete</td>
</tr>
<% for(int x=0;x<((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).size();x++){%>
<tr>

<td>
<%out.println(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getMonth()+"/"+((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getDay()+"/"+((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getYear());%> 
</td>
<td>
<%out.println(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getMonth2()+"/"+((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getDay2()+"/"+((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getYear2());%> 
</td>
<td>
<%out.println(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getStartTime());%> 
</td>
<td>
<%out.println(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getEndTime());%> 
</td>
<td>
<%out.println(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getReason());%> 
</td>
<td>
<%if(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getStatus()==2){%>
<%out.println("Yes");%> 
<%} %>
<%if(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getStatus()==1){%>
<%out.println("No");%> 
<%} %>
<%if(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getStatus()==0){%>
<%out.println("Pending");%> 
<%} %>
</td>
<td>
  <form action = "deletetimeoffrequest" method = "post">
   <button type="submit" name="upvote" value=<% out.println(((ArrayList<timeoffrequest>)session.getAttribute("timeoff")).get(x).getId());%>>Delete</button>
    </form> 
</td>
</tr>
<%} %>

</table>
</div>
</body>
</html>
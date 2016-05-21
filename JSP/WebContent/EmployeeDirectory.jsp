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
<div id=search>
<form name="employeedirectory" method="post" action="EmployeeDirectory">
<table>
<tr>
<td>Search:<INPUT TYPE=TEXT NAME=field SIZE=20></td>


<td>
<select name="search">
<option value="0">All</option>
<option value="1">ID</option>
<option value="2">Username</option>
<option value="3">First Name</option>
<option value="4">Last Name</option>
<option value="5">sex</option>
<option value="6">address</option>
<option value="7">city</option>
<option value="8">state</option>
<option value="9">zip code</option>
<option value="10">country</option>
<option value="11">Phone Number</option>
<option value="12">Email</option>
<option value="13">Birthday</option>
<option value="14">Location</option>
<option value="15">Dept</option>
<option value="16">Position</option>
<option value="17">Comment</option>
<option value="18">Training</option>
</select>
</td>
</tr>
</table>
<input type="submit" value="Search" />
${error}
</form>




</div>
<div id=directory>
<%//ArrayList<WorkInfo> user=(ArrayList<WorkInfo>)session.getAttribute("search");

%>
<form name="profile" method="get" action="profile">
<table border="1">
<tr>
<td>ID</td>
<td>Username</td>
<td>First Name</td>
<td>Last Name</td>
<td>Sex</td>
<td>Address</td>
<td>City</td>
<td>State</td>
<td>Zip Code</td>
<td>Country</td>
<td>Phone Number</td>
<td>Email</td>
<td>Birthday</td>
<td>Location</td>
<td>Sub Location</td>
<td>Dept</td>
<td>Position</td>
<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true)
	{%>
<td>Comment</td>
<%} %>
<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true)
	{%>
	<td>Points</td>
<td>Profile</td>
<%} %>

<td>Email</td>
<td>Message</td>
<td>Training</td>
</tr>

<% for(int x=0;x<((ArrayList<WorkInfo>)session.getAttribute("search")).size();x++){%>
	<tr>
	<td>
	
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getID());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPassword().getUsername());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getFirstname());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getLastname());%>
	</td>
	<td>
	<%if(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getSex()==true){
	
	out.println("Male");
	}else{
		out.println("Female");
	}%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getAddress());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getCity());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getState());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getZipcode());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getCountry());%>
	</td>
		<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getPhonenumber());%>
	</td>
		<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getEmail());%>
	</td>
		<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getMonth()+"/"+((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getDay()+"/"+((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getYear());%>
	</td>
		<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getLocation());%>
	</td>
	<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getSublocation());%>
	</td>
		<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getDept());%>
	</td>
		<td>
	<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getPosition());%>
	</td>
	<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true)
	{
		%>
		<td>
	<%
	
	out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getComment());
	%>
	</td>	
			<td>
	<%
	
	out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getPoints());
	%>
	</td>	
<td>
<form action = "profile" method = "get">
<button type="submit" name="profile" value=<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getID());%>">Update</a>
    </form> 
</td>
<%}%>
<td>
<form action = "email" method = "post">
<button type="submit" name="email" value=<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getID());%>">Email</a>
    </form> 
</td>
<td>
<form action = "messageid" method = "post">
<button type="submit" name="message" value=<%out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getUserData().getID());%>">Message</a>
    </form> 
</td>
<td>

<%for(int y=0;y<((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getTraining().size();y++){
	out.println(((ArrayList<WorkInfo>)session.getAttribute("search")).get(x).getPositionInfo().getTraining().get(y).getTraining());
}
%>
</td>

	</tr>
	
<% }%>

</table>
</form>
</div>

</body>
</html>
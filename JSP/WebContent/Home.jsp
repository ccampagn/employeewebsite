<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.UserData,user.Message,user.NewStories;" %>
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
<td>Delete</td>
<td>Reply</td>
<td>Message Info</td>
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
	<td>
<form action = "deletemessage" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Message>)session.getAttribute("message")).get(x).getID());%>>Delete</button>
    </form> 
    </td>
	<td>
<form action = "replymessage" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Message>)session.getAttribute("message")).get(x).getID());%>>Reply</button>
 
    </form> 
    </td>
	<td>
<form action = "messageinfo" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<Message>)session.getAttribute("message")).get(x).getSeriesID());%>>Message Info</button>
 
    </form> 
    </td>


	</tr>
<% }%>
</table>
		</div>
	<div id="newstories">
		<table border="1">
	<tr>New Stories

     </tr>	
<tr>
<td>Date</td>
<td>Title</td>

</tr>

<% for(int x=0;x<((ArrayList<NewStories>)session.getAttribute("newstories")).size();x++){%>
	<tr>
	
<td>
	<%out.println((((ArrayList<NewStories>)session.getAttribute("newstories")).get(x).getdate()).substring(5,7)+"/"+(((ArrayList<NewStories>)session.getAttribute("newstories")).get(x).getdate()).substring(8,10)+"/"+(((ArrayList<NewStories>)session.getAttribute("newstories")).get(x).getdate()).substring(0,4));%>
	</td>
	<td>
	<%out.println(((ArrayList<NewStories>)session.getAttribute("newstories")).get(x).gettitle());%>
	</td>
	<td>
<form action = "viewnewstories" method = "post">
<button type="submit" name="upvote" value=<% out.println(((ArrayList<NewStories>)session.getAttribute("newstories")).get(x).getid());%>>View News Stories</button>
    </form> 
    </td>
	</tr>
<% }%>
</table>
		<%if(((WorkInfo)session.getAttribute("userinfo")).getPositionInfo().getRight()==true)
	{
		%>
	<form action = "AddNewStories" method = "get">
<button type="submit" name="Add New Stories" >Add New Stories</a>
    </form> 
    <%} %>
		</div>
			
</body>
</html>
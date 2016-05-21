<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,user.UserData,user.WorkInfo,user.UserData;" %>
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
<div id=profile>
<form name="profile" method="post" action="profile">
<table>
<tr>
<td>First Name:</td><td><INPUT TYPE=TEXT NAME=firstname VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getFirstname())%>" SIZE=20></td>
</tr>
<tr>
<td>Last Name:</td><td><INPUT TYPE=TEXT NAME=lastname VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getLastname())%>" SIZE=20></td>
</tr>
<tr>
<td>Sex:</td>
<td>
<select name="sex">
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getSex()==true){ %>
<option selected value="male">Male</option>
<%} else{%>
<option value="male">Male</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getSex()==false){ %>
<option selected value="female">Female</option>
<%} else{%>
<option value="female">Female</option>
<%} %>

</select>
</td>
</tr>
<tr>
<td>Email:</td><td>  <INPUT TYPE=EMAIL NAME=email VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getEmail()) %>" SIZE=20></td>
<tr/>
<tr>
<td>Phone Number:</td><td>  <INPUT TYPE=TEXT NAME=phonenumber VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getPhonenumber()) %>" SIZE=20></td>
<tr/>
<tr>
<td>Address:</td><td>  <INPUT TYPE=TEXT NAME=address VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getAddress()) %>" SIZE=20></td>
<tr/>
<tr>
<td>City:</td><td>  <INPUT TYPE=TEXT NAME=city VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getCity()) %>" SIZE=20></td>
<tr/>
<tr>
<td>State:</td><td> <select name="state">

<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Alabama")){ %>
<option selected value="Alabama">Alabama</option>
<%} else{%>
<option value="Alabama">Alabama</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Alaska")){ %>
<option selected value="Alaska">Alaska</option>
<%} else{%>
<option value="Alaska">Alaska</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Arizona")){ %>
<option selected value="Arizona">Arizona</option>
<%} else{%>
<option value="Arizona">Arizona</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Arkansas")){ %>
<option selected value="Arkansas">Arkansas</option>
<%} else{%>
<option value="Arkansas">Arkansas</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("California")){ %>
<option selected value="California">California</option>
<%} else{%>
<option value="California">California</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Colorado")){ %>
<option selected value="Colorado">Colorado</option>
<%} else{%>
<option value="Colorado">Colorado</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Connecticut")){ %>
<option selected value="Connecticut">Connecticut</option>
<%} else{%>
<option value="Connecticut">Connecticut</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Delaware")){ %>
<option selected value="Delaware">Alabama</option>
<%} else{%>
<option value="Delaware">Delaware</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Florida")){ %>
<option selected value="Florida">Florida</option>
<%} else{%>
<option value="Florida">Florida</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Georgia")){ %>
<option selected value="Georgia">Georgia</option>
<%} else{%>
<option value="Georgia">Georgia</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Hawaii")){ %>
<option selected value="Hawaii">Hawaii</option>
<%} else{%>
<option value="Hawaii">Hawaii</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Idaho")){ %>
<option selected value="Idaho">Idaho</option>
<%} else{%>
<option value="Idaho">Idaho</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Illinois")){ %>
<option selected value="Illinois">Illinois</option>
<%} else{%>
<option value="Illinois">Illinois</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Indiana")){ %>
<option selected value="Indiana">Indiana</option>
<%} else{%>
<option value="Indiana">Indiana</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Iowa")){ %>
<option selected value="Iowa">Iowa</option>
<%} else{%>
<option value="Iowa">Iowa</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Kansas")){ %>
<option selected value="Kansas">Kansas</option>
<%} else{%>
<option value="Kansas">Kansas</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Kentucky")){ %>
<option selected value="Kentucky">Kentucky</option>
<%} else{%>
<option value="Kentucky">Kentucky</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Louisiana")){ %>
<option selected value="Louisiana">Louisiana</option>
<%} else{%>
<option value="Louisiana">Louisiana</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Maine")){ %>
<option selected value="Maine">Maine</option>
<%} else{%>
<option value="Maine">Maine</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Maryland")){ %>
<option selected value="Maryland">Maryland</option>
<%} else{%>
<option value="Maryland">Maryland</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Massachusetts")){ %>
<option selected value="Massachusetts">Massachusetts</option>
<%} else{%>
<option value="Massachusetts">Massachusetts</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Michigan")){ %>
<option selected value="Michigan">Michigan</option>
<%} else{%>
<option value="Michigan">Michigan</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Minnesota")){ %>
<option selected value="Minnesota">Minnesota</option>
<%} else{%>
<option value="Minnesota">Minnesota</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Mississippi")){ %>
<option selected value="Mississippi">Mississippi</option>
<%} else{%>
<option value="Mississippi">Mississippi</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Missouri")){ %>
<option selected value="Missouri">Missouri</option>
<%} else{%>
<option value="Missouri">Missouri</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Montana")){ %>
<option selected value="Montana">Montana</option>
<%} else{%>
<option value="Montana">Montana</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Nebraska")){ %>
<option selected value="Nebraska">Nebraska</option>
<%} else{%>
<option value="Nebraska">Nebraska</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Nevada")){ %>
<option selected value="Nevada">Nevada</option>
<%} else{%>
<option value="Nevada">Nevada</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("New Hampshire")){ %>
<option selected value="New Hampshire">New Hampshire</option>
<%} else{%>
<option value="New Hampshire">New Hampshire</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("New Jersey")){ %>
<option selected value="New Jersey">New Jersey</option>
<%} else{%>
<option value="New Jersey">New Jersey</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("New Mexico")){ %>
<option selected value="New Mexico">New Mexico</option>
<%} else{%>
<option value="New Mexico">New Mexico</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("New York")){ %>
<option selected value="New York">New York</option>
<%} else{%>
<option value="New York">New York</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("North Carolina")){ %>
<option selected value="North Carolina">North Carolina</option>
<%} else{%>
<option value="North Carolina">North Carolina</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("North Dakota")){ %>
<option selected value="North Dakota">North Dakota</option>
<%} else{%>
<option value="North Dakota">North Dakota</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Ohio")){ %>
<option selected value="Ohio">Ohio</option>
<%} else{%>
<option value="Ohio">Ohio</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Oklahoma")){ %>
<option selected value="Oklahoma">Oklahoma</option>
<%} else{%>
<option value="Oklahoma">Oklahoma</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Oregon")){ %>
<option selected value="Oregon">Oregon</option>
<%} else{%>
<option value="Oregon">Oregon</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Pennsylvania")){ %>
<option selected value="Pennsylvania">Pennsylvania</option>
<%} else{%>
<option value="Pennsylvania">Pennsylvania</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Rhode Island")){ %>
<option selected value="Rhode Island">Rhode Island</option>
<%} else{%>
<option value="Rhode Island">Rhode Island</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("South Carolina")){ %>
<option selected value="South Carolina">South Carolina</option>
<%} else{%>
<option value="South Carolina">South Carolina</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("South Dakota")){ %>
<option selected value="South Dakota">South Dakota</option>
<%} else{%>
<option value="South Dakota">South Dakota</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Tennessee")){ %>
<option selected value="Tennessee">Tennessee</option>
<%} else{%>
<option value="Tennessee">Tennessee</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Texas")){ %>
<option selected value="Texas">Texas</option>
<%} else{%>
<option value="Texas">Texas</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Utah")){ %>
<option selected value="Utah">Utah</option>
<%} else{%>
<option value="Utah">Utah</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Vermont")){ %>
<option selected value="Vermont">Vermont</option>
<%} else{%>
<option value="Vermont">Vermont</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Virginia")){ %>
<option selected value="Virginia">Virginia</option>
<%} else{%>
<option value="Virginia">Virginia</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Washington")){ %>
<option selected value="Washington">Washington</option>
<%} else{%>
<option value="Washington">Washington</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("West Virginia")){ %>
<option selected value="West Virginia">West Virginia</option>
<%} else{%>
<option value="West Virginia">West Virginia</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Wisconsin")){ %>
<option selected value="Wisconsin">Wisconsin</option>
<%} else{%>
<option value="Wisconsin">Wisconsin</option>
<%} %>
<%if((((WorkInfo)session.getAttribute("change")).getUserData().getState()).equals("Wyoming")){ %>
<option selected value="Wyoming">Wyoming</option>
<%} else{%>
<option value="Wyoming">Wyoming</option>
<%} %>
</select>
</td>
</tr>
<tr>
<td>Country:</td><td> <INPUT TYPE=TEXT NAME=country VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getCountry())%>" SIZE=20></td>
</tr>
<tr>
<td>Zip Code:</td><td> <INPUT TYPE=TEXT NAME=zipcode VALUE="<%=(((WorkInfo)session.getAttribute("change")).getUserData().getZipcode()) %>" SIZE=20></td>
</tr>
<tr>
<td>Month:</td>

<td>
<select name="month"  >

<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==1){ %>
<option selected value="1">January</option>
<%} else{%>
<option value="1">January</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==2){ %>
<option selected value="2">February</option>
<%} else{%>
<option value="2">February</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==3){ %>
<option selected value="3">March</option>
<%} else{%>
<option value="3">March</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==4){ %>
<option selected value="4">April</option>
<%} else{%>
<option value="4">April</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==5){ %>
<option selected value="5">May</option>
<%} else{%>
<option value="5">May</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==6){ %>
<option selected value="6">June</option>
<%} else{%>
<option value="6">June</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==7){ %>
<option selected value="7">July</option>
<%} else{%>
<option value="7">July</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==8){ %>
<option selected value="8">August</option>
<%} else{%>
<option value="8">August</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==9){ %>
<option selected value="9">September</option>
<%} else{%>
<option value="9">September</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==10){ %>
<option selected value="10">October</option>
<%} else{%>
<option value="10">October</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==11){ %>
<option selected value="11">November</option>
<%} else{%>
<option value="11">November</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getMonth()==12){ %>
<option selected value="12">December</option>
<%}  else{%>
<option value="12">December</option>
<%}
 %>




</select> 

</td>
</tr>
<tr>
<td>Day:</td>
<td>
<select name="day">
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==1){ %>
<option selected value="1">1</option>
<%} else{%>
<option value="1">1</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==2){ %>
<option selected value="2">2</option>
<%} else{%>
<option value="2">2</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==3){ %>
<option selected value="3"></option>
<%} else{%>
<option value="3">3</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==4){ %>
<option selected value="4">4</option>
<%} else{%>
<option value="4">4</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==5){ %>
<option selected value="5">5</option>
<%} else{%>
<option value="5">5</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==6){ %>
<option selected value="6">6</option>
<%} else{%>
<option value="6">6</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==7){ %>
<option selected value="7">7</option>
<%} else{%>
<option value="7">7</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==8){ %>
<option selected value="8">8</option>
<%} else{%>
<option value="8">8</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==9){ %>
<option selected value="9">9</option>
<%} else{%>
<option value="9">9</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==10){ %>
<option selected value="10">10</option>
<%} else{%>
<option value="10">10</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==11){ %>
<option selected value="11">11</option>
<%} else{%>
<option value="11">11</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==12){ %>
<option selected value="12">12</option>
<%} else{%>
<option value="12">12</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==13){ %>
<option selected value="13"></option>
<%} else{%>
<option value="13">13</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==14){ %>
<option selected value="14">14</option>
<%} else{%>
<option value="14">14</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==15){ %>
<option selected value="15">15</option>
<%} else{%>
<option value="15">15</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==16){ %>
<option selected value="16">16</option>
<%} else{%>
<option value="16">16</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==17){ %>
<option selected value="17">17</option>
<%} else{%>
<option value="17">17</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==18){ %>
<option selected value="18">18</option>
<%} else{%>
<option value="18">18</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==19){ %>
<option selected value="19">19</option>
<%} else{%>
<option value="19">19</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==20){ %>
<option selected value="20">20</option>
<%} else{%>
<option value="20">20</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==21){ %>
<option selected value="21">21</option>
<%} else{%>
<option value="21">21</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==22){ %>
<option selected value="22">22</option>
<%} else{%>
<option value="22">22</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==23){ %>
<option selected value="23"></option>
<%} else{%>
<option value="23">23</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==24){ %>
<option selected value="24">24</option>
<%} else{%>
<option value="24">24</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==25){ %>
<option selected value="25">25</option>
<%} else{%>
<option value="25">25</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==26){ %>
<option selected value="26">26</option>
<%} else{%>
<option value="26">26</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==27){ %>
<option selected value="27">27</option>
<%} else{%>
<option value="27">27</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==28){ %>
<option selected value="28">28</option>
<%} else{%>
<option value="28">28</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==29){ %>
<option selected value="29">29</option>
<%} else{%>
<option value="29">29</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==30){ %>
<option selected value="30">30</option>
<%} else{%>
<option value="30">30</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getUserData().getDay()==31){ %>
<option selected value="31">31</option>
<%} else{%>
<option value="31">31</option>
<%} %>
</select>
</td>
</tr>
<tr>


<td>Year:</td><td> <INPUT TYPE=TEXT NAME=year VALUE="<%=((WorkInfo)session.getAttribute("change")).getUserData().getYear()%>" SIZE=20 ></td>

</tr>
<tr>
<td>Company:</td><td> <INPUT TYPE=TEXT NAME=company VALUE="<%=((WorkInfo)session.getAttribute("change")).getPositionInfo().getCompany() %>" SIZE=20 ></td>
</tr>
<tr>
<td>Dept:</td><td> <INPUT TYPE=TEXT NAME=dept VALUE="<%=((WorkInfo)session.getAttribute("change")).getPositionInfo().getDept() %>" SIZE=20></td>
</tr>
<tr>
<td>Location:</td><td> <INPUT TYPE=TEXT NAME=location VALUE="<%=((WorkInfo)session.getAttribute("change")).getPositionInfo().getLocation() %>" SIZE=20></td>
</tr>
<tr>
<td>sublocation:</td><td> <INPUT TYPE=TEXT NAME=sublocation VALUE="<%=((WorkInfo)session.getAttribute("change")).getPositionInfo().getSublocation()%>" SIZE=20></td>
</tr>
<tr>
<td>Position:</td><td> <INPUT TYPE=TEXT NAME=position VALUE="<%=((WorkInfo)session.getAttribute("change")).getPositionInfo().getPosition()%>" SIZE=20></td>
</tr>
<tr>
<td>Comment:</td><td> <INPUT TYPE=TEXT NAME=comment VALUE="<%=((WorkInfo)session.getAttribute("change")).getPositionInfo().getComment()%>" SIZE=20></td>
</tr>
<tr>
<td>Admin:</td>

<td>
<select name="right">
<%if(((WorkInfo)session.getAttribute("change")).getPositionInfo().getRight()==true){ %>
<option selected value="true">True</option>
<%} else{%>
<option value="true">True</option>
<%} %>
<%if(((WorkInfo)session.getAttribute("change")).getPositionInfo().getRight()==false){ %>
<option selected value="false">False</option>
<%} else{%>
<option value="false">False</option>
<%} %>


</select>
</td>









</tr>
</table>
<input type="submit" value="Update" />
${error}
</form>
</div>
</body>
</html>
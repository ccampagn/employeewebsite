<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="stylesheet.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id=addcompany>
<form name="addcompany" method="post" action="AddCompany">
<table>
<tr>
<td>Username:</td><td><INPUT TYPE=TEXT NAME=username SIZE=20></td>
</tr>
<tr>
<tr>
<td>Password:</td><td><INPUT TYPE=PASSWORD NAME=password SIZE=20></td>
</tr>
<tr>
<td>Confirm Password:</td><td><INPUT TYPE=PASSWORD NAME=password2 SIZE=20></td>
</tr>
<tr>
<td>First Name:</td><td><INPUT TYPE=TEXT NAME=firstname SIZE=20></td>
</tr>
<tr>
<td>Last Name:</td><td><INPUT TYPE=TEXT NAME=lastname SIZE=20></td>
</tr>
<tr>
<td>Email:</td><td><INPUT TYPE=EMAIL NAME=email SIZE=20></td>
</tr>
<tr>
<td>Phone Number:</td><td><INPUT TYPE=TEXT NAME=phonenumber SIZE=20></td>
</tr>
<tr>
<td>Address:</td><td><INPUT TYPE=TEXT NAME=address SIZE=20></td>
</tr>
<tr>
<td>City:</td><td><INPUT TYPE=TEXT NAME=city SIZE=20></td>
</tr>
<tr>
<td>State:</td><td> <select name="state">
<option value="Alabama">Alabama</option>
<option value="Alaska">Alaska</option>
<option value="Arizona">Arizona</option>
<option value="Arkansas">Arkansas</option>
<option value="California">California</option>
<option value="Colorado">Colorado</option>
<option value="Connecticut">Connecticut</option>
<option value="Delaware">Delaware</option>
<option value="Florida">Florida</option>
<option value="Georgia">Georgia</option>
<option value="Hawaii">Hawaii</option>
<option value="Idaho">Idaho</option>
<option value="Illinois">Illinois</option>
<option value="Indiana">Indiana</option>
<option value="Iowa">Iowa</option>
<option value="Kansas">Kansas</option>
<option value="Kentucky">Kentucky</option>
<option value="Louisiana">Louisiana</option>
<option value="Maine">Maine</option>
<option value="Maryland">Maryland</option>
<option value="Massachusetts">Massachusetts</option>
<option value="Michigan">Michigan</option>
<option value="Minnesota">Minnesota</option>
<option value="Mississippi">Mississippi</option>
<option value="Missouri">Missouri</option>
<option value="Montana">Montana</option>
<option value="Nebraska">Nebraska</option>
<option value="Nevada">Nevada</option>
<option value="New Hampshire">New Hampshire</option>
<option value="New Jersey">New Jersey</option>
<option value="New Mexico">New Mexico</option>
<option value="New York">New York</option>
<option value="North Carolina">North Carolina</option>
<option value="North Dakota">North Dakota</option>
<option value="Ohio">Ohio</option>
<option value="Oklahoma">Oklahoma</option>
<option value="Oregon">Oregon</option>
<option value="Pennsylvania">Pennsylvania</option>
<option value="Rhode Island">Rhode Island</option>
<option value="South Carolina">South Carolina</option>
<option value="South Dakota">South Dakota</option>
<option value="Tennessee">Tennessee</option>
<option value="Texas">Texas</option>
<option value="Utah">Utah</option>
<option value="Vermont">Vermont</option>
<option value="Virginia">Virginia</option>
<option value="Washington">Washington</option>
<option value="West Virginia">West Virginia</option>
<option value="Wisconsin">Wisconsin</option>
<option value="Wyoming">Wyoming</option>
</select>
</td>
</tr>
<tr>
<td>Country:</td><td><INPUT TYPE=TEXT NAME=country SIZE=20></td>
</tr>
<tr>
<td>Zip Code:</td><td><INPUT TYPE=TEXT NAME=zipcode SIZE=20></td>
</tr>
<tr>
<td>Month:</td>
<td>
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
</td>
</tr>
<tr>
<td>Day:</td>
<td>
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
</td>
</tr>
<tr>
<td>Year:</td><td> <INPUT TYPE=TEXT NAME=year SIZE=20 ></td>
</tr>
<tr>
<td>Sex:</td>
<td>
<select name="sex">
<option value="male">Male</option>
<option value="female">Female</option>
</select>
</td>
</tr>
<tr>
<td>Company:</td><td> <INPUT TYPE=TEXT NAME=company SIZE=20 ></td>
</tr>
<tr>
<td>Dept:</td><td> <INPUT TYPE=TEXT NAME=dept SIZE=20 ></td>
</tr>
<tr>
<td>Location:</td><td> <INPUT TYPE=TEXT NAME=location SIZE=20 ></td>
</tr>
<tr>
<td>Sub Location:</td><td> <INPUT TYPE=TEXT NAME=sublocation SIZE=20 ></td>
</tr>
<tr>
<td>Position:</td><td> <INPUT TYPE=TEXT NAME=position SIZE=20 ></td>
</tr>
<tr>
<td>Comment:</td><td> <INPUT TYPE=TEXT NAME=comment SIZE=20 ></td>
</tr>
</table>
<input type="submit" value="Add" />
</form>
${error}
</div>
</body>
</html>
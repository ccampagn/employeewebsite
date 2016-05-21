
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,java.sql.*" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="stylesheet.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id=welcome>
Welcome To The <br>Employee Portal!
</div>

<div id=login>  
<form name="loginForm" method="post" action="index">

Please Sign In.<br> 

Username:<INPUT TYPE=TEXT NAME=username SIZE=20><br>
Password:<INPUT TYPE=PASSWORD NAME=password SIZE=20>
<P><input type="submit" value="Log In" /><br>
${error}<br>
<a id=menu href="forgetpassword">Forget Password</a>
<a  id=menu href="AddCompany">Add Company</a>
</FORM>
</div>	

</body>
</html>
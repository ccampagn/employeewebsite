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
<div id="forgetpassword">
Please enter you email and <br>we'll sent you a new password. 
<form name="forgetpassword" method="post" action="forgetpassword">
Email:<INPUT TYPE=TEXT NAME=email SIZE=20><br>
<P><input type="submit" value="Submit" />
<br>
${error}
</FORM>
</div>
</body>
</html>
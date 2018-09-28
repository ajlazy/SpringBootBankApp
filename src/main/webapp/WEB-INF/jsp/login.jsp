<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="home.jsp" %>
</head>
<body style="text-align:left">
<br>
	<h1>Login</h1>
	<form:form action="login.do" method="post" modelAttribute="customer">
		<br><br>
		<form:label path="customerEmail" >Email Id: </form:label>
		<form:input path="customerEmail"/>
		<br> <br> 
		<form:label  path="customerPassword">Password: </form:label>
		<form:input type="password" path="customerPassword" />
		<br> <br>
		<input type="submit" value="Login"/>
	</form:form>
</body>
</html>
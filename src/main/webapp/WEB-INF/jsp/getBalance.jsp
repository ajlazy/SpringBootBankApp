<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="home.jsp" %>
</head>
<body>
<br><br><br><br><br>
<h4  style="text-align:center" >Your balance is ${sessionScope.customer.customerAccount.accountBalance}!!</h4>
</body>
</html>
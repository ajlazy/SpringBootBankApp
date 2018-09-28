<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
            <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Money</title>
<%@ include file="home.jsp" %>
</head>
<body  bgcolour="CornFlowerBlue" style="text-align:left">
<br><br>
<h1>transaction...</h1>
<form action="transferAmount.do" method="post">
	<br><br><br>
	<label>From Account</label>
		<input type="text" value="${customer.customerAccount.accountId}" name="fromAccount"  readonly="readonly" />
		<br> <br>
		<label>to Account</label>
		<input type="text" name="toAccount" required="required"/>
		<br> <br>
		<label>Amount</label>
		<input type="number"  name="amount" required="required"/>
		<br> <br>
		<input type="reset" value="Clear"/>
	<input type="submit" value="Submit"/>
	<br>
	</form>
</body>
</html>
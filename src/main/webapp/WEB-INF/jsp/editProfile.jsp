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
<body>

<h1>Edit Profile</h1>
<br><br>
<form:form action="editProfile.do" method="post" modelAttribute="customer">
<form:label path="customerId">customer Id</form:label>
<form:input readonly="readonly" path="customerId"/>
<br>
<form:label path="customerName">Enter Name</form:label>
<form:input required="required" path="customerName"/>
<br>
<form:label path="customerPassword">Password</form:label>
<form:input readonly="readonly"  path="customerPassword"/>
<br>
<form:label path="customerEmail">Enter Email</form:label>
<form:input required="required" path="customerEmail"/>
<br>
<form:label path="customerAddress">Enter Address</form:label>
<form:input  required="required" path="customerAddress"/>
<br>
<form:label path="customerDateOfBirth">Enter Date Of birth</form:label>
<form:input  required="required" path="customerDateOfBirth"/>
		<input type="submit" value="Update"/>

</form:form>

</body>
</html>
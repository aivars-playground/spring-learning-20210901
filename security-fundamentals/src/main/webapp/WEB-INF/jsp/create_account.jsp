<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create Account</title>
    <link href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form:form modelAttribute="account" method="post">
    <form:errors path="*" element="div" />
    <il>
        <li /><input type="text" name="username" placeholder="username" />
        <li /><input type="text" name="firstName" placeholder="First Name" />
        <li /><input type="text" name="lastName" placeholder="Last Name" />
        <li /><input type="email" name="email" placeholder="Email" />
        <li /><input type="password" name="password" placeholder="Password" />
        <li /><input type="password" name="matchingPassword" placeholder="Retype Password" />
        <li /><input type="submit" role="button" value="CREATE">
    </il>
</form:form>
</body>
</html>

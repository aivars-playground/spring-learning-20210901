<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <link href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<c:if test="${not empty param.logout}">
    Logged out!!!
</c:if>
<c:if test="${not empty param.error}">
    ERR in param!!!
</c:if>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
    <div class="error">
        LAST_EXCEPTION: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
    </div>
</c:if>
<form:form action="perform_login" method="post">
    <form:errors path="*" element="div" />
    <il>
        <li /><input type="text" name="username" placeholder="username" />
        <li /><input type="password" name="password" placeholder="password" />
        <li /><input type="submit" role="button" value="LOGIN">
        <li /><input type="checkbox" name="remember-me">
    </il>
</form:form>
</body>
</html>

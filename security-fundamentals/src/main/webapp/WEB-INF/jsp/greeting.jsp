<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Greeting</title>
</head>
<body>
<h1>${message}</h1>

<sec:authorize access="hasAnyRole(\'ROLE_ADMIN\')">
    superstar
</sec:authorize>


</body>
</html>
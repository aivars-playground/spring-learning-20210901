<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <title><spring:message code="registration"/></title>
    <form:form modelAttribute="registration">
        <table>
            <tr>
                <td><spring:message code="name"/></td>
                <td>
                    <form:input path="name" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value=<spring:message code="submit.registration"/>>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
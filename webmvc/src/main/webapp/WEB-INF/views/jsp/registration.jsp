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
        <form:errors path="*" />
        <table>
            <tr>
                <td><spring:message code="name"/></td>
                <td>
                    <form:input path="name" />
                </td>
                <td>
                    <form:errors path="name" />
                </td>
            </tr>
            <tr>
                <td>id</td>
                <td>
                    <form:input path="id" />
                </td>
                <td>
                    <form:errors path="id" />
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value=<spring:message code="submit.registration"/>>
                </td>
            </tr>
        </table>
    </form:form>

    LANGUAGE
    <a href="?lang=lv">LV</a>
    <a href="?lang=en">EN</a>
</body>
</html>
<%@ page contentType="text/html; UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <form:form modelAttribute="registration">
        <table>
            <tr>
                <td>Name:</td>
                <td>
                    <form:input path="name" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Register">
                </td>
            </tr>
        </table>
    </form:form>
</head>
<body>
<p>Registration</p>
</body>
</html>
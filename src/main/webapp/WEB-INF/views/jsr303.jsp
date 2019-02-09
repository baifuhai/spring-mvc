<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getServletContext().getContextPath()%>/"/>
</head>
<body>
    <form:form action="testJSR303" method="post" modelAttribute="person">
        <form:errors path="*"/><br/>
        name: <form:input path="name"/><form:errors path="name"/><br/>
        email: <form:input path="email"/><form:errors path="email"/><br/>
        birth: <form:input path="birth"/><form:errors path="birth"/><br/>
        salary: <form:input path="salary"/><form:errors path="salary"/><br/>
        <form:hidden path="id"/>
        <input type="submit" value="testJSR303"/>
    </form:form>
</body>
</html>

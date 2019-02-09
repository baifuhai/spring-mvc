<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>success</h1>
    <p>requestScope.msg: ${requestScope.msg}</p>
    <p>requestScope.car: ${requestScope.car}</p>
    <p>sessionScope.msg: ${sessionScope.msg}</p>
    <p>sessionScope.car: ${sessionScope.car}</p>
    <p><fmt:message key="key.username"></fmt:message></p>
    <p><fmt:message key="key.password"></fmt:message></p>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getServletContext().getContextPath()%>/"/>
    <%--
    <base href="<%=request.getContextPath()%>/"/>
    <base href="${pageContext.request.contextPath}/"/>
    --%>
    <script src="static/js/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
    <style type="text/css">
        table {
            border-collapse: collapse;
            border: 1px solid #000;
        }

        table tr th {
            padding: 5px;
            border: 1px solid #000;
        }

        table tr td {
            padding: 5px;
            border: 1px solid #000;
        }
    </style>
</head>
<body>
    <a href="emp/emp">新增</a>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>age</th>
            <th>operation</th>
        </tr>
        <c:forEach var="emp" items="${empList}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.age}</td>
                <td>
                    <a href="emp/emp/${emp.id}">修改</a>
                    <form action="emp/emp/${emp.id}" method="post">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%=request.getServletContext().getContextPath()%><br/>
    <%=request.getContextPath()%><br/>
    ${pageContext.request.contextPath}<br/>
</body>
</html>

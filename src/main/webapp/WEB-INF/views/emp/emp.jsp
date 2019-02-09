<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getServletContext().getContextPath()%>/"/>
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
    <c:if test="${emp == null}">
        <form action="emp/emp" method="post">
            <input type="text" name="name"/>

            <input type="hidden" name="age" value="100"/>
            <input type="submit" value="insert"/>
        </form>
    </c:if>

    <c:if test="${emp != null}">
        <form action="emp/emp/${emp.id}" method="post">
            <input type="text" name="name" value="${emp.name}"/>

            <input type="hidden" name="id" value="${emp.id}"/>
            <input type="hidden" name="_method" value="put"/>
            <input type="submit" value="update"/>
        </form>
        <!--
            1. 为什么使用 form 标签
                可以更快速的开发出表单页面，而且可以更方便的进行表单值的回显
            2. 注意:
                可以通过 modelAttribute 属性指定绑定的模型属性
                若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean
                如果该属性值也不存在，则会发生错误
        -->
        <form:form action="emp/emp/${emp.id}" method="post" modelAttribute="emp">
            <form:input path="name"/>

            <form:hidden path="id"/>
            <!-- _method 不能使用 form:hidden 标签，因为 modelAttribute 对应的 bean 中没有 _method 这个属性 -->
            <input type="hidden" name="_method" value="put"/>
            <input type="submit" value="update"/>

            <%--
                Map<String, String> genders = new HashMap();
                genders.put("1", "Male");
                genders.put("0", "Female");

                request.setAttribute("genders", genders);
            --%>
            <%--
            Gender: <form:radiobuttons path="gender" items="${genders}" delimiter="<br>"/>
            Department: <form:select path="dept.id" items="${depts}" itemValue="id" itemLabel="name"/>
            --%>
        </form:form>
    </c:if>
</body>
</html>

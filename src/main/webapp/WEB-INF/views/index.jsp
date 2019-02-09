<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getServletContext().getContextPath()%>/"/>
</head>
<body>
    <h1>index</h1>

    <a href="testRequestMapping">testRequestMapping</a><br/>
    <a href="testRequestMapping2">testRequestMapping2</a><br/>
    <a href="testRequestMappingMethod">testRequestMappingMethod</a><br/>
    <a href="testRequestMappingParams?id=1&name=a&age=10">testRequestMappingParams</a><br/>
    <a href="testRequestMappingHeaders">testRequestMappingHeaders</a><br/>

    <a href="testAnt/a">testAnt</a><br/>
    <a href="testAnt2/aa">testAnt2</a><br/>
    <a href="testAnt3/a/a/abc">testAnt3</a><br/>

    <a href="testPathVariable/1">testPathVariable</a><br/>

    <form action="testRestGet/1" method="get">
        <input type="submit" value="get"/>
    </form>

    <form action="testRestPost" method="post">
        <input type="text" name="name"/>
        <input type="submit" value="post"/>
    </form>

    <form action="testRestPut/1" method="post">
        <input type="text" name="name"/>
        <input type="hidden" name="_method" value="put"/>
        <input type="submit" value="put"/>
    </form>

    <form action="testRestDelete/1" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <input type="submit" value="delete"/>
    </form>

    <a href="testRequestParam?username=a&age=10">testRequestParam</a><br/>

    <a href="testRequestHeader">testRequestHeader</a><br/>

    <a href="testCookieValue">testCookieValue</a><br/>

    <a href="testPojo?id=1&name=a&car.name=b">testPojo</a><br/>

    <a href="testServletAPI">testServletAPI</a><br/>

    <a href="testModelAndView">testModelAndView</a><br/>

    <a href="testMap">testMap</a><br/>

    <a href="testSessionAttributes">testSessionAttributes</a><br/>

    <a href="testModelAttribute?id=1&name=benz">testModelAttribute</a><br/>

    <a href="testViewAndViewResolver">testViewAndViewResolver</a><br/>
    <a href="testViewAndViewResolver2">testViewAndViewResolver2</a><br/>

    <a href="testHelloView1">testHelloView1</a><br/>
    <a href="testHelloView2">testHelloView2</a><br/>
    <a href="testHelloView3">testHelloView3</a><br/>
    <a href="testHelloView4">testHelloView4</a><br/>

    <a href="testExcelView">testExcelView</a><br/>
    <a href="testExcelView2">testExcelView2</a><br/>

    <a href="testRedirect">testRedirect</a><br/>

    <a href="emp/emps">emps</a><br/>

    <a href="testConverter?person=1-a">testConverter</a><br/>

    <a href="testFormatter?birth=2018-01-01&salary=1,234,567.8">testFormatter</a><br/>

    <a href="testJSR303Page">testJSR303Page</a><br/>

    <form action="testRequestBody" method="post">
        <input type="text" name="id"/>
        <input type="submit" value="testRequestBody"/>
    </form>

    <a href="testResponseBody">testResponseBody</a><br/>
    <a href="testResponseBodyString">testResponseBodyString</a><br/>
    <a href="testResponseBodyByteArray">testResponseBodyByteArray</a><br/>

    <a href="testResponseEntity">testResponseEntity</a><br/>

    <a href="testI18n">testI18n</a><br/>
    <a href="testI18n?locale=zh_CN">testI18n zh_CN</a><br/>
    <a href="testI18n?locale=en_US">testI18n en_US</a><br/>

    <form action="testFileUpload" method="post" enctype="multipart/form-data">
        <input type="text" name="desc"/>
        <input type="file" name="file"/>
        <input type="file" name="file2"/>
        <input type="submit" value="testFileUpload"/>
    </form>
    <form action="testFileUpload2" method="post" enctype="multipart/form-data">
        <input type="text" name="desc"/>
        <input type="file" name="file"/>
        <input type="file" name="file2"/>
        <input type="submit" value="testFileUpload2"/>
    </form>

    <a href="testInterceptor">testInterceptor</a><br/>

    <a href="testExceptionHandler?i=0">testExceptionHandler</a><br/>

    <a href="testResponseStatus?i=0">testResponseStatus</a><br/>
    <a href="testResponseStatus2?i=0">testResponseStatus2</a><br/>
    <a href="testResponseStatus2?i=1">testResponseStatus2-</a><br/>

    <a href="testDefaultHandlerExceptionResolver">testDefaultHandlerExceptionResolver</a><br/>

    <a href="testSimpleMappingExceptionResolver?i=1">testSimpleMappingExceptionResolver</a><br/>

</body>
</html>

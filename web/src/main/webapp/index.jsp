<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Start page</title>
</head>
<body>

<h1 style="text-align: center"><c:out value="Заказ гостиницы"/></h1>

<fieldset>
    <form action="${pageContext.request.contextPath}/login">
        <%--        <h4><c:out value="Войти"/></h4>--%>
        <input type="submit" value="Войти">
    </form>
</fieldset>

<fieldset>
    <form action="${pageContext.request.contextPath}/registration.jsp">
        <%--        <h4><c:out value="Регистрация"/></h4>--%>
        <input type="submit" value="Регистрация">
    </form>
</fieldset>

</body>
</html>

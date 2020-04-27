<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value= "en_Us"/>
<fmt:setBundle basename="translations" var="messages"/>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Введите данные для входа в систему</h3>
<form action="${pageContext.request.contextPath}/login" method="post">

<%--    <input id="login" type="text" name="login" required>--%>
<%--    <label for="login"> <fmt:message key="login.username" bundle="${messages}"/> </label><br/>--%>


    <input id="login" type="text" name="login">
    <label for="login">Логин</label><br/>

<%--    <input id="password" type="password" name="password" required>--%>
<%--    <label for="password"> <fmt:message key="login.password" bundle="${messages}"/> </label> <br/>--%>


    <input id="password" type="password" name="password">
    <label for="password">Пароль</label><br/>

<%--    <input type="submit" value=<fmt:message key="login.submit" bundle="${messages}"/>>--%>
    <input type="submit" value=Войти >
</form> <br/><br/>

<a href="${pageContext.request.contextPath}/registration.jsp">РЕГИСТРАЦИЯ</a>

<p style="color: red">${error}</p>

</body>
</html>

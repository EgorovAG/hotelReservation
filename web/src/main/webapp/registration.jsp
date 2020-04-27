<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Registration</title>
</head>
<body>

<h3>Регистрация пользователя</h3>
<form action="${pageContext.request.contextPath}/registration" method="post">

    <input id="login" type="text" name="login" required>
    <label for="login">Логин</label> <br/>

    <input id="password" type="password" name="password" required>
    <label for="password">Пароль</label> <br/>

    <input id="firstName" type="text" name="firstName" required>
    <label for="firstName">Имя</label> <br/>

    <input  id="secondName" type="text" name="secondName" required>
    <label for="secondName">Фамилия</label> <br/>

    <input id="email" type="email" name="email" required>
    <label for="email">email</label> <br/>

    <input id="phone" type="tel" name="phone" required>
    <label for="phone">phone</label> <br/>


    <input type="submit" value="Зерегистрироваться">
</form>

<p style="color: red">${errorUser}</p>
<a href="${pageContext.request.contextPath}/index.jsp">Home</a>

</body>
</html>

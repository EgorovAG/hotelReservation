<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<h2 style="text-align: center">Список зарегистрированных пользователей</h2>

<c:set var="x" value="1"/>
<c:forEach var="authUser" items="${authUserList}" >
    <fieldset>
        <c:out value="${x}"/>)
        <c:out value="${authUser}"/>
        <c:set var="x" value="${x+1}"/>
    </fieldset>
</c:forEach>

<form action="${pageContext.request.contextPath}/registratedUsers" method="post" >
    <h4>Введите id пользователя для удаления или блокировки</h4>
    <input id="text" type="text" name="id">
    <input type="submit" value=Удалить>
</form>
<form action="${pageContext.request.contextPath}/blockUser" method="post" >
    <input id="texts" type="text" name="id">
    <input type="submit" value=Заблокировать>
</form>

<form action="${pageContext.request.contextPath}/personalArea.jsp" method="post" >
    <input type="submit" value="перейти в личный кабинет">
</form>

<a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>

<p style="color: red">${error}</p>









</html>

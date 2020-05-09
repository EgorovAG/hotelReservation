<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<h2 style="text-align: center">Список заблокированных пользователей</h2>

<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>№</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Дата блокировки</th>
        <th>Удалить из черного списка </th>
    </tr>

    <c:set var="x" value="1"/>
    <c:forEach var="blackListUsers" items="${blackListUsers}">
        <tr>
            <td><c:out value="${x}"/>
                <c:set var="x" value="${x+1}"/></td>
            <td>${blackListUsers.firstName}</td>
            <td>${blackListUsers.secondName}</td>
            <td>${blackListUsers.dateBlock}</td>
            <td> <form action="${pageContext.request.contextPath}/blackListUsers" method="post" >
                <button name="id" value="${blackListUsers.id}">Удалить</button>
            </form></td>
        </tr>
    </c:forEach>
</table>


<%--<c:set var="x" value="1"/>--%>
<%--<c:forEach var="blackListUsers" items="${blackListUsers}" >--%>
<%--    <fieldset>--%>
<%--        <c:out value="${x}"/>)--%>
<%--        <c:out value="${blackListUsers}"/>--%>
<%--        <c:set var="x" value="${x+1}"/>--%>
<%--    </fieldset>--%>
<%--</c:forEach>--%>

<%--<form action="${pageContext.request.contextPath}/blackListUsers" method="post" >--%>
<%--        <h4>Введите user_id пользователя для удаления из черного списка</h4>--%>
<%--            <input id="text" type="text" name="id">--%>
<%--            <input type="submit" value=Удалить>--%>
<%--</form>--%>

<form action="${pageContext.request.contextPath}/personalArea.jsp" method="post" >
    <input type="submit" value="перейти в личный кабинет">
</form>

<a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>








</html>

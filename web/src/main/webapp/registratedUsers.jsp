<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<h2 style="text-align: center">Список зарегистрированных пользователей</h2>


<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>№</th>
        <th>id</th>
        <th>логин</th>
        <th>пароль</th>
        <th>имя</th>
        <th>фамилия</th>
        <th>email</th>
        <th>phone</th>
        <th>Действия над списком заказчиков</th>
    </tr>

    <c:set var="x" value="1"/>
    <c:forEach var="authUserWithClients" items="${authUserWithClients}">
        <tr>
            <td><c:out value="${x}"/>
                <c:set var="x" value="${x+1}"/></td>
            <td>${authUserWithClients.id}</td>
            <td>${authUserWithClients.login}</td>
            <td>${authUserWithClients.firstName}</td>
            <td>${authUserWithClients.secondName}</td>
            <td>${authUserWithClients.password}</td>
            <td>${authUserWithClients.email}</td>
            <td>${authUserWithClients.phone}</td>
            <td>

                <form action="${pageContext.request.contextPath}/registratedUsers" method="post">
                    <input id="id1" type="text" name="id" value="${authUserWithClients.id}" hidden="hidden">
                    <input type="submit" value=Удалить>
                </form>Z
                <form action="${pageContext.request.contextPath}/blockUser" method="post">
                    <input id="id2" type="text" name="id" value="${authUserWithClients.id}" hidden="hidden">
                    <input type="submit" value=Заблокировать>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<%--<c:set var="x" value="1"/>--%>
<%--<c:forEach var="authUser" items="${authUserList}" >--%>
<%--    <fieldset>--%>
<%--        <c:out value="${x}"/>)--%>
<%--        <c:out value="${authUser}"/>--%>
<%--        <c:set var="x" value="${x+1}"/>--%>
<%--    </fieldset>--%>
<%--</c:forEach>--%>


<%--<form action="${pageContext.request.contextPath}/registratedUsers" method="post">--%>
<%--    <h4>Введите id пользователя для удаления или блокировки</h4>--%>
<%--    <input id="text" type="text" name="id">--%>
<%--    <input type="submit" value=Удалить>--%>
<%--</form>--%>
<%--<form action="${pageContext.request.contextPath}/blockUser" method="post">--%>
<%--    <input id="texts" type="text" name="id">--%>
<%--    <input type="submit" value=Заблокировать>--%>
<%--</form>--%>

<form action="${pageContext.request.contextPath}/personalArea.jsp" method="post">
    <input type="submit" value="перейти в личный кабинет">
</form>

<a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>

<p style="color: red">${error}</p>


</html>

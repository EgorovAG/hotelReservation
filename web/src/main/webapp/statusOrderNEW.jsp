<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<h4 ><c:out value=" Имя пользователя: ${client.firstName} " /> </h4>
<c:if test="${orderForClients != null}">

    <h2 style="text-align: center">Список заказов</h2>

    <c:set var="x" value="${1}"/>
    <c:forEach var="orderForClients" items="${orderForClients}" >
        <fieldset>
            <c:out value="${x}"/>)    <c:out value="${orderForClients}"/>
            <c:set var="x" value="${x+1}"/>
        </fieldset>
    </c:forEach>

    <form action="${pageContext.request.contextPath}/toPayOrder" method="post" >
        <h4>Введите id заказа:</h4>
        <input id="ok" type="number" name="orderId" required> <br/>
        <input type="radio" name="condition" value="PAID"  checked> Оплатить<br>
        <input type="radio" name="condition" value="DELETE"> Удалить<br>
        <input type="submit">
    </form>
</c:if>

<c:if test="${orderForClients == null}">
    <h2> <c:out value="У Вас нет заказов"/></h2
</c:if>
<p style="color: red">${Error}</p>

<br/>
<form action="${pageContext.request.contextPath}/personalArea.jsp" method="post" >
    <input type="submit" value="перейти в личный кабинет">
</form>

<a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>

</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<c:if test="${orderWithClients !=null}">

    <h2 style="text-align: center">Список заказов</h2>

    <c:set var="x" value="${1}"/>
    <c:forEach var="orderWithClient" items="${orderWithClients}">
        <fieldset>
            <c:out value="${x}"/>) <c:out value="${orderWithClient}"/>
            <c:set var="x" value="${x+1}"/>
        </fieldset>
    </c:forEach>

    <form action="${pageContext.request.contextPath}/orderList" method="post">
        <h4>Введите id заказа:</h4>
        <input id="ok" type="number" name="orderId" required> <br/>
        <input type="radio" name="condition" value="APPROVED" checked> Одобрить<br>
        <input type="radio" name="condition" value="REJECTED"> Отказать<br>
        <input type="radio" name="condition" value="DELETE"> Удалить<br>
        <input type="submit">
    </form>
</c:if>

<c:if test="${orderWithClients == null}">
    <h2><c:out value="Заказов нет"/></h2
</c:if>

<p style="color: red">${error}</p>

<br/>
<form action="${pageContext.request.contextPath}/personalArea.jsp" method="post">
    <input type="submit" value="перейти в личный кабинет">
</form>

<a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>

</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body style="background-color: aliceblue">

<h4><c:out value=" Имя пользователя: ${client.firstName} "/></h4>
<h1 style="text-align: center"><c:out value="Ваш заказ принят, дождитесь проверки администратора"/></h1>

<%--<table style="width:50%">--%>
<table>
    <tr>
        <td><c:out value="число мест"/></td>
        <td><c:out value="${room.numOfSeats}"/></td>
    </tr>
    <tr>
        <td><c:out value="класс апартаментов"/></td>
        <td><c:out value="${room.classOfAp}"/></td>
    </tr>
    <tr>
        <td><c:out value="дата приезда"/></td>
        <td><c:out value="${order.startDate}"/></td>
    </tr>

    <tr>
        <td><c:out value="дата отъезда"/></td>
        <td><c:out value="${order.endDate}"/></td>
    </tr>

    <tr>
        <td><c:out value="дополнительные услуги"/></td>
        <td><c:out value="${serviceList}"/></td>
    </tr>

    <%--    <tr>--%>
    <%--        <td><c:out value="дата отъезда"/></td>--%>
    <%--        <td><c:out value="${order.endDate}"/></td>--%>
    <%--    </tr>--%>
</table>
<br/>

<form action="${pageContext.request.contextPath}/personalArea.jsp">
    <input type="submit" value="Перейти в личный кабинет">
</form>

<%--     <form action="${pageContext.request.contextPath}/statusOrderNEW.jsp">--%>
<%--        <input type="submit" value="Проверить статус заказа">--%>
<%--     </form>--%>

<form action="${pageContext.request.contextPath}/statusOrder">
    <input type="submit" value="Проверить статус заказа">
</form>


<br/>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/logout">Logout</a>


</body>
</html>

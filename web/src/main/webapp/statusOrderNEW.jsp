<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<h4 ><c:out value=" Имя пользователя: ${client.firstName} " /> </h4>
<c:if test="${orderForClients != null}">

    <h2 style="text-align: center">Список заказов</h2>


    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>№</th>
            <th>id</th>
            <th>время приезда</th>
            <th>время отъезда</th>
            <th>число мест</th>
            <th>класс комнаты</th>
            <th>цена</th>
            <th>состояние</th>
            <th>Действия над заказом</th>
        </tr>

        <c:set var="x" value="1"/>
        <c:forEach var="orderForClients" items="${orderForClients}">
            <tr>
                <td><c:out value="${x}"/>
                    <c:set var="x" value="${x+1}"/></td>
                <td>${orderForClients.id}</td>
                <td>${orderForClients.startDate}</td>
                <td>${orderForClients.endDate}</td>
                <td>${orderForClients.numOfSeats}</td>
                <td>${orderForClients.classOfAp}</td>
                <td>${orderForClients.price}</td>
                <td>${orderForClients.condition}</td>
                <td>
                        <%--                    <form action="${pageContext.request.contextPath}/orderList" method="post">--%>
                        <%--                        <button name="condition" value="APPROVED" name="orderId" value="${orderForAdmins.id}">--%>
                        <%--                            Одобрить--%>
                        <%--                        </button>--%>
                        <%--                    </form>--%>
                </td>

                    <%--                <td> <form action="${pageContext.request.contextPath}/orderList" method="post" >--%>
                    <%--                    <button  name="condition" value="APPROVED" >Одобрить</button>--%>
                    <%--                    <button  name="condition" value="REJECTED">Отказать</button>--%>
                    <%--                    <button  name="condition" value="DELETE">Удалить</button>--%>
                    <%--                </form></td>--%>
            </tr>
        </c:forEach>
    </table>

<%--    <c:set var="x" value="${1}"/>--%>
<%--    <c:forEach var="orderForClients" items="${orderForClients}" >--%>
<%--        <fieldset>--%>
<%--            <c:out value="${x}"/>)    <c:out value="${orderForClients}"/>--%>
<%--            <c:set var="x" value="${x+1}"/>--%>
<%--        </fieldset>--%>
<%--    </c:forEach>--%>

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

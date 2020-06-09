<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<c:if test="${orderForAdmins !=null}">

    <h2 style="text-align: center">Список заказов</h2>


    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>№</th>
                <%--            <th>id заказа</th>--%>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>email</th>
            <th>phone</th>
            <th>дата заезда</th>
            <th>дата отъезда</th>
            <th>состояние</th>
            <th>Дополнительные услуги</th>
            <th>Действие над заказом</th>


        </tr>

        <c:set var="x" value="1"/>
        <c:forEach var="orderForAdmins" items="${orderForAdmins}">
            <tr>
                <td><c:out value="${x}"/>
                    <c:set var="x" value="${x+1}"/></td>
                    <%--                <td>${orderForAdmins.id}</td>--%>
                <td>${orderForAdmins.firstName}</td>
                <td>${orderForAdmins.secondName}</td>
                <td>${orderForAdmins.email}</td>
                <td>${orderForAdmins.phone}</td>
                <td>${orderForAdmins.startDate}</td>
                <td>${orderForAdmins.endDate}</td>
                <td>${orderForAdmins.condition}</td>

                <td><c:forEach var="orderClients" items="${orderClients}">
                    <c:if test="${orderForAdmins.id == orderClients.orderId}">
                        <c:out value="${orderClients.services}"/>
                    </c:if>
                </c:forEach>
                </td>


                <td>
                    <form action="${pageContext.request.contextPath}/orderList" method="post">
                        <input id="id1" type="text" name="orderId" value="${orderForAdmins.id}" hidden="hidden">
                        <input id="status1" type="text" name="condition" value="APPROVED" hidden="hidden">
                        <input type="submit" value=Одобрить>
                    </form>
                    <form action="${pageContext.request.contextPath}/orderList" method="post">
                        <input id="id2" type="text" name="orderId" value="${orderForAdmins.id}" hidden="hidden">
                        <input id="status2" type="text" name="condition" value="REJECTED" hidden="hidden">
                        <input type="submit" value=Отказать>
                    </form>
                    <form action="${pageContext.request.contextPath}/orderList" method="post">
                        <input id="id3" type="text" name="orderId" value="${orderForAdmins.id}" hidden="hidden">
                        <input id="status3" type="text" name="condition" value="DELETE" hidden="hidden">
                        <input type="submit" value=Удалить>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <%--    <c:set var="x" value="${1}"/>--%>
    <%--    <c:forEach var="orderWithClient" items="${orderWithClients}">--%>
    <%--        <fieldset>--%>
    <%--            <c:out value="${x}"/>) <c:out value="${orderWithClient}"/>--%>
    <%--            <c:set var="x" value="${x+1}"/>--%>
    <%--        </fieldset>--%>
    <%--    </c:forEach>--%>

    <%--        <form action="${pageContext.request.contextPath}/orderList" method="post">--%>
    <%--            <h4>Введите id заказа:</h4>--%>
    <%--            <input id="ok" type="number" name="orderId" required> <br/>--%>
    <%--            <input type="radio" name="condition" value="APPROVED" checked> Одобрить<br>--%>
    <%--            <input type="radio" name="condition" value="REJECTED"> Отказать<br>--%>
    <%--            <input type="radio" name="condition" value="DELETE"> Удалить<br>--%>
    <%--            <input type="submit">--%>
    <%--        </form>--%>
</c:if>

<c:if test="${orderForAdmins == null}">
    <h2><c:out value="Заказов нет"/></h2
</c:if>

<p style="color: red">${error}</p>

<br/>
<form action="${pageContext.request.contextPath}/personalArea.jsp" method="post">
    <input type="submit" value="перейти в личный кабинет">
</form>

<a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>

</html>

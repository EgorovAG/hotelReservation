<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<c:if test="${orderForAdmins !=null}">

    <h2 style="text-align: center">Список заказов</h2>


    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>№</th>
            <th>id заказа </th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>email</th>
            <th>phone</th>
            <th>дата заезда</th>
            <th>дата отъезда</th>
            <th>состояние</th>
            <th>Действие над заказом</th>
        </tr>

        <c:set var="x" value="1"/>
        <c:forEach var="orderForAdmins" items="${orderForAdmins}">
            <tr>
                <td><c:out value="${x}"/>
                    <c:set var="x" value="${x+1}"/></td>
                <td>${orderForAdmins.id}</td>
                <td>${orderForAdmins.firstName}</td>
                <td>${orderForAdmins.secondName}</td>
                <td>${orderForAdmins.email}</td>
                <td>${orderForAdmins.phone}</td>
                <td>${orderForAdmins.startDate}</td>
                <td>${orderForAdmins.endDate}</td>
                <td>${orderForAdmins.condition}</td>
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
    <%--    <c:forEach var="orderWithClient" items="${orderWithClients}">--%>
    <%--        <fieldset>--%>
    <%--            <c:out value="${x}"/>) <c:out value="${orderWithClient}"/>--%>
    <%--            <c:set var="x" value="${x+1}"/>--%>
    <%--        </fieldset>--%>
    <%--    </c:forEach>--%>

        <form action="${pageContext.request.contextPath}/orderList" method="post">
            <h4>Введите id заказа:</h4>
            <input id="ok" type="number" name="orderId" required> <br/>
            <input type="radio" name="condition" value="APPROVED" checked> Одобрить<br>
            <input type="radio" name="condition" value="REJECTED"> Отказать<br>
            <input type="radio" name="condition" value="DELETE"> Удалить<br>
            <input type="submit">
        </form>
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

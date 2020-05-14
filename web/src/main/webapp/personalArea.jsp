<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<c:if test="${authUser.login != 'admin'}">
    <h4><c:out value=" Имя пользователя: ${client.firstName} "/></h4>
    <h1 style="text-align: center"><c:out value="  Личный кабинет "/></h1>

    <fieldset>
        <form action="${pageContext.request.contextPath}/clientOrder.jsp">
            <input type="submit" value="Сделать заказ">
        </form>
    </fieldset>

    <fieldset>
        <form action="${pageContext.request.contextPath}/statusOrder">
            <input type="submit" value="Просмотр заказов">
        </form>
    </fieldset>

    <br/>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</c:if>

<%--<c:if test="${authUser.login == 'blockClient'}">--%>
<%--    <h4 ><c:out value=" Имя пользователя: ${client.firstName} " /> </h4>--%>
<%--    <h1 style="text-align: center"><c:out value="  Личный кабинет " /> </h1>--%>

<%--    <p style="color: red">${error}</p>--%>

<%--    <br/>--%>
<%--    <br/>--%>
<%--    <br/>--%>
<%--    <a href="${pageContext.request.contextPath}/logout">Logout</a>--%>
<%--</c:if>--%>

<c:if test="${authUser.login == 'admin'}">
    <h4><c:out value=" АДМИНИСТРАТОР "/></h4>
    <h1 style="text-align: center"><c:out value="  Личный кабинет "/></h1>

    <fieldset>
        <form action="${pageContext.request.contextPath}/orderList">
                <%--                <h4><c:out value="Войти"/></h4>--%>
            <input type="submit" value="Просмотр заказов">
        </form>
    </fieldset>

    <fieldset>

            <%--        это если без пагинации--%>
            <%--        <form action="${pageContext.request.contextPath}/registratedUsers"> --%>

        <form action="${pageContext.request.contextPath}/paginationRegistratedUsers">
                <%--        <h4><c:out value="Регистрация"/></h4>--%>
            <input type="submit" value="Просмотр зарегистрированных пользователей">
        </form>
    </fieldset>

    <fieldset>
        <form action="${pageContext.request.contextPath}/blackListUsers">
                <%--                <h4><c:out value="Войти"/></h4>--%>
            <input type="submit" value="Список заблокированных пользователей">
        </form>
    </fieldset>
    <br/>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>


</c:if>

</html>

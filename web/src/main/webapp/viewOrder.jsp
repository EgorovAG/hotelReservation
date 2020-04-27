<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<c:if test="${authUser.login != 'admin'}">
    <h4 ><c:out value=" Имя пользователя: ${client.firstName} " /> </h4>
    <h1 style="text-align: center"><c:out value="  Личный кабинет " /> </h1>

    <fieldset>
        <form action="${pageContext.request.contextPath}/clientOrder.jsp">
                <%--                <h4><c:out value="Войти"/></h4>--%>
            <input type="submit" value="Сделать заказ">
        </form>
    </fieldset>

    <fieldset>
        <form action="${pageContext.request.contextPath}/statusOrder.jsp">
                <%--        <h4><c:out value="Регистрация"/></h4>--%>
            <input type="submit" value="Просмотр заказа и его статуса">
        </form>
    </fieldset>

    <br/>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>


</c:if>
</html>

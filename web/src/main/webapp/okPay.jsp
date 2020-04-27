<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<h4 ><c:out value=" Имя пользователя: ${client.firstName} " /> </h4>

    <title>OkPay</title>
    <c:out value="Оплата прошла успешно, ждем Вас в нашей гостинице, спасибо!"/>

    <br/>
    <form action="${pageContext.request.contextPath}/personalArea.jsp" method="post" >
        <input type="submit" value="перейти в личный кабинет">
    </form>

    <a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>

</html>

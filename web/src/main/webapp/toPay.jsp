<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 ><c:out value=" Имя пользователя: ${client.firstName} " /> </h4>
<h3 style="text-align: center"><c:out value="Оплата заказа " /></h3>
<br/>

<c:out value="Введите сумму для оплаты ${price} " />
<form action="${pageContext.request.contextPath}/checkPay" >
    <input id="number" type="number" name="sum">
    <input type="submit" value=Оплатить >
</form> <br/><br/>

<p style="color: red">${Error}</p>


<br/>
<form action="${pageContext.request.contextPath}/personalArea.jsp" method="post" >
    <input type="submit" value="перейти в личный кабинет">
</form>

<a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>
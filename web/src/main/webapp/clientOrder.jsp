<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:if test="${authUser.login != 'admin'}">
    <h4 ><c:out value=" Имя пользователя: ${client.firstName} " /> </h4>
    <h3 style="text-align: center"><c:out value="Выберите параметры номера"/> </h3>

    <form style="text-align: center" action="${pageContext.request.contextPath}/clientOrder" method="get">

        <p><b>Количество мест в номере:</b><Br>
            <input type="radio" name="numOfSeats" value="1" checked> одноместный<Br>
            <input type="radio" name="numOfSeats" value="2"> двухместный<Br>
            <input type="radio" name="numOfSeats" value="3"> трехместный<Br>

        </p>
        <br/>

        <p><b>Класс апартаментов:</b><Br>
            <input type="radio" name="classOfAp" value="ECONOM" checked> эконом<Br>
            <input type="radio" name="classOfAp" value="STANDART"> стандарт<Br>
            <input type="radio" name="classOfAp" value="BUSINESS"> бизнес<Br>
        </p>

        <p><b>Класс апартаментов:</b><Br>
            <input type="radio" name="typeOfService1" value="pool"> бассейн<Br>
            <input type="radio" name="typeOfService2" value="wifi"> wi-fi<Br>
            <input type="radio" name="typeOfService3" value="breakfast"> завтрак<Br>
            <input type="radio" name="typeOfService4" value="lunch"> обед<Br>
            <input type="radio" name="typeOfService5" value="dinner"> ужин<Br>
            <input type="radio" name="typeOfService6" value="bar"> бар<Br>
            <input type="radio" name="typeOfService7" value="gym"> тренажерный зал<Br>
        </p>
        <br/>

            <input type="date" name="startDate" required>
        Время въезда:
        <br/>
            <input type="date" name="endDate" required>
        Время отъезда:
        <br/>

        <input type="submit">
    </form>
    <br/>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</c:if>















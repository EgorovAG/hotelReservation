<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

    <h3  style="text-align: center"><c:out value="Вы заблокированы и не можете
     производить никаких действий, обратитесь к администратору"/></h3>

    <br/><br/>
    <a href="${pageContext.request.contextPath}/logout">Logout</a> <br/>

</html>

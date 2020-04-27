<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
    <title>Title</title>
</head>
<body>

<h1> Вы получили доступ к ресурсу и вошли в приложение</h1>

<br/>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/logout">Logout</a>

</body>
</html>

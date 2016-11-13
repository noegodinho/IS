<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Menu</title>
</head>
<body>
<form action="./MenuServlet" method="POST">
    <font face="verdana" color="black">
        <section align="center">
            <div class="login">
                <h1>Welcome ${user.name}</h1>
                <c:forEach items="${options}" var="item">
                    <p class="submit"><input type="submit" name="action" value="${item}" style="width: 150px; height: 50px"></p>
                </c:forEach>
            </div>
        </section>
    </font>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if ( session.getAttribute("user") == null){
%><jsp:forward page="index.jsp" />
<%
    }
%>

<html>
<head>
    <title>Menu</title>
</head>
<body>
    <form action="./MenuServlet" method="POST">
        <section align="center">
            <div class="login">
                <h1>Menu</h1>
                <h3 align="right">Welcome, ${user.name}</h3>
                <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>
                <c:forEach items="${options}" var="item">
                    <p class="submit"><input type="submit" name="action" value="${item}" style="width: 150px; height: 50px"></p>
                </c:forEach>
            </div>
        </section>
    </form>
</body>
</html>

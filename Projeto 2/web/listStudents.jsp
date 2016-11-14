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
    <title>Check Students</title>
</head>

<body>
    <form action="./ListStudentsServlet" method="POST">
        <h1 align = "center">Student List</h1>
        <h3 align="right">Welcome, ${user.name}</h3>
        <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>
        <c:forEach items="${studentsList}" var="student">
            <p>"${student.number} ${student.name} ${student.instEmail}"</p>
        </c:forEach>
    </form>
</body>
</html>

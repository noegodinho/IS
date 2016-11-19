<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ( session.getAttribute("user") == null){
%><jsp:forward page="index.jsp" />
<%
    }
%>

<html>
<head>
    <title>List Courses</title>
</head>
<body>
    <form action="./ListCoursesServlet" method="POST">
        <h1 align = "center">List Courses</h1>
        <h3 align="right">Welcome, ${user.name}</h3>
        <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>
    </form>

    <form action = "./ListCoursesServlet" method="POST">
        Courses:
        <c:forEach items="${coursesIN}" var="course">
            <p>${course.courseName}</p>
        </c:forEach>
    </form>
</body>
</html>

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
    <title>List and Download Materials</title>
</head>
<body>
<form action="./ListMaterialsServlet" method="POST">
    <h1 align = "center">List Courses</h1>
    <h3 align="right">Welcome, ${user.name}</h3>
    <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>
    Select course:
    <select name="courseName" id="course">
        <option value="dummy">Choose your option</option>
        <c:forEach items="${coursesIN}" var="course">
            <option>${course.courseName}</option>
        </c:forEach>
    </select>
    <p class="submit"><input type="submit" name="action" value="search" style="width: 150px; height: 50px"></p>

    Materials:
    <c:forEach items="${materialsList}" var="material">
        <p>${material.filename}</p>
    </c:forEach>
</form>
</body>
</html>

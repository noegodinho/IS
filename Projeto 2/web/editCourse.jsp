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
    <title>Edit course</title>
</head>
<body>
<form action="./EditCourseServlet" method="POST">
    <h1 align="center">Edit existent Course</h1>
    <h3 align="right">Welcome, ${user.name}</h3>
    <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>

    <p><input type="text" name="courseName" placeholder="Name" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p>${course}</p>
    <p class="submit"><input type="submit" name="action" value="search" style="width: 150px; height: 50px"></p>

    <p><input type="text" name="newCourseName" placeholder="New Course Name" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p class="submit"><input type="submit" name="action" value="edit" style="width: 150px; height: 50px"></p>
</form>

</body>
</html>

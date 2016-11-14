<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>New Course</title>
</head>
<body>
<form action="./NewCourseServlet" method="POST">
    <h1 align="center">Create New Course</h1>
    <h3 align="right">Welcome, ${user.name}</h3>
    <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>

    <p><input type="text" name="courseName" placeholder="Name" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p><input type="text" name="profEmail" placeholder="Professor Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p>Add students to the course</p>
    <p><input type="text" name="studentEmail" placeholder="Student Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p class="submit"><input type="submit" name="action" value="add" style="width: 150px; height: 50px"></p>
    <c:forEach items="${students}" var="student">
        <p>${student.number} ${student.name} ${student.instEmail}</p>
    </c:forEach>

    <p class="submit"><input type="submit" name="action" value="create" style="width: 150px; height: 50px"></p>
</form>

</body>
</html>

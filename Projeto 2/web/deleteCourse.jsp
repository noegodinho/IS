<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="./DeleteCourseServlet" method="POST">
        <h1 align = "center">Delete course</h1>
        <h3 align="right">Welcome, ${user.name}</h3>
        <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>
        Which course do you want to delete?
        <select name="course" id="course" onchange="optionCheck(this);">
            <option value="dummy">Choose your option</option>
            <c:forEach items="${coursesList}" var="course">
                <option>${course.courseName}</option>
            </c:forEach>
        </select>
        <p class="submit"><input type="submit" name="action" value="delete" style="width: 150px; height: 50px"></p>
    </form>
</body>
</html>

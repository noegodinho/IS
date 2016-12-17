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
    <title>Delete User</title>
</head>
<body>
    <form action="./DeleteUserServlet" method="POST">
        <h1 align = "center">Delete course</h1>
        <h3 align="right">Welcome, ${user.name}</h3>
        <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>
        Which course do you want to delete?
        <select name="userType" id="userType">
            <option value="dummy">Choose your option</option>
            <option value="student">Student</option>
            <option value="professor">Professor</option>
        </select>
        <p><input type="text" name="instEmail" placeholder="Institutional Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
        <p class="submit"><input type="submit" name="action" value="delete" style="width: 150px; height: 50px"></p>
    </form>
</body>
</html>
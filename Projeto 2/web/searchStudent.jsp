<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ( session.getAttribute("user") == null){
%><jsp:forward page="index.jsp" />
<%
    }
%>

<html>
<head>
    <title>Search For Student</title>
</head>
<body>
<form action="./SearchStudentServlet" method="POST">
    <h1 align="center">Search For Specific Student</h1>
    <h3 align="right">Welcome, ${user.name}</h3>
    <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>

    Looking for a student in particular?

    <p><input type="text" name="name" placeholder="Name" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p>
        <input type="text" name="day" placeholder="Day" style="text-align: center; font-size:14pt; width: 120px; height: 30px ">
        <input type="text" name="month" placeholder="Month" style="text-align: center; font-size:14pt; width: 120px; height: 30px ">
        <input type="text" name="year" placeholder="Year" style="text-align: center; font-size:14pt; width: 120px; height: 30px ">
    </p>
    <p><input type="text" name="instEmail" placeholder="Institutional Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p><input type="text" name="altEmail" placeholder="Alternative Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px "> </p>
    <p><input type="text" name="address" placeholder="Address" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
    <p><input type="text" name="telephone" placeholder="Telephone" style="text-align: center; font-size:14pt; width: 360px; height: 30px "> </p>
    <p><input type="text" name="number" placeholder="Number"  style="text-align: center; font-size:14pt; width: 360px; height: 30px "> </p>
    <p><input type="text" name="yearOfCourse" placeholder="Year Of Course" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>

    <p class="submit"><input type="submit" name="action" value="search" style="width: 150px; height: 50px"></p>
</form>

</body>
</html>

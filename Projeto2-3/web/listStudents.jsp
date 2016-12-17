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
        Retrieve students from which course?
        <select name="course" id="course">
            <option value="dummy">Choose your option</option>
            <c:forEach items="${coursesList}" var="course">
                <option>${course.courseName}</option>
            </c:forEach>
        </select>
        <p class="submit"><input type="submit" name="action" value="search" style="width: 150px; height: 50px"></p>

        <table border="1" align="center">
            <tr bgcolor="#9acd32" style="display:table-row;">
                <th>Number</th>
                <th>Name</th>
                <th>Institutional Email</th>
            </tr>
            <c:forEach begin="0" end= "${number}" step="1"
                       items="${studentsList}" var="student">
                <tr>
                    <td>
                        <c:out value="${student.number}" />
                    </td>
                    <td>
                        <c:out value="${student.name}" />
                    </td>
                    <td>
                        <c:out value="${student.instEmail}" />
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>

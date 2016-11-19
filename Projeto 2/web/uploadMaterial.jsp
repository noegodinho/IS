<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if ( session.getAttribute("user") == null){
%><jsp:forward page="index.jsp" />
<%
    }
%>

<head>
    <title>Upload Material</title>
</head>
<body>
    <form action="./UploadMaterialServlet" method="POST" enctype="multipart/form-data">
        <p><input type="text" name="courseName" placeholder="Course Name" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
        <input type="file" name="file" size="50" />
        <br />
        <p class="submit"><input type="submit" name="action" value="upload" style="width: 150px; height: 50px"></p>
    </form>
</body>
</html>

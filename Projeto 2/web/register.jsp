<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if ( session.getAttribute("user") == null){
%><jsp:forward page="index.jsp" />
<%
    }
%>
<html>
<head>
    <title>Create New User</title>
    <script language = javascript>
        function optionCheck(optionSelected) {
            var option = optionSelected.value;
            var elements = document.getElementsByClassName(option);
            var notElements = null;
            var i;

            if (option == "student") {
                notElements = document.getElementsByClassName("specific professor");
            }

            else if (option == "professor") {
                notElements = document.getElementsByClassName("specific student");
            }

            else {
                notElements = document.getElementsByClassName("specific");
            }

            for (i = 0; i < elements.length; i++)
                elements[i].style.display = "";

            for (i = 0; i < notElements.length; i++){
                notElements[i].style.display = "none";
            }
        }
    </script>
</head>
<body>
    <form action="./RegisterServlet" method="POST">
        <h1 align="center">Create New User</h1>
        <h3 align="right">Welcome, ${user.name}</h3>
        <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>

        What kind of user do you wish to create?
        <select name="userType" id="userType" onchange="optionCheck(this);">
            <option value="dummy">Choose your option</option>
            <option value="student">Student</option>
            <option value="professor">Professor</option>
        </select>
        <p><input type="text" name="name" placeholder="Name" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
        <p><input type="password" name="password" placeholder="Password" style="text-align: center; font-size:14pt; width: 360px; height: 30px "> </p>
        <p>
            <input type="text" name="day" placeholder="Day" style="text-align: center; font-size:14pt; width: 120px; height: 30px ">
            <input type="text" name="month" placeholder="Month" style="text-align: center; font-size:14pt; width: 120px; height: 30px ">
            <input type="text" name="year" placeholder="Year" style="text-align: center; font-size:14pt; width: 120px; height: 30px ">
        </p>
        <p><input type="text" name="instEmail" placeholder="Institutional Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
        <p><input type="text" name="altEmail" placeholder="Alternative Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px "> </p>
        <p><input type="text" name="address" placeholder="Address" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>
        <p><input type="text" name="telephone" placeholder="Telephone" style="text-align: center; font-size:14pt; width: 360px; height: 30px "> </p>

        <!--Student Info -->

        <p><input type="text" class="specific student"  name="number" placeholder="Number" style="display: none; text-align: center; font-size:14pt; width: 360px; height: 30px "> </p>
        <p><input type="text" class="specific student" name="yearOfCourse" placeholder="Year Of Course" style="display: none; text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>

        <!--Professor Info -->

        <p><input type="text" class="specific professor" name="internalNumber" placeholder="Internal Number" style="display: none; text-align: center; font-size:14pt; width: 350px; height: 30px "> </p>
        <p><input type="text" class="specific professor" name="category" placeholder="Category" style="display: none; text-align: center; font-size:14pt; width: 350px; height: 30px; "> </p>
        <p><input type="text" class="specific professor" name="office" placeholder="Office" style="display: none; text-align: center; font-size:14pt; width: 350px; height: 30px "> </p>
        <p><input type="text" class="specific professor" name="internalTelephoneNumber" placeholder="Internal Telephone Number" style="display: none; text-align: center; font-size:14pt; width: 350px; height: 30px; "> </p>
        <p><input type="text" class="specific professor" name="salary" placeholder="Salary" style="display: none; text-align: center; font-size:14pt; width: 350px; height: 30px "> </p>

        <p class="submit"><input type="submit" name="action" value="register" style="width: 150px; height: 50px"></p>
    </form>
</body>

</html>

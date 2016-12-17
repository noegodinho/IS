<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rocha
  Date: 21/11/2016
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
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
    <form action="./EditUserServlet" method="POST">
        <h1 align="center">Edit User</h1>
        <h3 align="right">Welcome, ${user.name}</h3>
        <p class="submit" align="right"><input type="submit" name="action" value="logout" style="width: 150px; height: 50px"></p>

        What kind of user do you wish to edit?
        <select name="userType" id="userType" onchange="optionCheck(this);">
            <option value="dummy">Choose your option</option>
            <option value="student">Student</option>
            <option value="professor">Professor</option>
        </select>

        <p><input type="text" name="instEmail" placeholder="Institutional Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px; "> </p>

        <p class="submit"><input type="submit" name="action" value="get" style="width: 150px; height: 50px"></p>


        <p><input type="text" name="name" placeholder="Name" style="text-align: center; font-size:14pt; width: 360px; height: 30px; " value = ${userSelected.name}> </p>
        <p><input type="text" name="newInstEmail" placeholder="Institutional Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px; " value = ${userSelected.instEmail}> </p>
        <p><input type="text" name="altEmail" placeholder="Alternative Email" style="text-align: center; font-size:14pt; width: 360px; height: 30px " value = ${userSelected.altEmail}> </p>
        <p><input type="text" name="address" placeholder="Address"  style="text-align: center; font-size:14pt; width: 360px; height: 30px; " value = ${userSelected.address}> </p>
        <p><input type="text" name="telephone" placeholder="Telephone" style="text-align: center; font-size:14pt; width: 360px; height: 30px " value = ${userSelected.telephone}> </p>

        <!--Student Info -->

        <p><input type="text" class="specific student"  name="number" placeholder="Number" style="display: none; text-align: center; font-size:14pt; width: 360px; height: 30px " value = ${userSelected.number}> </p>
        <p><input type="text" class="specific student" name="yearOfCourse" placeholder="Year Of Course" style="display: none; text-align: center; font-size:14pt; width: 360px; height: 30px; " value = ${userSelected.yearOfCourse}> </p>

        <!--Professor Info -->


        <p class="submit"><input type="submit" name="action" value="edit" style="width: 150px; height: 50px"></p>
    </form>
</body>
</html>

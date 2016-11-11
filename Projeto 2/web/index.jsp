<%--
  Created by IntelliJ IDEA.
  User: Rocha
  Date: 21/10/2016
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="./LoginServlet" method="POST">
    <font face="verdana" color="white">
      <section align="center">
        <div class="login">
          <h1>Login to Web App</h1>
          <p><input type="text" name="instEmail" placeholder="Email" style="text-align: center; font-size:14pt; width: 350px; height: 30px; "> </p>
          <p><input type="password" name="password" placeholder="Password" style="text-align: center; font-size:14pt; width: 350px; height: 30px "></p>
          <p class="submit"><input type="submit" name="action" value="login" style="width: 150px; height: 50px"></p>
        </div>
      </section>
    </font>
  </form>
  </body>
</html>

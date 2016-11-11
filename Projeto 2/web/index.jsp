<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Online Learning Platform</title>
  </head>
  <body>
  <form action="./LoginServlet" method="POST">
    <font face="verdana" color="black">
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


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign In</title>
  <link rel="stylesheet" href="css/sign_in.css">
</head>
<body>
  <div class="login-container">
    <h2>Sign In</h2>

    <% String error = request.getParameter("error"); %>
    <form action="LoginServlet" method="post">

      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
      </div>

      <span id="error">
          <% if ("invalid".equals(error)){ %>
            Invalid username or password.
          <% } %>
      </span>
      <button type="submit">Log In</button>
    </form>

    <div class="links">
      Don't have an account? <a href="view/sign_up.jsp">Sign Up</a>
    </div>
  </div>
</body>
</html>



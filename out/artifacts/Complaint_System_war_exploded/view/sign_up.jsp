
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="../css/sign_up.css">
</head>

<body>
<div class="signup-container">
    <h2>Create Account</h2>

    <form action="${pageContext.request.contextPath}/SignupServlet" method="post">

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required maxlength="50">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required maxlength="100">
        </div>

        <div class="form-group">
            <label for="role">Role</label>
            <select id="role" name="role" required>
                <option value="EMPLOYEE">Employee</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>

        <button type="submit">Sign Up</button>
    </form>

    <div class="login-link">
        Already registered? <a href="../index.jsp">Sign in</a>
    </div>
</div>
</body>
</html>



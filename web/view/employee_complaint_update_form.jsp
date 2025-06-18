<%@ page import="com.ijse.gdse72.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Form</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/complaint_form.css">
</head>
<body>
<div class="sidebar">
  <h2>👤 Employee</h2>
  <a href="${pageContext.request.contextPath}/view/employee_dashboard.jsp">Dashboard</a>
  <a href="${pageContext.request.contextPath}/view/employee_complaint_update_form.jsp" class="active">Edit Complaint Form</a>
  <a href="${pageContext.request.contextPath}/index.jsp">Log Out</a>
</div>

<div class="main">
  <h1>Edit Complaint</h1>

  <%
    User user = (User) session.getAttribute("user");
    String userId = user.getU_id();

    if (user == null || !user.getRole().equals("EMPLOYEE")) {
      response.sendRedirect(request.getContextPath() + "/index.jsp");
      return;
    }
  %>

  <form action="${pageContext.request.contextPath}/ComplainEdit" method="post">

    <input type="hidden" name="c_id" value="${complaint.c_id}"/>

    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" id="title" name="title" required maxlength="255"
             value="${complaint.title != null ? complaint.title : ''}">
    </div>

    <div class="form-group">
      <label for="description">Description:</label>
      <textarea id="description" name="description" required rows="5">${complaint.description != null ? complaint.description : ''}</textarea>
    </div>

    <button type="submit">Update Complaint</button>
  </form>
</div>
</body>
</html>

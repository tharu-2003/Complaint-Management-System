
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ijse.gdse72.dao.ComplaintDAO" %>
<%@ page import="com.ijse.gdse72.model.User" %>

<html>
    <head>
        <title>EMPLOYEE</title>
        <link rel="stylesheet" href="../css/employee_dashboard.css">
    </head>
    <body>
        <div class="sidebar">
            <h2>👤 Employee</h2>
            <a href="<%=request.getContextPath()%>/view/employee_dashboard.jsp" class="active">Dashboard</a>
            <a href="<%=request.getContextPath()%>/view/employee_complaint_form.jsp">Complaint Form</a>
            <a href="<%=request.getContextPath()%>/MyComplaintsServlet">My Complaints</a>
            <a href="<%=request.getContextPath()%>/index.jsp">Log Out</a>
         </div>

        <%
            User user = (User) session.getAttribute("user");
            String userId = user.getU_id();

            if (user == null || !user.getRole().equals("EMPLOYEE")) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }

            ComplaintDAO dao = new ComplaintDAO();
            int total = dao.countAllByUser(userId);
            int pending = dao.countByStatusAndUser("PENDING", userId);
            int inProgress = dao.countByStatusAndUser("IN_PROGRESS", userId);
            int resolved = dao.countByStatusAndUser("RESOLVED", userId);
        %>


        <div class="main">
            <h1>Employee Dashboard</h1>
            <h3> User : <%= user.getUsername() %></h3>
            <div class="cards">
                <div class="card"><h3>Total Complaints</h3><div class="count"><%= total %></div></div>
                <div class="card"><h3>Pending</h3><div class="count"><%= pending %></div></div>
                <div class="card"><h3>In Progress</h3><div class="count"><%= inProgress %></div></div>
                <div class="card"><h3>Resolved</h3><div class="count"><%= resolved %></div></div>
            </div>
        </div>
    </body>
</html>

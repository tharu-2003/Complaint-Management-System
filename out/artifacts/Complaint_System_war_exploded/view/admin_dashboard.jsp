<%@ page import="com.ijse.gdse72.dao.ComplaintDAO" %>
<%@ page import="com.ijse.gdse72.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>ADMIN</title>
        <link rel="stylesheet" href="../css/admin_dashboard.css">
    </head>

    <body>
        <div class="sidebar">
            <h2>⚙️ Admin</h2>
            <a href="<%=request.getContextPath()%>/view/admin_dashboard.jsp" class="active">Dashboard</a>
            <a href="<%=request.getContextPath()%>/AdminComplaint">All Complaints</a>
            <a href="<%=request.getContextPath()%>/index.jsp">Log Out</a>
        </div>

        <%
            User user = (User) session.getAttribute("user");

            if (user == null || !user.getRole().equals("ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }

            ComplaintDAO dao = new ComplaintDAO();

            int total = dao.countAll();
            int pending = dao.countByStatus("PENDING");
            int inProgress = dao.countByStatus("IN_PROGRESS");
            int resolved = dao.countByStatus("RESOLVED");
        %>

        <div class="main">
            <h1>Admin Dashboard</h1>
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



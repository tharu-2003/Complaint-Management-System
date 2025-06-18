<%@ page import="com.ijse.gdse72.model.Complaint" %>
<%@ page import="com.ijse.gdse72.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaint Form</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/complaint_form.css">
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>

</head>
<body>
    <div class="sidebar">
        <h2>👤 Employee</h2>
        <a href="<%=request.getContextPath()%>/view/employee_dashboard.jsp">Dashboard</a>
        <a href="<%=request.getContextPath()%>/view/employee_complaint_form.jsp" class="active">Complaint Form</a>
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
    %>

    <div class="main">
        <h1>Submit New Complaint</h1>

        <form id="complaintForm" action="${pageContext.request.contextPath}/SubmitComplaintServlet" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required maxlength="255">
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required rows="5"></textarea>
            </div>
            <button type="button" id="submitBtn">Submit Complaint</button>
        </form>
    </div>

    <script>
        document.getElementById('submitBtn').addEventListener('click', function () {
            Swal.fire({
                title: 'Submit complaint?',
                text: 'Are you sure you want to submit this complaint?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Yes, submit',
                cancelButtonText: 'Cancel',
                confirmButtonColor: '#4CAF50',
                cancelButtonColor: '#3085d6'
            }).then((result) => {
                if (result.isConfirmed) {
                    document.getElementById('complaintForm').submit();
                }
            });
        });
    </script>
</body>
</html>
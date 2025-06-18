<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.ijse.gdse72.model.Complaint" %>
<%@ page import="com.ijse.gdse72.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Complaints</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin_complaint.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
</head>
<body>
<div class="sidebar">
    <h2>👤 Employee</h2>
    <a href="<%=request.getContextPath()%>/view/employee_dashboard.jsp">Dashboard</a>
    <a href="<%=request.getContextPath()%>/view/employee_complaint_form.jsp">Complaint Form</a>
    <a href="<%=request.getContextPath()%>/MyComplaintsServlet" class="active">My Complaints</a>
    <a href="<%=request.getContextPath()%>/index.jsp">Log Out</a>
</div>

<div class="main">
    <h2>My Complaints</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th><th>Title</th><th>Description</th><th>Status</th>
            <th>Created At</th><th>Updated At</th><th>Remarks or Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            User user = (User) session.getAttribute("user");
            String userId = user.getU_id();

            if (user == null || !user.getRole().equals("EMPLOYEE")) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }

            List<Complaint> complaints = (List<Complaint>) request.getAttribute("myComplaints");
            if (complaints != null && !complaints.isEmpty()) {
                for (Complaint c : complaints) {
        %>
        <tr>
            <td><%= c.getC_id() %></td>
            <td><%= c.getTitle() %></td>
            <td><%= c.getDescription() %></td>
            <td><span class="status <%= c.getStatus().toLowerCase() %>"><%= c.getStatus() %></span></td>
            <td><%= c.getCreated_at() %></td>
            <td><%= c.getUpdated_at() %></td>
            <td>
                <% if (c.getRemarks() != null) { %>
                <%= c.getRemarks() %>
                <% } else { %>
                <a id="editBtn" href="<%=request.getContextPath()%>/ComplainEdit?id=<%=c.getC_id()%>">Edit</a>
                <a id="deleteBtn" href="<%=request.getContextPath()%>/DeleteComplaint?id=<%=c.getC_id()%>"
                   class="delete-confirm-btn">Delete</a>
                <% } %>
            </td>
        </tr>
        <%   }
        } else { %>
        <tr>
            <td colspan="7" style="text-align:center;">No complaints found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script>
    document.querySelectorAll('.delete-confirm-btn').forEach(btn => {
        btn.addEventListener('click', function(e) {
            e.preventDefault();
            const url = this.href;

            Swal.fire({
                title: 'Are you sure?',
                text: 'This complaint will be permanently deleted.',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'Cancel',
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6'
            }).then(result => {
                if (result.isConfirmed) {
                    window.location.href = url;
                }
            });
        });
    });
</script>
<script>
    if (new URLSearchParams(window.location.search).get('deleted') === 'true') {
        Swal.fire('Deleted!', 'Your complaint has been deleted.', 'success');

    } else if (new URLSearchParams(window.location.search).get('submitted') === 'true') {
        Swal.fire('Sent!', 'Your complaint has been successfully submitted.', 'success');

    } else if (new URLSearchParams(window.location.search).get('updated') === 'true') {
        Swal.fire('Updated!', 'Your complaint has been successfully updated.', 'success');

    }
</script>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List, com.ijse.gdse72.model.Complaint" %>
<%@ page import="com.ijse.gdse72.model.User" %>
<%@ page import="com.ijse.gdse72.dao.ComplaintDAO" %>

<html>
    <head>
        <title>All Complaint</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin_complaint.css">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
    </head>
    <body>

        <div class="sidebar">
            <h2>⚙️ Admin</h2>
            <a href="<%=request.getContextPath()%>/view/admin_dashboard.jsp" >Dashboard</a>
            <a href="<%=request.getContextPath()%>/AdminComplaint" class="active">All Complaints</a>
            <a href="<%=request.getContextPath()%>/index.jsp">Log Out</a>
        </div>

        <div class="main">
            <h2>All Complaints</h2>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>User Name</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                    <th>Remarks or Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    User user = (User) session.getAttribute("user");
                    String userId = user.getU_id();

                    if (user == null || !user.getRole().equals("ADMIN")) {
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                        return;
                    }

                    ComplaintDAO complaintDAO = new ComplaintDAO();
                    List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");

                    if (complaints != null) {
                        for (Complaint c : complaints) {
                %>
                    <tr>
                        <td><%= c.getC_id() %></td>
                        <td><%= c.getU_id() %></td>
                        <td><%= c.getTitle() %></td>
                        <td><%= c.getDescription() %></td>
                        <td><span class="status <%= c.getStatus().toLowerCase() %>"><%= c.getStatus() %></span></td>
                        <td><%= c.getCreated_at() %></td>
                        <td><%= c.getUpdated_at() %></td>
                        <td>
                            <% if (c.getStatus().equals("RESOLVED")) { %>
                            <%= c.getRemarks() %>
                            <% } else { %>
                            
                                <form action="<%= request.getContextPath() + "/AdminComplaint" %>" method="post">

                                    <input type="hidden" name="c_id" value="<%= c.getC_id() %>"/>

                                    <input type="text" name="remarks" value="<%= c.getRemarks() %>" placeholder="Add remarks" style="width: 120px;" required/>

                                    <select name="status" required>
                                        <option value="" disabled selected>-- Select Status --</option>
                                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                                        <option value="RESOLVED">RESOLVED</option>
                                    </select>

                                    <button id="sendBtn" type="submit">Send</button>
                                </form>

                            <% } %>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8" style="text-align:center;">No complaints found.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <script>
            if (new URLSearchParams(window.location.search).get('updated') === 'true') {
                Swal.fire('Updated!', 'Complaint has been successfully updated.', 'success');

            }
        </script>
    </body>
</html>

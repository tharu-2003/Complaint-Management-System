package com.ijse.gdse72.controller;

import com.ijse.gdse72.dao.ComplaintDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteComplaint")
public class EmployeeComplaintDeleteServlet extends HttpServlet {

    private ComplaintDAO dao = new ComplaintDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cId = req.getParameter("id");

        if (cId == null || cId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/view/employee_complaint.jsp");
            return;
        }

        boolean deleted = dao.deleteComplaint(cId);

        if (deleted) {
            resp.sendRedirect(req.getContextPath() + "/MyComplaintsServlet?deleted=true");
        } else {
            req.setAttribute("error", "Failed to delete complaint!");
            req.getRequestDispatcher("/view/employee_complaint.jsp").forward(req, resp);
        }
    }
}

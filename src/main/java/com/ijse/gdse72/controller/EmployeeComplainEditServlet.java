package com.ijse.gdse72.controller;

import com.ijse.gdse72.dao.ComplaintDAO;
import com.ijse.gdse72.model.Complaint;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/ComplainEdit")
public class EmployeeComplainEditServlet extends HttpServlet {
    private ComplaintDAO dao = new ComplaintDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String complaintId = req.getParameter("id");

        if (complaintId == null || complaintId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/view/employee_complaint.jsp");
            return;
        }

        Complaint c = dao.findComplaintById(complaintId);
        req.setAttribute("complaint", c);
        req.getRequestDispatcher("/view/employee_complaint_update_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cId = req.getParameter("c_id");
        String title = req.getParameter("title");
        String description = req.getParameter("description");


        Complaint c = new Complaint();
        c.setC_id(cId);
        c.setTitle(title);
        c.setDescription(description);
        c.setUpdated_at(null);

        boolean updated = dao.updateComplaintByEmployee(c);

        if (updated) {
            resp.sendRedirect(req.getContextPath() + "/MyComplaintsServlet?updated=true");
        } else {
            req.setAttribute("error", "Failed to update complaint, please try again.");
            req.setAttribute("complaint", c);
            req.getRequestDispatcher("/view/employee_complaint_form.jsp").forward(req, resp);
        }
    }

}

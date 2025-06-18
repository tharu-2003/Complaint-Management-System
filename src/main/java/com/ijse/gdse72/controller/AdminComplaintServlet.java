package com.ijse.gdse72.controller;

import com.ijse.gdse72.dao.ComplaintDAO;
import com.ijse.gdse72.model.Complaint;
import com.ijse.gdse72.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/AdminComplaint")
public class AdminComplaintServlet extends HttpServlet {

    private ComplaintDAO complaintDAO = new ComplaintDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "index.jsp");
            return;
        }

        ComplaintDAO complaintDAO = new ComplaintDAO();
        List<Complaint> complaints = complaintDAO.getComplaints();

        req.setAttribute("complaints", complaints);
        req.getRequestDispatcher("/view/admin_complaint.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cId = req.getParameter("c_id");
        String status = req.getParameter("status");
        String remarks = req.getParameter("remarks");

        Complaint complaint = new Complaint();
        complaint.setC_id(cId);
        complaint.setStatus(status);
        complaint.setRemarks(remarks);

        boolean isUpdated = complaintDAO.updateComplaintByAdmin(complaint);

        if (isUpdated) {
            resp.sendRedirect(req.getContextPath() + "/AdminComplaint?updated=true");
        } else {
            req.setAttribute("error", "Failed to update complaint.");
            req.getRequestDispatcher(req.getContextPath() + "/AdminComplaint").forward(req, resp);
        }
    }
}

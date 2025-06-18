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


@WebServlet("/MyComplaintsServlet")
public class EmployeeComplaintServlet extends HttpServlet {

    private ComplaintDAO complaintDAO = new ComplaintDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(false);

        if (session == null ) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        User currentUser = (User) session.getAttribute("user");
        String userId = currentUser.getU_id();


        List<Complaint> myComplaints = complaintDAO.getComplaintsByUser(userId);

        req.setAttribute("myComplaints", myComplaints);
        req.getRequestDispatcher("/view/employee_complaint.jsp").forward(req, resp);
    }
}
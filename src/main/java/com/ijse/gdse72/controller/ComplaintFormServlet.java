package com.ijse.gdse72.controller;

import com.ijse.gdse72.dao.ComplaintDAO;
import com.ijse.gdse72.model.Complaint;
import com.ijse.gdse72.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SubmitComplaintServlet")
public class ComplaintFormServlet extends HttpServlet {

    private ComplaintDAO dao = new ComplaintDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        String uId = user.getU_id();
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Complaint c = new Complaint();

        c.setC_id(dao.generateId());
        c.setU_id(uId);
        c.setTitle(title);
        c.setDescription(description);
        c.setStatus("PENDING");
        c.setUpdated_at(null);
        c.setRemarks(null);

        boolean isSaved = dao.saveComplaint(c);

        if (isSaved) {
            resp.sendRedirect(req.getContextPath() + "/MyComplaintsServlet?submitted=true");

        } else {
            req.setAttribute("error", "Failed to save complaint!");
            req.getRequestDispatcher("/complaint_form.jsp").forward(req, resp);
        }
    }
}

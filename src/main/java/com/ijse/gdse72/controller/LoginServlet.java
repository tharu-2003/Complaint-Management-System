package com.ijse.gdse72.controller;

import com.ijse.gdse72.dao.UserDAO;
import com.ijse.gdse72.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticate(username, password);

        if(user != null) {
            HttpSession session = req.getSession();

            session.setAttribute("user", user);

            if(user.getRole().equals("ADMIN")) {
                resp.sendRedirect(req.getContextPath() + "/view/admin_dashboard.jsp");
            }else {
                resp.sendRedirect(req.getContextPath() + "/view/employee_dashboard.jsp");
            }

        }else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp?error=invalid");
        }
    }
}

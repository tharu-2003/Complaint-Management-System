package com.ijse.gdse72.controller;

import com.ijse.gdse72.dao.UserDAO;
import com.ijse.gdse72.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SignupServlet")
public class SignUpServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.setAttribute("role", role);

        User newUser = new User(
                userDAO.generateId(),
                username,
                password,
                role
        );

        boolean isSaved = userDAO.addUser(newUser);

        if (isSaved) {
            System.out.println("User added successfully");
            resp.sendRedirect(req.getContextPath() + "/index.jsp?resp = success");

        }else {
            System.out.println("User not added");
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/view/sign_up.jsp").forward(req, resp);
        }
    }
}

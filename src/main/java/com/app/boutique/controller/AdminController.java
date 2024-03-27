package com.app.boutique.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.app.boutique.Service.AdminService;
import com.app.boutique.bo.Admin;
import com.app.boutique.dto.AdminDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.Session;

@WebServlet({"/adminLogin","/logout","/loginSession"})
public class AdminController extends HttpServlet {


    public void  doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        switch (request.getServletPath())
        {
            case "/logout"->
            {
                request.getSession().removeAttribute("admin");
            }
            case "/loginSession"-> {
                request.getSession().setAttribute("error","Vous devez se connecter");
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/AdminLogin.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDTO adminDTO = new AdminDTO(email,password);
        AdminService service=new AdminService();

        Admin admin;

        if ((admin=service.login(adminDTO))!=null) {
            request.getSession().setAttribute("admin",admin);
            request.setAttribute("successMessage", "Login successful!");
            response.sendRedirect(request.getContextPath() + "/AddProduct");
        } else {
            request.getSession().setAttribute("error", "Email ou Mot de passe est inccorecte!");
            doGet(request,response);
        }
    }
}

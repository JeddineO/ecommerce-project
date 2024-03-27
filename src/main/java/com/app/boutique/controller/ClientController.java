package com.app.boutique.controller;

import com.app.boutique.Service.AdminService;
import com.app.boutique.Service.ClientService;
import com.app.boutique.bo.Admin;
import com.app.boutique.bo.Client;
import com.app.boutique.dao.ClientDAO;
import com.app.boutique.dto.AdminDTO;
import com.app.boutique.dto.ClientDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet({"/login","/register"})
public class ClientController extends HttpServlet {


    public void  doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        switch (request.getServletPath())
        {
            case "/login"->
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/ClientLogin.jsp");
                dispatcher.forward(request, response);
            }
            case "/register"->
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/ClientRegister.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath())
        {
            case "/login"->
            {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                ClientDTO clientDTO = new ClientDTO(email,password);
                ClientService service=new ClientService();

                Client client;

                if ((client=service.login(clientDTO))!=null) {
                    request.getSession().setAttribute("client",client);
                    request.setAttribute("successMessage", "Login successful!");
                    response.sendRedirect(request.getContextPath() + "/store");
                } else {
                    request.getSession().setAttribute("error", "Email ou Mot de passe est inccorecte!");
                    doGet(request,response);
                }
            }

            case "/register"->
            {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String nom = request.getParameter("nom");

                Client client=new Client();
                client.setNom(nom);
                client.setEmail(email);
                client.setPassword(password);

                new ClientDAO().save(client);
                response.sendRedirect(request.getContextPath() + "/login");

            }
        }

    }
}

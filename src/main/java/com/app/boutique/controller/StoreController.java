package com.app.boutique.controller;

import com.app.boutique.Service.CategorieService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/store/*")
public class StoreController extends HttpServlet {

    public  void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        CategorieService categorieService=new CategorieService();
        String id=request.getParameter("id");
        if (id!=null) {
            request.setAttribute("categorie",categorieService.finById(Integer.parseInt(id)));
            request.setAttribute("categories",categorieService.getCategories());
            RequestDispatcher dispatcher=request.getRequestDispatcher("view/categorie.jsp");
            dispatcher.forward(request,response);
        }else
        {
            request.setAttribute("categories",categorieService.getCategories());
            RequestDispatcher dispatcher=request.getRequestDispatcher("view/store.jsp");
            dispatcher.forward(request,response);
        }

    }
}

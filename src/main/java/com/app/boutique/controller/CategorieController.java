package com.app.boutique.controller;

import com.app.boutique.Service.CategorieService;
import com.app.boutique.dto.CategorieDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet({"/categorie","/AddCategorie","/UpdateCategorie","/DeleteCategorie"})
public class CategorieController extends HttpServlet {

    private CategorieService categorieService=new CategorieService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("categories",categorieService.getCategories());
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/AddCategorie.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException ,IOException
    {

        switch (request.getServletPath())
        {
            case "/AddCategorie" ->
            {
                String nom=request.getParameter("nomCategorie");
                CategorieDTO categorieDTO=new CategorieDTO();
                categorieDTO.setNomCategorie(nom);
                if(categorieService.insertCategorie(categorieDTO))
                {
                    request.getSession().setAttribute("success", "Catégorie est bien ajouté!");
                    response.sendRedirect(request.getContextPath() + "/categorie");
                }else
                {
                    request.getSession().setAttribute("error","Insertion echoué veuluiez réssayer");
                    response.sendRedirect(request.getContextPath() + "/categorie");

                }
            }
            case "/UpdateCategorie"->
            {
                String nom=request.getParameter("CategorieName");
                String id=request.getParameter("idCategorie");
                CategorieDTO categorieDTO=new CategorieDTO();
                categorieDTO.setNomCategorie(nom);
                if(categorieService.update(categorieDTO,Integer.parseInt(id)))
                {
                    request.getSession().setAttribute("success", "Catégorie est bien modifier!");
                    response.sendRedirect(request.getContextPath() + "/categorie");
                }else
                {
                    request.getSession().setAttribute("error","Modification echoué ");
                    response.sendRedirect(request.getContextPath() + "/categorie");

                }

            }
            case "/DeleteCategorie"->
            {
                int idCategorie=Integer.parseInt( request.getParameter("idCategorie"));
                if(categorieService.deleteCategorie(idCategorie))
                {
                    request.getSession().setAttribute("success", "Catégorie est bien supprimer!");
                    response.sendRedirect(request.getContextPath() + "/categorie");
                }else
                {
                    request.getSession().setAttribute("error","Suppression echoué ");
                    response.sendRedirect(request.getContextPath() + "/categorie");

                }

            }
        }
    }

}

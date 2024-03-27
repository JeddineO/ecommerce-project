package com.app.boutique.controller;

import com.app.boutique.Service.CategorieService;
import com.app.boutique.Service.ProductService;
import com.app.boutique.bo.Categorie;
import com.app.boutique.bo.Produit;
import com.app.boutique.dto.ProduitDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.Part;


@WebServlet(value = {"/AddProduct", "/ListProduits","/UpdateProduct","/DeleteProduct"})
public class ProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        switch (request.getServletPath()) {
            case "/AddProduct" -> {
                CategorieService service = new CategorieService();
                request.setAttribute("categories", service.getCategories());
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/AddProduct.jsp");
                dispatcher.forward(request, response);
            }
            case "/ListProduits" ->
            {
                ProductService service=new ProductService();
                request.setAttribute("produits",service.getProduits());
                CategorieService categorieService = new CategorieService();
                request.setAttribute("categories", categorieService.getCategories());
                RequestDispatcher dispatcher = request.getRequestDispatcher("view/ProductsList.jsp");
                dispatcher.forward(request, response);
            }

        }
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws  ServletException,IOException
    {
        switch (request.getServletPath())
        {
            case "/AddProduct"->
            {
                String idCategorie=request.getParameter("categorie");
                CategorieService serv=new CategorieService();
                Categorie categorie= serv.finById(Integer.parseInt(idCategorie));
                String nomProduit=request.getParameter("nomProduit");
                String prix=request.getParameter("prix");

                String stock=request.getParameter("stock");

                ProduitDTO dto=new ProduitDTO(nomProduit,Double.parseDouble(prix),categorie);
                dto.setStock(Integer.parseInt(stock));
                ProductService service=new ProductService();
                if(service.insert(dto))
                {
                    request.getSession().setAttribute("success", "Produit est bien ajouté!");
                    response.sendRedirect(request.getContextPath() + "/ListProduits");
                }else
                {
                    request.getSession().setAttribute("error","Insertion echoué veuluiez réssayer");
                    response.sendRedirect(request.getContextPath() + "/ListProduits");

                }
            }
            case "/UpdateProduct"->
            {
                String idProduit=request.getParameter("idProduit");
                System.out.println(idProduit);
                ProductService service=new ProductService();
                Produit produit = service.getProduit(Integer.parseInt(idProduit));
                String categorieId=request.getParameter("categorie");
                CategorieService categorieService=new CategorieService();
                Categorie categorie=categorieService.finById(Integer.parseInt(categorieId));
                String name=request.getParameter("editproductName");
                String prix=request.getParameter("editprice");
                String stock=request.getParameter("editstock");
                produit.setCategorie(categorie);
                produit.setPrix(Double.parseDouble(prix));
                produit.setNomProduit(name);
                produit.setStock(Integer.parseInt(stock));
                if(service.editProduit(produit))
                {
                    request.getSession().setAttribute("success", "Produit est bien modifié!");
                    response.sendRedirect(request.getContextPath() + "/ListProduits");
                }else {
                    request.setAttribute("error","Modification echoué veuluiez réssayer");
                    response.sendRedirect(request.getContextPath() + "/ListProduits");
                }
            }
            case "/DeleteProduct"->
            {
                String idProduit=request.getParameter("idProduit");
                ProductService service=new ProductService();
                Produit produit=service.getProduit(Integer.parseInt(idProduit));
                if(service.deleteProduit(produit))
                {
                    request.getSession().setAttribute("success", "Produit est bien supprimé");
                    response.sendRedirect(request.getContextPath() + "/ListProduits");
                }else{
                    request.setAttribute("error","Suppression echoué veuluiez réssayer");
                    response.sendRedirect(request.getContextPath() + "/ListProduits");
                }
            }
        }
    }
}

package com.app.boutique.controller;

import com.app.boutique.Service.CategorieService;
import com.app.boutique.Service.PanierService;
import com.app.boutique.Service.ProductService;
import com.app.boutique.Service.VenteService;
import com.app.boutique.bo.Client;
import com.app.boutique.bo.Commande;
import com.app.boutique.bo.Produit;
import com.app.boutique.bo.Vente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/addPanier","/Payer"})
public class PanierController extends HttpServlet {
    private ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        CategorieService categorieService=new CategorieService();

        switch (request.getServletPath())
        {
            case "/Payer"->
            {
                Client client =(Client) request.getSession().getAttribute("client");
                if (client==null)
                {
                    request.getSession().setAttribute("error", "Vous devez se connecter pour payer!");
                    RequestDispatcher dispatcher=request.getRequestDispatcher("view/ClientLogin.jsp");
                    dispatcher.forward(request,response);

                }else{
                    Vente vente=(Vente) request.getSession().getAttribute("vente");
                    vente.setClient(client);
                    new VenteService().insert(vente);
                    for(Commande c:vente.getCommandes())
                    {
                        Produit p=c.getProduit();
                        p.setStock(p.getStock()-c.getQuantité());
                        productService.editProduit(p);
                    }
                    request.getSession().removeAttribute("vente");
                    request.getSession().setAttribute("success", "Vente effectué avec succes!");
                    request.setAttribute("categories",categorieService.getCategories());
                    RequestDispatcher dispatcher=request.getRequestDispatcher("view/store.jsp");
                    dispatcher.forward(request,response);
                }
            }
            case "/addPanier"->
            {
                String idProduit = request.getParameter("idProduit");
                String quantiteStr = request.getParameter("quantite");


                if (idProduit != null && quantiteStr != null) {
                    Produit produit = productService.getProduit(Integer.parseInt(idProduit));
                    int quantite = Integer.parseInt(quantiteStr);

                    Vente vente=(Vente)request.getSession().getAttribute("vente");
                    if (vente==null)
                        vente=new Vente();
                    Boolean test=true;
                    for (Commande c: vente.getCommandes())
                    {
                        if(c.getProduit().getIdProduit()==produit.getIdProduit())
                        {
                            if(c.getQuantité()+quantite<=produit.getStock())
                            {
                                c.setQuantité(c.getQuantité()+quantite);
                                request.getSession().setAttribute("success", "Ajouté au panier!");

                            }else
                            {
                                request.getSession().setAttribute("error", "Stock non disponible!(Commande non validé dans la panier");

                            }

                            test=false;
                        }

                    }
                    if(test)
                    {
                        Commande commande=new Commande();
                        commande.setQuantité(quantite);
                        commande.setProduit(produit);
                        vente.addCommande(commande);
                    }

                    request.getSession().setAttribute("vente",vente);
                    request.setAttribute("categories",categorieService.getCategories());
                    RequestDispatcher dispatcher=request.getRequestDispatcher("view/store.jsp");
                    dispatcher.forward(request,response);

                } else {
                    // Handle invalid or missing parameters
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println("Invalid or missing parameters.");
                }
            }
        }

    }
}

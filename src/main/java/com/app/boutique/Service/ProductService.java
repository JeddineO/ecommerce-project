package com.app.boutique.Service;

import com.app.boutique.bo.Categorie;
import com.app.boutique.bo.Produit;
import com.app.boutique.dao.CategorieDAO;
import com.app.boutique.dao.ProduitDAO;
import com.app.boutique.dto.ProduitDTO;

import java.util.List;

public class ProductService {
    public boolean insert(ProduitDTO dto)
    {
       return new ProduitDAO().save(this.toProduit(dto));
    }

    public List<Produit> getProduits()
    {
        return new ProduitDAO().findAll();
    }

    public Produit getProduit(int id)
    {
        return  new ProduitDAO().findById(id);
    }

    public boolean editProduit(Produit produit)
    {
        return new ProduitDAO().update(produit);
    }

    public boolean deleteProduit(Produit produit)
    {
        return new ProduitDAO().delete(produit);
    }
    public Produit toProduit(ProduitDTO dto)
    {
        Produit produit=new Produit();
        produit.setNomProduit(dto.getNomProduit());
        produit.setPrix(dto.getPrix());
        produit.setCategorie(dto.getCategorie());
        produit.setStock(dto.getStock());
        return produit;
    }
}

package com.app.boutique.dto;

import com.app.boutique.bo.Categorie;
import com.app.boutique.bo.Commande;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ProduitDTO {


    private String nomProduit;
    private double prix;
    private Categorie categorie;
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProduitDTO(String nomProduit, double prix, Categorie categorie) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.categorie = categorie;
    }



    public String getNomProduit() {
        return nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }
}

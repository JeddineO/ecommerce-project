package com.app.boutique.bo;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;
    private String nomProduit;
    private String description;
    private double prix;
    private String image;
    private int Stock;

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @OneToMany(mappedBy = "produit")
    private List<Commande> commandes;

    @ManyToOne()
    private Categorie categorie;

    public Produit() {
    }

    public Produit(String nomProduit, String description, double prix,Categorie categorie) {
        this.nomProduit = nomProduit;
        this.description = description;
        this.prix = prix;
        this.categorie=categorie;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setCategorie(Categorie categorie) {
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

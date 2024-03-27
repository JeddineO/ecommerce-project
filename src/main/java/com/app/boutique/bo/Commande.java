package com.app.boutique.bo;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCommande;
    private int Quantité;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Vente vente;

    public int getIdCommande() {
        return idCommande;
    }

    public Commande(int quantité) {
        Quantité = quantité;
    }
    public Commande()
    {

    }

    public void setQuantité(int quantité) {
        Quantité = quantité;
    }


    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantité() {
        return Quantité;
    }

    public Produit getProduit() {
        return produit;
    }
}

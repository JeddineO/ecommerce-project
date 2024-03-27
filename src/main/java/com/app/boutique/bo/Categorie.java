package com.app.boutique.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;

    private String nomCategorie;
    @OneToMany(mappedBy = "categorie",fetch = FetchType.EAGER)
    private List<Produit> produits;

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Categorie() {
    }

    public Categorie( String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }


}

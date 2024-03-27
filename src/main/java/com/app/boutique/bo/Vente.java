package com.app.boutique.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


@Entity
public class Vente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVente;

    @OneToMany(mappedBy = "vente")
    private List<Commande> commandes;


    @ManyToOne()
    private Client client;


    public Vente()
    {
        this.commandes=new LinkedList<Commande>();
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public void addCommande(Commande commande)
    {
        this.commandes.add(commande);
    }
}

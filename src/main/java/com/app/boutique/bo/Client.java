package com.app.boutique.bo;


import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String email;
    private String password;
    @OneToMany(mappedBy = "client")
    private  List<Vente> ventes;
    public Client(String nom, String email, String password) {
        this.nom = nom;
        this.email = email;
        this.password = password;
    }
    public Client(){};

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

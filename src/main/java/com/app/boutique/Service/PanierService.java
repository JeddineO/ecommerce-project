package com.app.boutique.Service;

import com.app.boutique.bo.Commande;
import com.app.boutique.bo.Produit;
import com.app.boutique.bo.Vente;
import jakarta.servlet.http.Cookie;
import java.io.*;

public class PanierService {

    public Cookie addProductToCart(Produit produit, int quantite, Cookie[] cookies) {
        // Retrieve the vente object from the cookie
        Vente vente = getVenteFromCookie(cookies);

        // If vente is null, create a new vente object
        if (vente == null) {
            vente = new Vente();
        }

        // Create a new Commande object
        Commande commande = new Commande();
        commande.setProduit(produit);
        commande.setQuantité(quantite);

        // Add the commande to the vente object
        vente.addCommande(commande);

        // Serialize the vente object to a byte array
        byte[] serializedVente = serializeObject(vente);

        // Create a cookie with the serialized vente object
        Cookie cookie = new Cookie("vente", new String(serializedVente));

        // Set additional properties for the cookie
        cookie.setPath("/"); // Set the cookie path to the root
        cookie.setMaxAge(3600); // Set the cookie expiration time (in seconds), e.g., 1 hour

        // Return the created cookie
        return cookie;
    }


    private byte[] serializeObject(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
            return null;
        }
    }

    public Vente getVenteFromCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && cookie.getName().equals("vente")) {
                    // Get the serialized string from the cookie
                    String serializedVente = cookie.getValue();
                    // Deserialize the serialized string back to a Vente object
                    byte[] byteArray = serializedVente.getBytes();
                    return deserializeObject(byteArray);
                }
            }
        }
        return null;
    }

    private Vente deserializeObject(byte[] byteArray) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Vente) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
            return null;
        }
    }
}

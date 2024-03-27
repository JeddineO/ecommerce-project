package com.app.boutique.Service;

import com.app.boutique.bo.Vente;
import com.app.boutique.dao.VenteDAO;

public class VenteService {



    public boolean insert(Vente vente)
    {
        return new VenteDAO().save(vente);
    }
}

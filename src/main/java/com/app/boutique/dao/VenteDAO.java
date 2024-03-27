package com.app.boutique.dao;

import com.app.boutique.bo.Vente;
import com.app.boutique.util.HibernateConf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class VenteDAO implements IDAO<Vente>{
    private SessionFactory factory= HibernateConf.getFactory();

    @Override
    public boolean save(Vente vente) {
        Transaction transaction=null;
        try(Session session= factory.openSession()) {
            transaction=session.beginTransaction();
            session.save(vente);
            transaction.commit();
            return true;
        }catch(Exception e)
        {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Vente object) {
        return false;
    }

    @Override
    public boolean delete(Vente object) {
        return false;
    }

    @Override
    public Vente findById(int motif) {
        return null;
    }
}

package com.app.boutique.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.app.boutique.util.HibernateConf;
import com.app.boutique.bo.Commande;

public class CommandeDAO implements IDAO<Commande>{
    private SessionFactory factory= HibernateConf.getFactory();

    @Override
    public boolean save(Commande commande) {
        Transaction transaction=null;
        try(Session session= factory.openSession())
        {
            transaction=session.beginTransaction();
            session.save(commande);
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
    public boolean update(Commande commande) {
        Transaction transaction=null;
        try(Session session= factory.openSession())
        {
            transaction=session.beginTransaction();
            session.update(commande);
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
    public boolean delete(Commande commande) {
        Transaction transaction=null;
        try(Session session= factory.openSession())
        {
            transaction=session.beginTransaction();
            session.delete(commande);
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
    public Commande findById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Commande.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

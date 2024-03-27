package com.app.boutique.dao;

import com.app.boutique.bo.Categorie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.app.boutique.util.HibernateConf;
import com.app.boutique.bo.Produit;
import org.hibernate.query.Query;

import java.util.List;

public class ProduitDAO implements IDAO<Produit>{
    private SessionFactory factory= HibernateConf.getFactory();

    @Override
    public boolean save(Produit produit) {
        Transaction transaction=null;
        try(Session session= factory.openSession()) {
            transaction=session.beginTransaction();
            session.save(produit);
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
    public boolean update(Produit produit) {
        Transaction transaction=null;
        try(Session session= factory.openSession()) {
            transaction=session.beginTransaction();
            session.update(produit);
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
    public boolean delete(Produit produit) {
        Transaction transaction=null;
        try(Session session= factory.openSession()) {
            transaction=session.beginTransaction();
            session.delete(produit);
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
    public Produit findById(int id) {
        try (Session session = factory.openSession())
        {
            return session.get(Produit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produit> findAll() {
        try (Session session = factory.openSession()) {
            Query<Produit> query = session.createQuery("FROM Produit ", Produit.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

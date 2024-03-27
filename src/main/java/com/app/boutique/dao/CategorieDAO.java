package com.app.boutique.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.app.boutique.util.HibernateConf;
import com.app.boutique.bo.Categorie;
import org.hibernate.query.Query;

import java.util.List;

public class CategorieDAO implements IDAO<Categorie> {
    private SessionFactory factory = HibernateConf.getFactory();

    @Override
    public boolean save(Categorie categorie) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(categorie);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Categorie categorie) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(categorie);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Categorie categorie) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(categorie);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return false;
        }
    }

    @Override
    public Categorie findById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Categorie.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categorie> findAll() {
        try (Session session = factory.openSession()) {
            Query<Categorie> query = session.createQuery("FROM Categorie", Categorie.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

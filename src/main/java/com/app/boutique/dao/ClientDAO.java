package com.app.boutique.dao;

import com.app.boutique.bo.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.app.boutique.util.HibernateConf;
import com.app.boutique.bo.Client;
import org.hibernate.query.Query;

public class ClientDAO implements IDAO<Client> {
    private SessionFactory factory = HibernateConf.getFactory();

    @Override
    public boolean save(Client client) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Client client) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Client login(Client client) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            Query<Client> query = session.createQuery("from Client where email = :email and password= :password", Client.class);
            query.setParameter("email", client.getEmail());
            query.setParameter("password",client.getPassword());
            Client user = query.uniqueResult();

            if (user != null ) {
                // Password matches
                transaction.commit();
                return user;
            } else {
                transaction.rollback();
                return null;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client findById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Client.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

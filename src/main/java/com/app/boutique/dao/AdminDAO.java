package com.app.boutique.dao;

import com.app.boutique.bo.Admin;
import com.app.boutique.util.HibernateConf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.query.Query;

public class AdminDAO
{
    private SessionFactory factory= HibernateConf.getFactory();

        public Admin login(Admin admin) {
            Transaction transaction = null;
            try (Session session = factory.openSession()) {
                transaction = session.beginTransaction();

                Query<Admin> query = session.createQuery("from Admin where email = :email and password= :password", Admin.class);
                query.setParameter("email", admin.getEmail());
                query.setParameter("password",admin.getPassword());
                Admin user = query.uniqueResult();

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



    }

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.jasapengiriman.dao.KurirDao;
import org.jasapengiriman.model.Kurir;
import org.jasapengiriman.util.HibernateUtil;

/**
 *
 * @author Polma E. Tambunan
 */
public class KurirDaoImplHibernate implements KurirDao
{
    @Override
    public void saveDataKurir(Kurir p) 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(p);
        session.getTransaction().commit();
        HibernateUtil.closeSession();    
    }

    @Override
    public List<Kurir> getAllKurir() 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Kurir> kurirs = 
                session.createCriteria(Kurir.class).list();
        session.getTransaction().commit();
        return kurirs;
    }

    @Override
    public void updateStatus(int id, String status) 
    {
        for(Kurir kurir : getAllKurir())
        {
            if(kurir.getId() == id)
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                kurir.setStatus(status);
                session.update(kurir);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }            
        }
    }

    @Override
    public void updateDataKurir(Kurir p) 
    {
        for(Kurir kurir : getAllKurir())
        {
             if(kurir.getId() == p.getId())
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                kurir = p;
                session.update(kurir);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }
            
        }
    }

    @Override
    public void deleteDataKurir(int id) 
    {
        for(Kurir kurir : getAllKurir())
        {
            if(kurir.getId() == id)
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                session.delete(kurir);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }
            
        }
    }
    
}

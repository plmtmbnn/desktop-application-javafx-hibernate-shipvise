/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.jasapengiriman.dao.PengirimanDao;
import org.jasapengiriman.model.Pengiriman;
import org.jasapengiriman.util.HibernateUtil;

/**
 *
 * @author Polma E. Tambunan
 */
public class PengirimanDaoImplHibernate implements PengirimanDao
{

    @Override
    public void saveDataPengiriman(Pengiriman p) 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(p);
        session.getTransaction().commit();
        HibernateUtil.closeSession();    
    }

    @Override
    public List<Pengiriman> getAllPengiriman() 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Pengiriman> pengirimans = 
                session.createCriteria(Pengiriman.class).list();
        session.getTransaction().commit();
        return pengirimans;
    }   

    @Override
    public void updateStatus(String noResi, String status) 
    {
       for(Pengiriman pengiriman : getAllPengiriman())
        {
            if(pengiriman.getNomorResi().equalsIgnoreCase(noResi))
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                pengiriman.setStatus(status);
                session.update(pengiriman);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }            
        }
    }

    @Override
    public void deleteDataPengiriman(String noResi) 
    {
        for(Pengiriman pengiriman : getAllPengiriman())
        {
             if(pengiriman.getNomorResi().equalsIgnoreCase(noResi))
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                session.delete(pengiriman);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }
            
        }
    }

    @Override
    public void updateDataPengiriman(Pengiriman p) 
    {
        for(Pengiriman pengiriman : getAllPengiriman())
        {
             if(pengiriman.getId() == p.getId())
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                pengiriman = p;
                session.update(pengiriman);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }
            
        }
    }
}

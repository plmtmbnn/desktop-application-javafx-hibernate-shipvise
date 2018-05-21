/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.jasapengiriman.dao.MobilDao;
import org.jasapengiriman.model.Mobil;
import org.jasapengiriman.util.HibernateUtil;

/**
 *
 * @author Polma E. Tambunan
 */
public class MobilDaoImplHibernate implements MobilDao
{

    @Override
    public void saveDataMobil(Mobil p) 
    {
       Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(p);
        session.getTransaction().commit();
        HibernateUtil.closeSession();   
    }

    @Override
    public List<Mobil> getAllMobil() 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Mobil> mobils = 
                session.createCriteria(Mobil.class).list();
        session.getTransaction().commit();
        return mobils;
    }

    @Override
    public void updateStatus(int id, String status) 
    {
        for(Mobil mobil : getAllMobil())
        {
            if(mobil.getId() == id)
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                mobil.setStatus(status);
                session.update(mobil);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }            
        }
    }

    @Override
    public void updateDataMobil(Mobil p) 
    {
       for(Mobil mobil : getAllMobil())
        {
            if(mobil.getId() == p.getId())
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                mobil = p;
                session.update(mobil);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }
        }
    }

    @Override
    public void deleteDataMobil(int id) 
    {
        for(Mobil mobil : getAllMobil())
        {
            if(mobil.getId() == id)
            {
                Session session = HibernateUtil.getSession();
                session.getTransaction().begin();
                session.delete(mobil);
                session.getTransaction().commit();
                HibernateUtil.closeSession();
                break;
            }
            
        }
    }
    
}

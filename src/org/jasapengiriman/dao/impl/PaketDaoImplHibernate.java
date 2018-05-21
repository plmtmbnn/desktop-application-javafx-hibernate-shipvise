package org.jasapengiriman.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.jasapengiriman.dao.PaketDao;
import org.jasapengiriman.model.Paket;
import org.jasapengiriman.util.HibernateUtil;

/**
 *
 * @author Polma E. Tambunan
 */
public class PaketDaoImplHibernate implements PaketDao
{

    @Override
    public List<Paket> getAllPaket() 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Paket> pakets = 
                session.createCriteria(Paket.class).list();
        session.getTransaction().commit();
        return pakets;
    }
}

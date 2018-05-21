package org.jasapengiriman.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.jasapengiriman.dao.TrackDao;
import org.jasapengiriman.model.Track;
import org.jasapengiriman.util.HibernateUtil;

/**
 *
 * @author Polma E. Tambunan
 */
public class TrackDaoImplHibernate implements TrackDao
{

    @Override
    public List<Track> getAllTrack() 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Track> tracks = 
                session.createCriteria(Track.class).list();
        session.getTransaction().commit();
        return tracks;
    }
}

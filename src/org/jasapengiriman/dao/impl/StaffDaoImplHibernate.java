package org.jasapengiriman.dao.impl;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.password;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.jasapengiriman.dao.StaffDao;
import org.jasapengiriman.model.Staff;
import org.jasapengiriman.util.HibernateUtil;

/**
 *
 * @author Polma E. Tambunan
 */
public class StaffDaoImplHibernate implements StaffDao
{

    @Override
    public void saveDataStaff(Staff s) 
    {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(s);
        session.getTransaction().commit();
        HibernateUtil.closeSession();    
    }

    @Override
    public List<Staff> getAllStaff() 
    {
       Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Staff> staffs = 
                session.createCriteria(Staff.class).list();
        session.getTransaction().commit();
        return staffs;
    }
 
    Map<String, String> DB = new HashMap<String, String>();
    public static final String SALT = "my-salt-text";

    
    @Override
    public boolean login(String user, String pass) {
        boolean loginF = false;
       
        String saltedPassword = SALT + pass;
		String hashedPassword = generateHash(saltedPassword);
        
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<Staff> staffs = getAllStaff();
        for(Staff staff : staffs){
            if(staff.getUsername().equalsIgnoreCase(user) && staff.getPassword().equalsIgnoreCase(hashedPassword)){
                loginF = true;
                break;
            }
        }
        return loginF;
    }
    
    public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}

}

package org.jasapengiriman.dao;

import java.util.List;
import org.jasapengiriman.model.Staff;

/**
 *
 * @author Polma E. Tambunan
 */
public interface StaffDao 
{
    public void saveDataStaff(Staff s);
    public List<Staff> getAllStaff();
    public boolean login(String user, String pass);
}

package org.jasapengiriman.dao;

import java.util.List;
import org.jasapengiriman.model.Kurir;
/**
 *
 * @author Polma E. Tambunan
 */
public interface KurirDao 
{
    public void saveDataKurir(Kurir p);
    public List<Kurir> getAllKurir();
    public void updateStatus(int id, String status);
    public void updateDataKurir(Kurir p);
    public void deleteDataKurir(int id);
}

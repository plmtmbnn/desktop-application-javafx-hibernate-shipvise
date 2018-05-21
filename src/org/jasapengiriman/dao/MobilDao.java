/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.dao;

import java.util.List;
import org.jasapengiriman.model.Mobil;

/**
 *
 * @author Polma E. Tambunan
 */
public interface MobilDao 
{
    public void saveDataMobil(Mobil p);
    public List<Mobil> getAllMobil();
    public void updateStatus(int id, String status);
    public void updateDataMobil(Mobil p);
    public void deleteDataMobil(int id);
}

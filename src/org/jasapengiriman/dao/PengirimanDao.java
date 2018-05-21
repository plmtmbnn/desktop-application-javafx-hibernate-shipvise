/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.dao;

import java.util.List;
import org.jasapengiriman.model.Pengiriman;

/**
 *
 * @author Polma E. Tambunan
 */
public interface PengirimanDao 
{
    public void saveDataPengiriman(Pengiriman p);
    public List<Pengiriman> getAllPengiriman();
    public void updateStatus(String noResi, String status);
    public void updateDataPengiriman(Pengiriman p);
    public void deleteDataPengiriman(String noResi);
}

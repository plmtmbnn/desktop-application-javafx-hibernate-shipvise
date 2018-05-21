/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Polma E. Tambunan
 */
@Entity
@Table(name ="track")
public class Track 
{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="daerahAsal")
    private String daerahAsal;
    @Column(name ="daerahTujuan")
    private String daerahTujuan;
    @Column(name ="harga")
    private int harga;

    public Track() {
    }
    
    
    
    public Track(int id, String daerahAsal, String daerahTujuan, int harga) {
        this.id = id;
        this.daerahAsal = daerahAsal;
        this.daerahTujuan = daerahTujuan;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDaerahAsal() {
        return daerahAsal;
    }

    public void setDaerahAsal(String daerahAsal) {
        this.daerahAsal = daerahAsal;
    }

    public String getDaerahTujuan() {
        return daerahTujuan;
    }

    public void setDaerahTujuan(String daerahTujuan) {
        this.daerahTujuan = daerahTujuan;
    }

    public int getHarga() 
    {
        return harga;
    }

    public void setHarga(int harga) 
    {
        this.harga = harga;
    }    
}

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
@Table(name ="mobil")
public class Mobil 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="tipe")
    private String tipe;
    @Column(name ="keterangan")
    private String keterangan;
    @Column(name ="status")
    private String status;
    @Column(name ="nomorPelat")
    private String nomorPelat;

    

    public Mobil(int id, String tipe,  String keterangan, String status, String nomorPelat) {
        this.id = id;
        this.tipe = tipe;
        
        this.keterangan = keterangan;
        this.status = status;
        this.nomorPelat = nomorPelat;
    }

    public Mobil() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

   

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomorPelat() {
        return nomorPelat;
    }

    public void setNomorPelat(String nomorPelat) {
        this.nomorPelat = nomorPelat;
    }
}

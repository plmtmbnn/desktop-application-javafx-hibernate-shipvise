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
@Table(name ="kurir")
public class Kurir 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="nama")
    private String nama;
    @Column(name ="alamat")
    private String alamat;
    @Column(name ="email")
    private String email;
    @Column(name ="nomorHp")
    private String nomorHp;
    @Column(name ="status")
    private String status;
    @Column(name ="tempatLahir")
    private String tempatLahir;
    @Column(name ="tanggalLahir")
    private String tanggalLahir;

    public Kurir() {
    }

    public Kurir(int id, String nama, String alamat, String email, String nomorHp, String status, String tempatLahir, String tanggalLahir) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.nomorHp = nomorHp;
        this.status = status;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

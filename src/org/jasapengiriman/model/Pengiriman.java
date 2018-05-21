package org.jasapengiriman.model;

import java.io.Serializable;
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
@Table(name ="pengiriman")
public class Pengiriman 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="pengirim")
    private String pengirim;
    @Column(name ="tanggalPengiriman")
    private String tanggalPengiriman;
    @Column(name ="rute")
    private String rute;
    @Column(name ="alamat")
    private String alamat;
    @Column(name ="keterangan")
    private String keterangan;
    @Column(name ="berat")
    private int berat;
    @Column(name ="status")
    private String status;
    @Column(name ="jenisPaket")
    private String jenisPaket;
    @Column(name ="harga")
    private int harga;
@Column(name ="nomorResi")
    private String nomorResi;
    @Column(name ="penerima")
    private String penerima;
    @Column(name ="alamatPenerima")
    private String alamatPenerima;
    @Column(name ="nomorHpPenerima")
    private String nomorHpPenerima;
    @Column(name ="nomorHpPengirim")
    private String nomorHpPengirim;
    
    
    
    public Pengiriman() {
    }

    public Pengiriman(int id, String pengirim, String tanggalPengiriman, String rute, String alamat, String keterangan, int berat, String status, String jenisPaket, int harga, String nomorResi, String penerima, String alamatPenerima, String nomorHpPenerima, String nomorHpPengirim) {
        this.id = id;
        this.pengirim = pengirim;
        this.tanggalPengiriman = tanggalPengiriman;
        this.rute = rute;
        this.alamat = alamat;
        this.keterangan = keterangan;
        this.berat = berat;
        this.status = status;
        this.jenisPaket = jenisPaket;
        this.harga = harga;
        this.nomorResi = nomorResi;
        this.penerima = penerima;
        this.alamatPenerima = alamatPenerima;
        this.nomorHpPenerima = nomorHpPenerima;
        this.nomorHpPengirim = nomorHpPengirim;
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getTanggalPengiriman() {
        return tanggalPengiriman;
    }

    public void setTanggalPengiriman(String tanggalPengiriman) {
        this.tanggalPengiriman = tanggalPengiriman;
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getJenisPaket() {
        return jenisPaket;
    }

    public void setJenisPaket(String jenisPaket) {
        this.jenisPaket = jenisPaket;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 

    public String getNomorResi() {
        return nomorResi;
    }

    public void setNomorResi(String nomorResi) {
        this.nomorResi = nomorResi;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    public void setAlamatPenerima(String alamatPenerima) {
        this.alamatPenerima = alamatPenerima;
    }

    public String getNomorHpPenerima() {
        return nomorHpPenerima;
    }

    public void setNomorHpPenerima(String nomorHpPenerima) {
        this.nomorHpPenerima = nomorHpPenerima;
    }

    public String getNomorHpPengirim() {
        return nomorHpPengirim;
    }

    public void setNomorHpPengirim(String nomorHpPengirim) {
        this.nomorHpPengirim = nomorHpPengirim;
    }
    
    
}

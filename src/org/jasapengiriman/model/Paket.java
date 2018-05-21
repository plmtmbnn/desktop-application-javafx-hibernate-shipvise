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
@Table(name ="paket")
public class Paket 
{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="namaPaket")
    private String namaPaket;
    @Column(name ="penambahanHarga")
    private double penambahanHarga;

    public Paket() {
    }

    
    public Paket(int id, String namaPaket, double penambahanHarga) 
    {
        this.id = id;
        this.namaPaket = namaPaket;
        this.penambahanHarga = penambahanHarga;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNamaPaket() 
    {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public double getPenambahanHarga() {
        return penambahanHarga;
    }

    public void setPenambahanHarga(double penambahanHarga) {
        this.penambahanHarga = penambahanHarga;
    }
    
}

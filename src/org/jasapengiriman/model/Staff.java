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
@Table(name ="staff")
public class Staff 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="nama")
    private String nama;
    @Column(name ="username")
    private String username;
    @Column(name ="password")
    private String password;
    @Column(name ="nomorHp")
    private String nomorHp;
    @Column(name ="alamat")
    private String Alamat;

    public Staff(int id, String nama, String username, String password, String nomorHp, String Alamat) 
    {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.nomorHp = nomorHp;
        this.Alamat = Alamat;
    }

    public Staff() {
    }

    
    public int getNik() 
    {
        return id;
    }

    public void setNik(int id) 
    {
        this.id = id;
    }

    public String getNama() 
    {
        return nama;
    }

    public void setNama(String nama) 
    {
        this.nama = nama;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getNomorHp() 
    {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) 
    {
        this.nomorHp = nomorHp;
    }

    public String getAlamat() 
    {
        return Alamat;
    }

    public void setAlamat(String Alamat) 
    {
        this.Alamat = Alamat;
    } 
}

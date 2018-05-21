/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jasapengiriman.dao.KurirDao;
import org.jasapengiriman.dao.impl.KurirDaoImplHibernate;
import org.jasapengiriman.model.Kurir;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class FormKurirController implements Initializable 
{
    @FXML
    private TextField namaTF;
    @FXML
    private DatePicker tanggalDP;
    @FXML
    private TextField tempatLahirTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextArea alamatTA;
    @FXML
    private TextField nomorHpTF;
    @FXML
    private Label namaL;
    @FXML
    private Label ttlL;
    @FXML
    private Label noHpL;
    @FXML
    private Label emailL;
    @FXML
    private Label alamatL;
    @FXML
    
    KurirDao kurirDao;
    
    public FormKurirController()
    {
        kurirDao = new KurirDaoImplHibernate();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tanggalDP.setValue(LocalDate.now());
    }    
    
    @FXML
    public void btnTambahkanKurir(ActionEvent event) throws IOException
    {
        String nama = namaTF.getText();
        String tempatLahir = tempatLahirTF.getText();
        String tanggalPengirim = tanggalDP.getValue().toString();       
        String email = emailTF.getText();
        String nomorHp = nomorHpTF.getText();
        String alamat = alamatTA.getText();
        String status = "Available";
        if(namaTF.getText().equalsIgnoreCase("") && tempatLahirTF.getText().equals(""))
        {
            namaL.setVisible(true);
            namaL.setText("nama tidak boleh kosong");
            ttlL.setVisible(true);
            ttlL.setText("masukkan tempat lahir");
        }
        
        if(nomorHpTF.getText().equalsIgnoreCase("") && emailTF.getText().equals("") && alamatTA.getText().equals(""))
        {
            noHpL.setVisible(true);
            noHpL.setText("nama tidak boleh kosong");
            emailL.setVisible(true);
            emailL.setText("masukkan tempat lahir");
            alamatL.setVisible(true);
            alamatL.setText("alamat tidak boleh kosong");
        }
        
        //int id, String nama, String alamat, String email, String nomorHp, String status, String ttl
        else{
        Kurir k = new Kurir(0, nama, alamat, email, nomorHp, status, tempatLahir,tanggalPengirim);
        System.out.println("dipanggil cesss");
        kurirDao.saveDataKurir(k);
        JOptionPane.showMessageDialog(null, "Berhasil ditambahkan !");
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Kurir.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
        }
    }
    
    @FXML
    public void btnKembali(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Kurir.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FormEditDataKurirController implements Initializable 
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
    private Label noHpL;
    @FXML
    private Label emailL;
    @FXML
    private Label alamatL;
    @FXML
    private Label ttlL;
    
            
    private KurirDao kurirDao;
    
    ObservableList<Kurir> data;
    
    static ArrayList<Integer> penampungId = new ArrayList();
    
    public static void getPenampungId(int id)
    {
        penampungId.add(id);
    }
    
    public FormEditDataKurirController()
    {
        kurirDao = new KurirDaoImplHibernate();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tanggalDP.setValue(LocalDate.now());
        loadData();
    }    
    
    public void loadData()
    {
        
        List<Kurir> pa = kurirDao.getAllKurir();
        ObservableList<String> id = FXCollections.observableArrayList();
        for(Kurir p : pa)
        {
            id.add(String.valueOf(p.getId()));
        }
      
        for(Kurir p : pa)
        {
           if(KurirController.penampungId.get(0) == p.getId())
           {
               namaTF.setText(p.getNama());
               tanggalDP.setValue(LocalDate.parse(p.getTanggalLahir()));
               tempatLahirTF.setText(p.getTempatLahir());
               alamatTA.setText(p.getAlamat());
               emailTF.setText(p.getEmail());
               nomorHpTF.setText(p.getNomorHp());
           }
        }
    }
   

    
    @FXML
    public void updateData(ActionEvent event) throws IOException
    {
        List<Kurir> pa = kurirDao.getAllKurir();
        for(Kurir p : pa)
       {
           if(KurirController.penampungId.get(0) == p.getId())
           {
               String nama = namaTF.getText();
               String tempatLahir = tempatLahirTF.getText();
               String tanggalLahir = tanggalDP.getValue().toString();       
               String email = emailTF.getText();
               String nomorHp = nomorHpTF.getText();
               String alamat = alamatTA.getText();
               String status = p.getStatus();
               
                if(namaTF.getText().equalsIgnoreCase("") && nomorHpTF.getText().equals(""))
        {
            namaL.setVisible(true);
            namaL.setText("nama tidak boleh kosong");
            noHpL.setVisible(true);
            noHpL.setText("no.Hp tidak boleh kosong");
        }
                if(emailTF.getText().equalsIgnoreCase("") && alamatTA.getText().equals("") && tempatLahirTF.getText().equals(""))
        {
            emailL.setVisible(true);
            emailL.setText("email tidak boleh kosong");
            alamatL.setVisible(true);
            alamatL.setText("alamat tidak boleh kosong");
            ttlL.setVisible(true);
            ttlL.setText("tempat lahir kosong");
        }
                else{
                Kurir k = new Kurir(p.getId(), nama, alamat, email, nomorHp, status, tempatLahir, tanggalLahir);
                kurirDao.updateDataKurir(k);
                JOptionPane.showMessageDialog(null, "Berhasil diupdate !");
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                Pane myPane = null;
                myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Kurir.fxml"));
                Scene scene = new Scene(myPane);
                stage.setScene(scene);
                stage.show();
                }
           }
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

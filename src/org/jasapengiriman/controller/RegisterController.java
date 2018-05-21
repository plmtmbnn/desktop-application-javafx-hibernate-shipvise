/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jasapengiriman.dao.StaffDao;
import org.jasapengiriman.dao.impl.StaffDaoImplHibernate;
import org.jasapengiriman.model.Staff;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class RegisterController implements Initializable 
{   
    @FXML
    private TextField namaTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField nomorHpTF;
    @FXML
    private TextField alamatTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField confirmPasswordTF;
    @FXML
    private Label suksesL;
    @FXML
    private Label namaL;
    @FXML
    private Label usernameRL;
    @FXML
    private Label noHPL;
    @FXML
    private Label alamatL;
    @FXML
    private Label passL;
    @FXML
    private Label confPassL;
    
    ObservableList<Staff> data;
    
    private StaffDao sDao;
    
    public RegisterController()
    {
        sDao = new StaffDaoImplHibernate();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
    }
    
    @FXML
    public void btnDaftar(ActionEvent event) throws IOException
    {
        String password = passwordTF.getText();
        String nama = namaTF.getText();
        String username = usernameTF.getText();
        String nomorHp = nomorHpTF.getText();
        String alamat = alamatTF.getText();
            
        
        if(namaTF.getText().equalsIgnoreCase("") && usernameTF.getText().equals(""))
        {
            namaL.setVisible(true);
            namaL.setText("nama tidak boleh kosong");
            usernameRL.setVisible(true);
            usernameRL.setText("username tidak boleh kosong");
        }
        if(nomorHpTF.getText().equalsIgnoreCase("") && alamatTF.getText().equals(""))
        {
            noHPL.setVisible(true);
            noHPL.setText("no.Hp tidak boleh kosong");
            alamatL.setVisible(true);
            alamatL.setText("alamat tidak boleh kosong");
        }
        
        
        if(passwordTF.getText().equalsIgnoreCase("") && confirmPasswordTF.getText().equals(""))
        {
            passL.setVisible(true);
            passL.setText("password tidak boleh kosong");
            confPassL.setVisible(true);
            confPassL.setText("confirmasi password tidak boleh kosong");
        }
        
        else if(!confirmPasswordTF.getText().equals(password))
        {
            LabelAwalRegistrasiPass();
            confPassL.setVisible(true);
            confPassL.setText("password tidak sesuai");
        }
        else
        {   
            String saltedPassword = SALT + password;
            String hashedPassword = generateHash(saltedPassword);
            LabelAwalRegistrasi();
            DB.put(username, hashedPassword);
            Staff  s = new Staff(0, nama, username, hashedPassword, nomorHp, alamat);   
            suksesL.setText("Pendaftaran sukses !");
            suksesL.setVisible(true);
            sDao.saveDataStaff(s);
            inisialisasiAwalInputanStaff();
            JOptionPane.showMessageDialog(null, "Sukses !");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Login.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
        }
    }
    
    Map<String, String> DB = new HashMap<String, String>();
    public static final String SALT = "my-salt-text";
    
    public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
    
    @FXML
    public void btnBack(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Login.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    public void loadData()
    {
        List<Staff> staffs = sDao.getAllStaff();
        data = FXCollections.observableArrayList(staffs);
        String nama = namaTF.getText();
        String username = usernameTF.getText();
        String nomorHp = nomorHpTF.getText();
        String alamat = alamatTF.getText();
        String password = passwordTF.getText();
        String confirmasi = confirmPasswordTF.getText();
    }

    private void inisialisasiAwalInputanStaff() {
        namaTF.setText("");
        usernameTF.setText("");
        nomorHpTF.setText("");
        alamatTF.setText("");
        passwordTF.setText("");
        confirmPasswordTF.setText("");
    }

    private void LabelAwalRegistrasi() {
       namaL.setText("");
       usernameRL.setText("");
       noHPL.setText("");
       alamatL.setText("");
       passL.setText("");
       confPassL.setText("");
       suksesL.setText("");
    }

    private void LabelAwalRegistrasiPass() {
       namaL.setText("");
       usernameRL.setText("");
       noHPL.setText("");
       alamatL.setText("");
       passL.setText("");
       suksesL.setText("");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jasapengiriman.dao.StaffDao;
import org.jasapengiriman.dao.impl.StaffDaoImplHibernate;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class LoginController implements Initializable 
{

   @FXML
   private  TextField usernameTF;
   @FXML
   private PasswordField passwordTF;
   @FXML
   private  Label usernameL;
   @FXML
   private Label passwordL;

   static ArrayList<String> penampungUsername = new ArrayList();

   
    public static void addPenampungUsername(String komputer)
    {
        penampungUsername.add(komputer);
        
    }

   
   private StaffDao staffDao;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       loadData();
    }    
    
    
    public LoginController()
    {
        staffDao = new StaffDaoImplHibernate();
    }
    
 
    
    @FXML
    public void btnLogin(ActionEvent event) throws IOException
    {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        
        addPenampungUsername(username);
        
        if(staffDao.login(username, password))
        {   
            usernameL.setVisible(false);
            passwordL.setVisible(false);
            
            
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            Stage stage = new Stage();
            Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Home.fxml"));
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            stage.show();
            loadData();
        }
        else
        {
            usernameL.setVisible(true);
            usernameL.setText("username tidak sesuai");
            passwordL.setVisible(true);
            passwordL.setText("password tidak sesuai");
        }
        
        if(usernameTF.getText().equalsIgnoreCase("") && passwordTF.getText().equals(""))
        {
            usernameL.setVisible(true);
            usernameL.setText("username tidak boleh kosong");
            passwordL.setVisible(true);
            passwordL.setText("password tidak boleh kosong");
        }
        
    }
    
    @FXML
    public void btnRegister(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Register.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();

    }
    
    public void loadData()
    {
        usernameL.setVisible(false);
        passwordL.setVisible(false);
    }
}

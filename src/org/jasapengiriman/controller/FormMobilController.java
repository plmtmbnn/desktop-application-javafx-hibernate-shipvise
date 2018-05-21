package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jasapengiriman.dao.MobilDao;
import org.jasapengiriman.dao.impl.MobilDaoImplHibernate;
import org.jasapengiriman.model.Mobil;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class FormMobilController implements Initializable {
    @FXML
    private ComboBox tipeCB;
    @FXML
    private TextField nomorPelatTX;
    @FXML
    private TextArea keteranganTA;
    @FXML
    private Label noPelatL;
    @FXML
    private Label keteranganL;
    
    
    MobilDao mobilDao;
    
     ObservableList<String> tipe = FXCollections.observableArrayList("Sedan","Truk","Bus");
    
    public FormMobilController()
    {
        mobilDao = new MobilDaoImplHibernate();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//       tipeCB.setItems(tipe);   
       loadData();
    }    
    
    public void loadData()
    {
       tipeCB.getItems().clear();
       noPelatL.setVisible(false);
       keteranganL.setVisible(false);
       tipeCB.setItems(tipe);
       tipeCB.setValue(tipe.get(0));
    }        
            
    @FXML
    public void btnTambahkanMobil(ActionEvent event) throws IOException
    {
        String tipe = tipeCB.getValue().toString();       
        String nomorPelat = nomorPelatTX.getText();
        String keterangan = keteranganTA.getText();
        String status = "Available";
        if(nomorPelat.equalsIgnoreCase(""))// && keterangan.equals(""))
        {
            noPelatL.setVisible(true);            
            keteranganL.setVisible(true);           
        }
        else
        {
            noPelatL.setVisible(false);
            keteranganL.setVisible(false);
            Mobil p = new Mobil(0, tipe, keterangan, status, nomorPelat);
            mobilDao.saveDataMobil(p);
            JOptionPane.showMessageDialog(null, "Berhasil ditambahkan !");
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Pane myPane = null;
            myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Mobil.fxml"));
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
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Mobil.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
}

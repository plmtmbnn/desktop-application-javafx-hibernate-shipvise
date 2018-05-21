package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class FormEditDataMobilController implements Initializable 
{
    @FXML
    private TextField nomorPelatTX;
    @FXML
    private TextArea keteranganTA;
    @FXML
    private Label noPelatL;
    @FXML
    private Label ketL;
    
    
    @FXML
    private ComboBox tipeCB;
    
    private final MobilDao mobilDao;
    
    ObservableList<String> tipe = FXCollections.observableArrayList("Sedan","Truk","Bus");
    ObservableList<Mobil> data;
    
    public FormEditDataMobilController()
    {
        mobilDao = new MobilDaoImplHibernate();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadData();    
    }    

    public  void loadData() {
       
        tipeCB.setItems(tipe);
        tipeCB.setValue(tipe.get(0));
        List<Mobil> mo = mobilDao.getAllMobil();
        ObservableList<String> id = FXCollections.observableArrayList();
        for(Mobil m : mo)
        {
            id.add(String.valueOf(m.getId()));
        }
        
        for(Mobil m : mo)
        {
           if(MobilController.penampungId.get(0) == m.getId())
           {
               tipeCB.setValue(m.getTipe());
               nomorPelatTX.setText(m.getNomorPelat());
               keteranganTA.setText(m.getKeterangan());
           }
       }
        
    }
    
    
    @FXML
    public void updateData(ActionEvent event)throws IOException
    {
        List<Mobil> mo = mobilDao.getAllMobil();
        for(Mobil m : mo)
       {
           if(MobilController.penampungId.get(0) == m.getId())
           {
               String nomorPelat = nomorPelatTX.getText();
               String keterangan = keteranganTA.getText();
               String tipe = tipeCB.getValue().toString();
               String status = m.getStatus();
               
                if(nomorPelatTX.getText().equalsIgnoreCase("") && keteranganTA.getText().equals(""))
        {
            noPelatL.setVisible(true);
            noPelatL.setText("no pelat tidak boleh kosong");
            ketL.setVisible(true);
            ketL.setText("masukkan keterangan");
        }
                else{
               Mobil a = new Mobil(m.getId(), tipe, keterangan, status, nomorPelat);
               mobilDao.updateDataMobil(a);
               JOptionPane.showMessageDialog(null, "Berhasil diupdate !");
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                Pane myPane = null;
                myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Mobil.fxml"));
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
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Mobil.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
}

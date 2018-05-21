package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jasapengiriman.dao.PengirimanDao;
import org.jasapengiriman.dao.impl.PengirimanDaoImplHibernate;
import org.jasapengiriman.model.Pengiriman;
import org.jasapengiriman.controller.LoginController;
import org.jasapengiriman.dao.StaffDao;
import org.jasapengiriman.dao.impl.StaffDaoImplHibernate;
import org.jasapengiriman.model.Staff;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class HomeController implements Initializable 
{   
    @FXML
    private TextField nomorResiTF;
    @FXML
    private Label statusL;
    @FXML
    private Label haiL;
    
    private PengirimanDao pengirimanDao;

    private StaffDao staffDao;
            
    public HomeController() 
    {
           staffDao = new StaffDaoImplHibernate();
           pengirimanDao = new PengirimanDaoImplHibernate();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tulisLabelUsername();
    }    
    
    public void tulisLabelUsername()
    {
        List<Staff> pa = staffDao.getAllStaff();
        for(Staff s : pa)
        {
            if(s.getUsername().equals(LoginController.penampungUsername.get(0)))
            {
                haiL.setText("Hai, "+s.getNama()+"!");
               
            }
        }
    }
    
    @FXML
    public void btnCariNomorResi(ActionEvent event) 
    {
        String nomorResi = nomorResiTF.getText();        
        List<Pengiriman> pengirimans = pengirimanDao.getAllPengiriman();
        for (Pengiriman p : pengirimans)
        {
            if(nomorResi.equalsIgnoreCase(p.getNomorResi()))
            {
                statusL.setText("Status : "+p.getStatus());
                break;
            }
            else if(!nomorResi.equalsIgnoreCase(p.getNomorResi()))
            {
                statusL.setText("No. Resi tidak ditemukan");
            }
        }
    }
    
    @FXML
    public void btnPengiriman(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Pengiriman.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void btnStatistik(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Statistik.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void btnMobil(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Mobil.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void btnKurir(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Kurir.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void btnLogout(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Login.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
}

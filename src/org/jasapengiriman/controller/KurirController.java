package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static org.jasapengiriman.controller.MobilController.getPenampungId;
import static org.jasapengiriman.controller.MobilController.penampungId;
import org.jasapengiriman.dao.KurirDao;
import org.jasapengiriman.dao.impl.KurirDaoImplHibernate;
import org.jasapengiriman.model.Kurir;
import org.jasapengiriman.model.Pengiriman;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class KurirController implements Initializable 
{
    @FXML
    private ComboBox statusCB;
    @FXML
    private TextField idTF;
    
    
    @FXML
    private TableView<Kurir> kurirTB;
    @FXML
    private TableColumn<Kurir,Integer> id;
    @FXML
    private TableColumn<Kurir,String> nama;
    @FXML
    private TableColumn<Kurir,String> ttl;
    @FXML
    private TableColumn<Kurir,String> email;
    @FXML
    private TableColumn<Kurir,String> alamat;
    @FXML
    private TableColumn<Kurir,String> nomorHp;
    @FXML
    private TableColumn<Kurir,String> status;
       
    ObservableList<Kurir> data;
   
    private KurirDao kurirDao;
    
    static ArrayList<Integer> penampungId = new ArrayList();
    
    public static void getPenampungId(int id)
    {
        penampungId.add(id);
    }
    
    
    public KurirController()
    {
        kurirDao = new KurirDaoImplHibernate();
        kurirTB = new TableView<>();
    }
    
    public void loadData()
    {
        statusCB.getItems().clear();
        
        List<Kurir> kurirs = kurirDao.getAllKurir();
        data = FXCollections.observableArrayList(kurirs);
        id.setCellValueFactory(
        new PropertyValueFactory<Kurir, Integer>("id"));
        nama.setCellValueFactory(
        new PropertyValueFactory<Kurir, String>("nama"));
        ttl.setCellValueFactory(
        new PropertyValueFactory<Kurir, String>("tempatLahir"));
        email.setCellValueFactory(
        new PropertyValueFactory<Kurir, String>("email"));
        alamat.setCellValueFactory(
        new PropertyValueFactory<Kurir, String>("alamat"));
        nomorHp.setCellValueFactory(
        new PropertyValueFactory<Kurir, String>("nomorHp"));
        status.setCellValueFactory(
        new PropertyValueFactory<Kurir, String>("status"));
        
        kurirTB.setItems(data);
        ObservableList<String> status = 
        FXCollections.observableArrayList(
        "Available",
        "Busy"
        );
        statusCB.getItems().addAll(status);
        statusCB.setValue(status.get(0));
        
        kurirTB.setOnMouseClicked((MouseEvent me) -> 
        {
            penampungId.clear();
            if (me.getClickCount() >= 1) 
            {
                int  id = kurirTB.getItems().get(kurirTB.getSelectionModel().getSelectedIndex()).getId();
                idTF.setText(String.valueOf(id));
                getPenampungId(id);
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       loadData();
    }    
    
    @FXML
    public void konfirmasiStatus(ActionEvent event) 
    {        
        kurirDao.updateStatus(Integer.parseInt(idTF.getText()), statusCB.getValue().toString()); 
        System.out.println(statusCB.getValue().toString());
        refreshTable();
        JOptionPane.showMessageDialog(null, "Konfirmasi berhasil !");
    }
    
    
    @FXML
    public void hapusDataKurir(ActionEvent event) 
    {   
        kurirDao.deleteDataKurir(Integer.parseInt(idTF.getText()));
        refreshTable();
        JOptionPane.showMessageDialog(null, "Berhasil dihapus !");
    }
    
    
    @FXML
    public void btnTambahKurir(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/FormKurir.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void btnUpdateKurir(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/FormEditDataKurir.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    void refreshTable() 
    {
        final List<Kurir> items = kurirTB.getItems();
        
        if( items == null || items.size() == 0) return;

        final Kurir item = kurirTB.getItems().get(0);
        items.remove(0);
        Platform.runLater(new Runnable()
        {
            @Override
            public void run() {
                items.add(0, item);
            }
        });
        loadData();
    }
 
    
    @FXML
    public void btnHome(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Home.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
}

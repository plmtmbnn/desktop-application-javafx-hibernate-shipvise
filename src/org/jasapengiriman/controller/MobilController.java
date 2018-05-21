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
import org.jasapengiriman.dao.MobilDao;
import org.jasapengiriman.dao.impl.MobilDaoImplHibernate;
import org.jasapengiriman.model.Mobil;


/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class MobilController implements Initializable {
    @FXML
    private ComboBox statusCB;
    @FXML
    private TextField idTF;
   

    @FXML
    private TableView<Mobil> mobilTB;
    @FXML
    private TableColumn<Mobil, Integer> id;
    @FXML
    private TableColumn<Mobil,String> tipe;
    @FXML
    private TableColumn<Mobil,Integer> nomorP;
    @FXML
    private TableColumn<Mobil,String> keterangan;
    @FXML
    private TableColumn<Mobil,String> status;
       
    ObservableList<Mobil> data;
   
    private MobilDao mobilDao;
    
    static ArrayList<Integer> penampungId = new ArrayList();
    
    public static void getPenampungId(int id)
    {
        penampungId.add(id);
    }
    
    public MobilController()
    {
        mobilDao = new MobilDaoImplHibernate();
        mobilTB = new TableView<>();
    }

    public void loadData() {
        statusCB.getItems().clear();
        
        List<Mobil> mobils = 
                mobilDao.getAllMobil();
        data = FXCollections.observableArrayList(mobils);
        id.setCellValueFactory(new PropertyValueFactory<Mobil, Integer>("id"));
        tipe.setCellValueFactory(new PropertyValueFactory<Mobil, String>("tipe"));
        nomorP.setCellValueFactory(new PropertyValueFactory<Mobil, Integer>("nomorPelat"));
        keterangan.setCellValueFactory(new PropertyValueFactory<Mobil, String>("keterangan"));
        status.setCellValueFactory(new PropertyValueFactory<Mobil, String>("status"));
        
        mobilTB.setItems(data);
        ObservableList<String> status = FXCollections.observableArrayList(
        "Available",
        "Busy"
        );
        statusCB.getItems().addAll(status);
        statusCB.setValue(status.get(0));
        mobilTB.setOnMouseClicked((MouseEvent me) -> 
        {
            penampungId.clear();
            if (me.getClickCount() >= 1) 
            {
                int  id = mobilTB.getItems().get(mobilTB.getSelectionModel().getSelectedIndex()).getId();
                idTF.setText(String.valueOf(id));
                getPenampungId(id);
            }
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadData();
    }    
    
    @FXML
    public void konfirmasiStatus(ActionEvent event) 
    {        
        mobilDao.updateStatus(Integer.parseInt(idTF.getText()), statusCB.getValue().toString()); 
        System.out.println(statusCB.getValue().toString());
        refreshTable();
        JOptionPane.showMessageDialog(null, "Konfirmasi sukses !");
    }
    
    @FXML
    public void hapusDataMobil(ActionEvent event) 
    {   
        mobilDao.deleteDataMobil(Integer.parseInt(idTF.getText()));
        refreshTable();
        JOptionPane.showMessageDialog(null, "Berhasil dihapus !");
    }
    
    @FXML
    public void btnTambahMobil(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/FormMobil.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void btnUpdateMobil(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/FormEditDataMobil.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
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

    void refreshTable() {
         final List<Mobil> items = mobilTB.getItems();
        
        if( items == null || items.size() == 0) return;

        final Mobil item = mobilTB.getItems().get(0);
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
    
}

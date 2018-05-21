package org.jasapengiriman.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.jasapengiriman.dao.PengirimanDao;
import org.jasapengiriman.dao.impl.PengirimanDaoImplHibernate;
import org.jasapengiriman.model.Pengiriman;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class PengirimanController implements Initializable 
{    
    @FXML
    private ComboBox statusCB;
    @FXML
    private TextField idTF;
            
            
    @FXML
    private TableView<Pengiriman> pengirimanTB;
    @FXML
    private TableColumn<Pengiriman,Integer> id;
    @FXML
    private TableColumn<Pengiriman,String> pengirim;
    @FXML
    private TableColumn<Pengiriman,String> tanggalPengiriman;
    @FXML
    private TableColumn<Pengiriman,String> penerima;
    @FXML
    private TableColumn<Pengiriman,String> alamat;
    @FXML
    private TableColumn<Pengiriman,String> keterangan;
    @FXML
    private TableColumn<Pengiriman,Integer> berat;
    @FXML
    private TableColumn<Pengiriman,String> jenisPaket;
    @FXML
    private TableColumn<Pengiriman,Integer> harga;
    @FXML
    private TableColumn<Pengiriman,String> status;
    
    ObservableList<Pengiriman> data;
    
    private PengirimanDao pDao;

    static ArrayList<String> penampungNoResi = new ArrayList();
    
    public static void getPenampungNoresi(String noResi)
    {
        penampungNoResi.add(noResi);
    }
    
    public PengirimanController() 
    {
        pDao = new PengirimanDaoImplHibernate();
        pengirimanTB = new TableView<>();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
    }    
    
    public void loadData()
    {
        
        statusCB.getItems().clear();
        //int id, String pengirim, String tanggalPengiriman, String rute, String alamat, String keterangan, int berat, String jenisPaket, int harga, String status)
  
        List<Pengiriman> pengirimans = pDao.getAllPengiriman();
        data = FXCollections.observableArrayList(pengirimans);
        id.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, Integer>("nomorResi"));
        pengirim.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, String>("pengirim"));
        tanggalPengiriman.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, String>("tanggalPengiriman"));
        penerima.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, String>("penerima"));
        alamat.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, String>("alamatPenerima"));
        keterangan.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, String>("keterangan"));
        berat.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, Integer>("berat"));
        jenisPaket.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, String>("jenisPaket"));
        harga.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, Integer>("harga"));
        status.setCellValueFactory(
        new PropertyValueFactory<Pengiriman, String>("status"));
        
        pengirimanTB.setItems(data);
        
        ObservableList<String> status = 
        FXCollections.observableArrayList(
        "Sending",
        "Received"
        );
        statusCB.getItems().addAll(status);
        statusCB.setValue(status.get(0));
        
        pengirimanTB.setOnMouseClicked((MouseEvent me) -> 
        {
            penampungNoResi.clear();
            if (me.getClickCount() >= 1) 
            {
                String  nomorResi = pengirimanTB.getItems().get(pengirimanTB.getSelectionModel().getSelectedIndex()).getNomorResi();
                idTF.setText(nomorResi);
                getPenampungNoresi(nomorResi);
            }
        });
        
    }
   
    
    
    @FXML
    public void btnSavePdf(ActionEvent event)
    {
        if(idTF.getText().equalsIgnoreCase(""))
        {
             JOptionPane.showMessageDialog(null, "No Resi tidak ditemukan !");
        }
        else
        {
        
        List<Pengiriman> pengirimans = pDao.getAllPengiriman();
        for(Pengiriman p : pengirimans)
        {
            if(p.getNomorResi().equalsIgnoreCase(idTF.getText()))
            {
            try 
            {
                String namaDocumen = "FormPengiriman" + p.getPengirim();
                Document doc = new Document();
            try 
            {
                PdfWriter.getInstance(doc, new FileOutputStream(namaDocumen + ".pdf"));
            } 
            catch (DocumentException ex) 
            {
                Logger.getLogger(PengirimanController.class.getName()).log(Level.SEVERE, null, ex);
            }
                doc.open();
                doc.add(new Paragraph("SHIPVISE - Jasa pengiriman cepat dan aman"));
                doc.add(new Paragraph("Jl. Sisingamangaraja No 27, Situluama, Toba Samosir\n\n\n"));
                
                doc.add(new Paragraph("-------------------------------------------------------------------------"));
                doc.add(new Paragraph("Pengirim : "));
                doc.add(new Paragraph("-------------------------------------------------------------------------"));
                doc.add(new Paragraph(p.getPengirim()));
                doc.add(new Paragraph(p.getAlamat()));
                doc.add(new Paragraph(p.getNomorHpPengirim()+"\n"));
                doc.add(new Paragraph("-------------------------------------------------------------------------"));         
                doc.add(new Paragraph("Penerima : "));
                doc.add(new Paragraph("-------------------------------------------------------------------------"));
                doc.add(new Paragraph(p.getPenerima()));
                doc.add(new Paragraph(p.getAlamatPenerima()));
                doc.add(new Paragraph(p.getNomorHpPenerima()));                
                doc.close();
                JOptionPane.showMessageDialog(null, "Generate ke PDF sukses !");
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(PengirimanController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (DocumentException ex) 
            {
                Logger.getLogger(PengirimanController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        }
        
    }
    
    @FXML
    public void btnTambahPengiriman(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/FormPengiriman.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    public void btnUpdatePengiriman(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/FormEditDataPengiriman.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    public void konfirmasiStatus(ActionEvent event) 
    {  
        if(idTF.getText().equalsIgnoreCase(""))
        {
             JOptionPane.showMessageDialog(null, "No Resi tidak ditemukan !");
        }
        else
        {
        pDao.updateStatus(idTF.getText(), statusCB.getValue().toString()); 
        System.out.println(statusCB.getValue().toString());
        JOptionPane.showMessageDialog(null, "Konfirmasi sukses !");
        refreshTable();
        }
    }
    
    
    @FXML
    public void hapusDataPengiriman(ActionEvent event) 
    {   
        if(idTF.getText().equalsIgnoreCase(""))
        {
             JOptionPane.showMessageDialog(null, "No Resi tidak ditemukan !");
        }
        else
        {
            pDao.deleteDataPengiriman(idTF.getText());
            refreshTable();
            JOptionPane.showMessageDialog(null, "Berhasil dihapus !");

        }
    }
    
    void refreshTable() 
    {
        final List<Pengiriman> items = pengirimanTB.getItems();
        
        if( items == null || items.size() == 0) return;

        final Pengiriman item = pengirimanTB.getItems().get(0);
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


package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import org.jasapengiriman.dao.PaketDao;
import org.jasapengiriman.dao.PengirimanDao;
import org.jasapengiriman.dao.TrackDao;
import org.jasapengiriman.dao.impl.PaketDaoImplHibernate;
import org.jasapengiriman.dao.impl.PengirimanDaoImplHibernate;
import org.jasapengiriman.dao.impl.TrackDaoImplHibernate;
import org.jasapengiriman.model.Paket;
import org.jasapengiriman.model.Pengiriman;
import org.jasapengiriman.model.Track;

/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class FormEditDataPengirimanController implements Initializable 
{
    @FXML
    private TextField namaTF;
    @FXML
    private ComboBox beratCB;
    @FXML
    private DatePicker tanggalDP;
    @FXML
    private ComboBox ruteCB;
    @FXML
    private ComboBox paketCB;
    @FXML
    private TextArea alamatTA;
    @FXML
    private TextArea keteranganTA;
    @FXML
    private Label hargaL;
    @FXML
    private Label namaPerL;
    @FXML
    private Label namaPenL;
    @FXML
    private Label alamatL;
    @FXML
    private Label alamatPenL;
    @FXML
    private Label noHpPngL;
    @FXML
    private Label noHpPnrL;
    @FXML
    private Label keteranganL;
    
    
    @FXML
    private TextField namaPenerimaTF;
    @FXML
    private TextArea alamatPenerimaTA;
    @FXML
    private TextField nomorHpPengirimTF;
    @FXML
    private TextField nomorHpPenerimaTF;
    
    
    ObservableList<Pengiriman> data;
    ObservableList<Paket> dataPaket;
    
    private PengirimanDao pengirimanDao;
    private PaketDao paketDao;
    private TrackDao trackDao;
    
    
    private int hargaPaket;
    private int hargaTrack;
    private int hargaTotal;
    private int berat;

    public FormEditDataPengirimanController() 
    {
        pengirimanDao = new PengirimanDaoImplHibernate();
        paketDao = new PaketDaoImplHibernate();
        trackDao = new TrackDaoImplHibernate();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tanggalDP.setValue(LocalDate.now());
        loadData();
    }    
    
    public void loadData()
    {
        noHpPngL.setVisible(false);
        noHpPnrL.setVisible(false);
        keteranganL.setVisible(false);
        namaPerL.setVisible(false);
        namaPenL.setVisible(false);
        alamatL.setVisible(false);
        alamatPenL.setVisible(false);
    
            
        ruteCB.getItems().clear();
        paketCB.getItems().clear();
        beratCB.getItems().clear();
        
        
        ObservableList<String> namaPaket = FXCollections.observableArrayList();
        ObservableList<String> trackTujuan = FXCollections.observableArrayList();
        ObservableList<Integer> berat = FXCollections.observableArrayList(
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        ObservableList<String> nomorResi = FXCollections.observableArrayList();
        

        
        List<Paket> pa = paketDao.getAllPaket();
        List<Track> tr = trackDao.getAllTrack();
        List<Pengiriman> pe = pengirimanDao.getAllPengiriman();
        
        for(Pengiriman p : pe)
        {
            nomorResi.add(p.getNomorResi());
        }
        
        for(Track t : tr)
        {
            trackTujuan.add(t.getDaerahTujuan());
        }
//        
        for(Paket p : pa)
        {
            namaPaket.add(p.getNamaPaket());
        }

        beratCB.getItems().addAll(berat);
        beratCB.setValue(berat.get(0));
        ruteCB.getItems().addAll(trackTujuan);
        ruteCB.setValue(trackTujuan.get(0));
        paketCB.getItems().addAll(namaPaket);
        paketCB.setValue(namaPaket.get(0));      
        
       // List<Pengiriman> pa = pengirimanDao.getAllPengiriman();
        for(Pengiriman p : pe)
       {
           if(PengirimanController.penampungNoResi.get(0).equalsIgnoreCase(p.getNomorResi()))
           {
               namaTF.setText(p.getPengirim());
               tanggalDP.setValue(LocalDate.parse(p.getTanggalPengiriman()));
               ruteCB.setValue(p.getRute());
               alamatTA.setText(p.getAlamat());
               beratCB.setValue(p.getBerat());
               paketCB.setValue(p.getJenisPaket());
               keteranganTA.setText(p.getKeterangan());
               namaPenerimaTF.setText(p.getPenerima());
               alamatPenerimaTA.setText(p.getAlamatPenerima());
               nomorHpPenerimaTF.setText(p.getNomorHpPenerima());
               nomorHpPengirimTF.setText(p.getNomorHpPengirim());
               hargaL.setText("Harga " + String.valueOf(p.getHarga()));
           }
       }
    }
    
    
    @FXML
    public void pilihPaket(ActionEvent event)
    {
       System.out.println(Integer.parseInt(beratCB.getValue().toString()));
       List<Paket> pa = paketDao.getAllPaket();
       for(Paket p : pa)
       {
           if(paketCB.getValue().toString().equals(p.getNamaPaket()))
           {
               hargaPaket = (int) (hargaTrack * p.getPenambahanHarga());                       
           }
       }
       totalHarga();
    }
    
    public void totalHarga()
    {
        hargaTotal = (hargaTrack + hargaPaket) * berat;
        hargaL.setText("Harga : " + hargaTotal);
    }
    
    @FXML
    public void pilihTrack(ActionEvent event)
    {
       List<Track> tr = trackDao.getAllTrack();
       for(Track t : tr)
       {
           if(ruteCB.getValue().toString().equals(t.getDaerahTujuan()))
           {
               hargaTrack = t.getHarga() ;               
           }
       }
       totalHarga();
    }
    
    @FXML
    public void pilihBerat(ActionEvent event)
    {
        berat = Integer.parseInt(beratCB.getValue().toString());
        totalHarga();
    }
    

    @FXML
    public void updateData(ActionEvent event) throws IOException
    {
        List<Pengiriman> pa = pengirimanDao.getAllPengiriman();
        for(Pengiriman p : pa)
        {
           if(PengirimanController.penampungNoResi.get(0).equals(p.getNomorResi()))
           {
                String pengirim = namaTF.getText();
                String tanggalPengirim = tanggalDP.getValue().toString();       
                String rute = ruteCB.getValue().toString();
                String alamat = alamatTA.getText();
                String keterangan = keteranganTA.getText();
                String jenisPaket = paketCB.getValue().toString();
                String status = p.getStatus();
                String nomorResi = p.getNomorResi();
                String penerima = namaPenerimaTF.getText();
                String alamatPenerima = alamatPenerimaTA.getText();
                String nomorHpPenerima = nomorHpPenerimaTF.getText();
                String nomorHpPengirim = nomorHpPengirimTF.getText();
                
                if(namaTF.getText().equalsIgnoreCase("") && namaPenerimaTF.getText().equals(""))
        {
            namaPerL.setVisible(true);
            namaPenL.setVisible(true);
        }
                
                if(alamatPenerimaTA.getText().equalsIgnoreCase("") && alamatTA.getText().equals(""))
        {
            alamatL.setVisible(true);
            alamatPenL.setVisible(true);
        }
                
                if(nomorHpPengirimTF.getText().equalsIgnoreCase("") && nomorHpPenerimaTF.getText().equals("") && keteranganTA.getText().equalsIgnoreCase(""))
        {
            noHpPngL.setVisible(true);
            noHpPnrL.setVisible(true);
            keteranganL.setVisible(true);
        }
                
                else
                {
                //(int id, String pengirim, String tanggalPengiriman, String rute, String alamat, String keterangan, int berat, String status,
                //String jenisPaket, int harga, String nomorResi, String penerima, String alamatPenerima, String nomorHpPenerima, String nomorHpPengirim)
                Pengiriman pe = new Pengiriman(p.getId(), pengirim, tanggalPengirim, rute, alamat, keterangan, berat, status, jenisPaket, hargaTotal, nomorResi, penerima, alamatPenerima, nomorHpPenerima, nomorHpPengirim);
                pengirimanDao.updateDataPengiriman(pe);
                JOptionPane.showMessageDialog(null, "Berhasil diupdate !");
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Pengiriman.fxml"));
                Scene scene = new Scene(myPane);
                stage.setScene(scene);
                stage.show();
                break;
                }
           }
        }
    }
    
    
    @FXML
    public void btnKembali(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Pengiriman.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
}

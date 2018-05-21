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

import org.jasapengiriman.dao.TrackDao;
import org.jasapengiriman.dao.PengirimanDao;
import org.jasapengiriman.dao.PaketDao;

import org.jasapengiriman.dao.impl.PengirimanDaoImplHibernate;
import org.jasapengiriman.dao.impl.TrackDaoImplHibernate;
import org.jasapengiriman.dao.impl.PaketDaoImplHibernate;

import org.jasapengiriman.model.Pengiriman;
import org.jasapengiriman.model.Track;
import org.jasapengiriman.model.Paket;


/**
 * FXML Controller class
 *
 * @author Polma E. Tambunan
 */
public class FormPengirimanController implements Initializable 
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
    private TextField namaPenerimaTF;
    @FXML
    private TextArea alamatPenerimaTA;
    @FXML
    private TextField nomorHpPengirimTF;
    @FXML
    private TextField nomorHpPenerimaTF;
    @FXML
    private Label namaL;
    @FXML
    private Label namaPL;
    @FXML
    private Label alamatL;
    @FXML
    private Label noHpL;
    @FXML
    private Label NoHpPL;
    @FXML
    private Label alamatPL;
    @FXML
    private Label keteranganL;
    
    
    
    
    ObservableList<Pengiriman> data;
    ObservableList<Paket> dataPaket;
    
    private PengirimanDao pengirimanDao;
    private PaketDao paketDao;
    private TrackDao trackDao;
    
    
    private int hargaPaket;
    private int hargaTrack;
    private int hargaTotal;
    private int berat;

    
    public FormPengirimanController() 
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
        
        ruteCB.getItems().clear();
        paketCB.getItems().clear();
        beratCB.getItems().clear();
        
        ObservableList<String> namaPaket = FXCollections.observableArrayList();
        ObservableList<String> trackTujuan = FXCollections.observableArrayList();
        ObservableList<Integer> berats = FXCollections.observableArrayList(
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        
        
        List<Paket> pa = paketDao.getAllPaket();
        List<Track> tr = trackDao.getAllTrack();
        
        hargaTotal=tr.get(0).getHarga();
        double hargaPaketDou;
        hargaPaketDou = pa.get(0).getPenambahanHarga();
        hargaPaket=(int) hargaPaketDou;
        hargaTrack=tr.get(0).getHarga();
        berat = berats.get(0);
        totalHarga();
        for(Track t : tr)
        {
            trackTujuan.add(t.getDaerahTujuan());
        }
        
        for(Paket p : pa)
        {
            namaPaket.add(p.getNamaPaket());
            System.out.println(p.getNamaPaket());
        }
        System.out.println(namaPaket);

        beratCB.getItems().addAll(berats);
        beratCB.setValue(berats.get(0));
        ruteCB.getItems().addAll(trackTujuan);
        ruteCB.setValue(trackTujuan.get(0));
        paketCB.getItems().addAll(namaPaket);
        paketCB.setValue(namaPaket.get(0));               
    }
    
    @FXML
    public void btnTambahkanPengiriman(ActionEvent event) throws IOException
    {
        String pengirim = namaTF.getText();
        String tanggalPengirim = tanggalDP.getValue().toString();       
        String rute = ruteCB.getValue().toString();
        String alamat = alamatTA.getText();
        String keterangan = keteranganTA.getText();
        String jenisPaket = paketCB.getValue().toString();
        System.out.println(tanggalPengirim);
        String status = "Sending";
        String nomorResi = "SHIP" + pengirimanDao.getAllPengiriman().size();
        String penerima = namaPenerimaTF.getText();
        String alamatPenerima = alamatPenerimaTA.getText();
        String nomorHpPenerima = nomorHpPenerimaTF.getText();
        String nomorHpPengirim = nomorHpPengirimTF.getText();
        
        if(namaTF.getText().equalsIgnoreCase("") && namaPenerimaTF.getText().equals(""))
        {
            namaL.setVisible(true);
            namaL.setText("nama tidak boleh kosong");
            namaPL.setVisible(true);
            namaPL.setText("nama penerima tidak boleh kosong");
        }
        
        if(alamatPenerimaTA.getText().equalsIgnoreCase("") && nomorHpPengirimTF.getText().equals(""))
        {
            alamatL.setVisible(true);
            alamatL.setText("alamat tidak boleh kosong");
            noHpL.setVisible(true);
            noHpL.setText("no.Hp tidak boleh kosong");
        }
        
        if(nomorHpPenerimaTF.getText().equalsIgnoreCase("") && alamatTA.getText().equals("") && keteranganTA.getText().equals(""))
        {
            NoHpPL.setVisible(true);
            NoHpPL.setText("no.Hp tidak boleh kosong");
            alamatPL.setVisible(true);
            alamatPL.setText("alamat tidak boleh kosong");
            keteranganL.setVisible(true);
            keteranganL.setText("keterangan tidak boleh kosong");
        }
        //Pengiriman(int id, String pengirim, String tanggalPengiriman, String rute, String alamat, String keterangan, int berat, String status, String jenisPaket, int harga, String nomorResi, String penerima, String alamatPenerima, String nomorHpPenerima, String nomorHpPengirim)
        else{
        Pengiriman p = new Pengiriman(0, pengirim, tanggalPengirim, rute, alamat, keterangan, berat, status, jenisPaket, hargaTotal, nomorResi, penerima, alamatPenerima, nomorHpPenerima, nomorHpPengirim);
        pengirimanDao.saveDataPengiriman(p);
        JOptionPane.showMessageDialog(null, "Berhasil ditambahkan !");
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Pengiriman.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
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
    public void btnKembali(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource("/org/jasapengiriman/view/Pengiriman.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jasapengiriman.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
public class StatistikController implements Initializable 
{
    @FXML
    private BarChart barChartHarian;
    @FXML
    private DatePicker tanggalDP;
    @FXML
    private Label tanggalL;
    @FXML
    private PieChart pieChart;
    
    private PengirimanDao pengirimanDao;
    private TrackDao trackDao;
    private PaketDao paketDao;
    
    ObservableList<Pengiriman> data;
    
    public StatistikController() 
    {
        pengirimanDao = new PengirimanDaoImplHibernate();
        trackDao = new TrackDaoImplHibernate();
        paketDao = new PaketDaoImplHibernate();
    }
    
    public void loadDataPengiriman(String tanggal)
    {
                List<Pengiriman> pengirimans = pengirimanDao.getAllPengiriman();
                List<Track> tracks =trackDao.getAllTrack();
                
                ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList(); 
                
                for(Track t :tracks)
                {
                    
                    final BarChart.Series<String, Number> series =  new BarChart.Series<String, Number>();
                    series.setName(t.getDaerahTujuan());
                    int jumlah = 0;
                    for(Pengiriman p : pengirimans)
                    { 
                        if(t.getDaerahTujuan().equals(p.getRute()))
                        {
                            if(p.getTanggalPengiriman().equals(tanggal))
                            {
                            jumlah++;
                            series.getData().add(new XYChart.Data<String, Number>(t.getDaerahTujuan(), jumlah));
                            }
                        }
                    }                                   
                    barChartData.add(series);        
                }
                barChartHarian.setData(barChartData);             
    }
    
    
    public void loadDataPaket()
    {
        List<Pengiriman> pengirimans = pengirimanDao.getAllPengiriman();
        
        List<Paket> pakets = paketDao.getAllPaket();
        ObservableList<PieChart.Data> pieChartDatas = FXCollections.observableArrayList();
        for(Paket p : pakets)
        {
            int totalHarga = 0;
            for(Pengiriman pe : pengirimans)
            {
                if(p.getNamaPaket().equals(pe.getJenisPaket()))
                {
                    totalHarga += pe.getHarga();
                }                
            }
            pieChartDatas.add(new PieChart.Data(p.getNamaPaket(), totalHarga));
        }
        pieChart.setData(pieChartDatas);
    }        
    
    
    
    @FXML
    public void pilihTanggal(ActionEvent event)
    {
        loadDataPengiriman(tanggalDP.getValue().toString());
    }
    
    @FXML
    public void pilihPendapatanMenuItem(ActionEvent event)
    {
        barChartHarian.setVisible(false);
        tanggalDP.setVisible(false);
        tanggalL.setVisible(false);
        pieChart.setVisible(true);
    }
    
    
    @FXML
    public void pilihPengirimanMenuItem(ActionEvent event)
    {
        barChartHarian.setVisible(true);
        tanggalDP.setVisible(true);
        tanggalL.setVisible(true);
        pieChart.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tanggalDP.setValue(LocalDate.now());
        loadDataPengiriman(tanggalDP.getValue().toString());
        pieChart.setVisible(false);
        loadDataPaket();
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

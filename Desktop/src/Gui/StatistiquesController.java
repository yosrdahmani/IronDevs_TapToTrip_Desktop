/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Services.CategorieService;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StatistiquesController implements Initializable {

    @FXML
    private TabPane tabPanePieChart;
    @FXML
    private Tab Tab1;
    @FXML
    private Label pont;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniPieChart();
    }    
    
    
    private void iniPieChart(){
        Tab1.setContent(new ChartViewer(createChart("One")));
    }
    
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
         CategorieService cs = new CategorieService();
         List<Categorie> categories = cs.afficher();
         List<String> categorieNames = new ArrayList<>();
         for(Categorie c : categories){
             categorieNames.add(c.getNom());
         }
         
         List<Integer> numberUserByCategorieName = new ArrayList<>();

        for(String nom : categorieNames){
             numberUserByCategorieName.add(cs.findNumberUsersByCategorieName(nom));
         }
                       
         for (int i = 0; i < categorieNames.size(); i ++){
             dataset.setValue(categorieNames.get(i), numberUserByCategorieName.get(i));
         }       

        
        return dataset;
    }
    
    private static JFreeChart createChart(String name) {
        PieDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createPieChart(name, dataset, false, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        //plot.setOutlineVisible(false);
        plot.setSectionPaint(0,Color.RED);
        plot.setSectionPaint(1, Color.BLUE);
        plot.setSectionPaint(2, Color.GREEN);
        plot.setSectionPaint(3, Color.YELLOW);
        plot.setSectionPaint(4, Color.CYAN);
        plot.setLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        // Custom labels https://stackoverflow.com/a/17507061/230513
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {2}", new DecimalFormat("0"), new DecimalFormat("0.0%"));
        plot.setLabelGenerator(gen);
        return chart;
    }

    @FXML
    private void dashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @FXML
    private void transport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TransportBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TransportBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void categorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SeConnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeTransportFront.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

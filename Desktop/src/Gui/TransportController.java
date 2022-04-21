/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie;
import Entities.Transport;
import Services.CategorieService;
import Services.TransportService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;


import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class TransportController implements Initializable {

    @FXML
    private Label pont;
    @FXML
    private TextField matricule;
    @FXML
    private TextField marque;
    @FXML
    private TextField model;
    @FXML
    private TextField siege;
    @FXML
    private TextField prix;
    
    String img="";
    List<String> type;
    
    @FXML
    private ImageView imprt;
    @FXML
    private TableColumn<?, ?> matri;
    @FXML
    private ImageView im;
    @FXML
    private TableColumn<?, ?> marq;
    @FXML
    private TableColumn<?, ?> mod;
    @FXML
    private TableColumn<?, ?> sieg;
    @FXML
    private TableColumn<Transport, Integer> catg;
    @FXML
    private TableColumn<?, ?> user;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableView<Transport> listaa;
    @FXML
    private TableColumn<?, ?> pri;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private ImageView affichageImage;
    
    private int transportId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type =new ArrayList();
        type.add("*.jpg");
        type.add("*.png");
        afficherListeTransport();
        afficherCategoriesNom();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Transport.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    private void categorie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void ajouterTransport(ActionEvent event) {
        Transport t = new Transport();
        TransportService ts = new TransportService();
        CategorieService cs = new CategorieService();
        t.setCategorie(cs.getIdCategorie(combo.getValue()));
        t.setMatricule(matricule.getText());
        t.setMarque(marque.getText());
        t.setModele(model.getText());
        t.setNbSiege(Integer.parseInt(siege.getText()));
        t.setImage(img);
        t.setPrix(Double.parseDouble(prix.getText()));
        
        if (matricule.getText().isEmpty() || marque.getText().isEmpty() || model.getText().isEmpty() || siege.getText().isEmpty() || combo.getValue().isEmpty() || img.isEmpty() || prix.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez remplir tous les champs");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else {
            if (ts.chercherMatriculeTransport(matricule.getText()) == true) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Erreur");
                alert1.setContentText("Matricule");
                alert1.setHeaderText(null);
                alert1.show();
            }
            else {
                if(Double.parseDouble(prix.getText()) < 0) {
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Erreur");
                    alert1.setContentText("Prix");
                    alert1.setHeaderText(null);
                    alert1.show();
                }
                else {
                    if (Integer.parseInt(siege.getText()) < 0) {
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("Erreur");
                        alert1.setContentText("siege");
                        alert1.setHeaderText(null);
                        alert1.show(); 
                    }
                    else {
                        ts.ajouter(t);
                    } 
                }
            }
        }
        afficherListeTransport();
    }
    
    @FXML
    private void modifierTransport(ActionEvent event) {
        Transport t = new Transport();
        TransportService ts = new TransportService();
        CategorieService cs = new CategorieService();
        t.setId(transportId);
        t.setCategorie(cs.getIdCategorie(combo.getValue()));
        t.setMatricule(matricule.getText());
        t.setMarque(marque.getText());
        t.setModele(model.getText());
        t.setNbSiege(Integer.parseInt(siege.getText()));
        t.setImage(img);
        t.setPrix(Double.parseDouble(prix.getText()));
        
        if (matricule.getText().isEmpty() || marque.getText().isEmpty() || model.getText().isEmpty() || siege.getText().isEmpty() || combo.getValue().isEmpty() || img.isEmpty() || prix.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez remplir tous les champs");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else {
            if(Double.parseDouble(prix.getText()) < 0) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Erreur");
                alert1.setContentText("Prix");
                alert1.setHeaderText(null);
                alert1.show();
            }
            else {
                if (Integer.parseInt(siege.getText()) < 0) {
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Erreur");
                    alert1.setContentText("siege");
                    alert1.setHeaderText(null);
                    alert1.show(); 
                }
                else {
                    ts.modifier(t);
                } 
            }
        }
        afficherListeTransport();
        
    }

    @FXML
    private void importerImage(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        
        if(fc != null)
        {   
            System.out.println(fc.getName());
            img=fc.getName();
           FileSystem fileSys = FileSystems.getDefault();
           Path srcPath= fc.toPath();
           Path destPath= fileSys.getPath("E:\\3A46\\pi\\transport\\"+fc.getName());
            try {
                Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(TransportController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(srcPath.toString());
            Image i = new Image(fc.getAbsoluteFile().toURI().toString());
            imprt.setImage(i);
            
        }
        
    }
    
        private void afficherListeTransport() {
        //Transport t = new Transport();
            TransportService ts = new TransportService();
            CategorieService cs = new CategorieService();
            String categorieNom;
            
        List <Transport> transports = ts.afficher();
      
        ObservableList list = FXCollections.observableArrayList(transports);
    
        matri.setCellValueFactory(new PropertyValueFactory<>("matricule"));
//        String picture ="E:\\3A46\\pi\\transport\\" +  t.getImage();
//        Image imagee = new Image(picture, 110, 110, false, true);
//        im.setImage(imagee);
        marq.setCellValueFactory(new PropertyValueFactory<>("marque"));
        mod.setCellValueFactory(new PropertyValueFactory<>("modele"));
        sieg.setCellValueFactory(new PropertyValueFactory<>("nbSiege"));
       
  /*   catg.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transport, Integer>, ObservableValue<Integer>>() {

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Transport, Integer> param) {
                ObservableValue<Integer> obsInt = new SimpleIntegerProperty(param.getValue().getCategorie()).asObject();
                System.out.println("------------"+obsInt.toString());
                System.out.println("------------"+param.getValue().getCategorie());
                Categorie c = cs.findById(param.getValue().getCategorie());
                catg.setCellValueFactory(new PropertyValueFactory<>(c.getNom()));
                return obsInt;
            }
        });*/
     
    
        catg.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pri.setCellValueFactory(new PropertyValueFactory<>("prix"));
        listaa.setItems(list);
        
    }
        
        
        private void afficherCategoriesNom() {
        CategorieService cs = new CategorieService();
        List<Categorie> fcts = cs.afficher();
        ObservableList<String> nomCategories = FXCollections.observableArrayList();
        for(int i = 0; i<fcts.size();i++){
            nomCategories.add(fcts.get(i).getNom());
        }
       combo.setItems(nomCategories); //pour remplir le combo box   
    }

    @FXML
    private void onClick(MouseEvent event) {
        
        Transport t = listaa.getSelectionModel().getSelectedItem();
        
       // affichageImage.setImage(c.getNom());
        FileSystem fileSys = FileSystems.getDefault();
        Path destPath= fileSys.getPath("E:\\3A46\\pi\\transport\\"+t.getImage());
        Image i = new Image(destPath.toAbsolutePath().toUri().toString());
        affichageImage.setImage(i);
        
        matricule.setText(t.getMatricule());
        marque.setText(t.getMarque());
        model.setText(t.getModele());
        siege.setText(String.valueOf(t.getNbSiege()));
        combo.setValue(String.valueOf(t.getCategorie()));
        prix.setText(String.valueOf(t.getPrix()));
        
        transportId = t.getId();

    }

    @FXML
    private void supprimerTransport(ActionEvent event) {
        Transport t = new Transport();
        TransportService ts = new TransportService();
        CategorieService cs = new CategorieService();
        
        t.setId(transportId);
        t.setCategorie(cs.getIdCategorie(combo.getValue()));
        t.setMatricule(matricule.getText());
        t.setMarque(marque.getText());
        t.setModele(model.getText());
        t.setNbSiege(Integer.parseInt(siege.getText()));
        t.setImage(img);
        t.setPrix(Double.parseDouble(prix.getText()));
        
       ts.supprimer(t);
       
       afficherListeTransport();
    }
                

}

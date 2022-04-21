/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.CategorieService;
import Entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import static javax.swing.UIManager.getString;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CategorieController implements Initializable {

    @FXML
    private Label pont;
    @FXML
    private TableView<Categorie> listeCategorie;
    @FXML
    private TableColumn<?, ?> idCategorie;
    @FXML
    private TableColumn<?, ?> nomCategorie;
    @FXML
    private TableColumn<?, ?> boiteVitesse;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> boite;
    
    private int categorieId;
    
    ObservableList<String> listeBoite = FXCollections.observableArrayList("Manuelle","Automatique");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherListeCategorie();
        boite.setItems(listeBoite);
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
            Logger.getLogger(TransportController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void afficherListeCategorie() {
        CategorieService cs = new CategorieService();
        List categories = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(categories);
        listeCategorie.setItems(list);
        idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom"));
        boiteVitesse.setCellValueFactory(new PropertyValueFactory<>("boiteVitesse"));
    }
    
//    private boolean validateInputs() {
//        if (TextField_nom.getText().isEmpty()) {
//            Alert alert1 = new Alert(Alert.AlertType.WARNING);
//            alert1.setTitle("Erreur");
//            alert1.setContentText("Veuillez saisir votre nom");
//            alert1.setHeaderText(null);
//            alert1.show();
//            return false;
//        } else if ((TextField_prenom.getText().isEmpty())) {
//            Alert alert2 = new Alert(Alert.AlertType.WARNING);
//            alert2.setTitle("Erreur");
//            alert2.setContentText("Veuillez saisir votre prenom");
//            alert2.setHeaderText(null);
//            alert2.show();
//            return false;
//        }   else if (DatePicker_Datenaissance.getValue().isAfter(LocalDate.now())) {
//            Alert alert2 = new Alert(Alert.AlertType.WARNING);
//            alert2.setTitle("Erreur");
//            alert2.setContentText("Veuillez vérifier votre date de naissance");
//            alert2.setHeaderText(null);
//            alert2.show();
//            return false;
//        } else if ((TextField_email.getText().isEmpty())) {
//            Alert alert2 = new Alert(Alert.AlertType.WARNING);
//            alert2.setTitle("Erreur");
//            alert2.setContentText("Veuillez saisir votre email");
//            alert2.setHeaderText(null);
//            alert2.show();
//            return false;
//        } else if (!(validate(TextField_email.getText()))) {
//            Alert alert2 = new Alert(Alert.AlertType.WARNING);
//            alert2.setTitle("Erreur");
//            alert2.setContentText("Veuillez vérifier votre email");
//            alert2.setHeaderText(null);
//            alert2.show();
//            return false;
//        }
//        else if ((TextField_adresse.getText().isEmpty())) {
//            Alert alert2 = new Alert(Alert.AlertType.WARNING);
//            alert2.setTitle("Erreur");
//            alert2.setContentText("Veuillez saisir votre adresse");
//            alert2.setHeaderText(null);
//            alert2.show();
//            return false;
//        } else if ((TextField_password.getText().isEmpty())) {
//            Alert alert2 = new Alert(Alert.AlertType.WARNING);
//            alert2.setTitle("Erreur");
//            alert2.setContentText("Veuillez saisir votre mot de passe");
//            alert2.setHeaderText(null);
//            alert2.show();
//            return false;
//        } else if (TextField_password.getText().length() < 6) {
//            Alert alert2 = new Alert(Alert.AlertType.WARNING);
//            alert2.setTitle("Erreur");
//            alert2.setContentText("Mot de passe doit dépasser les 6 caractères");
//            alert2.setHeaderText(null);
//            alert2.show();
//            return false;
//        }
//        return true;
//    }

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        
        Categorie c = new Categorie();
        CategorieService cs = new CategorieService();
        c.setNom(nom.getText());
        if (nom.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez remplir le champs du nom");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else {
            c.setBoiteVitesse(boite.getValue());
            if(cs.chercherNomCategorie(nom.getText()) == true){
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Erreur");
                alert1.setContentText("Categorie existe déjà");
                alert1.setHeaderText(null);
                alert1.show();
            }
            else {
                cs.ajouter(c);
                afficherListeCategorie();
            } 
        }
    }

    @FXML
    private void modifierCategorie(ActionEvent event) {
        Categorie c = new Categorie();
        CategorieService cs = new CategorieService();
        c.setId(categorieId);
        c.setNom(String.valueOf(nom.getText()));
        if (nom.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez remplir le champs du nom");
            alert1.setHeaderText(null);
            alert1.show();
        }
        else {
            c.setBoiteVitesse(boite.getValue());
            if (boite.getValue().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Erreur");
                alert1.setContentText("Veuillez remplir le champs de boite");
                alert1.setHeaderText(null);
                alert1.show();
            }
            else {
               cs.modifier(c);
            }
        }
        afficherListeCategorie();
    }

    @FXML
    private void supprimerCategorie(ActionEvent event) {
        Categorie c = new Categorie();
        CategorieService cs = new CategorieService();
        c.setId(categorieId);
        c.setNom(nom.getText());        
        c.setBoiteVitesse(boite.getValue());
        
       cs.supprimer(c);
       
       afficherListeCategorie();
    }

    @FXML
    private void onClick(MouseEvent event) {
        
        Categorie c = listeCategorie.getSelectionModel().getSelectedItem();
        
        nom.setText(c.getNom());
        boite.setValue(c.getBoiteVitesse());
        categorieId = c.getId();
        
    }
    
}

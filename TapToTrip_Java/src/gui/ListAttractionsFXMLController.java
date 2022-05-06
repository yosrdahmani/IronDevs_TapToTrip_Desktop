/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Attraction;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.AttractionService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListAttractionsFXMLController implements Initializable {

    @FXML
    private Label pont;
    @FXML
    private Button pont1;
    @FXML
    private TableView<Attraction> Table_Attraction;
    @FXML
    private TableColumn<Attraction, Integer> Col_idAttraction;
    @FXML
    private TableColumn<Attraction, String> Col_nomAttraction;
    @FXML
    private TableColumn<Attraction, String> Col_lieuAttraction;
    @FXML
    private TableColumn<Attraction, String> Col_descriptionAttraction;
    @FXML
    private TableColumn Col_Supprimer;
    @FXML
    private TextField tnomAtt;
    @FXML
    private TextField tlieuAtt;
    @FXML
    private TextArea tdescripAtt;
    @FXML
    private TableColumn Col_Modifier;
    
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AttractionService as = new AttractionService();
        ObservableList<Attraction> db=FXCollections.observableArrayList(as.afficherAttractions());  

        
      //afficher le contenu de la tableAttraction
      Col_idAttraction.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_nomAttraction.setCellValueFactory(new PropertyValueFactory<>("nom"));
      Col_lieuAttraction.setCellValueFactory(new PropertyValueFactory<>("lieu"));
      Col_descriptionAttraction.setCellValueFactory(new PropertyValueFactory<>("description"));
      Table_Attraction.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<Attraction,String>, TableCell<Attraction,String>> cellFactory =(param) -> {
      final TableCell<Attraction,String> cell=new TableCell<Attraction,String>(){
          
          
      @Override
      public void updateItem(String item,boolean empty){
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
               
                final Button deleteButton = new Button("supprimer");
              
                
                 setGraphic(deleteButton);
                 setText(null);
                 
                 deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     Attraction att = getTableView().getItems().get(getIndex());
                     as.supprimerAttraction(att);
                     
                    Notifications notif = Notifications.create()
                    .title("Notification")
                    .text("Attraction supprimée avec succés!")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_LEFT);
                    notif.show();
                    //JOptionPane.showMessageDialog(null, "Attraction supprimée avec succés!");
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<Attraction> db=FXCollections.observableArrayList(as.afficherAttractions());  
                   
                        Col_idAttraction.setCellValueFactory(new PropertyValueFactory<>("id"));
                        Col_nomAttraction.setCellValueFactory(new PropertyValueFactory<>("nom"));
                        Col_lieuAttraction.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                        Col_descriptionAttraction.setCellValueFactory(new PropertyValueFactory<>("description"));
                        Table_Attraction.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_Supprimer.setCellFactory(cellFactory);

        
//POUR MODIFIER
        
                     Col_nomAttraction.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_lieuAttraction.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_descriptionAttraction.setCellFactory(TextFieldTableCell.forTableColumn());
                     
                     Col_nomAttraction.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     });
                     Col_lieuAttraction.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setLieu(e.getNewValue());
                     });
                     Col_descriptionAttraction.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescription(e.getNewValue());
                     });
                     
                     Table_Attraction.setEditable(true);         
                     Callback<TableColumn<Attraction,String>, TableCell<Attraction,String>> cellFactory1 =(param) -> {
                     final TableCell<Attraction,String> cell=new TableCell<Attraction,String>(){
         
                     @Override
                     public void updateItem(String item,boolean empty){
             
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        }
                    else{
               
                        final Button modifButton = new Button("modifier");
                         setGraphic(modifButton);
                         setText(null);
                         modifButton.setOnAction(e -> { 
                  
                     //extraire les infos de la ligne selectionnée
                    Attraction attraction = new Attraction();
                    attraction= Table_Attraction.getSelectionModel().getSelectedItem();
                    
                    if (attraction == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte");
                    alert.setHeaderText("Alerte");
                    alert.setContentText("Veuillez Choisir une attraction");
                    alert.show();
                 } else {

                    System.out.println(attraction);
                    tnomAtt.setText(attraction.getNom());
                    tlieuAtt.setText(attraction.getLieu());
                    tdescripAtt.setText(attraction.getDescription());
              
                    
                    }
                   
                    //JOptionPane.showMessageDialog(null, "catégorie modifiée !");
                 });
             }
            };
          };  
            return cell;   
        };
        
        Col_Modifier.setCellFactory(cellFactory1);
        Table_Attraction.setItems(db);
  
    }    
    
    
    private void listeAttractions(SortEvent<Attraction> event) {
      AttractionService as = new AttractionService();
      ObservableList<Attraction> db=FXCollections.observableArrayList(as.afficherAttractions());  
            
      Col_idAttraction.setCellValueFactory(new PropertyValueFactory<>("id"));
      Col_nomAttraction.setCellValueFactory(new PropertyValueFactory<>("nom"));
      Col_lieuAttraction.setCellValueFactory(new PropertyValueFactory<>("lieu"));
      Col_descriptionAttraction.setCellValueFactory(new PropertyValueFactory<>("description"));
      
      Table_Attraction.setItems(db);
      
    }
   
    
       
    @FXML
    private void modifierAttraction(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("voulez-vous vraiment modifier cette attraction?");
        Optional<ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.OK) {
            Attraction attraction = new Attraction();
            attraction = Table_Attraction.getSelectionModel().getSelectedItem();
            AttractionService attractionService = new AttractionService();
            //Attraction att = Attraction.getSelectionModel().getSelectedItem();
            //int id=attraction.getId(); 

            attraction.setNom(tnomAtt.getText());
            attraction.setLieu(tlieuAtt.getText());
            attraction.setDescription(tdescripAtt.getText());
  
            attractionService.modifierAttraction(attraction,attraction.getId());
            
            ObservableList<Attraction> db=FXCollections.observableArrayList(attractionService.afficherAttractions());  
            Col_idAttraction.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_nomAttraction.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Col_lieuAttraction.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            Col_descriptionAttraction.setCellValueFactory(new PropertyValueFactory<>("description"));
      
             Table_Attraction.setItems(db);
             
             

        }
        
    }
    
    @FXML
    private void Voyage(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listVoyagesFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListVoyagesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    

    @FXML
    private void Attraction(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listAttractionsFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }

    
    @FXML
    private void dashboard(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(gui.AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    @FXML
    private void NouvelleAttraction(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAttractionsFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(gui.AddAttractionsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deconnecter(ActionEvent event) {
              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontVoyage.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FrontVoyageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




  
    
    
    
}

    
    

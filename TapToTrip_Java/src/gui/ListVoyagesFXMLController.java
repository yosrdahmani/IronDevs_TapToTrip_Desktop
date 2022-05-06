/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Attraction;
import entities.Voyage;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import services.VoyageService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListVoyagesFXMLController implements Initializable {

    @FXML
    private Label pont;
    @FXML
    private Button pont1;
    @FXML
    private TableView<Voyage> table_Voyage;
    @FXML
    private TableColumn<Voyage, String> Col_dest;
    @FXML
    private TableColumn<Voyage, String> Col_duree;
    @FXML
    private TableColumn<Voyage, String> Col_prog;
    @FXML
    private TableColumn<Voyage, String> Col_hotel;
    @FXML
    private TableColumn<Voyage, String> Col_prix;
    @FXML
    private TableColumn Col_modif;
    @FXML
    private TableColumn Col_supp;
    @FXML
    private TextField dest;
    @FXML
    private TextArea prog;
    @FXML
    private TextField duree;
    @FXML
    private TextField hotel;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<Attraction> comboAttr;
    @FXML
    private TableColumn<Voyage, Integer> Col_id;
    @FXML
    private TableColumn<Voyage, String> Col_image;
    @FXML
    private TableColumn<Voyage, Integer> Col_idAttr;
    @FXML
    private TextField searchVoyage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       showAttractions();
       VoyageService vs = new VoyageService();
       ObservableList<Voyage> db=FXCollections.observableArrayList(vs.afficherVoyages());  
        
       
         
      //afficher le conenu de la table voyages
             Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_dest.setCellValueFactory(new PropertyValueFactory<>("destination"));
            Col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            Col_prog.setCellValueFactory(new PropertyValueFactory<>("programme"));
            Col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            Col_hotel.setCellValueFactory(new PropertyValueFactory<>("hotel"));
            Col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            Col_idAttr.setCellValueFactory(new PropertyValueFactory<>("attraction_id"));
      
        table_Voyage.setItems(db);
     
      //POUR SUPPRIMER
      Callback<TableColumn<Voyage,String>, TableCell<Voyage,String>> cellFactory =(param) -> {
          final TableCell<Voyage,String> cell=new TableCell<Voyage,String>(){
          
          
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
                     Voyage pdt = getTableView().getItems().get(getIndex());
                    try {
                        // sa.deleteById(cat.getId_produitplat());
                        vs.supprimerVoyage(pdt);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListVoyagesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     //JOptionPane.showMessageDialog(null, "voyage organisé supprimé avec succès!");
                            Notifications notif = Notifications.create()
                            .title("Notification")
                            .text("Voyage organisé supprimé avec succés!")
                            .graphic(null)
                            .hideAfter(Duration.seconds(4))
                            .position(Pos.TOP_LEFT);
                            notif.show();
                     
                     // REFRESH DE LA TABLEVIEW
                     ObservableList<Voyage> db=FXCollections.observableArrayList(vs.afficherVoyages());  
                   
                        Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                        Col_dest.setCellValueFactory(new PropertyValueFactory<>("destination"));
                        Col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
                        Col_prog.setCellValueFactory(new PropertyValueFactory<>("programme"));
                        Col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
                        Col_hotel.setCellValueFactory(new PropertyValueFactory<>("hotel"));
                        Col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                        Col_idAttr.setCellValueFactory(new PropertyValueFactory<>("attraction_id"));

                    table_Voyage.setItems(db);
                    
                    // refraichir la tableview
    
                 });
             }
            };
          };  
            return cell;   
        };
        Col_supp.setCellFactory(cellFactory);
        
       
        
//POUR MODIFIER
        
                     Col_dest.setCellFactory(TextFieldTableCell.forTableColumn());
                     Col_dest.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDestination(e.getNewValue());
                     });
                     table_Voyage.setEditable(true);  
                     
                        Callback<TableColumn<Voyage,String>, TableCell<Voyage,String>> cellFactory1 =(param) -> {
                        final TableCell<Voyage,String> cell=new TableCell<Voyage,String>(){

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
                    Voyage voyage = new Voyage();
                    voyage= table_Voyage.getSelectionModel().getSelectedItem();
                    
                    if (voyage == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte");
                    alert.setHeaderText("Alerte");
                    alert.setContentText("Veuillez Choisir un voyage");
                    alert.show();
                 } else {

                    System.out.println(voyage);
                    dest.setText(voyage.getDestination());
                    duree.setText(voyage.getDuree());
                    prog.setText(voyage.getProgramme());
                    hotel.setText(voyage.getHotel());
                    prix.setText(voyage.getPrix());

                    showAttractions();
                    
                    
                    
                    
                    }
                     //sa.update(act,act.getId_categorie());
                    // JOptionPane.showMessageDialog(null, "catégorie modifiée !");
                 });
             }
            };
          };  
            return cell;   
        };
        
        Col_modif.setCellFactory(cellFactory1);
        table_Voyage.setItems(db);
      
    }  
    
   

    
    private void listeVoyage(SortEvent<Voyage> event) {
        VoyageService vs = new VoyageService();
        ObservableList<Voyage> db=FXCollections.observableArrayList(vs.afficherVoyages());  
            
            Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_dest.setCellValueFactory(new PropertyValueFactory<>("destination"));
            Col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            Col_prog.setCellValueFactory(new PropertyValueFactory<>("programme"));
            Col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            Col_hotel.setCellValueFactory(new PropertyValueFactory<>("hotel"));
            Col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            Col_idAttr.setCellValueFactory(new PropertyValueFactory<>("attraction_id"));
      
        table_Voyage.setItems(db);
      
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
            Logger.getLogger(ListAttractionsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void NouveauVoyage(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addVoyageFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddVoyageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void modifierVoyage(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("voulez-vous vraiment modifier ce voyage?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
            Voyage voyage = new Voyage();
            voyage = table_Voyage.getSelectionModel().getSelectedItem();
            VoyageService voyageService = new VoyageService();
           // Category cat = Categorie.getSelectionModel().getSelectedItem();
//          int id=cat.getId(); 
            voyage.setAttraction_id(4);
            voyage.setDestination(dest.getText());
            voyage.setDuree(duree.getText());
            voyage.setProgramme(prog.getText());
            voyage.setHotel(hotel.getText());
            voyage.setPrix(prix.getText());
            
            voyageService.modifierVoyage(voyage,voyage.getId());
            ObservableList<Voyage> db=FXCollections.observableArrayList(voyageService.afficherVoyages());  
            Col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            Col_dest.setCellValueFactory(new PropertyValueFactory<>("destination"));
            Col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            Col_prog.setCellValueFactory(new PropertyValueFactory<>("programme"));
            Col_hotel.setCellValueFactory(new PropertyValueFactory<>("hotel"));
            Col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            Col_idAttr.setCellValueFactory(new PropertyValueFactory<>("attraction_id"));
            table_Voyage.setItems(db);

    }
    }
    
 
    private void showAttractions(){
        List<Attraction> listAtt = new AttractionService().afficherAttractions();
        
        ArrayList<Attraction> nomAtt= new ArrayList<>();
        for(Attraction att : listAtt){
            Attraction attra = new Attraction();
            attra.setId(att.getId());
            attra.setNom(att.getNom());
            nomAtt.add(attra);
        }
        ObservableList<Attraction> choices = FXCollections.observableArrayList(nomAtt);
        comboAttr.setItems(choices);
    }

    @FXML
    private void statVoyages(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatVoyages.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListAttractionsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}



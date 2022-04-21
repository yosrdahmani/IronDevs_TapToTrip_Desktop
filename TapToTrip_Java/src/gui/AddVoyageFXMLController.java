/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Attraction;
import entities.Voyage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import services.AttractionService;
import services.VoyageService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddVoyageFXMLController implements Initializable {

    private String path="",imgname="";
    @FXML
    private Label pont;
    @FXML
    private TextField DestinationVoyage;
    @FXML
    private TextArea ProgrammeVoyage;
    @FXML
    private TextField DureeVoyage;
    @FXML
    private ImageView ImageVoyage;
    @FXML
    private TextField HotelVoyage;
    @FXML
    private TextField PrixVoyage;
    @FXML
    private ComboBox<Attraction> ComboBoxAttraction;
    @FXML
    private Button pont1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAttractions();
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
    private void AjouterVoyage(ActionEvent event) {
         VoyageService vs = new VoyageService();
         if (DestinationVoyage.getText().equals("")||DureeVoyage.getText().equals("")||ProgrammeVoyage.getText().equals("")||HotelVoyage.getText().equals("")||PrixVoyage.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
       } 
          else if(DestinationVoyage.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Le champ destination manquant!");
       }
          
          else if(DureeVoyage.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Le champ duree manquant!");
       }
          
           else if(ProgrammeVoyage.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Le champ programme manquant!");
       }
         
          
           else if(HotelVoyage.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Le champ hotel manquant!");
       }
          
         else if(PrixVoyage.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Le champ prix manquant!");
       }
         
       else  if(ComboBoxAttraction.getSelectionModel().isEmpty() ){
           JOptionPane.showMessageDialog(null, "Veuillez choisir une attraction!");
       }
       
       else if(!( Pattern.matches("[a-zA-Z]*", DestinationVoyage.getText()))){
           JOptionPane.showMessageDialog(null, "La destination du voyage doit etre un texte!");
       }
    
      else if(!( Pattern.matches("[0-9_ ]*", PrixVoyage.getText()))){
           JOptionPane.showMessageDialog(null, "Le prix doit etre positif!");
       }
       else if (validatePr()){
          Attraction att = ComboBoxAttraction.getSelectionModel().getSelectedItem();
          int id=att.getId(); 
         //pps.ajouter(new Produit(Nom_ProduitPlat.getText(),Prix.getText(), Desc_ProduitPlat.getText(),qte.getText()));
           vs.ajouterVoyage(new Voyage(DestinationVoyage.getText(),DureeVoyage.getText(),ProgrammeVoyage.getText(),imgname,HotelVoyage.getText(),PrixVoyage.getText(),id));
       // pps.add(new Produit(Nom_ProduitPlat.getText(),Double.parseDouble(Prix.getText()),Integer.parseInt(qte.getText()),Desc_ProduitPlat.getText(),id));
           JOptionPane.showMessageDialog(null, "Voyage organisé ajouté avec succés!");
       }

    }
    
    
    private boolean validatePr() {
     Pattern p = Pattern.compile("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
        Matcher m = p.matcher(PrixVoyage.getText());
        if(m.find() && m.group().equals(PrixVoyage.getText())){
            return true;
        }else{
                JOptionPane.showMessageDialog(null,"Veuillez saisir un prix valide!");

            return false;            
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
        ComboBoxAttraction.setItems(choices);
    }
    
    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        try (
                    FileChannel in = new FileInputStream(sourceFile).getChannel();
                    FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }  

        @FXML
    private void uploadImage(ActionEvent event) throws IOException {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
            path=filename;
            imgname=f.getName();
            Image getAbsolutePath = null;


            String dd="C:\\wamp64\\www\\IronDevs_TapToTrip_Desktop\\TapToTrip_Java\\src\\Images"+f.getName()  ;
           
            File dest = new File(dd);
            this.copyFile(f,dest);


           System.out.println(dd);

           ImageVoyage.setImage(new Image("file:" +dest.getAbsolutePath()));
    }
  
   

    @FXML
    private void AnnulerAjoutVoyage(ActionEvent event) {
        
        DestinationVoyage.setText("");
        DureeVoyage.setText("");
        ProgrammeVoyage.setText("");
        HotelVoyage.setText("");
        PrixVoyage.setText("");
    }

    @FXML
    private void BoutonBack(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listVoyagesFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListVoyagesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Voyage(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listVoyagesFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(gui.ListVoyagesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Attraction(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listAttractionsFXML.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(gui.AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
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

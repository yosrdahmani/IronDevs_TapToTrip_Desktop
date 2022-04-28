/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.APIConnector;
import Entities.Categorie;
import Entities.Transport;
import Services.CategorieService;
import Services.TransportService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ListeTransportFrontController implements Initializable {

    @FXML
    private GridPane transportContainer;
    @FXML
    private Button pont;
    @FXML
    private TextField chercherSiege;
    @FXML
    private GridPane triContainer;
    @FXML
    private AnchorPane anchorAffiche;
    @FXML
    private AnchorPane anchorTri;
    @FXML
    private ComboBox<String> categorieNoms;
    @FXML
    private TextField cityInput;
    @FXML
    private TextArea weatherText;
    
    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorAffiche.setVisible(true);
        anchorTri.setVisible(false);
        afficherCategoriesNom();
        
        TransportService ts = new TransportService();
        List <Transport> listeTransport = ts.afficher();
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : listeTransport) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                    VBox transportBox = fxmlLoader.load();
                    TransportFrontController transportFrontController = fxmlLoader.getController();
                    transportFrontController.setData(t);
                    
                    if (colonne == 2) {
                        colonne = 0;
                        rang++;
                    }
                    
                    transportContainer.add(transportBox, colonne++, rang);
                    GridPane.setMargin(transportBox, new Insets(10));
            }
        } 
        catch (IOException ex) {
                Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void SeConnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilBack.fxml"));
            Parent root = loader.load();
            pont.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficherCategoriesNom() {
        CategorieService cs = new CategorieService();
        List<Categorie> fcts = cs.afficher();
        ObservableList<String> nomCategories = FXCollections.observableArrayList();
        for(int i = 0; i<fcts.size();i++){
            nomCategories.add(fcts.get(i).getNom());
        }
       categorieNoms.setItems(nomCategories); //pour remplir le combo box   
    }
    
    @FXML
    private void chercherSiege(ActionEvent event) {
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        System.out.println(chercherSiege.getText());
        List <Transport> transports = ts.ChercherBySiegeAndNomCategorie(chercherSiege.getText(),categorieNoms.getValue());
        
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void favoris(ActionEvent event) {
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        List <Transport> transports = ts.afficherFavoris(2);
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reserves(ActionEvent event) {
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        List <Transport> transports = ts.afficherReserves(2);
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherTous(ActionEvent event) {
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        List <Transport> transports = ts.afficher();
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getWeatherData(ActionEvent event) throws MalformedURLException {
        JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);

        weatherText.setText(
            "Min temperature: " + todaysWeather.get("min_temp") +
            "\nCurrent temperature: " + todaysWeather.get("the_temp") +
            "\nMax temperature: " + todaysWeather.get("max_temp")
        );
    }
    
    public String getWoeid() throws MalformedURLException {
        APIConnector apiConnectorCity = new APIConnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(cityInput.getText())).get(0);

        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return  (JSONObject) weatherArray.get(0);
    }

    @FXML
    private void trier(ActionEvent event) {
        triContainer.setGridLinesVisible(false);
        triContainer.getColumnConstraints().clear();
        triContainer.getRowConstraints().clear();
        triContainer.getChildren().clear();
        triContainer.setGridLinesVisible(true);
        
        TransportService ts = new TransportService();
        List <Transport> transports = ts.afficherByPrixFront();
        
        anchorAffiche.setVisible(false);
        anchorTri.setVisible(true);
        
        int colonne = 0;
        int rang =1;
        try {
            for (Transport t : transports) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("TransportFront.fxml"));
                VBox transportBox = fxmlLoader.load();
                TransportFrontController transportFrontController = fxmlLoader.getController();
                transportFrontController.setData(t);

                if (colonne == 2) {
                    colonne = 0;
                    rang++;
                }

                triContainer.add(transportBox, colonne++, rang);
                GridPane.setMargin(transportBox, new Insets(10));
            }

        } 
        catch (IOException ex) {
                 Logger.getLogger(ListeTransportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

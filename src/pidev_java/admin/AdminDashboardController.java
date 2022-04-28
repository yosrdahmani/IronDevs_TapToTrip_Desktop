/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.admin;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pidev_java.entities.User;
import pidev_java.services.UserService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Button btn_addUser;
    @FXML
    private Button btn_lock;
    @FXML
    private TableColumn<User, String> ColNom;
    @FXML
    private TableColumn<User, String> ColPrenom;
    @FXML
    private TableColumn<User, String> ColEmail;
    @FXML
    private TableColumn<User, Integer> ColState;
    @FXML
    private TableView<User> tableUsers;
    ObservableList<User> list;
    @FXML
    private Button btn_refresh;
    @FXML
    private TableColumn<User, String> ColNum;
    @FXML
    private TextField txt_nom;
    @FXML
    private TableColumn<?, ?> ColRole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService us = new UserService();
        list = FXCollections.observableArrayList();
        List<User> Users = us.afficheruser();
        System.out.println("---------------------------------------");
        System.out.println(Users);
        System.out.println("-----------------------------------");
         Users.stream().forEach((j) -> {
            list.add(j);
        });
         ColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         ColPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         ColState.setCellValueFactory(new PropertyValueFactory<>("state"));
         ColNum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
         ColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
         tableUsers.setItems(list);
    }

    @FXML
    private void BtnAaddUser(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
    }

    @FXML
    private void BtnLock(ActionEvent event) {
        
        User u = tableUsers.getSelectionModel().getSelectedItem();
        
        if (u.getState()==1){
            boolean check = Confirm_Box("Activer ce compte", "Vous êtes sur d'activer ce compte");
            if (check){
                new UserService().Activeruser(u.getId());
            }
        }else{
             boolean check = Confirm_Box("Désactiver ce compte", "Vous êtes sur de désactiver ce compte");
            if (check){
                new UserService().Desactiveruser(u.getId());
            }
        }
        refreshTable();
    }
    
    private void refreshTable(){
        list.clear();
        UserService us = new UserService();
        list = FXCollections.observableArrayList();
        List<User> Users = us.afficheruser();
         Users.stream().forEach((j) -> {
            list.add(j);
        });
         ColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         ColPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         ColState.setCellValueFactory(new PropertyValueFactory<>("state"));
         ColNum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
         ColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
         tableUsers.setItems(list);
        
    }
    
    private boolean Confirm_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;
    }

    @FXML
    private void BtnRefresh(ActionEvent event) {
        refreshTable();
    }

  

    @FXML
    private void SearchByName(KeyEvent event) {
        list.clear();
        UserService us = new UserService();
        list = FXCollections.observableArrayList();
        List<User> Users = us.SearchUserByName(txt_nom.getText());
         Users.stream().forEach((j) -> {
            list.add(j);
        });
         ColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         ColPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         ColState.setCellValueFactory(new PropertyValueFactory<>("state"));
         ColNum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
         ColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
         tableUsers.setItems(list);
    }

}

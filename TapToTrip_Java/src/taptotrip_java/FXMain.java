/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taptotrip_java;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class FXMain extends Application {
    
    Parent parent;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        

        try {
                   Parent parent = FXMLLoader.load(getClass().getResource("/gui/FrontVoyage.fxml"));
                   Scene scene = new Scene(parent);
                   primaryStage.setScene(scene);
                   primaryStage.show();

               }catch (IOException ex) {
                   System.out.println(ex.getMessage());
               }
           }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author c051618
 */
public class Main extends Application {
    
    private static Scene mainScene;
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            ScrollPane scrollPane = loader.load();
            
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            
            mainScene = new Scene (scrollPane);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Sample JavaFX application");
            primaryStage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            
        }
    
    public static Scene getMainScene () {
        return mainScene;
    }
        
    public static void main(String[] args) {
        launch(args);
    }
    
}

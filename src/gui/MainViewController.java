/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import gui.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

/**
 * FXML Controller class
 *
 * @author c051618
 */
public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemVendedor;
    @FXML
    private MenuItem menuItemDepartamento;
    @FXML
    private MenuItem menuItemAbout;
    
    @FXML
    public void onMenuItemVendedorAction (){
        loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
        controller.setSellerService(new SellerService());
            controller.updateTableView();
        });
    }
    
    @FXML
    public void onMenuItemDepartamentoAction (){
        loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
        controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        });
    }
    
    @FXML
    public void onMenuItemAboutAction (){
        loadView("/gui/About.fxml", x -> {});
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private synchronized <T> void loadView (String absoluteName, Consumer<T> initializingAction) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();
            
            Scene mainScene = Main.getMainScene();
            
            VBox mainVBox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();
            
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());
            
            T controller = loader.getController();
            initializingAction.accept(controller);
        }
        catch (IOException e) {
            Alerts.showAlert("IOException", "Erro ao Carregar a página", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}


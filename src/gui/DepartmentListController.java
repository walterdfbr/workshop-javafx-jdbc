/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

/**
 *
 * @author c051618
 */
public class DepartmentListController implements Initializable, DataChangeListener{

    private DepartmentService service;
    
    @FXML
    private TableView<Department> tableViewDepartamento;
    
    @FXML
    private TableColumn<Department, Integer> tableColumnId;
    
    @FXML
    private TableColumn<Department, String> tableColumnNome;
    
    @FXML
    private TableColumn<Department, Department> tableColunEdIT;
    
    @FXML
    private Button btNew;
    
    private ObservableList <Department> obsList;
    
    @FXML
    public void onBtNewAction (ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Department obj = new Department();
        createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
    }
    
    public void setDepartmentService (DepartmentService service) {
        this.service = service;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initializeNodes ();
        
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Name"));
        
        Stage stage = (Stage)Main.getMainScene().getWindow();
        tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
    }
    
    public void updateTableView () {
        if (service  == null) {
            throw new IllegalStateException ("Service was null");
        }
        List<Department> list = service.findAll();
        
        obsList = FXCollections.observableArrayList(list);
        tableViewDepartamento.setItems(obsList);
        InitEditButtons();
    }
    
    private void createDialogForm (Department obj, String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            
            DepartmentFormController controller = loader.getController();
            controller.setDepartment(obj);
            controller.setDepartmentService(new DepartmentService());
            controller.subscribeDataChangeListener(this);
            controller.updateFormData();
                    
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Entre com os dados do Departamento");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL); //trava a janela anterior
            dialogStage.showAndWait();
        }
        catch (IOException e) {
            Alerts.showAlert("IOEXception", "Error load view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void onDataChanged() {
        updateTableView();
    }
    
    private void InitEditButtons() {
        tableColunEdIT.setCellValueFactory( param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColunEdIT.setCellFactory(param -> new TableCell <Department, Department> () {
        private final Button button = new Button ("edit");
        
        @Override
        protected void updateItem (Department obj, boolean empty) {
            super.updateItem(obj, empty);
            
            if (obj == null) {
                setGraphic(null);
                return;
            }
            setGraphic(button);
            button.setOnAction(
            event -> createDialogForm(
                    obj, "/gui/DepartmentForm.fxml", Utils.currentStage(event)));
        }
    });
    }
}

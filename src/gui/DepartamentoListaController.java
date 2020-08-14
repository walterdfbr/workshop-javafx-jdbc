/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import application.Main;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Departamento;
import model.services.DepartamentoService;

/**
 *
 * @author c051618
 */
public class DepartamentoListaController implements Initializable{

    private DepartamentoService service;
    
    @FXML
    private TableView<Departamento> tableViewDepartamento;
    
    @FXML
    private TableColumn<Departamento, Integer> tableColumnId;
    
    @FXML
    private TableColumn<Departamento, String> tableColumnNome;
    
    @FXML
    private Button btNew;
    
    private ObservableList <Departamento> obsList;
    
    @FXML
    public void onBtNewAction () {
        System.out.println("onBtNewAction");
    }
    
    public void setDepartamentoService (DepartamentoService service) {
        this.service = service;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initializeNodes ();
        
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        
        Stage stage = (Stage)Main.getMainScene().getWindow();
        tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
    }
    
    public void updateTableView () {
        if (service  == null) {
            throw new IllegalStateException ("Service was null");
        }
        List<Departamento> list = service.findAll();
        
        obsList = FXCollections.observableArrayList(list);
        tableViewDepartamento.setItems(obsList);
    }
    
}

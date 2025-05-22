package co.edu.uniquindio.reservasuq.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {
    private ControladorPrincipal controladorPrincipal;
    private AlojamientosPopularesCiudadController alojamientosPopularesCiudad;
    private GananciasTotalesController gananciasTotalesController;
    private GananciasTotalesTipoController gananciasTotalesTipoController;

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private Tab tab3;

    @FXML
    private Tab tab4;

    @FXML
    private TabPane tabpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            alojamientosPopularesCiudad = (AlojamientosPopularesCiudadController) cargarTab(tab1, "/co/edu/uniquindio/reservasuq/AlojamientosPopularesCiudadView.fxml");
            gananciasTotalesController = (GananciasTotalesController) cargarTab(tab2, "/co/edu/uniquindio/reservasuq/GananciasTotalesView.fxml");
            gananciasTotalesTipoController = (GananciasTotalesTipoController) cargarTab(tab3, "/co/edu/uniquindio/reservasuq/GananciasTotalesTipoView.fxml");
            cargarTab(tab4,"/co/edu/uniquindio/reservasuq/OcupacionPorcentualView.fxml");
        }catch (Exception e){
            e.printStackTrace();
        }
        tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cargarDatos();
        });
    }

    private Initializable cargarTab(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
        return loader.getController();
    }

    private void cargarDatos(){
        alojamientosPopularesCiudad.cargarAlojamientosPopulares();
        gananciasTotalesController.cargarDatos();
        gananciasTotalesTipoController.cargarDatos();
    }
}


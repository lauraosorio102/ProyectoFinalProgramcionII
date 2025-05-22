package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AlojamientosPopularesCiudadController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private ObservableList<Alojamiento> alojamientos;

    @FXML
    private TableColumn<Alojamiento, String> columnDescripcion;

    @FXML
    private TableColumn<Alojamiento, String> columnHuespedes;

    @FXML
    private TableColumn<Alojamiento, String> columnNombre;

    @FXML
    private TableColumn<Alojamiento, String> columnPrecioporNoche;

    @FXML
    private TableColumn<Alojamiento, String> columnciudad;

    @FXML
    private TableView<Alojamiento> tableAlojamientos;

    public AlojamientosPopularesCiudadController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnciudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad().toString()));
        columnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnHuespedes.setCellValueFactory(cellData->new SimpleStringProperty(""+cellData.getValue().getCapacidadHuespedes()));
        columnPrecioporNoche.setCellValueFactory(cellData ->new SimpleStringProperty(""+cellData.getValue().getPrecioPorNoche()));
        alojamientos = FXCollections.observableArrayList();
        cargarAlojamientosPopulares();
    }

    protected void cargarAlojamientosPopulares() {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().alojamientosMasPopularesCiudad().values());
        tableAlojamientos.setItems(alojamientos);
    }
}



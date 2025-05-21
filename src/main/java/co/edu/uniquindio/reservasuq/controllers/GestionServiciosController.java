package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.config.Sesion;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionServiciosController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private Alojamiento alojamientoseleccionado;
    private String servicioseleccionado;
    private ObservableList<Alojamiento> alojamientos;
    private ObservableList<String> servicios;

    @FXML
    private TableView<String> TableServiciosGestionServicios;

    @FXML
    private Button buttonAnadirGestionaServicios;

    @FXML
    private Button buttonEliminarGestionaServicios;

    @FXML
    private TableColumn<Alojamiento, String> columnCiudad;

    @FXML
    private TableColumn<String, String> columnServicio;

    @FXML
    private TableColumn<Alojamiento, String> columnDescripcionalojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnNombre;

    @FXML
    private TableColumn<Alojamiento, Image> columnfoto;

    @FXML
    private TableView<Alojamiento> tableAlojamientosGestionServicios;

    @FXML
    private TextField txtServiciogestionServicios;

    public GestionServiciosController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void AñadirAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            try{
                controladorPrincipal.getEmpresaServicio().agregarServicio(alojamientoseleccionado,txtServiciogestionServicios.getText());
                limpiar();
                cargarServicios();
                controladorPrincipal.crearAlerta("Se agregó el servicio correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(),Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione un Alojamiento de la tabla de alojamientos",Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void EliminarAction(ActionEvent event) {
        if (servicioseleccionado != null) {
            try{
                controladorPrincipal.getEmpresaServicio().eliminarServicio(alojamientoseleccionado, servicioseleccionado);
                limpiar();
                cargarServicios();
                controladorPrincipal.crearAlerta("Se eliminó el servicio correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(),Alert.AlertType.ERROR);
            }
        }else {
            controladorPrincipal.crearAlerta("Seleccione un servicio de la lista de servicios",Alert.AlertType.INFORMATION);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnDescripcionalojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        columnCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad().toString()));
        columnfoto.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getFoto())
        );

        columnfoto.setCellFactory(column -> new TableCell<Alojamiento, Image>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(80);
                imageView.setFitHeight(60);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);

                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });

        columnServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        alojamientos = FXCollections.observableArrayList();
        servicios = FXCollections.observableArrayList();

        cargarAlojamientos();
        cargarServicios();
        tableAlojamientosGestionServicios.setOnMouseClicked(event -> {
            alojamientoseleccionado = tableAlojamientosGestionServicios.getSelectionModel().getSelectedItem();
            cargarServicios();
            servicioseleccionado = null;
        });

        TableServiciosGestionServicios.setOnMouseClicked(event -> {
            servicioseleccionado = TableServiciosGestionServicios.getSelectionModel().getSelectedItem();
        });
    }

    protected void cargarServicios() {
        if (alojamientoseleccionado != null) {
            servicios.setAll(controladorPrincipal.getEmpresaServicio().listarServicios(alojamientoseleccionado));
            TableServiciosGestionServicios.setItems(servicios);
        }else{
            servicios.setAll(new ArrayList<>());
            TableServiciosGestionServicios.setItems(servicios);
        }
    }

    protected void cargarAlojamientos() {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().listarAlojamientos());
        tableAlojamientosGestionServicios.setItems(alojamientos);
    }

    private void limpiar(){
        txtServiciogestionServicios.clear();
        servicioseleccionado = null;
        alojamientoseleccionado = null;
    }
}

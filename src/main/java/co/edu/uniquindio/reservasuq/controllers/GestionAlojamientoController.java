package co.edu.uniquindio.reservasuq.controllers;
import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.AlojamientoFactory;
import co.edu.uniquindio.reservasuq.model.factory.Apartamento;
import co.edu.uniquindio.reservasuq.model.factory.Casa;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GestionAlojamientoController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private Alojamiento alojamientoseleccionado;
    private ObservableList<Alojamiento> alojamientos;

    @FXML
    private Button btnlimpiarfiltros;

    @FXML
    private Button buttonAgregarGestionAlojamiento;

    @FXML
    private Button buttonEditarGestionAlojamiento;

    @FXML
    private Button buttonEliminarGestionAlojamiento;

    @FXML
    private ComboBox<String> cbxTipoAlojamientoGestionAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnNombreAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnPreciopornoche;

    @FXML
    private TableColumn<Alojamiento, String> columnTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnciudadAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnhuespedesAlojamiento;

    @FXML
    private ComboBox<String> comboTipofiltro;

    @FXML
    private ComboBox<Ciudad> combociudad;

    @FXML
    private ComboBox<Ciudad> combociudadfiltro;

    @FXML
    private ImageView imagenFotoGestionAlojamiento;

    @FXML
    private Spinner<Integer> spinnerHuespedesfiltro;

    @FXML
    private Spinner<Integer> spinnercantidadHuespedesGestionAlojamiento2;

    @FXML
    private TableView<Alojamiento> tableAlojamientosGestionAlojamientos;

    @FXML
    private TextField txtCostoAdicionalGestionAlojamiento;

    @FXML
    private TextField txtDescripcionGestionAlojamiento;

    @FXML
    private TextField txtNombreGestionAlojamiento;

    @FXML
    private TextField txtPrecioNocheGestionAlojamiento;

    @FXML
    private TextField txtnombrefiltro;

    public GestionAlojamientoController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void AgregarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().agregarAlojamiento(cbxTipoAlojamientoGestionAlojamiento.getValue(),txtNombreGestionAlojamiento.getText(),txtDescripcionGestionAlojamiento.getText(),combociudad.getValue(),imagenFotoGestionAlojamiento.getImage(),txtPrecioNocheGestionAlojamiento.getText(),spinnercantidadHuespedesGestionAlojamiento2.getValue(),txtCostoAdicionalGestionAlojamiento.getText());
            limpiarCampos();
            cargarAlojamientos();
            controladorPrincipal.crearAlerta("Alojamiento creado correctamente", Alert.AlertType.INFORMATION);
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void DeseleccionarAction(ActionEvent event) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombreGestionAlojamiento.clear();
        txtDescripcionGestionAlojamiento.clear();
        txtPrecioNocheGestionAlojamiento.clear();
        txtCostoAdicionalGestionAlojamiento.clear();
        imagenFotoGestionAlojamiento.setImage(null);
        cbxTipoAlojamientoGestionAlojamiento.getSelectionModel().clearSelection();
        combociudad.getSelectionModel().clearSelection();
        spinnercantidadHuespedesGestionAlojamiento2.getValueFactory().setValue(0);
        alojamientoseleccionado = null;
    }

    @FXML
    void EditarAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            try{
                controladorPrincipal.getEmpresaServicio().editarAlojamiento(alojamientoseleccionado,txtNombreGestionAlojamiento.getText(),txtDescripcionGestionAlojamiento.getText(),combociudad.getValue(),imagenFotoGestionAlojamiento.getImage(),txtPrecioNocheGestionAlojamiento.getText(),spinnercantidadHuespedesGestionAlojamiento2.getValue());
                limpiarCampos();
                cargarAlojamientos();
                controladorPrincipal.crearAlerta("Alojamiento creado correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione un Alojamiento", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void EliminarAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            try {
                controladorPrincipal.getEmpresaServicio().eliminarAlojamiento(alojamientoseleccionado);
                limpiarCampos();
                cargarAlojamientos();
                controladorPrincipal.crearAlerta("Alojamiento eliminado correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione un Alojamiento", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void LimpiarFiltrosAction(ActionEvent event) {
        txtnombrefiltro.setText("");
        comboTipofiltro.getSelectionModel().clearSelection();
        combociudadfiltro.getSelectionModel().clearSelection();
        spinnerHuespedesfiltro.getValueFactory().setValue(0);
    }

    @FXML
    void SeleccionarFotoAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagen", "*.png"), new FileChooser.ExtensionFilter("Imagen", "*.jpg"));
        fileChooser.setTitle("Elige la foto");
        fileChooser.setInitialDirectory(new File("C:/"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                imagenFotoGestionAlojamiento.setImage(image);
                controladorPrincipal.crearAlerta("Imagen cargada correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                controladorPrincipal.crearAlerta("Error al cargar la imagen", Alert.AlertType.ERROR);
            }
        } else {
            controladorPrincipal.crearAlerta("Seleccione una imagen de perfil", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void filtrarAction(ActionEvent event) {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().filtrarAlojamientos(AlojamientoFactory.seleccionarTipoAlojamiento(comboTipofiltro.getValue()),combociudadfiltro.getValue(),spinnerHuespedesfiltro.getValue(),txtnombrefiltro.getText()));
        tableAlojamientosGestionAlojamientos.setItems(alojamientos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombreAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnTipoAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        columnciudadAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad().toString()));
        columnhuespedesAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCapacidadHuespedes()));
        columnPreciopornoche.setCellValueFactory(cellData -> new SimpleStringProperty(""+ cellData.getValue().getPrecioPorNoche()));
        alojamientos = FXCollections.observableArrayList();
        cargarAlojamientos();

        combociudad.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(Ciudad.values()))));
        combociudadfiltro.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(Ciudad.values()))));
        cbxTipoAlojamientoGestionAlojamiento.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Hotel", "Casa", "Apartamento", "Habitacion"))));
        comboTipofiltro.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Hotel", "Casa", "Apartamento"))));
        spinnerHuespedesfiltro.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnercantidadHuespedesGestionAlojamiento2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        tableAlojamientosGestionAlojamientos.setOnMouseClicked(mouseEvent -> {
            alojamientoseleccionado = tableAlojamientosGestionAlojamientos.getSelectionModel().getSelectedItem();
            cargarDatos();
        });

        spinnerHuespedesfiltro.valueProperty().addListener((observable, oldValue, newValue) -> {
            filtrarAction(new ActionEvent());
        });

        txtnombrefiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarAction(new ActionEvent());
        });
    }

    private void cargarDatos() {
        if (alojamientoseleccionado != null) {
            txtNombreGestionAlojamiento.setText(alojamientoseleccionado.getNombre());
            txtDescripcionGestionAlojamiento.setText(alojamientoseleccionado.getDescripcion());
            txtPrecioNocheGestionAlojamiento.setText(""+alojamientoseleccionado.getPrecioPorNoche());
            imagenFotoGestionAlojamiento.setImage(alojamientoseleccionado.getFoto());
            cbxTipoAlojamientoGestionAlojamiento.getSelectionModel().select(alojamientoseleccionado.getClass().getSimpleName());
            combociudad.getSelectionModel().select(alojamientoseleccionado.getCiudad());
            spinnercantidadHuespedesGestionAlojamiento2.getValueFactory().setValue(alojamientoseleccionado.getCapacidadHuespedes());
            if (alojamientoseleccionado instanceof Apartamento || alojamientoseleccionado instanceof Casa) {
                if (alojamientoseleccionado instanceof Apartamento){
                    txtCostoAdicionalGestionAlojamiento.setText(""+((Apartamento) alojamientoseleccionado).getCostoAdicional());
                }
                if (alojamientoseleccionado instanceof Casa){
                    txtCostoAdicionalGestionAlojamiento.setText(""+((Casa)alojamientoseleccionado).getCostoAdicional());
                }
            }else{
                txtCostoAdicionalGestionAlojamiento.clear();
            }
        }
    }

    protected void cargarAlojamientos() {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().listarAlojamientosPrincipales());
        tableAlojamientosGestionAlojamientos.setItems(alojamientos);
    }
}

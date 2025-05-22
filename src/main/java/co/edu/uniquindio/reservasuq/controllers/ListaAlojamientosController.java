package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.entities.Oferta;
import co.edu.uniquindio.reservasuq.model.factory.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ListaAlojamientosController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private Alojamiento alojamientoseleccionado;
    private Habitacion habitacionseleccionada;
    private ObservableList<Habitacion> habitaciones;
    private ObservableList<Alojamiento> alojamientos;


    @FXML
    private Button ButtonVerServicios;

    @FXML
    private Label LabelHabitaciones;

    @FXML
    private Button btnlimpiarfiltros;

    @FXML
    private Button btnReseñas;

    @FXML
    private ComboBox<String> cbxTipoAlojamientoGestionAlojamiento;

    @FXML
    private TableColumn<Habitacion, String> columnHuespedesHabitacion;

    @FXML
    private TableColumn<Alojamiento, String> columnNombreAlojamiento;

    @FXML
    private TableColumn<Habitacion, String> columnNumeroHabitacion;

    @FXML
    private TableColumn<Habitacion, String> columnPrecioHabitacion;

    @FXML
    private TableColumn<Alojamiento, String> columnTipoAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnPreciopornoche;

    @FXML
    private TableColumn<Alojamiento, String> columnciudadAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnhuespedesAlojamiento;

    @FXML
    private TableColumn<Oferta, String> columnhuespedesoferta;

    @FXML
    private TableColumn<Oferta, String> columnnombreOferta;

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
    private TableView<Habitacion> TableHabitaciones;


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

    public ListaAlojamientosController() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void LimpiarFiltrosAction(ActionEvent event) {
        txtnombrefiltro.setText("");
        comboTipofiltro.getSelectionModel().clearSelection();
        combociudadfiltro.getSelectionModel().clearSelection();
        spinnerHuespedesfiltro.getValueFactory().setValue(0);
        filtrarAction(event);
    }


    @FXML
    void MostrarReseñasAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            if (habitacionseleccionada!= null) {
                controladorPrincipal.crearAlerta(habitacionseleccionada.listarresenias().toString(),Alert.AlertType.INFORMATION);
            }else{
                controladorPrincipal.crearAlerta(alojamientoseleccionado.listarresenias().toString(), Alert.AlertType.INFORMATION);
            }
        }
    }
    @FXML
    void filtrarAction(ActionEvent event) {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().filtrarAlojamientos(AlojamientoFactory.seleccionarTipoAlojamiento(comboTipofiltro.getValue()),combociudadfiltro.getValue(),spinnerHuespedesfiltro.getValue(),txtnombrefiltro.getText()));
        ArrayList<Alojamiento> filtrados = controladorPrincipal.getEmpresaServicio().filtrarAlojamientos(AlojamientoFactory.seleccionarTipoAlojamiento(comboTipofiltro.getValue()),combociudadfiltro.getValue(),spinnerHuespedesfiltro.getValue(),txtnombrefiltro.getText());
        for (Alojamiento alojamiento : filtrados) {
            if (alojamiento instanceof Habitacion) {
                alojamientos.remove(alojamiento);
            }
        }
        tableAlojamientosGestionAlojamientos.setItems(alojamientos);
    }

    @FXML
    void MostrarServiciosAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            if (habitacionseleccionada != null) {
                controladorPrincipal.crearAlerta(alojamientoseleccionado.listarServicios() + habitacionseleccionada.listarServicios().toString(), Alert.AlertType.INFORMATION);
            }else{
                controladorPrincipal.crearAlerta(alojamientoseleccionado.listarServicios().toString(),Alert.AlertType.INFORMATION);
            }
        }
    }

    @FXML
    void DeseleccionarAction(ActionEvent event) {
        limpiarCampos();
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

        columnNumeroHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnPrecioHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPrecioPorNoche()));
        columnHuespedesHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCapacidadHuespedes()));
        habitaciones = FXCollections.observableArrayList();

        combociudad.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(Ciudad.values()))));
        combociudadfiltro.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(Ciudad.values()))));
        cbxTipoAlojamientoGestionAlojamiento.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Hotel", "Casa", "Apartamento", "Habitacion"))));
        comboTipofiltro.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Hotel", "Casa", "Apartamento"))));
        spinnerHuespedesfiltro.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnercantidadHuespedesGestionAlojamiento2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        tableAlojamientosGestionAlojamientos.setOnMouseClicked(mouseEvent -> {
            alojamientoseleccionado = tableAlojamientosGestionAlojamientos.getSelectionModel().getSelectedItem();
            verificarHabitaciones();
            cargarDatos();
            habitacionseleccionada = null;
        });

        TableHabitaciones.setOnMouseClicked(mouseEvent -> {
            habitacionseleccionada = TableHabitaciones.getSelectionModel().getSelectedItem();
           cargarDatosHabitacion();
        });

        spinnerHuespedesfiltro.valueProperty().addListener((observable, oldValue, newValue) -> {
            filtrarAction(new ActionEvent());
        });

        txtnombrefiltro.textProperty().addListener((observable, oldValue, newValue) -> {
           filtrarAction(new ActionEvent());
        });
    }

    private void cargarDatosHabitacion() {
        if (habitacionseleccionada != null) {
            txtNombreGestionAlojamiento.setText(habitacionseleccionada.getNombre());
            txtDescripcionGestionAlojamiento.setText(habitacionseleccionada.getDescripcion());
            txtPrecioNocheGestionAlojamiento.setText(""+ habitacionseleccionada.getPrecioPorNoche());
            imagenFotoGestionAlojamiento.setImage(habitacionseleccionada.getFoto());
            cbxTipoAlojamientoGestionAlojamiento.getSelectionModel().select(habitacionseleccionada.getClass().getSimpleName());
            combociudad.getSelectionModel().select(habitacionseleccionada.getCiudad());
            spinnercantidadHuespedesGestionAlojamiento2.getValueFactory().setValue(habitacionseleccionada.getCapacidadHuespedes());
            txtCostoAdicionalGestionAlojamiento.setText("N/A");
        }
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
                txtCostoAdicionalGestionAlojamiento.setText("N/A");
            }
        }
    }

    protected void cargarAlojamientos() {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().listarAlojamientosPrincipales());
        tableAlojamientosGestionAlojamientos.setItems(alojamientos);
    }

    private void verificarHabitaciones() {
        if (alojamientoseleccionado instanceof Hotel) {
            TableHabitaciones.setVisible(true);
            LabelHabitaciones.setVisible(true);
            habitaciones.setAll(controladorPrincipal.getEmpresaServicio().listarHabitaciones((Hotel) alojamientoseleccionado));
            TableHabitaciones.setItems(habitaciones);
        }else{
            LabelHabitaciones.setVisible(false);
            TableHabitaciones.setVisible(false);
            habitaciones.removeAll();
            TableHabitaciones.setItems(habitaciones);
            habitacionseleccionada = null;
        }
    }

    private void limpiarCampos() {
        LabelHabitaciones.setVisible(false);
        TableHabitaciones.setVisible(false);
        habitaciones.removeAll();
        TableHabitaciones.setItems(habitaciones);
        habitacionseleccionada = null;
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
}

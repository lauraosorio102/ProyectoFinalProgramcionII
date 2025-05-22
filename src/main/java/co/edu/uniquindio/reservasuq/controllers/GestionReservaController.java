package co.edu.uniquindio.reservasuq.controllers;
import co.edu.uniquindio.reservasuq.config.Sesion;
import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Reserva;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GestionReservaController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion;
    private Reserva reservaseleccionada;
    private Alojamiento alojamientoseleccionado;
    private Habitacion habitacionseleccionada;
    private ObservableList<Reserva> reservas;
    private ObservableList<Habitacion> habitaciones;
    private ObservableList<Alojamiento> alojamientos;

    @FXML
    private Button ButtonServicioshabitacion;

    @FXML
    private Button ButtonVerServicios;

    @FXML
    private Button btndeseleccionarHabitacion;

    @FXML
    private Label LabelCapacidadHabitacion;

    @FXML
    private Label LabelCiudadHabitacion;

    @FXML
    private Label LabelFotohabitacion;

    @FXML
    private Label LabelReseñaHabitacion;

    @FXML
    private Label LabelServiciosHabitacion;

    @FXML
    private Label LabelfiltroHabitaciones;

    @FXML
    private Button btnReseñas;

    @FXML
    private Button btnReseñasHabitaciones;

    @FXML
    private Button btnlimpiarfiltros;

    @FXML
    private Button btnlimpiarfiltrosHabitacion;

    @FXML
    private Button buttonAgregarGestionReserva;

    @FXML
    private Button buttonEliminarGestionReserva;

    @FXML
    private TableColumn<Reserva, String> columnCantidadHuespedesReserva;

    @FXML
    private TableColumn<Habitacion, String> columnCapacidadHuespedes;

    @FXML
    private TableColumn<Habitacion, String> columnCiudadHabitacion;

    @FXML
    private TableColumn<Alojamiento, String> columnCosteAdicionalAlojamiento;

    @FXML
    private TableColumn<Reserva, String> columnCostoReserva;

    @FXML
    private TableColumn<Alojamiento, String> columnDescripcion;

    @FXML
    private TableColumn<Habitacion, String> columnDescripcionHabitacion;

    @FXML
    private TableColumn<Reserva, String> columnFechasReserva;

    @FXML
    private TableColumn<Alojamiento, String> columnNombreAlojamiento;

    @FXML
    private TableColumn<Reserva, String> columnNombreAlojamientoReserva;

    @FXML
    private TableColumn<Reserva, String> columnNombreClienteReserva;

    @FXML
    private TableColumn<Habitacion, String> columnNumeroHabitacion;

    @FXML
    private TableColumn<Habitacion, String> columnPrecioPorNocheHabitacion;

    @FXML
    private TableColumn<Alojamiento, String> columnPreciopornoche;

    @FXML
    private TableColumn<Alojamiento, String> columnTipoAlojamiento;

    @FXML
    private TableColumn<Habitacion, String> columnTipoHabitacion;

    @FXML
    private TableColumn<Alojamiento, String> columnciudadAlojamiento;

    @FXML
    private TableColumn<Alojamiento, String> columnhuespedesAlojamiento;

    @FXML
    private ComboBox<String> comboTipofiltro;

    @FXML
    private ComboBox<Ciudad> combociudadfiltro;

    @FXML
    private ComboBox<Ciudad> combociudadfiltroHabitacion;

    @FXML
    private DatePicker dateFechaGestionReserva;

    @FXML
    private ImageView imgFotoHabitacion;

    @FXML
    private ImageView imgfoto;

    @FXML
    private Label labelFotohabitaciones;

    @FXML
    private Spinner<Integer> spinnerDiasReservaGestionreserva;

    @FXML
    private Spinner<Integer> spinnerHuespedesGestionreserva;

    @FXML
    private Spinner<Integer> spinnerHuespedesHabitacion;

    @FXML
    private Spinner<Integer> spinnerHuespedesfiltro;

    @FXML
    private TableView<Alojamiento> tableAlojamientos;

    @FXML
    private TableView<Habitacion> tableHabitaciones;

    @FXML
    private TableView<Reserva> tableReservas;

    @FXML
    private TextField txtnombrefiltro;

    public GestionReservaController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
    }


    @FXML
    void DeseleccionarAlojamientoAction(ActionEvent event) {
        alojamientoseleccionado = null;
        cargarAlojamientos();
        cargarFoto();
        DeseleccionarHabitacionAction(event);
    }

    @FXML
    void DeseleccionarHabitacionAction(ActionEvent event) {
        habitacionseleccionada = null;
        cargarHabitaciones();
        cargarFotoHabitacion();
    }

    @FXML
    void CancelarReservaAction(ActionEvent event) {
        if (reservaseleccionada != null) {
            try {
                controladorPrincipal.getEmpresaServicio().eliminarReserva(reservaseleccionada);
                Limpiar();
                controladorPrincipal.crearAlerta("Reserva cancelada con exito", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una reserva", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void HacerReservaAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            if (habitacionseleccionada != null) {
                try{
                    controladorPrincipal.getEmpresaServicio().agregarReserva(habitacionseleccionada,(Cliente) sesion.getUsuario(),spinnerHuespedesGestionreserva.getValue(),dateFechaGestionReserva.getValue(),spinnerDiasReservaGestionreserva.getValue());
                    Limpiar();
                    controladorPrincipal.crearAlerta("Reserva realizada con exito", Alert.AlertType.INFORMATION);
                }catch (Exception e){
                    controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }else{
                try{
                    controladorPrincipal.getEmpresaServicio().agregarReserva(alojamientoseleccionado,(Cliente) sesion.getUsuario(),spinnerHuespedesGestionreserva.getValue(),dateFechaGestionReserva.getValue(),spinnerDiasReservaGestionreserva.getValue());
                    Limpiar();
                    controladorPrincipal.crearAlerta("Reserva realizada con exito", Alert.AlertType.INFORMATION);
                }catch (Exception e){
                    controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione un alojamiento de la tabla de alojamientos", Alert.AlertType.INFORMATION);
        }
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
    void LimpiarFiltrosHabitacionAction(ActionEvent event) {
        combociudadfiltroHabitacion.getSelectionModel().clearSelection();
        spinnerHuespedesHabitacion.getValueFactory().setValue(0);
        filtrarHabitacionAction(event);
    }

    @FXML
    void MostrarReseñasAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            controladorPrincipal.crearAlerta(alojamientoseleccionado.getResenias().toString(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void MostrarReseñasHabitacionAction(ActionEvent event) {
        if (habitacionseleccionada!= null) {
            controladorPrincipal.crearAlerta(habitacionseleccionada.getResenias().toString(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void MostrarServiciosAction(ActionEvent event) {
        if (alojamientoseleccionado != null) {
            controladorPrincipal.crearAlerta(alojamientoseleccionado.getServicios().toString(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void MostrarServiciosHabitacionAction(ActionEvent event) {
        if (habitacionseleccionada!= null) {
            controladorPrincipal.crearAlerta(habitacionseleccionada.getServicios().toString(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void filtrarAction(ActionEvent event) {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().filtrarAlojamientos(AlojamientoFactory.seleccionarTipoAlojamiento(comboTipofiltro.getValue()),combociudadfiltro.getValue(),spinnerHuespedesfiltro.getValue(),txtnombrefiltro.getText()));
        tableAlojamientos.setItems(alojamientos);
    }

    @FXML
    void filtrarHabitacionAction(ActionEvent event) {
        ArrayList<Habitacion> filtradas = new ArrayList<>();
        ArrayList<Alojamiento> alojamientoss = controladorPrincipal.getEmpresaServicio().filtrarAlojamientos(Habitacion.class,combociudadfiltroHabitacion.getValue(),spinnerHuespedesHabitacion.getValue(),"");
        for (Alojamiento alojamiento : alojamientoss) {
            filtradas.add((Habitacion) alojamiento);
        }
        habitaciones.setAll(filtradas);
        tableHabitaciones.setItems(habitaciones);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombreAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        columnTipoAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        columnciudadAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad().toString()));
        columnhuespedesAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCapacidadHuespedes()));
        columnPreciopornoche.setCellValueFactory(cellData -> new SimpleStringProperty(""+ cellData.getValue().getPrecioPorNoche()));
        columnCosteAdicionalAlojamiento.setCellValueFactory(cellData->{
            Alojamiento alojamiento = cellData.getValue();
            if (!(alojamiento instanceof Hotel)){
                if (alojamiento instanceof Casa){
                    return new SimpleStringProperty(""+((Casa) alojamiento).getCostoAdicional());
                }
                if (alojamiento instanceof Apartamento){
                    return new SimpleStringProperty(""+ ((Apartamento) alojamiento).getCostoAdicional());
                }
            }
            return new SimpleStringProperty("N/A");
        });
        alojamientos = FXCollections.observableArrayList();
        cargarAlojamientos();

        columnNumeroHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnDescripcionHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        columnTipoHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        columnCiudadHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad().toString()));
        columnCapacidadHuespedes.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCapacidadHuespedes()));
        columnPrecioPorNocheHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(""+ cellData.getValue().getPrecioPorNoche()));
        habitaciones = FXCollections.observableArrayList();
        cargarHabitaciones();

        columnNombreAlojamientoReserva.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getAlojamiento().getNombre()));
        columnNombreClienteReserva.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCliente().getNombre()));
        columnCantidadHuespedesReserva.setCellValueFactory(cellData-> new SimpleStringProperty(""+ cellData.getValue().getNumeroHuespedes()));
        columnFechasReserva.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getDiasReserva().getFirst().toString() + " - " + cellData.getValue().getDiasReserva().getLast().toString()));
        columnCostoReserva.setCellValueFactory(cellData-> new SimpleStringProperty(""+cellData.getValue().getPrecio()));
        reservas = FXCollections.observableArrayList();
        cargarReservas();

        combociudadfiltro.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(Ciudad.values()))));
        combociudadfiltroHabitacion.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList(Ciudad.values()))));
        comboTipofiltro.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Hotel", "Casa", "Apartamento"))));
        spinnerHuespedesfiltro.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnerHuespedesHabitacion.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnerDiasReservaGestionreserva.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnerHuespedesGestionreserva.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        tableReservas.setOnMouseClicked(mouseEvent -> {
            reservaseleccionada = tableReservas.getSelectionModel().getSelectedItem();
            cargarDatos();
        });

        tableAlojamientos.setOnMouseClicked(mouseEvent -> {
            alojamientoseleccionado = tableAlojamientos.getSelectionModel().getSelectedItem();
            cargarHabitaciones();
            cargarFoto();
        });

        tableHabitaciones.setOnMouseClicked(mouseEvent -> {
            habitacionseleccionada = tableHabitaciones.getSelectionModel().getSelectedItem();
            cargarFotoHabitacion();
        });

        txtnombrefiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarAction(new ActionEvent());
        });
        spinnerHuespedesfiltro.valueProperty().addListener((observable, oldValue, newValue) -> {
            filtrarAction(new ActionEvent());
        });
        spinnerHuespedesHabitacion.valueProperty().addListener((observable, oldValue, newValue) -> {
            filtrarHabitacionAction(new ActionEvent());
        });

    }

    private void cargarFotoHabitacion() {
        if (habitacionseleccionada != null) {
            imgFotoHabitacion.setImage(habitacionseleccionada.getFoto());
        }else{
            imgFotoHabitacion.setImage(null);
        }
    }

    private void cargarFoto() {
        if (alojamientoseleccionado != null) {
            imgfoto.setImage(alojamientoseleccionado.getFoto());
        }else{
            imgfoto.setImage(null);
        }
    }

    private void cargarDatos() {
        if (reservaseleccionada != null) {
            dateFechaGestionReserva.setValue(reservaseleccionada.getDiasReserva().getFirst());
            spinnerDiasReservaGestionreserva.getValueFactory().setValue((int)ChronoUnit.DAYS.between(reservaseleccionada.getDiasReserva().getFirst(), reservaseleccionada.getDiasReserva().getLast())+1);
            spinnerHuespedesGestionreserva.getValueFactory().setValue(reservaseleccionada.getNumeroHuespedes());
        }
    }

    protected void cargarReservas() {
        reservas.setAll(controladorPrincipal.getEmpresaServicio().listarReservas(sesion.getUsuario()));
        tableReservas.setItems(reservas);
    }

    protected void cargarHabitaciones() {
        if (alojamientoseleccionado != null && alojamientoseleccionado instanceof Hotel){
            habitaciones.setAll(controladorPrincipal.getEmpresaServicio().listarHabitaciones((Hotel) alojamientoseleccionado));
            tableHabitaciones.setItems(habitaciones);
            tableHabitaciones.setVisible(true);
            labelFotohabitaciones.setVisible(true);
            imgFotoHabitacion.setVisible(true);
            btnlimpiarfiltrosHabitacion.setVisible(true);
            LabelCapacidadHabitacion.setVisible(true);
            LabelfiltroHabitaciones.setVisible(true);
            LabelCiudadHabitacion.setVisible(true);
            LabelFotohabitacion.setVisible(true);
            LabelCapacidadHabitacion.setVisible(true);
            LabelReseñaHabitacion.setVisible(true);
            LabelServiciosHabitacion.setVisible(true);
            combociudadfiltroHabitacion.setVisible(true);
            spinnerHuespedesHabitacion.setVisible(true);
            ButtonServicioshabitacion.setVisible(true);
            btnReseñasHabitaciones.setVisible(true);
            btndeseleccionarHabitacion.setVisible(true);
        }else{
            habitaciones.setAll(new ArrayList<>());
            tableHabitaciones.setItems(habitaciones);
            tableHabitaciones.setVisible(false);
            labelFotohabitaciones.setVisible(false);
            imgFotoHabitacion.setVisible(false);
            btnlimpiarfiltrosHabitacion.setVisible(false);
            LabelCapacidadHabitacion.setVisible(false);
            LabelfiltroHabitaciones.setVisible(false);
            LabelCiudadHabitacion.setVisible(false);
            LabelFotohabitacion.setVisible(false);
            LabelCapacidadHabitacion.setVisible(false);
            LabelReseñaHabitacion.setVisible(false);
            LabelServiciosHabitacion.setVisible(false);
            combociudadfiltroHabitacion.setVisible(false);
            spinnerHuespedesHabitacion.setVisible(false);
            ButtonServicioshabitacion.setVisible(false);
            btnReseñasHabitaciones.setVisible(false);
            btndeseleccionarHabitacion.setVisible(false);
        }
    }

    protected void cargarAlojamientos() {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().listarAlojamientosPrincipales());
        tableAlojamientos.setItems(alojamientos);
    }
    private void Limpiar(){
        dateFechaGestionReserva.setValue(null);
        spinnerHuespedesGestionreserva.getValueFactory().setValue(0);
        spinnerDiasReservaGestionreserva.getValueFactory().setValue(0);
        alojamientoseleccionado = null;
        habitacionseleccionada = null;
        reservaseleccionada = null;
        cargarFoto();
        cargarFotoHabitacion();
        cargarReservas();
        cargarHabitaciones();
        cargarAlojamientos();

    }
}

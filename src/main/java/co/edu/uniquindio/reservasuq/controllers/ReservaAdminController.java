package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.entities.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservaAdminController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private Reserva reservaseleccionada;
    private ObservableList<Reserva> reservas;

    @FXML
    private Button buttonEliminarGestionReserva;

    @FXML
    private TableColumn<Reserva, String> columnCantidadHuespedesReserva;

    @FXML
    private TableColumn<Reserva, String> columnCostoReserva;

    @FXML
    private TableColumn<Reserva, String> columnFechasReserva;

    @FXML
    private TableColumn<Reserva, String> columnNombreAlojamientoReserva;

    @FXML
    private TableColumn<Reserva, String> columnNombreClienteReserva;

    @FXML
    private TableView<Reserva> tableReservas;

    public ReservaAdminController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void CancelarReservaAction(ActionEvent event) {
        if (reservaseleccionada != null) {
            try {
                controladorPrincipal.getEmpresaServicio().eliminarReserva(reservaseleccionada);
                reservaseleccionada = null;
                cargarReservas();
                controladorPrincipal.crearAlerta("Reserva cancelada con exito", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una reserva", Alert.AlertType.INFORMATION);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombreAlojamientoReserva.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getAlojamiento().getNombre()));
        columnNombreClienteReserva.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCliente().getNombre()));
        columnCantidadHuespedesReserva.setCellValueFactory(cellData-> new SimpleStringProperty(""+ cellData.getValue().getNumeroHuespedes()));
        columnFechasReserva.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getDiasReserva().getFirst().toString() + " - " + cellData.getValue().getDiasReserva().getLast().toString()));
        columnCostoReserva.setCellValueFactory(cellData-> new SimpleStringProperty(""+cellData.getValue().getPrecio()));
        reservas = FXCollections.observableArrayList();
        cargarReservas();
        tableReservas.setOnMouseClicked(mouseEvent -> {
            reservaseleccionada = tableReservas.getSelectionModel().getSelectedItem();
        });
    }

    protected void cargarReservas() {
        reservas.setAll(controladorPrincipal.getEmpresaServicio().listarReservas());
        tableReservas.setItems(reservas);
    }
}
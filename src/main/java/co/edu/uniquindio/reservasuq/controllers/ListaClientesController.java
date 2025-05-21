package co.edu.uniquindio.reservasuq.controllers;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.simplejavamail.api.internal.clisupport.model.Cli;

import java.net.URL;
import java.util.ResourceBundle;


public class ListaClientesController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private Cliente clienteseleccionado;
    private ObservableList<Cliente> clientes;

    @FXML
    private TableColumn<Cliente, String> columnCedula;

    @FXML
    private TableColumn<Cliente, String> columnNombre;

    @FXML
    private TableColumn<Cliente, String> columnTelefono;

    @FXML
    private TableColumn<Cliente, String> columncorreo;

    @FXML
    private TableColumn<Cliente, String> columnestado;

    @FXML
    private TableColumn<Cliente, String> columnsaldo;

    @FXML
    private TableView<Cliente> tableClientes;

    public ListaClientesController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void DeshabilitarCuenta(ActionEvent event) {
        if (clienteseleccionado != null) {
            controladorPrincipal.getEmpresaServicio().buscarCliente(clienteseleccionado.getCedula()).setEstado(false);
            clienteseleccionado = null;
            controladorPrincipal.crearAlerta("Cliente deshabilitado correctamente", Alert.AlertType.INFORMATION);
        }else{
            controladorPrincipal.crearAlerta("Seleccione un cliente", Alert.AlertType.INFORMATION);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombre.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getNombre()));
        columncorreo.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCorreo()));
        columnTelefono.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getTelefono()));
        columnCedula.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getCedula()));
        columnsaldo.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().consultarSaldo()));
        columnestado.setCellValueFactory(cellData -> new SimpleStringProperty(((Boolean)cellData.getValue().isEstado()).toString()));
        clientes = FXCollections.observableArrayList();
        cargarClientes();

        tableClientes.setOnMouseClicked(event -> {
            clienteseleccionado = tableClientes.getSelectionModel().getSelectedItem();
        });

    }

    protected void cargarClientes() {
        clientes.setAll(controladorPrincipal.getEmpresaServicio().listarClientes());
        tableClientes.setItems(clientes);
    }
}

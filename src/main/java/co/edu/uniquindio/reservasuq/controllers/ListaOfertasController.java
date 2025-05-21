package co.edu.uniquindio.reservasuq.controllers;
import co.edu.uniquindio.reservasuq.model.entities.Oferta;
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

public class ListaOfertasController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private ObservableList<Oferta> ofertas;

    @FXML
    private TableView<Oferta> TableOfertas;

    @FXML
    private TableColumn<Oferta,String> columnAlojamientosOferta;

    @FXML
    private TableColumn<Oferta,String> columnValordescuento;

    @FXML
    private TableColumn<Oferta,String> columndiasoferta;

    @FXML
    private TableColumn<Oferta,String> columnfechaInicio;

    @FXML
    private TableColumn<Oferta,String> columnfechafin;

    @FXML
    private TableColumn<Oferta,String> columnhuespedesoferta;

    @FXML
    private TableColumn<Oferta,String> columnnombreOferta;

    public ListaOfertasController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    protected void cargarOfertas() {
        ofertas.setAll(controladorPrincipal.getEmpresaServicio().listarOfertas());
        TableOfertas.setItems(ofertas);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnnombreOferta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnValordescuento.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getValorDescuento()));
        columnfechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechasdescuento().getFirst().toString()));
        columnfechafin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechasdescuento().getLast().toString()));
        columnhuespedesoferta.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getHuespedes()));
        columndiasoferta.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getDiasReserva()));
        columnAlojamientosOferta.setCellValueFactory(cellData -> {
            StringBuilder alojamientosoferta = new StringBuilder();
            for (Alojamiento alojamiento : cellData.getValue().getAlojamientos()) {
                alojamientosoferta.append(alojamiento.getNombre()).append(", ");
            }
            return new SimpleStringProperty(alojamientosoferta.toString());
        });
        ofertas = FXCollections.observableArrayList();
        cargarOfertas();
    }
}

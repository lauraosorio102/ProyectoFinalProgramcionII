package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.entities.Oferta;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class GestionOfertaController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private Alojamiento alojamientoseleccionado;
    private ObservableList<Alojamiento> alojamientos;
    private Oferta ofertaseleccionada;
    private ObservableList<Oferta> ofertas;
    @FXML
    private Button buttonAgregarGestionOferta;

    @FXML
    private Button buttonEditarGestionOferta;

    @FXML
    private Button buttonEliminarGestionOferta;

    @FXML
    private TableColumn<Alojamiento, String> columnCiudad;

    @FXML
    private TableColumn<Alojamiento, String> columnDescripcionalojamiento;

    @FXML
    private TableColumn<Oferta, String> columnDiasReservaOferta;

    @FXML
    private TableColumn<Oferta, String> columnFechasOferta;

    @FXML
    private TableColumn<Oferta, String> columnHuespedesOferta;

    @FXML
    private TableColumn<Alojamiento, String> columnNombre;

    @FXML
    private TableColumn<Oferta, String> columnNombreOferta;

    @FXML
    private TableColumn<Oferta, String> columnValorDescuento;

    @FXML
    private TableColumn<Alojamiento, Image> columnfoto;

    @FXML
    private DatePicker dateFechaGestionOferta;

    @FXML
    private Spinner<Integer> spinnerDiasReservaGestionOferta;

    @FXML
    private Spinner<Integer> spinnerHuespedesGestionOferta;

    @FXML
    private Spinner<Integer> spinnercantidadDiasGestionOferta;

    @FXML
    private TableView<Alojamiento> tableAlojamientosGestionOfertas;

    @FXML
    private TableView<Oferta> tableOfertasGestionOferta;

    @FXML
    private TextField txtBuscarNombreGestionOferta;

    @FXML
    private TextField txtNombreGestionOferta;

    @FXML
    private TextField txtValorDescuentoGestionOferta;

    public GestionOfertaController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void AñadirOfertaAlojamientoAction(ActionEvent event) {
        if (ofertaseleccionada != null) {
            try{
                controladorPrincipal.getEmpresaServicio().añadirOfertaAlojamiento(ofertaseleccionada, alojamientoseleccionado);
                limpiar();
                controladorPrincipal.crearAlerta("Alojamiento agregado correctamente a la oferta", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una oferta de la tabla de ofertas", Alert.AlertType.ERROR);
        }
    }

    private void limpiar() {
        txtBuscarNombreGestionOferta.clear();
        txtNombreGestionOferta.clear();
        txtValorDescuentoGestionOferta.clear();
        dateFechaGestionOferta.setValue(null);
        spinnerHuespedesGestionOferta.getValueFactory().setValue(0);
        spinnercantidadDiasGestionOferta.getValueFactory().setValue(0);
        spinnerDiasReservaGestionOferta.getValueFactory().setValue(0);
        alojamientoseleccionado = null;
        ofertaseleccionada = null;
    }

    @FXML
    void EliminarAction(ActionEvent event) {
        if (ofertaseleccionada != null){
            try {
                controladorPrincipal.getEmpresaServicio().eliminarOferta(ofertaseleccionada);
                controladorPrincipal.crearAlerta("La oferta se eliminó correctamente", Alert.AlertType.INFORMATION);
                limpiar();
                cargarOfertas();
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(),Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una oferta de la tabla de ofertas", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void EliminarOfertaAlojamientoAction(ActionEvent event) {
        if (ofertaseleccionada != null) {
            try{
                controladorPrincipal.getEmpresaServicio().eliminarOfertaAlojamiento(ofertaseleccionada, alojamientoseleccionado);
                limpiar();
                controladorPrincipal.crearAlerta("Alojamiento eliminado correctamente de la oferta", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una oferta de la tabla de ofertas", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void agregarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().agregarOferta(txtNombreGestionOferta.getText(),dateFechaGestionOferta.getValue(),spinnercantidadDiasGestionOferta.getValue(),spinnerHuespedesGestionOferta.getValue(),txtValorDescuentoGestionOferta.getText(),spinnerDiasReservaGestionOferta.getValue());
            controladorPrincipal.crearAlerta("La oferta se creó correctamente", Alert.AlertType.INFORMATION);
            limpiar();
            cargarOfertas();
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(),Alert.AlertType.ERROR);
        }
    }

    @FXML
    void editarAction(ActionEvent event) {
        if (ofertaseleccionada != null){
            try{
                controladorPrincipal.getEmpresaServicio().editarOferta(ofertaseleccionada,txtNombreGestionOferta.getText(),dateFechaGestionOferta.getValue(),spinnercantidadDiasGestionOferta.getValue(),spinnerHuespedesGestionOferta.getValue(),txtValorDescuentoGestionOferta.getText(),spinnerDiasReservaGestionOferta.getValue());
                controladorPrincipal.crearAlerta("La oferta se creó correctamente", Alert.AlertType.INFORMATION);
                limpiar();
                cargarOfertas();
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(),Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una oferta de la tabla de ofertas", Alert.AlertType.INFORMATION);
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

        columnNombreOferta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnFechasOferta.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getFechasdescuento().getFirst().toString() + " - " + cellData.getValue().getFechasdescuento().getLast().toString()));
        columnHuespedesOferta.setCellValueFactory(cellData ->new SimpleStringProperty(""+cellData.getValue().getHuespedes()));
        columnDiasReservaOferta.setCellValueFactory(cellData ->new SimpleStringProperty(""+cellData.getValue().getDiasReserva()));
        columnValorDescuento.setCellValueFactory(cellData ->new SimpleStringProperty(""+cellData.getValue().getValorDescuento()));

        spinnercantidadDiasGestionOferta.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnerDiasReservaGestionOferta.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnerHuespedesGestionOferta.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        ofertas = FXCollections.observableArrayList();
        alojamientos = FXCollections.observableArrayList();

        cargarOfertas();
        cargarAlojamientos();

        tableAlojamientosGestionOfertas.setOnMouseClicked(mouseEvent -> {
            alojamientoseleccionado = tableAlojamientosGestionOfertas.getSelectionModel().getSelectedItem();
        });

        tableOfertasGestionOferta.setOnMouseClicked(mouseEvent -> {
            ofertaseleccionada = tableOfertasGestionOferta.getSelectionModel().getSelectedItem();
            cargarDatosOferta();
        });

        txtBuscarNombreGestionOferta.textProperty().addListener((observable, oldValue, newValue) -> {
           filtarPornombre();
        });
    }

    private void cargarDatosOferta() {
        if (ofertaseleccionada !=null){
            txtNombreGestionOferta.setText(ofertaseleccionada.getNombre());
            dateFechaGestionOferta.setValue(ofertaseleccionada.getFechasdescuento().getFirst());
            spinnercantidadDiasGestionOferta.getValueFactory().setValue((int) ChronoUnit.DAYS.between(ofertaseleccionada.getFechasdescuento().getFirst(),ofertaseleccionada.getFechasdescuento().getLast())+1);
            spinnerHuespedesGestionOferta.getValueFactory().setValue(ofertaseleccionada.getHuespedes());
            spinnerDiasReservaGestionOferta.getValueFactory().setValue(ofertaseleccionada.getDiasReserva());
            txtValorDescuentoGestionOferta.setText(""+ofertaseleccionada.getValorDescuento());
        }
    }

    private void filtarPornombre() {
        ofertas.setAll(controladorPrincipal.getEmpresaServicio().filtrarOferta(txtBuscarNombreGestionOferta.getText()));
        tableOfertasGestionOferta.setItems(ofertas);
    }

    protected void cargarOfertas() {
        ofertas.setAll(controladorPrincipal.getEmpresaServicio().listarOfertas());
        tableOfertasGestionOferta.setItems(ofertas);
    }

    protected void cargarAlojamientos() {
        alojamientos.setAll(controladorPrincipal.getEmpresaServicio().listarAlojamientos());
        tableAlojamientosGestionOfertas.setItems(alojamientos);
    }
}

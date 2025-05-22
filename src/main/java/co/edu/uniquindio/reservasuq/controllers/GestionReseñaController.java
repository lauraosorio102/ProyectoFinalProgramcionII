package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.config.Sesion;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Resenia;
import co.edu.uniquindio.reservasuq.model.entities.Reserva;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionReseñaController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private Sesion sesion;
    private Reserva reservaseleccionada;
    private Resenia reseniaseleccionada;
    private ObservableList<Resenia> resenias;
    private ObservableList<Reserva> reservas;

    @FXML
    private TableColumn<Resenia, String> columnNombreAlojamiento;

    @FXML
    private TableColumn<Reserva, Image> columnfoto;

    @FXML
    private Spinner<Integer> SpinnerValoración;

    @FXML
    private Button buttonAnadirGestionReseña;

    @FXML
    private Button buttonEliminarGestionReseña;

    @FXML
    private TableColumn<Reserva, String> columnCiudad;

    @FXML
    private TableColumn<Resenia, String> columnDescripcionReseña;

    @FXML
    private TableColumn<Reserva, String> columnDescripcionalojamiento;

    @FXML
    private TableColumn<Reserva, String> columnFecha;

    @FXML
    private TableColumn<Reserva, String> columnNombre;

    @FXML
    private TableColumn<Resenia, String> columnValoracion;

    @FXML
    private TableColumn<Resenia, String> columntitulo;

    @FXML
    private TableView<Reserva> tableAlojamientosGestionReseña;

    @FXML
    private TableView<Resenia> tableReseñasGestionReseña;

    @FXML
    private TextField txtDescripcionGestionReseña;

    @FXML
    private TextField txtTituloGestionReseña;


    @FXML
    void AñadirAction(ActionEvent event) {
        if (reservaseleccionada != null) {
            try{
                controladorPrincipal.getEmpresaServicio().agregarResenia((Cliente)sesion.getUsuario(),txtTituloGestionReseña.getText(),txtDescripcionGestionReseña.getText(),SpinnerValoración.getValue(),reservaseleccionada);
                cargarReseñas();
                limpiar();
                controladorPrincipal.crearAlerta("Reseña realizada correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una reserva de la tabla de reservas😊", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void EliminarAction(ActionEvent event) {
        if (reseniaseleccionada != null) {
            try{
                controladorPrincipal.getEmpresaServicio().eliminarResenia((Cliente) sesion.getUsuario(),reseniaseleccionada);
                cargarReseñas();
                limpiar();
                controladorPrincipal.crearAlerta("Reseña eliminada correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una reseña de la tabla de reseñas😊", Alert.AlertType.INFORMATION);
        }
    }

    public GestionReseñaController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiasReserva().getFirst().toString() + " - " + cellData.getValue().getDiasReserva().getLast().toString()));
        columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getNombre()));
        columnDescripcionalojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getDescripcion()));
        columnCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getCiudad().toString()));
        columnfoto.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getAlojamiento().getFoto())
        );

        columnfoto.setCellFactory(column -> new TableCell<Reserva, Image>() {
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

        columntitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        columnDescripcionReseña.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        columnValoracion.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().getValoracion()));
        columnNombreAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreAlojamiento()));

        SpinnerValoración.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5));

        reservas = FXCollections.observableArrayList();
        resenias = FXCollections.observableArrayList();
        cargarReservas();
        cargarReseñas();
        tableAlojamientosGestionReseña.setOnMouseClicked(event -> {
            reservaseleccionada = tableAlojamientosGestionReseña.getSelectionModel().getSelectedItem();
        });

        tableReseñasGestionReseña.setOnMouseClicked(event -> {
            reseniaseleccionada = tableReseñasGestionReseña.getSelectionModel().getSelectedItem();
        });
    }

    protected void cargarReseñas() {
        resenias.setAll(((Cliente) sesion.getUsuario()).getResenias());
        tableReseñasGestionReseña.setItems(resenias);
    }

    protected void cargarReservas() {
        reservas.setAll(controladorPrincipal.getEmpresaServicio().listarReservas(sesion.getUsuario()));
        tableAlojamientosGestionReseña.setItems(reservas);
    }

    private void limpiar(){
        txtDescripcionGestionReseña.clear();
        txtTituloGestionReseña.clear();
        reservaseleccionada = null;
        reseniaseleccionada = null;
        SpinnerValoración.getValueFactory().setValue(0);
    }
}

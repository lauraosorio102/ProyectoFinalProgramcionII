package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.Habitacion;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionHabitacionesController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private Hotel hotelseleccionado;
    private Habitacion habitacionseleccionada;
    private ObservableList<Hotel>hoteles;
    private ObservableList<Habitacion>habitaciones;

    @FXML
    private Button buttonAnadirGestionHabitaciones;

    @FXML
    private Button buttonEliminarGestionHabitaciones;

    @FXML
    private Button buttonSeleccionarFotoGestionHabitaciones;

    @FXML
    private TableColumn<Hotel, String> columnCiudad;

    @FXML
    private TableColumn<Habitacion, String> columnCiudadHabitacion;

    @FXML
    private TableColumn<Hotel, String> columnDescripcionalojamiento;

    @FXML
    private TableColumn<Habitacion, String> columnDescripcionalojamientoHabitacion;

    @FXML
    private TableColumn<Hotel, String> columnNombre;

    @FXML
    private TableColumn<Habitacion, String> columnNombreHabitacion;

    @FXML
    private TableColumn<Hotel, Image> columnfoto;

    @FXML
    private TableColumn<Habitacion, Image> columnfotoHabitacion;

    @FXML
    private ImageView imagenFotoGestionHabitaciones;

    @FXML
    private Spinner<Integer> spinneeNumeroHabitacionesGestionHabitaciones;

    @FXML
    private Spinner<Integer> spinnercantidadHuespedesGestionHabitaciones;

    @FXML
    private TableView<Habitacion> tableHabitacion;

    @FXML
    private TableView<Hotel> tableHoteles;

    @FXML
    private TextField txtCostoGestionHabitaciones;

    @FXML
    private TextField txtDescripcionGestionHabitaciones;

    public GestionHabitacionesController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void AñadirAction(ActionEvent event) {
        if (hotelseleccionado != null) {
            try{
                controladorPrincipal.getEmpresaServicio().agregarHabitacion(hotelseleccionado,""+spinneeNumeroHabitacionesGestionHabitaciones.getValue(),txtDescripcionGestionHabitaciones.getText(),txtCostoGestionHabitaciones.getText(),spinnercantidadHuespedesGestionHabitaciones.getValue(),imagenFotoGestionHabitaciones.getImage());
                limpiar();
                cargarHabitaciones();
                controladorPrincipal.crearAlerta("Habitación agregada Correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione un hotel de la tabla de hoteles", Alert.AlertType.INFORMATION);
        }
    }

    private void limpiar() {
        spinneeNumeroHabitacionesGestionHabitaciones.getValueFactory().setValue(0);
        spinnercantidadHuespedesGestionHabitaciones.getValueFactory().setValue(0);
        txtCostoGestionHabitaciones.clear();
        txtDescripcionGestionHabitaciones.clear();
        imagenFotoGestionHabitaciones.setImage(null);
        hotelseleccionado = null;
        habitacionseleccionada = null;
    }

    @FXML
    void EliminarAction(ActionEvent event) {
        if (habitacionseleccionada != null) {
            try{
                controladorPrincipal.getEmpresaServicio().eliminarHabitacion(hotelseleccionado,habitacionseleccionada);
                limpiar();
                cargarHabitaciones();
                controladorPrincipal.crearAlerta("La habitación se elimino correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una habitación de la tabla de habitaciones", Alert.AlertType.INFORMATION);
        }
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
                imagenFotoGestionHabitaciones.setImage(image);
                controladorPrincipal.crearAlerta("Imagen cargada correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                controladorPrincipal.crearAlerta("Error al cargar la imagen", Alert.AlertType.ERROR);
            }
        } else {
            controladorPrincipal.crearAlerta("Seleccione una imagen de perfil", Alert.AlertType.INFORMATION);
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

        columnfoto.setCellFactory(column -> new TableCell<Hotel, Image>() {
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

        columnNombreHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnDescripcionalojamientoHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        columnCiudadHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad().toString()));
        columnfotoHabitacion.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getFoto())
        );

        columnfotoHabitacion.setCellFactory(column -> new TableCell<Habitacion, Image>() {
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

        habitaciones = FXCollections.observableArrayList();
        hoteles = FXCollections.observableArrayList();

        cargarHoteles();
        cargarHabitaciones();

        spinneeNumeroHabitacionesGestionHabitaciones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        spinnercantidadHuespedesGestionHabitaciones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        tableHoteles.setOnMouseClicked(event -> {
            hotelseleccionado = tableHoteles.getSelectionModel().getSelectedItem();
            cargarHabitaciones();
            habitacionseleccionada = null;
        });

        tableHabitacion.setOnMouseClicked(event -> {
            habitacionseleccionada = tableHabitacion.getSelectionModel().getSelectedItem();
        });

    }

    protected void cargarHabitaciones() {
        if (hotelseleccionado != null) {
            habitaciones.setAll(controladorPrincipal.getEmpresaServicio().listarHabitaciones(hotelseleccionado));
            tableHabitacion.setItems(habitaciones);
        }else {
            habitaciones.setAll(new ArrayList<>());
            tableHabitacion.setItems(habitaciones);
        }
    }

    protected void cargarHoteles() {
        hoteles.setAll(controladorPrincipal.getEmpresaServicio().listarHoteles());
        tableHoteles.setItems(hoteles);
    }
}

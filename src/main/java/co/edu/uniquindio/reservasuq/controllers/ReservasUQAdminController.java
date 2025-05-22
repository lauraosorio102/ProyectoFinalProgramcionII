package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.config.Sesion;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ReservasUQAdminController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion;
    private GestionAlojamientoController gestionAlojamientoController;
    private GestionHabitacionesController gestionHabitacionesController;
    private GestionOfertaController gestionOfertaController;
    private GestionServiciosController gestionServiciosController;
    private ListaAlojamientosController listaAlojamientosController;
    private ListaOfertasController listaOfertasController;
    private ListaClientesController listaClientesController;
    private ReservaAdminController reservaAdminController;

    @FXML
    private Button buttonCerrarsesionReservas;

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private Tab tab3;

    @FXML
    private Tab tab4;

    @FXML
    private Tab tab5;

    @FXML
    private Tab tab6;

    @FXML
    private Tab tab7;

    @FXML
    private Tab tab8;

    @FXML
    private Tab tab9;

    @FXML
    private Tab tab10;

    @FXML
    private TabPane tabpane;


    public ReservasUQAdminController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
    }

    @FXML
    void CerrarSesionAction(ActionEvent event) {
        sesion.cerrarSesion();
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQView.fxml");
        controladorPrincipal.cerrarVentana(buttonCerrarsesionReservas);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            gestionAlojamientoController = (GestionAlojamientoController) cargarTab(tab1,"/co/edu/uniquindio/reservasuq/gestionAlojamientoview.fxml");
            gestionHabitacionesController = (GestionHabitacionesController) cargarTab(tab2,"/co/edu/uniquindio/reservasuq/gestionHabitacionesview.fxml");
            gestionOfertaController = (GestionOfertaController) cargarTab(tab3, "/co/edu/uniquindio/reservasuq/gestionOfertaView.fxml");
            gestionServiciosController = (GestionServiciosController) cargarTab(tab4,"/co/edu/uniquindio/reservasuq/gestionServiciosView.fxml");
            listaAlojamientosController = (ListaAlojamientosController) cargarTab(tab5, "/co/edu/uniquindio/reservasuq/ListaAlojamientosView.fxml");
            listaOfertasController = (ListaOfertasController) cargarTab(tab6, "/co/edu/uniquindio/reservasuq/ListaOfertasView.fxml");
            cargarTab(tab7,"/co/edu/uniquindio/reservasuq/cambiarContraseñaView.fxml");
            listaClientesController = (ListaClientesController) cargarTab(tab8, "/co/edu/uniquindio/reservasuq/ListaClientesView.fxml");
            cargarTab(tab9,"/co/edu/uniquindio/reservasuq/ReportesView.fxml");
            reservaAdminController = (ReservaAdminController) cargarTab(tab10,"/co/edu/uniquindio/reservasuq/ReservaAdminView.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
        tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           actualizarTablas();
        });
    }

    private void actualizarTablas() {
        gestionServiciosController.cargarServicios();
        gestionServiciosController.cargarAlojamientos();
        gestionHabitacionesController.cargarHabitaciones();
        gestionHabitacionesController.cargarHoteles();
        gestionOfertaController.cargarOfertas();
        gestionOfertaController.cargarAlojamientos();
        gestionAlojamientoController.cargarAlojamientos();
        listaAlojamientosController.cargarAlojamientos();
        listaOfertasController.cargarOfertas();
        listaClientesController.cargarClientes();
        reservaAdminController.cargarReservas();
    }

    private Initializable cargarTab(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
        return loader.getController();
    }
}

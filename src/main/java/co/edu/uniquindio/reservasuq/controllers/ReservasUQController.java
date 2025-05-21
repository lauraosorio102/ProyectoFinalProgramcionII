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

public class ReservasUQController implements Initializable {

    private ControladorPrincipal controladorPrincipal;
    private Sesion sesion;
    private ListaOfertasController listaOfertasController;
    private ListaAlojamientosController listaAlojamientosController;
    private GestionReservaController gestionReservaController;
    private GestionReseñaController gestionReseñaController;

    @FXML
    private Button buttonCerrarsesionReservas;

    @FXML
    private Button buttonIniciarSesionReservas;

    @FXML
    private Button buttonRegistrarseReservas;

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
    private TabPane tabpane;

    public ReservasUQController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
    }
    @FXML
    void CerrarSesionAction(ActionEvent event) {
        sesion.cerrarSesion();
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQView.fxml");
        controladorPrincipal.cerrarVentana(buttonCerrarsesionReservas);
    }

    @FXML
    void IniciarSesionAction(ActionEvent event) {
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/iniciarSesionView.fxml");
        controladorPrincipal.cerrarVentana(buttonIniciarSesionReservas);
    }

    @FXML
    void RegistrarseAction(ActionEvent event) {
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/registrarView.fxml");
        controladorPrincipal.cerrarVentana(buttonRegistrarseReservas);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (sesion.getUsuario() == null) {
            tab2.setDisable(true);
            tab3.setDisable(true);
            tab4.setDisable(true);
            tab5.setDisable(true);
            buttonCerrarsesionReservas.setVisible(false);
        }else{
            buttonRegistrarseReservas.setVisible(false);
            buttonIniciarSesionReservas.setVisible(false);
            try {
                gestionReservaController = (GestionReservaController) cargarTab(tab2, "/co/edu/uniquindio/reservasuq/gestionReservaView.fxml");
                gestionReseñaController = (GestionReseñaController) cargarTab(tab3,"/co/edu/uniquindio/reservasuq/gestionReseñaview.fxml");
                cargarTab(tab4,"/co/edu/uniquindio/reservasuq/gestionarClienteView.fxml");
                cargarTab(tab5, "/co/edu/uniquindio/reservasuq/recargarBilleteraView.fxml");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            listaAlojamientosController = (ListaAlojamientosController) cargarTab(tab1, "/co/edu/uniquindio/reservasuq/ListaAlojamientosView.fxml");
            listaOfertasController = (ListaOfertasController) cargarTab(tab6, "/co/edu/uniquindio/reservasuq/ListaOfertasView.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizarTablas();
        });

    }

    private void actualizarTablas() {
        if (sesion.getUsuario() != null) {
            gestionReservaController.cargarReservas();
            gestionReservaController.cargarHabitaciones();
            gestionReservaController.cargarAlojamientos();
            gestionReseñaController.cargarReseñas();
            gestionReseñaController.cargarReservas();
        }
        listaAlojamientosController.cargarAlojamientos();
        listaOfertasController.cargarOfertas();
    }


    private Initializable cargarTab(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
        return loader.getController();
    }
}

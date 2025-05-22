package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class OcupacionPorcentualController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;

    @FXML
    private BarChart<String, Float> barChart;

    @FXML
    private DatePicker dateFechafin;

    @FXML
    private DatePicker dateFechainicio;

    public OcupacionPorcentualController() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    void GenerarReporteAction(ActionEvent event) {
        if (dateFechafin.getValue() != null && dateFechainicio.getValue() != null) {
            if (!dateFechainicio.getValue().isAfter(dateFechafin.getValue())) {
                // Limpieza adecuada
                barChart.getData().clear();

                // Crear nueva serie para evitar reutilizar la anterior
                XYChart.Series<String, Float> nuevaSerie = new XYChart.Series<>();
                nuevaSerie.setName("Ocupación Porcentual");

                Map<Alojamiento, Float> ocupacion = controladorPrincipal.getEmpresaServicio()
                        .ocupacionPorcentual(dateFechainicio.getValue(), dateFechafin.getValue());

                for (Map.Entry<Alojamiento, Float> entry : ocupacion.entrySet()) {
                    nuevaSerie.getData().add(
                            new XYChart.Data<>(entry.getKey().getNombre(), entry.getValue())
                    );
                }

                barChart.getData().add(nuevaSerie);
                controladorPrincipal.crearAlerta("Reporte realizado correctamente", Alert.AlertType.INFORMATION);

                // Resetear fechas
                dateFechainicio.setValue(null);
                dateFechafin.setValue(null);
            } else {
                controladorPrincipal.crearAlerta("Rango de fechas inválido", Alert.AlertType.ERROR);
            }
        } else {
            controladorPrincipal.crearAlerta("Selecciona las fechas primero", Alert.AlertType.INFORMATION);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barChart.getData().clear();
    }
}

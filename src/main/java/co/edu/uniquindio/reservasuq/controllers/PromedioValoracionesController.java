package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class PromedioValoracionesController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    XYChart.Series<String,Float> series;

    @FXML
    private BarChart<String, Float> barChart;

    public PromedioValoracionesController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        series = new XYChart.Series<>();
        series.setName("Promedio de valoración");
        cargarDatos();
    }

    protected void cargarDatos() {
        Map<Alojamiento, Float> alojamientos = controladorPrincipal.getEmpresaServicio().promedioValoracion();
        for (Map.Entry<Alojamiento, Float> entry : alojamientos.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey().getNombre(), entry.getValue()));
        }
        barChart.getData().clear();
        barChart.getData().addAll(series);
    }
}

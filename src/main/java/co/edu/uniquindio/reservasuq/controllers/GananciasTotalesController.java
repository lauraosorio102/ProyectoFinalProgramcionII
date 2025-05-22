package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GananciasTotalesController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    XYChart.Series<String,Float> series;

    @FXML
    private BarChart<String, Float> barChart;

    public GananciasTotalesController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        series = new XYChart.Series<>();
        series.setName("Ganancias por Alojamientos");
        cargarDatos();
    }

    protected void cargarDatos() {
        Map<Alojamiento, Float> alojamientos = controladorPrincipal.getEmpresaServicio().gananciasTotales();
        for (Map.Entry<Alojamiento, Float> entry : alojamientos.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey().getNombre(), entry.getValue()));
        }
        barChart.getData().clear();
        barChart.getData().addAll(series);
    }
}

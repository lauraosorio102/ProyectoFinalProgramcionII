package co.edu.uniquindio.reservasuq.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GananciasTotalesTipoController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    XYChart.Series<String,Float> series;

    @FXML
    private BarChart<String, Float> barChart;

    public GananciasTotalesTipoController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        series = new XYChart.Series<>();
        series.setName("Ganancias por tipo de Alojamientos");
        cargarDatos();
    }

    protected void cargarDatos() {
        Map<Class<?>, Float> tipos = controladorPrincipal.getEmpresaServicio().alojamientosMasRentables();
        for (Map.Entry<Class<?>, Float> entry : tipos.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey().getSimpleName(), entry.getValue()));
        }
        barChart.getData().clear();
        barChart.getData().addAll(series);
    }
}


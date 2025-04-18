module com.uniquindio.reservasuq {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens com.uniquindio.reservasuq to javafx.fxml;
    exports com.uniquindio.reservasuq;
    exports com.uniquindio.reservasuq.controllers;
    opens com.uniquindio.reservasuq.controllers to javafx.fxml;
}
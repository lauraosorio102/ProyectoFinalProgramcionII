module com.uniquindio.reservasuq {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens co.edu.uniquindio.reservasuq to javafx.fxml;
    exports co.edu.uniquindio.reservasuq;
    exports co.edu.uniquindio.reservasuq.controllers;
    opens co.edu.uniquindio.reservasuq.controllers to javafx.fxml;
}
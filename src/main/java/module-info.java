module co.edu.uniquindio.reservasuq {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires jakarta.mail;
    requires javafx.swing;


    opens co.edu.uniquindio.reservasuq to javafx.fxml;
    exports co.edu.uniquindio.reservasuq;
    exports co.edu.uniquindio.reservasuq.controllers;
    opens co.edu.uniquindio.reservasuq.controllers to javafx.fxml;
}
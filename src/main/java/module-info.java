module edu.pharmacie.pharmacie {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.kordamp.ikonli.fontawesome5;

    opens edu.pharmacie to javafx.fxml;
    exports edu.pharmacie;
    exports edu.pharmacie.controller;
    opens edu.pharmacie.controller to javafx.fxml;
    exports edu.pharmacie.event;
    opens edu.pharmacie.event to javafx.fxml;
}
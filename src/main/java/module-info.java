module com.example.hibernategestionpedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires org.hibernate.orm.core;


    opens com.example.hibernategestionpedidos to javafx.fxml;
    exports com.example.hibernategestionpedidos;
    exports com.example.hibernategestionpedidos.controllers;
    opens com.example.hibernategestionpedidos.controllers to javafx.fxml;
}
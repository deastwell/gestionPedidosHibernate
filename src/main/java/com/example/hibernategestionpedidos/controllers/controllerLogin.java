package com.example.hibernategestionpedidos.controllers;


import com.example.hibernategestionpedidos.HelloApplication;
import com.example.hibernategestionpedidos.modelos.UsuarioDAO;
import com.example.hibernategestionpedidos.modelos.UsuarioDAOimp;
import com.example.hibernategestionpedidos.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Data;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Data
public class controllerLogin implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private Button btnEntrar;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Label info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void btnlog() {
        UsuarioDAO dao = new UsuarioDAOimp();
        try {
//            HelloApplication.loadFXML("ventanaPedidos.fxml", "Pedido");
            if (dao.isCorrectUser(tfUsuario.getText(), tfPass.getText())) {
                // Autenticación exitosa: cargar la ventana de pedidos
                Session.setUsuarioActual(dao.loadLogin(tfUsuario.getText(), tfPass.getText()));
                // HelloApplication.loadFXML("/ventanaPedidos.fxml", "Pedidos de " + Session.getUsuarioActual().getNombre());
                HelloApplication.loadFXML("/ventanaPedidos.fxml", "Pedidos de " + Session.getUsuarioActual().getNombre());

            } else {
                // Credenciales incorrectas
                tfUsuario.setText("");
                tfPass.setText("");
                info.setText("Nombre de usuario o contraseña incorrecto(s)");
            }
        } catch (SQLException e) {
            // Error de conexión con la base de datos
            e.printStackTrace(); // Imprimir la traza de la excepción para depurar
            info.setText("Error de conexión con la base de datos: " + e.getMessage());
        } catch (Exception e) {
            // Otra excepción inesperada
            e.printStackTrace(); // Imprimir la traza de la excepción para depurar
            info.setText("Se produjo un error: " + e.getMessage());
        }
    }


}
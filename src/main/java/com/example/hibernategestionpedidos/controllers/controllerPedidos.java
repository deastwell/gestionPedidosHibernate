package com.example.                        hibernategestionpedidos.controllers;

import com.example.hibernategestionpedidos.HelloApplication;
import com.example.hibernategestionpedidos.modelos.Pedido;
import com.example.hibernategestionpedidos.modelos.PedidoDAOimp;
import com.example.hibernategestionpedidos.modelos.Usuario;
import com.example.hibernategestionpedidos.session.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class controllerPedidos implements Initializable {
    @javafx.fxml.FXML
    private Label labelNombre;
    @javafx.fxml.FXML
    private TableView<Pedido> tablaPedidos;

    @javafx.fxml.FXML
    private TableColumn<Pedido,String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido,String> cUsuario;
    @javafx.fxml.FXML
    private TableColumn<Pedido,String> cTotal;
    @javafx.fxml.FXML
    private TableColumn<Pedido,String>  cId;
    @javafx.fxml.FXML
    private Button btnLogout;
    @javafx.fxml.FXML
    private BorderPane ventana;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Inicializando controllerPedidos...");


        Usuario user = Session.getUsuarioActual();
        System.out.println("Usuario actual: " + user);
        System.out.println(user);

        PedidoDAOimp daoPedido = new PedidoDAOimp();
        List<Pedido> pedidosDeUser = daoPedido.pedidosDeUnUsuario(user);
        System.out.println("Número de pedidos: " + pedidosDeUser.size());

        // Cambiar Titulo
        labelNombre.setText("Pedidos de " + user.getNombre() + " (" + user.getEmail() + ")");
        // Rellenar la tabla
        cId.setCellValueFactory((fila) -> {
            Integer id = fila.getValue().getId();
            return new SimpleStringProperty(id.toString());
        });
        cFecha.setCellValueFactory((fila) -> {
            String cantidad = fila.getValue().getFecha();
            return new SimpleStringProperty(cantidad);
        });
        cUsuario.setCellValueFactory((fila) -> {
            String nombre = Session.getUsuarioActual().getNombre();
            return new SimpleStringProperty(nombre);
        });
        cTotal.setCellValueFactory((fila) -> {
            String total = fila.getValue().getTotal();
            return new SimpleStringProperty(total);
        });tablaPedidos.getItems().addAll(pedidosDeUser);
    }


    /**
     * Boton logout
     */
    @javafx.fxml.FXML
    public void logoutButton() {
        Session.setUsuarioActual(null);
        // Session.setPedidoPulsado(null);
        HelloApplication.loadFXML("/ventanaLogin.fxml", "Iniciar Sesión");
    }
}
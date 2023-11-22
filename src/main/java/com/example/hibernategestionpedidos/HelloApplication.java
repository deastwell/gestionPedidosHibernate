package com.example.hibernategestionpedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage myStage;


    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        System.out.println("Iniciando la aplicación");

        // Agrega una declaración de impresión para verificar el archivo FXML que se carga
        System.out.println("Cargando ventanaLogin.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/ventanaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(true);
        stage.setMinWidth(695);
        stage.setMinHeight(475);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadFXML(String fxml, String titulo) {
        System.out.println("Cargando escena: " + fxml);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/ventanaPedidos.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle(titulo);
            myStage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo " + fxml);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

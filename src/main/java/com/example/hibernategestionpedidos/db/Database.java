package com.example.hibernategestionpedidos.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Clase que se encarga de la conexión y configuración con la base de datos "pedidos".
 */
public class Database {

    private static Connection connection;
    private final static Logger logger = Logger.getLogger(Database.class.getName());

    static {
        try {
            // Cargamos la configuración desde el archivo "database.properties"
            InputStream is = Database.class.getClassLoader().getResourceAsStream("database.properties");
            Properties config = new Properties();
            config.load(is);

            String host = config.getProperty("db_host");
            String name = config.getProperty("db_name");
            String pass = config.getProperty("db_pass");
            String port = config.getProperty("db_port");
            String user = config.getProperty("db_user");

            // URL de conexión para la base de datos "pedidos"
            String url = "jdbc:mysql://" + host + ":" + port + "/" + name;

            // Establecemos la conexión
            connection = DriverManager.getConnection(url, user, pass);
            logger.info("Conexión exitosa a la base de datos 'pedidos'");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error de conexión con la base de datos");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

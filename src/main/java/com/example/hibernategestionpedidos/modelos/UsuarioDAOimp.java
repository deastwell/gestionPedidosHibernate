package com.example.hibernategestionpedidos.modelos;


import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import com.example.hibernategestionpedidos.db.Database;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Data
public class UsuarioDAOimp implements UsuarioDAO {


    private Connection connection = (new Database().getConnection());

    private final String QUERYLOADALL = "SELECT * FROM usuarios";

    private final String QUERYLOADLOGIN = "SELECT * FROM usuarios WHERE nombre = ? and password = ?";

    @Override
    public Usuario load(Long id) {
        return null;
    }

    @Override
    public ArrayList<Usuario> loadAll() {
        var salida = new ArrayList<Usuario>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(QUERYLOADALL);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setPass(rs.getString("pass"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombreusuario"));
                salida.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public boolean isCorrectUser(String username, String password) {
        Connection conn = Database.getConnection();
        String query = "SELECT * FROM usuarios WHERE nombre = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Usuario loadLogin(String user, String pass) {
        Usuario usuario = new Usuario();
        try(PreparedStatement pst = connection.prepareStatement(QUERYLOADLOGIN)){
            pst.setString(1,user);
            pst.setString(2,pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPass(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}

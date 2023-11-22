package com.example.hibernategestionpedidos.modelos;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {

    public Usuario load(Long id) throws SQLException;

    public ArrayList<Usuario> loadAll() throws SQLException;

    public boolean isCorrectUser(String user, String pass) throws SQLException;

    public Usuario loadLogin(String user, String pass) throws SQLException;
}

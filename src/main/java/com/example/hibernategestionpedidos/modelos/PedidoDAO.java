package com.example.hibernategestionpedidos.modelos;


import java.util.ArrayList;


public interface PedidoDAO {

    public Pedido load(Long id);

    public ArrayList<Pedido> loadAll();

    public ArrayList<Pedido> pedidosDeUnUsuario(Usuario user);
}

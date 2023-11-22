package com.example.hibernategestionpedidos.modelos;

import com.example.hibernategestionpedidos.db.Database;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PedidoDAOimp implements PedidoDAO {

    private final Connection connection = (new Database().getConnection());

    private final String QUERYLOADPEDIDOSDEUSUARIO = "SELECT * FROM pedidos WHERE usuario_id = ?";

    private final String QUERYDETALLESDEUNPEDIDO = "SELECT productos.nombre, itempedido.cantidad, productos.precio FROM " +
            "pedidos INNER JOIN itempedido ON pedidos.id = itempedido.pedido_id " +
            "INNER JOIN productos ON productos.id = itempedido.producto_id " +
            "INNER JOIN usuarios ON usuarios.id = pedidos.usuario_id " +
            "WHERE pedidos.id = ?";

    private final String QUERYSUMATOTALDEUNPEDIDO = "SELECT SUM(itempedido.cantidad * productos.precio) AS suma " +
            "FROM pedidos INNER JOIN itempedido ON pedidos.id = itempedido.pedido_id " +
            "INNER JOIN productos ON productos.id = itempedido.producto_id " +
            "WHERE pedidos.id = ?";

    @Override
    public Pedido load(Long id) {
        return null;
    }

    @Override
    public ArrayList<Pedido> loadAll() {
        return null;
    }

    @Override
    public ArrayList<Pedido> pedidosDeUnUsuario(Usuario user) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(QUERYLOADPEDIDOSDEUSUARIO)) {
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setUsuarioId(rs.getInt("usuario_id"));
                pedido.setTotal(this.sumaTotalDeUnPedido(pedido.getId()));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    public ArrayList<Producto> detallesDeUnPedido(Pedido pedido) {
        ArrayList<Producto> items = new ArrayList<>();
        Producto item;
        try (PreparedStatement pst = connection.prepareStatement(QUERYDETALLESDEUNPEDIDO)) {
            pst.setInt(1, pedido.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                item = new Producto();
                item.setNombre(rs.getString("nombre"));
                item.setCantidad(rs.getInt("cantidad"));
                item.setPrecio(rs.getDouble("precio"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public String sumaTotalDeUnPedido(int idPedido) {
        double resultado = 0.0;
        try (PreparedStatement pst = connection.prepareStatement(QUERYSUMATOTALDEUNPEDIDO)) {
            pst.setInt(1, idPedido);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                resultado = rs.getDouble("suma");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DecimalFormat formato = new DecimalFormat("#.00");
        return formato.format(resultado);
    }
}
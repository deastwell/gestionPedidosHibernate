package com.example.hibernategestionpedidos.session;
import com.example.hibernategestionpedidos.modelos.Pedido;
import com.example.hibernategestionpedidos.modelos.Usuario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Session {

    @Setter
    @Getter
    private static Usuario usuarioActual;

    @Setter
    @Getter
    private static Pedido pedidoPulsado;


}
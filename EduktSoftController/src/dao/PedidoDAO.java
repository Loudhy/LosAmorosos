/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Cliente;
import model.LineaPedido;
import model.Pedido;
import model.Vendedor;

/**
 *
 * @author alulab14
 */
public interface PedidoDAO {
    int insertar(Pedido pedido);
    int actualizar(Pedido pedido);
    int eliminar(int id_pedido);
    ArrayList<Pedido> listarPorVendedor(Vendedor vendedor);
    ArrayList<Pedido> listarPorCliente(Cliente cliente);
    ArrayList<LineaPedido> listarLineasPedido(Pedido pedido);
}
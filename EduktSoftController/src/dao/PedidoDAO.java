/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Date;
import model.Cliente;
import model.EstadoPedido;
import model.LineaPedido;
import model.Pedido;
import model.Producto;
import model.Vendedor;

/**
 *
 * @author alulab14
 */
public interface PedidoDAO extends CrudDAO<Pedido> {
    ArrayList<Pedido> listarPorVendedor(Vendedor vendedor);
    ArrayList<Pedido> listarPorVendedorPorRangoDeFechas(Vendedor vendedor,Date fechaIni,Date fechaFin);
    ArrayList<Pedido> listarPorCliente(Cliente cliente);
    ArrayList<Pedido> listarPorEstadoDePedido();
    ArrayList<LineaPedido> listarLineasPedido(Pedido pedido);
    ArrayList<LineaPedido> listarLineasPedidoEnRangoFechas(Date fechaIni,Date fechaFin);
    ArrayList<LineaPedido> listarLineasPedidoPorProducto(Producto producto);
    int actualizarLineaPedidoSolicitado(int id_linea);
    int actualizarLineaPedidoRechazado(int id_linea); 
    int actualizarLineaPedidoAceptado(int id_linea); 
}

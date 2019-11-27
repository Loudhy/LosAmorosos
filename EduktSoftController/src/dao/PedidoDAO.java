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
    int actualizarLineasPedido(ArrayList<LineaPedido> lineas);
    ArrayList<Pedido> listarPorVendedor(Vendedor vendedor);
    ArrayList<Pedido> listarPorVendedorOrdenado(Vendedor vendedor, int orden);
    ArrayList<Pedido> listarPorVendedorPorEstadoPedido(Vendedor vendedor, EstadoPedido estado);
    ArrayList<Pedido> listarPorVendedorPorRangoDeFechas(Vendedor vendedor,Date fechaIni,Date fechaFin);
    ArrayList<Pedido> listarPorVendedorPorCliente(Vendedor vendedor, String filtro);
    ArrayList<Pedido> listarPorCliente(Cliente cliente);
    ArrayList<Pedido> listarPorEstadoDePedido(EstadoPedido estado);
    ArrayList<LineaPedido> listarTodasLineasPedido(Pedido pedido);
    ArrayList<LineaPedido> listarLineasPedido(Pedido pedido);
    ArrayList<LineaPedido> listarLineasPedidoEnRangoFechas(Date fechaIni,Date fechaFin);
    ArrayList<LineaPedido> listarLineasPedidoPorProducto(Producto producto);
    int actualizarFechaFacturacion(Pedido pedido);
    int actualizarFechaPagado(Pedido pedido);
    int eliminarLineasPedido(ArrayList<LineaPedido> lineas);
    int actualizarLineaPedidoSolicitado(int id_linea);
    int actualizarLineaPedidoRechazado(int id_linea); 
    int actualizarLineaPedidoAceptado(int id_linea);
    ArrayList<Pedido> listarPedidosPorVendedorPorClientePorEstadoDePedido(Vendedor vendedor, String filtro, EstadoPedido estado);
    ArrayList<Pedido> listarPedidosPorFiltroDeCliente(String filtro);
    int actualizarMontoDePedido(Pedido pedido,float monto);
}

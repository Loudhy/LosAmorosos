/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import config.DBController;
import java.util.ArrayList;
import model.Cliente;
import model.EstadoLineaPedido;
import model.EstadoPedido;
import model.LineaPedido;
import model.Producto;
import model.Pedido;
import model.Vendedor;

public class ListadoPedidosService {    
    public ArrayList<Pedido> listar(String filtro,Cliente cliente,Vendedor vendedor,EstadoPedido estado){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        if (filtro == "Default"){
            pedidos = DBController.listarPedidos();    //por fechas       
        }
        else if (filtro == "Cliente"){
            pedidos = DBController.listarPedidosPorCliente(cliente);            
        }
        else if (filtro == "Vendedor"){
            pedidos = DBController.listarPedidosPorVendedor(vendedor);
        }
        else if (filtro == "Estado"){
            pedidos = DBController.listarPedidosPorEstado(estado);
        }
        return pedidos;
    }
    public ArrayList<LineaPedido> listarLineasDePedido(Pedido pedido){
        ArrayList<LineaPedido> lineasPedido = new ArrayList<>();
        lineasPedido = DBController.listarLineasPedido(pedido);
        return lineasPedido;
    }
    
    public int editarPedido(Pedido pedido){
        int resultado = 0;
        resultado = DBController.actualizarPedido(pedido);
        return resultado;
    }
}
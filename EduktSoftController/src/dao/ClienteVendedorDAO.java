/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Date;
import model.Cliente;
import model.Cliente_Vendedor;
import model.Pedido;
import model.Vendedor;

/**
 *
 * @author UsuarioA
 */
public interface ClienteVendedorDAO {
    int insertar(Cliente_Vendedor cliente_vendedor);
    Cliente_Vendedor encontrarPorId(int id);
    Cliente_Vendedor buscarRelacion(Cliente cliente, Vendedor vendedor);
    ArrayList<Cliente> listarClientesPorVendedor(int id_vendedor);
    ArrayList<Cliente_Vendedor> listarPorCliente(int id_cliente);
    ArrayList<Pedido> listarPedidosEnRangoDeFechas(Date fechaIni, Date fechaFin, Vendedor vendedor);
    int eliminar(int id_cliente_vendedor);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
import model.Vendedor;

/**
 *
 * @author UsuarioA
 */
public interface ClienteDAO extends CrudDAO<Cliente> {
    ArrayList<Cliente> listarClientesPorNombre(String nombre);
    Pedido buscarUltimoPedido(int id_cliente);
}

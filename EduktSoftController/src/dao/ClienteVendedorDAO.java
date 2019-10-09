/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cliente_Vendedor;

/**
 *
 * @author UsuarioA
 */
public interface ClienteVendedorDAO {
    int insertar(Cliente_Vendedor cliente_vendedor);
    int eliminar(int id_cliente_vendedor);
}

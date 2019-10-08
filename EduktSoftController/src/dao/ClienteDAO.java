/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Cliente;
import model.Vendedor;

/**
 *
 * @author UsuarioA
 */
public interface ClienteDAO {
    int insertar(Cliente cliente);
    int actualizar(Cliente cliente);
    ArrayList<Cliente> listarPorVendedor(Vendedor vendedor);
}

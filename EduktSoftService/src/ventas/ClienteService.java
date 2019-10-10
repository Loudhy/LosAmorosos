/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import config.DBController;
import model.Cliente;

/**
 *
 * @author alulab14
 */
public class ClienteService {
    public int registrarCliente(Cliente cliente){
        int resultado = 0;
        DBController.insertarCliente(cliente);
        System.out.println("Cliente registrado correctamente");
        return resultado;
    }
    
}

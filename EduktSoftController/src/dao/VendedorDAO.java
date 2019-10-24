/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.ArrayList;
import java.util.Date;
import model.Pedido;
import model.Vendedor;

/**
 *
 * @author alulab14
 */
public interface VendedorDAO extends CrudDAO<Vendedor>{
    ArrayList<Pedido> listarPedidosEnRangoDeFechas(Date fechaIni, Date fechaFin, Vendedor vendedor);
}

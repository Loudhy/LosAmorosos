/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Presentacion;
import model.Producto;

/**
 *
 * @author UsuarioA
 */
public interface ProductoDAO extends CrudDAO<Producto>{
    ArrayList<Producto> listarPorNombre(String filtro);
    ArrayList<Producto> listarDisponibles();
    Producto buscarProductoPorNombre(String nombre);
    ArrayList<Presentacion> listarPresentaciones(int id_producto);
}

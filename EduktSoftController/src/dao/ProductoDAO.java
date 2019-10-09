/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Producto;

/**
 *
 * @author UsuarioA
 */
public interface ProductoDAO {
    int insertar(Producto producto);
    int actualizar(Producto producto);
    int eliminar(int id_producto);
    ArrayList<Producto> listarDisponibles();
    Producto buscarProductoPorNombre(String nombre);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import config.DBController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Producto;

/**
 *
 * @author alulab14
 */
public class ProductoVendedorService {
    public ArrayList<Producto> listarProductosOrdenados(){
        ArrayList<Producto> productos;
        productos = DBController.listarProductosDisponibles();
        Collections.sort(productos, new Comparator<Producto>(){
            @Override
            public int compare(Producto p1, Producto p2) {
                return (p1.getNombre()).compareTo((p2.getNombre()));
            }
        });
        return productos;
    }
}

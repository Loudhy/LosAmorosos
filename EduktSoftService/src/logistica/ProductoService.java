/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistica;

import config.DBController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.LineaPedido;
import model.LineaSolicitud;
import model.Producto;
import model.Solicitud;

/**
 *
 * @author alulab14
 */
public class ProductoService {
    
    public int crearNuevoProducto(Producto producto){
        int resultado = 0;
        resultado = DBController.insertarProducto(producto);
        return resultado;
    }
    
    public ArrayList<LineaPedido> listadoProductosAgrupados(){
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        ArrayList<LineaPedido> lineasPedido = new ArrayList<>();
        solicitudes = DBController.listarSolicitudes();
        for(Solicitud aux : solicitudes){
            ArrayList<LineaSolicitud> lineas = new ArrayList<>();
            lineas = aux.getLineasSolicitud();
            for(LineaSolicitud m : lineas){
                int flag = 0;
                for(LineaPedido lin : lineasPedido){
                    if(lin.getProducto().getNombre().equals(m.getLineaPedido().getProducto().getNombre())){
                        lin.setCantidad(lin.getCantidad()+ 
                              m.getLineaPedido().getCantidad());
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0){
                    lineasPedido.add(m.getLineaPedido());
                }
            }      
        }
        Collections.sort(lineasPedido, new Comparator<LineaPedido>(){
            @Override
            public int compare(LineaPedido o1, LineaPedido o2) {
                return (o1.getProducto().getNombre()).compareTo((o2.getProducto().getNombre())); 
            }
        });
        return lineasPedido;
    }
}

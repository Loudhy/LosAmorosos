/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistica;

import config.DBController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import model.EstadoLineaSolicitud;
import model.LineaSolicitud;
import model.Solicitud;

/**
 *
 * @author alulab14
 */
public class ProductosSolicitud {
 
    private int codigo;
    private String nombre;
    private int cantidad;
    public ProductosSolicitud(){
        
    }
    
    public ArrayList<ProductosSolicitud> sacarCantidadAcumuladaDeSolicitud(){
        ArrayList<ProductosSolicitud> productosAcumulados = new ArrayList<ProductosSolicitud>();
        Map<Integer,Pair<String,Integer>> mapa = new HashMap<Integer,Pair<String, Integer>>();
        ArrayList<Solicitud> solicitudes = DBController.listarSolicitudes();
        for (Solicitud aux : solicitudes){
            
            for (LineaSolicitud aux2 : aux.getLineasSolicitud()){
                if (aux2.getEstadoSolicitud() == EstadoLineaSolicitud.Pendiente){
                    if(mapa.containsKey(aux2.getLineaPedido().getProducto().getId())){
                        Pair<String, Integer> pair = mapa.get(aux2.getLineaPedido().getProducto().getId());
                        Pair<String, Integer> pair2 = new Pair<String,Integer>(pair.getKey(),pair.getValue() +aux2.getCantidad());
                        mapa.put(aux2.getLineaPedido().getProducto().getId(),pair2);
                    }
                    else{
                      Pair<String,Integer> pair = new Pair<String,Integer>(aux2.getLineaPedido().getProducto().getNombre(),aux2.getCantidad());
                      mapa.put(aux2.getLineaPedido().getProducto().getId(), pair);
                    }
                }
            }
        }
        
        for (Map.Entry<Integer,Pair<String,Integer>> entry : mapa.entrySet()){
            ProductosSolicitud productoSoli = new ProductosSolicitud();
            productoSoli.setCodigo(entry.getKey());
            Pair<String, Integer> pair = entry.getValue();
            productoSoli.setNombre(pair.getKey());
            productoSoli.setCantidad(pair.getValue());
            productosAcumulados.add(productoSoli);
        }
        
        return productosAcumulados;
    }
    
    public int getCodigo() {
        return codigo;
    }


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

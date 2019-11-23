/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistica;

import config.DBController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import model.EstadoLineaPedido;
import model.EstadoLineaSolicitud;
import model.LineaPedido;
import model.LineaSolicitud;
import model.Producto;
import model.Solicitud;

/**
 *
 * @author UsuarioA
 */
public class aprobarSolicitudes {
    public aprobarSolicitudes(){
        
    }
    
    public int aprobarLineasDeSolicitudConProducto(ArrayList<Solicitud> solicitudes, String nombreProducto){
        int numLineasCambiadas = 0;
        System.out.println(nombreProducto);
        ArrayList<LineaSolicitud> lineasCambiadasSolicitud = new ArrayList<LineaSolicitud>();
        ArrayList<LineaPedido> lineasCambiadasPedido = new ArrayList<LineaPedido>();
        Producto producto = null;
        for (Solicitud solicitud:solicitudes){
            for (LineaSolicitud lineaSolicitud: solicitud.getLineasSolicitud()){
                if (Objects.equals(lineaSolicitud.getLineaPedido().getProducto().getNombre(),nombreProducto)){
                    if (producto == null)
                        producto = lineaSolicitud.getLineaPedido().getProducto();
                    numLineasCambiadas++;
                    lineaSolicitud.setEstadoSolicitud(EstadoLineaSolicitud.Aceptado);
                    Date today = Calendar.getInstance().getTime();
                    lineaSolicitud.getLineaPedido().setFechaAtencion(today);
                    lineaSolicitud.getLineaPedido().setEstadoLineaPedido(EstadoLineaPedido.Aceptado);
                    lineaSolicitud.getLineaPedido().setCantidadPorAtender(0);
                    lineasCambiadasSolicitud.add(lineaSolicitud);
                    lineasCambiadasPedido.add(lineaSolicitud.getLineaPedido());
                }
            }
        }
        
        int resultado = DBController.actualizarLineasSolicitud(lineasCambiadasSolicitud);
        System.out.println(resultado);
        resultado = DBController.actualizarLineasPedido(lineasCambiadasPedido);
        System.out.println(resultado);
        producto.setStockEmpresa(0);
        DBController.actualizarProducto(producto);
        
        return numLineasCambiadas;
    }
}

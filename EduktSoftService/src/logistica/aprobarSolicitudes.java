/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistica;

import config.DBController;
import java.util.ArrayList;
import java.util.Objects;
import model.EstadoLineaPedido;
import model.EstadoLineaSolicitud;
import model.LineaPedido;
import model.LineaSolicitud;
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
        for (Solicitud solicitud:solicitudes){
            for (LineaSolicitud lineaSolicitud: solicitud.getLineasSolicitud()){
                if (Objects.equals(lineaSolicitud.getLineaPedido().getProducto().getNombre(),nombreProducto)){
                    System.out.println("ENTRO");
                    numLineasCambiadas++;
                    lineaSolicitud.setEstadoSolicitud(EstadoLineaSolicitud.Aceptado);
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
        
        return numLineasCambiadas;
    }
}

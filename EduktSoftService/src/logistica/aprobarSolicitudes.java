/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistica;

import java.util.ArrayList;
import model.EstadoLineaPedido;
import model.EstadoLineaSolicitud;
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
        for (Solicitud solicitud:solicitudes){
            for (LineaSolicitud lineaSolicitud: solicitud.getLineasSolicitud()){
                if (lineaSolicitud.getLineaPedido().getProducto().getNombre() == nombreProducto){
                    numLineasCambiadas++;
                    lineaSolicitud.setEstadoSolicitud(EstadoLineaSolicitud.Atendido);
                    lineaSolicitud.getLineaPedido().setEstadoLineaPedido(EstadoLineaPedido.Aceptado);
                    lineaSolicitud.getLineaPedido().setCantidadPorAtender(0);
                }
            }
        }
        return numLineasCambiadas;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.EstadoSolicitud;
import model.LineaSolicitud;
import model.Pedido;
import model.Solicitud;

/**
 *
 * @author UsuarioA
 */
public interface SolicitudDAO extends CrudDAO<Solicitud> {
    ArrayList<LineaSolicitud> listarLineasSolicitud(Solicitud solicitud);
    ArrayList<Solicitud> listarSolicitudesPorEstado(EstadoSolicitud estado);
    Solicitud buscarSolicitudPorPedido(Pedido pedido);
    ArrayList<LineaSolicitud> listarSolicitudesPorProducto(String nombreProd);
}

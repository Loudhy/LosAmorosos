/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.EstadoSolicitud;
import model.LineaSolicitud;
import model.Solicitud;

/**
 *
 * @author UsuarioA
 */
public interface SolicitudDAO {
    int insertar(Solicitud solicitud);
    int actualizar(Solicitud solicitud);
    int eliminar(int id_solicitud);
    ArrayList<LineaSolicitud> listarLineasSolicitud(Solicitud solicitud);
    ArrayList<Solicitud> listarSolicitudes();
    ArrayList<Solicitud> listarSolicitudesPorEstado(EstadoSolicitud estado);
}

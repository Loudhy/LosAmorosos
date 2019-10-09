/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alulab14
 */
public class Solicitud {

    /**
     * @return the lineasSolicitud
     */
    public ArrayList<LineaSolicitud> getLineasSolicitud() {
        return lineasSolicitud;
    }

    /**
     * @param lineasSolicitud the lineasSolicitud to set
     */
    public void setLineasSolicitud(ArrayList<LineaSolicitud> lineasSolicitud) {
        this.lineasSolicitud = lineasSolicitud;
    }

   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the estadoSolicitud
     */
    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    /**
     * @param estadoSolicitud the estadoSolicitud to set
     */
    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the logistico
     */
    public Empleado getLogistico() {
        return logistico;
    }

    /**
     * @param logistico the logistico to set
     */
    public void setLogistico(Empleado logistico) {
        this.logistico = logistico;
    }

    /**
     * @return the facturador
     */
    public Empleado getFacturador() {
        return facturador;
    }

    /**
     * @param facturador the facturador to set
     */
    public void setFacturador(Empleado facturador) {
        this.facturador = facturador;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public Solicitud(){
        lineasSolicitud = new ArrayList<LineaSolicitud>();
        pedido = new Pedido();
        logistico = new Empleado();
        facturador = new Empleado();
    }
    
    private int id;
    private EstadoSolicitud estadoSolicitud;
    private Date fechaRegistro;
    private Empleado logistico;
    private Empleado facturador;
    private ArrayList<LineaSolicitud> lineasSolicitud;
    private Pedido pedido;
}

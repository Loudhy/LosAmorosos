/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alulab14
 */
public class LineaSolicitud {

    /**
     * @return the estadoSolicitud
     */
    public EstadoLineaPedido getEstadoSolicitud() {
        return estadoSolicitud;
    }

    /**
     * @param estadoSolicitud the estadoSolicitud to set
     */
    public void setEstadoSolicitud(EstadoLineaPedido estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
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
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the lineaPedido
     */
    public LineaPedido getLineaPedido() {
        return lineaPedido;
    }

    /**
     * @param lineaPedido the lineaPedido to set
     */
    public void setLineaPedido(LineaPedido lineaPedido) {
        this.lineaPedido = lineaPedido;
    }
    
    public LineaSolicitud(){
        lineaPedido = new LineaPedido();
    }
    
    private int id;
    private int cantidad;
    private LineaPedido lineaPedido;
    private EstadoLineaPedido estadoSolicitud;
}

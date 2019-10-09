/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author UsuarioA
 */
public class LineaPedido {

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
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
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
     * @return the subtotal
     */
    public float getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the estadoLineaPedido
     */
    public EstadoLineaPedido getEstadoLineaPedido() {
        return estadoLineaPedido;
    }

    /**
     * @param estadoLineaPedido the estadoLineaPedido to set
     */
    public void setEstadoLineaPedido(EstadoLineaPedido estadoLineaPedido) {
        this.estadoLineaPedido = estadoLineaPedido;
    }

    /**
     * @return the fechaAtencion
     */
    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    /**
     * @param fechaAtencion the fechaAtencion to set
     */
    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    /**
     * @return the cantidadPorAtender
     */
    public int getCantidadPorAtender() {
        return cantidadPorAtender;
    }

    /**
     * @param cantidadPorAtender the cantidadPorAtender to set
     */
    public void setCantidadPorAtender(int cantidadPorAtender) {
        this.cantidadPorAtender = cantidadPorAtender;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public LineaPedido(){
        this.producto = new Producto();
    }
    
    public LineaPedido(Producto producto, int cantidad,EstadoLineaPedido estadoLineaPedido,
            Date fechaAtencion){
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = cantidad*producto.getPrecioUnitario();
        this.estadoLineaPedido = estadoLineaPedido;
        this.fechaAtencion = fechaAtencion;
        this.cantidadPorAtender = cantidad;
        this.active = true;
    }
    
    private int id;
    private Producto producto;
    private int cantidad;
    private float subtotal;
    private EstadoLineaPedido estadoLineaPedido;
    private Date fechaAtencion;
    private int cantidadPorAtender;
    private boolean active;
}

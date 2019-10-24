
package model;

import java.util.Date;

public class LineaPedido {
    
    private int id;
    private Producto producto;
    private int cantidad;
    private float subtotal;
    private EstadoLineaPedido estadoLineaPedido;
    private Date fechaAtencion;
    private int cantidadPorAtender;
    private boolean active;
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public EstadoLineaPedido getEstadoLineaPedido() {
        return estadoLineaPedido;
    }

    public void setEstadoLineaPedido(EstadoLineaPedido estadoLineaPedido) {
        this.estadoLineaPedido = estadoLineaPedido;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public int getCantidadPorAtender() {
        return cantidadPorAtender;
    }

    public void setCantidadPorAtender(int cantidadPorAtender) {
        this.cantidadPorAtender = cantidadPorAtender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}

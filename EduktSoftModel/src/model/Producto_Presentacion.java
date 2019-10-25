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
public class Producto_Presentacion {
    private int id;
    private Producto producto;
    private Presentacion presentacion;
    private boolean active;
    
    public Producto_Presentacion(){
        producto = new Producto();
        presentacion = new Presentacion();
    }
    
    public Producto_Presentacion(Producto producto, Presentacion presentacion){       
        this.producto = producto;
        this.presentacion = presentacion;
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

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

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
public class Presentacion {
    private int id;
    private Producto producto;
    private String diseño;
    private boolean active;
    
    public Presentacion(){ 
        this.producto = new Producto();
    }
    
    public Presentacion(String diseño){
        this.producto = new Producto();
        this.diseño = diseño;
        this.active = true;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseño() {
        return diseño;
    }

    public void setDiseño(String diseño) {
        this.diseño = diseño;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}

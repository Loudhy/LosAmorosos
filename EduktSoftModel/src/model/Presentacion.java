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
    private int id_producto;
    private String diseño;
    private boolean active;
    

    public Presentacion(){ 
        
    }
    
    public Presentacion(String diseño){
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

    public int getId_producto() {
        return id_producto;
    }

  
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}

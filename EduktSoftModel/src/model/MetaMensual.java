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
public class MetaMensual {

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
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaLimite
     */
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /**
     * @param fechaLimite the fechaLimite to set
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * @return the cantidadObjetivo
     */
    public float getCantidadObjetivo() {
        return cantidadObjetivo;
    }

    /**
     * @param cantidadObjetivo the cantidadObjetivo to set
     */
    public void setCantidadObjetivo(float cantidadObjetivo) {
        this.cantidadObjetivo = cantidadObjetivo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public MetaMensual(){
        
    }
    
    public MetaMensual(Date fechaInicio, Date fechaLimite, float cantidadObjetivo, String descripcion){
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
        this.cantidadObjetivo = cantidadObjetivo;
        this.descripcion = descripcion;
    }
    
    private int id;
    private Date fechaInicio;
    private Date fechaLimite;
    private float cantidadObjetivo;
    private String descripcion;
    private boolean active;
    
}

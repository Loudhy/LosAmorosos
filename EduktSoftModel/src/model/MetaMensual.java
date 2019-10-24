package model;

import java.util.ArrayList;
import java.util.Date;

public class MetaMensual {
    
    private int id;
    private Date fechaInicio;
    private Date fechaLimite;
    private float cantidadObjetivo;
    private String descripcion;
    private boolean active;
    private ArrayList<ObjetivoVendedor> objetivosVendedor;
    
    public MetaMensual(){    
    }
    
    public MetaMensual(Date fechaInicio, Date fechaLimite, float cantidadObjetivo, String descripcion){
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
        this.cantidadObjetivo = cantidadObjetivo;
        this.descripcion = descripcion;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public float getCantidadObjetivo() {
        return cantidadObjetivo;
    }

    public void setCantidadObjetivo(float cantidadObjetivo) {
        this.cantidadObjetivo = cantidadObjetivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public ArrayList<ObjetivoVendedor> getObjetivosVendedor() {
        return objetivosVendedor;
    }


    public void setObjetivosVendedor(ArrayList<ObjetivoVendedor> objetivosVendedor) {
        this.objetivosVendedor = objetivosVendedor;
    }           
}

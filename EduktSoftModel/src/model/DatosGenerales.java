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
public class DatosGenerales {

    /**
     * @return the igv
     */
    public float getIgv() {
        return igv;
    }

    /**
     * @param igv the igv to set
     */
    public void setIgv(float igv) {
        this.igv = igv;
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
     * @return the sueldoMinimo
     */
    public float getSueldoMinimo() {
        return sueldoMinimo;
    }

    /**
     * @param sueldoMinimo the sueldoMinimo to set
     */
    public void setSueldoMinimo(float sueldoMinimo) {
        this.sueldoMinimo = sueldoMinimo;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the plazoDePago
     */
    public int getPlazoDePago() {
        return plazoDePago;
    }

    /**
     * @param plazoDePago the plazoDePago to set
     */
    public void setPlazoDePago(int plazoDePago) {
        this.plazoDePago = plazoDePago;
    }


    public void setCorreoEmpresa(String correoEmpresa){
        this.correoEmpresa = correoEmpresa;
    }

    public String getCorreoEmpresa(){
        return correoEmpresa;
    }

    public void setContraseñaEmpresa(String contraseñaEmpresa){
      this.contraseñaEmpresa = contraseñaEmpresa;
    }

    public String getContraseñaEmpresa(){
      return contraseñaEmpresa;
    }

    public DatosGenerales(){

    }

    public DatosGenerales(float igv,float sueldoMinimo,String correoEmpresa, String contraseñaEmpresa,Date fecha,int plazoDePago){
        this.igv = igv;
        this.sueldoMinimo = sueldoMinimo;
        this.correoEmpresa = correoEmpresa;
        this.contraseñaEmpresa = contraseñaEmpresa;
        this.fecha = fecha;
        this.plazoDePago = plazoDePago;
        this.active = true;
    }

    private int id;
    private float igv;
    private float sueldoMinimo;
    private String correoEmpresa;
    private String contraseñaEmpresa;
    private Date fecha;
    private int plazoDePago;
    private boolean active;
}

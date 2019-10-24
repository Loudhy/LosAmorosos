package model;

import java.util.Date;

public class DatosGenerales {

    private int id;
    private float igv;
    private float sueldoMinimo;
    private String correoEmpresa;
    private String contraseñaEmpresa;
    private Date fecha;
    private int plazoDePago;
    private boolean active;
    
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
        
    public float getIgv() {
        return igv;
    }

    public void setIgv(float igv) {
        this.igv = igv;
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

    public float getSueldoMinimo() {
        return sueldoMinimo;
    }

    public void setSueldoMinimo(float sueldoMinimo) {
        this.sueldoMinimo = sueldoMinimo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPlazoDePago() {
        return plazoDePago;
    }

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
    
}

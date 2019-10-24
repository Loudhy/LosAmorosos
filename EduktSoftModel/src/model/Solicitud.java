package model;

import java.util.ArrayList;
import java.util.Date;

public class Solicitud {
    
    private int id;
    private String descripcion;
    private EstadoSolicitud estadoSolicitud;
    private Date fechaRegistro;
    private ArrayList<LineaSolicitud> lineasSolicitud;
    private Pedido pedido;
    
     public Solicitud(){
        lineasSolicitud = new ArrayList<LineaSolicitud>();
        pedido = new Pedido();
    }    

    public ArrayList<LineaSolicitud> getLineasSolicitud() {
        return lineasSolicitud;
    }

    public void setLineasSolicitud(ArrayList<LineaSolicitud> lineasSolicitud) {
        this.lineasSolicitud = lineasSolicitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

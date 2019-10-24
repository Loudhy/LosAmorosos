package model;

public class LineaSolicitud {
    
    private int id;
    private int cantidad;
    private LineaPedido lineaPedido;
    private EstadoLineaSolicitud estadoSolicitud;
        
    public LineaSolicitud(){
        lineaPedido = new LineaPedido();
    }
    
    public EstadoLineaSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoLineaSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LineaPedido getLineaPedido() {
        return lineaPedido;
    }

    public void setLineaPedido(LineaPedido lineaPedido) {
        this.lineaPedido = lineaPedido;
    }

}

 package model;

import java.util.ArrayList;
import java.util.Date;

public class Pedido { 
    private int id;
    private float total;
    private ArrayList<LineaPedido> lineasPedido;
    private EstadoPedido estadoPedido;
    private float facturado;
    private Cliente_Vendedor clienteVendedor;
    private Date fechaRegistro;
    private Date fechaPago;
    private Date fechaFacturacion;
    private boolean active;
    
    public Pedido(){
        lineasPedido = new ArrayList<LineaPedido>();
    }
        
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public void setLineasPedido(ArrayList<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }
    
    public void insertarLineaPedido(LineaPedido lineaPedido){
        this.lineasPedido.add(lineaPedido);
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getFacturado() {
        return facturado;
    }

    public void setFacturado(float facturado) {
        this.facturado = facturado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }
    
    public void setClienteVendedor(Cliente_Vendedor clienteVendedor) {
        this.clienteVendedor = clienteVendedor;
    }
    
    public Cliente_Vendedor getClienteVendedor() {
        return clienteVendedor;
    }
}


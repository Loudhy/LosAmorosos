/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alulab14
 */
public class Pedido {

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
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
     * @return the lineasPedido
     */
    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    /**
     * @param lineasPedido the lineasPedido to set
     */
    public void setLineasPedido(ArrayList<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }

    /**
     * @return the estadoPedido
     */
    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    /**
     * @param estadoPedido the estadoPedido to set
     */
    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the cliente_vendedor
     */
    public Cliente_Vendedor getCliente_vendedor() {
        return cliente_vendedor;
    }

    /**
     * @param cliente_vendedor the cliente_vendedor to set
     */
    public void setCliente_vendedor(Cliente_Vendedor cliente_vendedor) {
        this.cliente_vendedor = cliente_vendedor;
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
    private int id;
    private float total;
    private ArrayList<LineaPedido> lineasPedido;
    private EstadoPedido estadoPedido;
    private Date fechaRegistro;
    private Cliente_Vendedor cliente_vendedor;
    private boolean active;
}

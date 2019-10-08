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
public class Cliente_Vendedor {

    /**
     * @return the id_cliente_vendedor
     */
    public int getId_cliente_vendedor() {
        return id_cliente_vendedor;
    }

    /**
     * @param id_cliente_vendedor the id_cliente_vendedor to set
     */
    public void setId_cliente_vendedor(int id_cliente_vendedor) {
        this.id_cliente_vendedor = id_cliente_vendedor;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
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
    
    public Cliente_Vendedor(){
        
    }
    
    public Cliente_Vendedor(Cliente cliente, Vendedor vendedor){
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.active = true;
    }
    
    private int id_cliente_vendedor;
    private Cliente cliente;
    private Vendedor vendedor;
    private boolean active;
}

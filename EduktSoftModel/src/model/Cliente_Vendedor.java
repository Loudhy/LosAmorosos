package model;

import java.util.ArrayList;

public class Cliente_Vendedor {
    
    private int id_cliente_vendedor;
    private Cliente cliente;
    private Vendedor vendedor;
    private ArrayList<Pedido> pedidos;
    private boolean active;
    
    public Cliente_Vendedor(){
        this.cliente = new Cliente();
        this.vendedor = new Vendedor();
        this.pedidos = new ArrayList<Pedido>();
    }
    
    public Cliente_Vendedor(Cliente cliente, Vendedor vendedor){
        this.pedidos = new ArrayList<Pedido>();
        this.active = true;
    }
    
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {       
        this.pedidos = pedidos;
    }

    public int getId_cliente_vendedor() {
        return id_cliente_vendedor;
    }

    public void setId_cliente_vendedor(int id_cliente_vendedor) {
        this.id_cliente_vendedor = id_cliente_vendedor;
    }

    public void insertarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}

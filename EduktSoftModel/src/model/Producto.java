/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author UsuarioA
 */
public class Producto {

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
     * @return the stockEmpresa
     */
    public int getStockEmpresa() {
        return stockEmpresa;
    }

    /**
     * @param stockEmpresa the stockEmpresa to set
     */
    public void setStockEmpresa(int stockEmpresa) {
        this.stockEmpresa = stockEmpresa;
    }

    /**
     * @return the stockVendedor
     */
    public int getStockVendedor() {
        return stockVendedor;
    }

    /**
     * @param stockVendedor the stockVendedor to set
     */
    public void setStockVendedor(int stockVendedor) {
        this.stockVendedor = stockVendedor;
    }

    /**
     * @return the precioUnitario
     */
    public float getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public Producto(){
        
    }
    
    public Producto(int stockEmpresa, float precioUnitario, String nombre, String descripcion){
        this.stockEmpresa = stockEmpresa;
        this.stockVendedor = stockEmpresa;
        this.precioUnitario = precioUnitario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.active = true;
    }
    
    private int id;
    private int stockEmpresa;
    private int stockVendedor;
    private float precioUnitario;
    private String nombre;
    private String descripcion;
    private boolean active;
}

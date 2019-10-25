package model;

import java.util.ArrayList;

public class Producto {    
    private int id;
    private int stockEmpresa;
    private int stockVendedor;
    private float precioUnitario;
    private String nombre;
    private String descripcion;
    private byte[] foto;
    private ArrayList<Presentacion> presentaciones;
    private boolean active;
    
    public Producto(){
        this.presentaciones = new ArrayList<Presentacion>();
    }
    
    public Producto(int stockEmpresa, float precioUnitario, String nombre, String descripcion){
        this.stockEmpresa = stockEmpresa;
        this.stockVendedor = stockEmpresa;
        this.precioUnitario = precioUnitario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presentaciones = new ArrayList<Presentacion>();
        this.active = true;
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

    public int getStockEmpresa() {
        return stockEmpresa;
    }

    public void setStockEmpresa(int stockEmpresa) {
        this.stockEmpresa = stockEmpresa;
    }

    public int getStockVendedor() {
        return stockVendedor;
    }

    public void setStockVendedor(int stockVendedor) {
        this.stockVendedor = stockVendedor;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    public ArrayList<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(ArrayList<Presentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }
}

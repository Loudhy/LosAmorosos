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
public class ObjetivoVendedor {

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
     * @return the metaMensual
     */
    public MetaMensual getMetaMensual() {
        return metaMensual;
    }

    /**
     * @param metaMensual the metaMensual to set
     */
    public void setMetaMensual(MetaMensual metaMensual) {
        this.metaMensual = metaMensual;
    }

    /**
     * @return the monto
     */
    public float getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(float monto) {
        this.monto = monto;
    }

    /**
     * @return the bono
     */
    public float getBono() {
        return bono;
    }

    /**
     * @param bono the bono to set
     */
    public void setBono(float bono) {
        this.bono = bono;
    }

    /**
     * @return the comision
     */
    public float getComision() {
        return comision;
    }

    /**
     * @param comision the comision to set
     */
    public void setComision(float comision) {
        this.comision = comision;
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
    
    public ObjetivoVendedor(){
        
    }
    
    public ObjetivoVendedor(MetaMensual metaMensual, float monto, float bono, float comision){
        this.metaMensual = metaMensual;
        this.monto = monto;
        this.bono = bono;
        this.comision = comision;
        this.active = true;
    }
    
    private int id;
    private MetaMensual metaMensual;
    private float monto;
    private float bono;
    private float comision;
    private Vendedor vendedor;
    private boolean active;
}

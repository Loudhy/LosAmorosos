package model;

public class ObjetivoVendedor {
    
    private int id;
    private MetaMensual metaMensual;
    private float monto;
    private float bono;
    private float comision;
    private boolean active;
    
    public ObjetivoVendedor(){        
    }
    
    public ObjetivoVendedor(MetaMensual metaMensual, float monto, float bono, float comision){
        this.metaMensual = metaMensual;
        this.monto = monto;
        this.bono = bono;
        this.comision = comision;
        this.active = true;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MetaMensual getMetaMensual() {
        return metaMensual;
    }

    public void setMetaMensual(MetaMensual metaMensual) {
        this.metaMensual = metaMensual;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public float getBono() {
        return bono;
    }

    public void setBono(float bono) {
        this.bono = bono;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}

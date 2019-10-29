package model;

public class Departamento {
        
    private int id;
    private String nombre;
    private boolean active;
    
    public Departamento(){        
    }
    
    public Departamento(String nombre,String ubigeo){
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}

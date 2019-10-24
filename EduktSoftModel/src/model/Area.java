package model;

public class Area {
    
    private int id;
    private String nombre;
    private int codigo;
    private boolean active;
    
    public Area(){        
    }
   
    public Area(String nombre,int codigo){
        this.nombre = nombre;
        this.codigo = codigo;
        this.active = true;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

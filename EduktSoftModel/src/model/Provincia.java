package model;

public class Provincia {
    
    private int id;
    private String nombre;
    private Departamento departamento;
    private String ubigeo;
    private boolean active;
    
    public Provincia(){
        this.departamento = new Departamento();
    }
    
    public Provincia(String nombre, Departamento departamento, String ubigeo){
        this.nombre = nombre;
        this.departamento = departamento;
        this.ubigeo = ubigeo;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }
    
}

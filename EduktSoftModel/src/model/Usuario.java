package model;

public class Usuario {
 
    private int id;
    private String nombre;
    private String contraseña;
    private EstadoUsuario estado;
    private boolean active;
            
    public Usuario(){
    }
    
    public Usuario(String nombre,String contraseña){
        this.nombre = nombre;    
        this.contraseña = contraseña;
        this.estado = EstadoUsuario.Creado;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }
    
    public EstadoUsuario getEstado() {
        return estado;
    }

}

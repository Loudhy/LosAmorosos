package model;

public class Cliente {
    
    private int id;
    private String ruc;
    private String razonSocial;
    private String telefono;
    private String direccion;
    private String correo;
    private Provincia provincia;
    private TipoCliente tipoCliente;
    private int puntaje;
    private boolean active;
    
    public Cliente(){
        this.provincia = new Provincia();
    }
    
    public Cliente(String ruc, String razonSocial, String telefono, String direccion,
            String correo, Provincia provincia,TipoCliente tipoCliente, int puntaje){
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.provincia = provincia;
        this.tipoCliente = tipoCliente;
        this.puntaje = puntaje;
        this.active = true;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
}

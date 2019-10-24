package model;

import java.util.ArrayList;
import java.util.Date;

public class Vendedor extends Empleado{
    
    private int id_vendedor;
    private ObjetivoVendedor objetivoVendedor;
    private ArrayList<Cliente_Vendedor> clientes_vendedor;
    private boolean active;
    
    public Vendedor(){
        clientes_vendedor = new ArrayList<Cliente_Vendedor>();
        objetivoVendedor = new ObjetivoVendedor();
    }
    
     public Vendedor(String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fecha_nacimiento,
           String telefono, String correo, EstadoCivil estado_civil, float sueldo, Area area, Date fecha_ingreso){
        super(dni,nombre,apellidoPaterno,apellidoMaterno,fecha_nacimiento,
           telefono, correo, estado_civil,  sueldo,  area,  fecha_ingreso);
        clientes_vendedor = new ArrayList<Cliente_Vendedor>();
        objetivoVendedor = new ObjetivoVendedor();
        this.setActive(true);
    }        

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public ObjetivoVendedor getObjetivoVendedor() {
        return objetivoVendedor;
    }

    public void setObjetivoVendedor(ObjetivoVendedor objetivoVendedor) {
        this.objetivoVendedor = objetivoVendedor;
    }

    public ArrayList<Cliente_Vendedor> getClientes_vendedor() {
        return clientes_vendedor;
    }

    public void setClientes_vendedor(ArrayList<Cliente_Vendedor> clientes_vendedor) {
        this.clientes_vendedor = clientes_vendedor;
    }

    
}

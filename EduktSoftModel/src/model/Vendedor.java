/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alulab14
 */
public class Vendedor extends Empleado{

    public Vendedor() {
        objetivosVentas = new ArrayList<ObjetivoVendedor>();
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


    /**
     * @return the id_vendedor
     */
    public int getId_vendedor() {
        return id_vendedor;
    }

    /**
     * @param id_vendedor the id_vendedor to set
     */
    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    /**
     * @return the objetivosVentas
     */
    public ArrayList<ObjetivoVendedor> getObjetivosVentas() {
        return objetivosVentas;
    }

    /**
     * @param objetivosVentas the objetivosVentas to set
     */
    public void setObjetivosVentas(ArrayList<ObjetivoVendedor> objetivosVentas) {
        this.objetivosVentas = objetivosVentas;
    }
    
    public Vendedor(String dni, String nombre, String apellidos, Date fecha_nacimiento,
           String telefono, String correo, EstadoCivil estado_civil, float sueldo, Area area, Date fecha_ingreso){
        super(dni,nombre,apellidos, fecha_nacimiento,
           telefono, correo, estado_civil,  sueldo,  area,  fecha_ingreso);
        objetivosVentas = new ArrayList<ObjetivoVendedor>();
        this.setActive(true);
    }
    
    
    private int id_vendedor;
    private ArrayList<ObjetivoVendedor> objetivosVentas;
    private boolean active;
}

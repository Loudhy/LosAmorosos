/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Area;
import model.Empleado;

/**
 *
 * @author UsuarioA
 */
public interface EmpleadoDAO extends CrudDAO<Empleado>{
    ArrayList<Empleado> listarTodosLosEmpleados();
    ArrayList<Empleado> listarEmpleadosPorNombre(String nombre);
    ArrayList<Empleado> listarPorArea(Area area);
    ArrayList<Empleado> listarEmpleadosNoActivos();
    ArrayList<Empleado> listarEmpleadosPorAreaPorFiltro(Area area, String filtro);
    ArrayList<Empleado> listarEmpleadosPorAreaPorDni(Area area, String dni);
    Empleado buscarEmpleadoPorDni(String dni);
    Empleado buscarEmpleadoPorApellidos(String apellido_paterno,String apellido_materno);
    Empleado buscarEmpleadoPorCorreo(String correo);
    ArrayList<Empleado> listarEmpleadosPorDni(String dni);
    int darDeAltaAEmpleado(int id_empleado);
}

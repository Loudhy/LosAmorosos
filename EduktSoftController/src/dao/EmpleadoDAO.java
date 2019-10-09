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
public interface EmpleadoDAO {
    int insertar(Empleado empleado);
    int actualizar(Empleado empleado);
    int eliminar(int id_empleado);
    ArrayList<Empleado> listar();
    ArrayList<Empleado> listarPorArea(Area area);
    Empleado buscarEmpleadoPorDni(String dni);
    Empleado buscarEmpleadoPorApellidos(String apellidos);
    Empleado buscarEmpleadoPorCorreo(String correo);
}

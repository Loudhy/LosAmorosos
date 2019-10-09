/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Empleado;
import model.Usuario;

/**
 *
 * @author UsuarioA
 */
public interface UsuarioDAO {
    int insertar(Usuario usuario);
    int actualizar(Usuario usuario);
    int eliminar(int id_usuario);
    ArrayList<Usuario> listar();
    String buscarArea(Usuario usuario);
    Usuario buscarPorEmpleado(Empleado empleado);
}

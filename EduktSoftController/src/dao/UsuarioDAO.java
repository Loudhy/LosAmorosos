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
public interface UsuarioDAO extends CrudDAO<Usuario> {
    String buscarArea(Usuario usuario);
    boolean validarUsuario(String nombre, String contraseña);
    Usuario buscarPorEmpleado(Empleado empleado);
}

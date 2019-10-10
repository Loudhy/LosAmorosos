/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administracion;

import config.DBController;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import model.Usuario;

public class LogginUsuarioService {
    public boolean buscarUsuario(String correo,String contraseña){
        ArrayList<Usuario> usuarios = DBController.listarUsuarios();
        for (Usuario u : usuarios){
            if ((u.getEmpleado().getCorreo().compareTo(correo) == 0) &&
                (u.getContraseña().compareTo(contraseña) == 0)){
                int id = u.getEmpleado().getArea().getId();               
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "No ingresa los datos correctamente");
        return false;
    }
}

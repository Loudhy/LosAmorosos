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
import model.Empleado;
import model.Usuario;

public class LogginUsuarioService {
    public Empleado buscarUsuario(String correo,String contraseña){
        if (DBController.validarLoginDeUsuario(correo, contraseña)){
            return DBController.buscarEmpleadoPorCorreo(correo);
        }
        else return null;
    }
}

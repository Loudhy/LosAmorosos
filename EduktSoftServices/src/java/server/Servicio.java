/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import administracion.LogginUsuarioService;
import administracion.PasswordService;
import config.DBController;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alulab14
 */
@WebService(serviceName = "Servicio")
public class Servicio {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "validarLogin")
    public int validarLogin(@WebParam(name = "nombre") String nombre, @WebParam(name = "contraseña") String contraseña ){
        LogginUsuarioService login = new LogginUsuarioService();
        return login.buscarUsuario(nombre, contraseña);
    }

    @WebMethod(operationName = "restaurarContra")
    public int restaurarContraseña(@WebParam(name = "name") String correo){
        PasswordService passwordService = new PasswordService();
        return passwordService.enviarCorreo(correo);
    }
}

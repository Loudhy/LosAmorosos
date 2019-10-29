/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import administracion.LogginUsuarioService;
import administracion.PasswordService;
import config.DBController;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Cliente;
import model.Empleado;
import model.Presentacion;
import model.Producto;

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
    public Empleado validarLogin(@WebParam(name = "nombre") String nombre, @WebParam(name = "contraseña") String contraseña ){
        LogginUsuarioService login = new LogginUsuarioService();
        return login.buscarUsuario(nombre, contraseña);
    }

    @WebMethod(operationName = "restaurarContra")
    public int restaurarContraseña(@WebParam(name = "name") String correo){
        PasswordService passwordService = new PasswordService();
        return passwordService.enviarCorreo(correo);
    }
    
    @WebMethod(operationName = "listarProductos")
    public String listarProductos(){
        ArrayList<Producto> productos = DBController.listarProductos();
        String mensaje="";
        for (Producto aux : productos){
            mensaje+=aux.getNombre();
        }
        
        return mensaje;
    }
    
    @WebMethod(operationName = "listarPresentacionesDeProducto")
    public String listarPresentaciones(@WebParam(name = "id_producto") int id_producto){
        ArrayList<Presentacion> presentaciones =  DBController.listarPresentaciones(id_producto);
        String mensaje="";
        for (Presentacion aux: presentaciones){
            mensaje+=aux.getDiseño();
        }
        return mensaje;
    }
    
    @WebMethod(operationName = "eliminarPedido")
    public int eliminarPedido(int id){
        return DBController.eliminarPedido(id);
    }
    
    @WebMethod(operationName = "listarCliente")
    public ArrayList<Cliente> listarClientesPorVendedor(@WebParam(name = "id_vendedor") int id){
        return DBController.listarClientesPorVendedor(id);
    }
    
    @WebMethod(operationName = "listarClientesPorNombre")
    public ArrayList<Cliente> listarClientesPorNombre(@WebParam(name = "nombre") String nombre){
        return DBController.listarClientesPorNombre(nombre);
    }
}

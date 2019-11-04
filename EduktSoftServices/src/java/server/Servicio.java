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
import model.Solicitud;
import model.Empleado;
import model.Presentacion;
import model.Producto;
import ventas.MejoresProductosService;

/**
 *
 * @author alulab14
 */
@WebService(serviceName = "Servicio")
public class Servicio {

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
    public ArrayList<Producto> listarProductos(){
        return DBController.listarProductos();
    }

    @WebMethod(operationName = "listarPresentacionesDeProducto")
    public ArrayList<Presentacion> listarPresentaciones(@WebParam(name = "id_producto") int id_producto){
        return DBController.listarPresentaciones(id_producto);
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

    @WebMethod(operationName = "listarMejoresProductosDeCliente")
    public ArrayList<Producto> listarMejoresProductosDeCliente(@WebParam(name = "id_cliente") int id_cliente){
        MejoresProductosService mejoresTres = new MejoresProductosService();
        return mejoresTres.listarTresMejoresProductosPorCliente(id_cliente);
    }

    @WebMethod(operationName = "listarSolicitudes")
    public Solicitud listarSolicitud(@WebParam(name = "id_solicitud") int id_solicitud){
        return DBController.buscarSolicitudPorId(id_solicitud);
    }

    @WebMethod(operationName = "actualizarSolicitud")
    public int actualizarSolicitud(@WebParam(name = "solicitud") Solicitud solicitud){
        return DBController.actualizarSolicitud(solicitud);
    }

    @WebMethod(operationName = "buscarClientePorId")
    public Cliente buscarClientePorId(@WebParam(name = "id_cliente") int id_cliente){
        return DBController.buscarClientePorId(id_cliente);
    }

    @WebMethod(operationName = "buscarClientePorRUC")
    public Cliente buscarClientePorFiltro(@WebParam(name = "filtro") String filtro){
        return DBController.buscarClientePorFiltro(filtro);
    }

    @WebMethod(operationName = "actualizarCliente")
    public int actualizarCliente(@WebParam(name = "cliente") Cliente cliente){
        return DBController.actualizarCliente(cliente);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import administracion.LogginUsuarioService;
import administracion.PasswordService;
import comercial.ReporteProductosDisponibles;
import comercial.listarVendedores;
import config.DBController;
import config.DBManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Objects;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logistica.ProductosSolicitud;
import logistica.aprobarSolicitudes;
import model.Area;
import model.Cliente;
import model.Cliente_Vendedor;
import model.Solicitud;
import model.Empleado;
import model.LineaPedido;
import model.LineaSolicitud;
import model.Pedido;
import model.Presentacion;
import model.Producto;
import model.Provincia;
import model.Vendedor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
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
    public int restaurarContraseña(@WebParam(name = "correo") String correo){
        PasswordService passwordService = new PasswordService();
        return passwordService.enviarCorreo(correo);
    }
    
    @WebMethod(operationName = "buscarSolicitudPorId")
    public Solicitud buscarSolicitudPorId(@WebParam(name = "id")int id){
        return DBController.buscarSolicitudPorId(id);
    }
    
    @WebMethod(operationName = "listarProvincias")
    public ArrayList<Provincia> listarProvincias(){
        return DBController.listarProvincias();
    }
    
    @WebMethod(operationName = "buscarEmpleadoPorId")
    public Empleado buscarEmpleadoPorId(@WebParam(name = "idEmpleado") int idEmpleado){
        return DBController.buscarEmpleadoPorId(idEmpleado);
    }
    
    @WebMethod(operationName = "insertarProducto")
    public int insertarProducto(@WebParam(name = "producto") Producto producto){
        return DBController.insertarProducto(producto);
    }
    
    @WebMethod(operationName = "actualizarProducto")
    public int actualizarProducto(@WebParam(name = "producto") Producto producto){
        return DBController.actualizarProducto(producto);
    }

    @WebMethod(operationName = "listarProductosPorNombre")
    public ArrayList<Producto> listarProductosPorNombre(@WebParam(name = "filtro") String filtro){
        return DBController.listarProductosPorNombre(filtro);
    }
    
    @WebMethod(operationName = "buscarProductoPorId")
    public Producto buscarProductoPorId(@WebParam(name = "id") int id){
        return DBController.buscarProductoPorId(id);
    }
    
    @WebMethod(operationName = "listarPresentacionesDeProducto")
    public ArrayList<Presentacion> listarPresentaciones(@WebParam(name = "id_producto") int id_producto){
        return DBController.listarPresentaciones(id_producto);
    }

    @WebMethod(operationName = "eliminarPedido")
    public int eliminarPedido(int id){
        return DBController.eliminarPedido(id);
    }
    
    @WebMethod(operationName = "listarPedidoPorEstadoDePedido")
    public ArrayList<Pedido> listarPedidoPorEstadoDePedido(){
        return DBController.listarPedidosPorEstadoDePedido();
    }
    
    @WebMethod(operationName = "listarPedidosPorVendedor")
    public ArrayList<Pedido> listarPedidoPorvendedor(@WebParam(name = "vendedor")Vendedor vendedor){
        return DBController.listarPedidosPorVendedor(vendedor);
    }
    @WebMethod(operationName = "buscarClientePorPedido")
    public Cliente buscarClientePorPedido(@WebParam(name = "Pedido") Pedido pedido){
        return DBController.buscarClientePorClienteVendedor(pedido.getClienteVendedor().getId_cliente_vendedor());
    }
    
    @WebMethod(operationName = "insertarPedido")
    public int insertarPedido(@WebParam(name = "pedido") Pedido pedido){
        return DBController.insertarPedido(pedido);
    }
    
    @WebMethod(operationName = "insertarCliente")
    public int insertarCliente(@WebParam(name = "cliente") Cliente cliente){
        return DBController.insertarCliente(cliente);
    }
    
    @WebMethod(operationName = "actualizarCliente")
    public int actualizarCliente(@WebParam(name = "cliente") Cliente cliente){
        return DBController.actualizarCliente(cliente);
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
    public ArrayList<Producto> listarMejoresProductosDeCliente(@WebParam(name = "cliente") Cliente cliente){
        MejoresProductosService mejoresTres = new MejoresProductosService();
        return mejoresTres.listarTresMejoresProductosPorCliente(cliente);
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

    @WebMethod(operationName = "buscarClientePorFiltro")
    public Cliente buscarClientePorFiltro(@WebParam(name = "filtro") String filtro){
        return DBController.buscarClientePorFiltro(filtro);
    }

    
    @WebMethod(operationName = "listarProductosDeSolicitudes")
    public ArrayList<ProductosSolicitud> listarProductosDeSolicitudes(){
        ProductosSolicitud prod = new ProductosSolicitud();
        return prod.sacarCantidadAcumuladaDeSolicitud();
    }
    
    @WebMethod(operationName = "buscarRelacionClienteVendedor")
    public Cliente_Vendedor buscarRelacionClienteVendedor(@WebParam(name = "cliente") Cliente cliente, @WebParam(name = "vendedor")Vendedor vendedor){
        return DBController.buscarRelacionClienteVendedor(cliente, vendedor);
    }
    
    @WebMethod(operationName = "insertarPresentacion")
    public int insertarPresentacion(@WebParam(name = "presentacion") Presentacion presentacion){
        return DBController.insertarPresentacion(presentacion);
    }
    

    @WebMethod(operationName = "listarLineasSolciitudesProducto")
    public ArrayList<LineaSolicitud> listarLineasSolicitudesProducto(@WebParam(name = "nombreProducto") String nombreProducto){
        return DBController.listarLineasSolicitudesProducto(nombreProducto);
    }

    @WebMethod(operationName = "aprobarListasSolicitudConProducto")
    public int aprobarListasSolicitudConProducto(@WebParam(name = "solicitudes") ArrayList<Solicitud> solicitudes, String nombreProducto){
        aprobarSolicitudes aprobar = new aprobarSolicitudes();
        return  aprobar.aprobarLineasDeSolicitudConProducto(solicitudes, nombreProducto);
    }
    

    @WebMethod(operationName = "generarPdfReporteDeProductosDisponibles")
    public byte[] generarPdfReporteDeProductosDisponibles(){
        byte[] arreglo = null;
        try{
            JasperReport reporte = 
                    (JasperReport) 
           JRLoader.loadObjectFromFile(
     Servicio.class.getResource(
     "/reports/ReporteProductosDisponibles.jasper").getFile());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = 
        DriverManager.getConnection(
          DBManager.url, DBManager.user, DBManager.password);
            JasperPrint jp = 
                    JasperFillManager.fillReport(reporte,null,con);
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;  
    }
    @WebMethod(operationName = "listarPedidos")
    public ArrayList<Pedido> listarPedidos(){
        return DBController.listarPedidos();
    }
    
    @WebMethod(operationName = "actualizarLineaASolicitado")
    public int actualizarLineaPedidoSolicitado(@WebParam(name = "idLineaPedido")  int id_linea){
        return DBController.actualizarLineaSolicitado(id_linea);
    }
    
    @WebMethod(operationName = "listarLineasSolciitudes")
    public ArrayList<LineaSolicitud> listarLineasSolciitudes(@WebParam(name = "solicitud") Solicitud solicitud){
        return DBController.listarLineasDeSolicitud(solicitud);
    }
    

    @WebMethod(operationName = "listarEmpleados")
    public ArrayList<Empleado> listarEmpleados(@WebParam(name = "area") Area area){
        return DBController.listarEmpleadosPorArea(area);
    }
    
    @WebMethod(operationName = "listarVendedores")
    public ArrayList<Vendedor> listarVendedores(){
        listarVendedores lista = new listarVendedores();
        return lista.listarEmpleadosDeAreaVentas();
    }
    @WebMethod(operationName = "actualizarLineaARechazado")
    public int actualizarLineaPedidoRechazado(@WebParam(name = "idLineaPedido")  int id_linea){
        return DBController.actualizarLineaRechazado(id_linea);
    }
}

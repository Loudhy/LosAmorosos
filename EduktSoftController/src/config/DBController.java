/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.ArrayList;
import java.util.Date;
import model.Area;
import model.Cliente;
import model.Cliente_Vendedor;
import model.DatosGenerales;
import model.Departamento;
import model.Empleado;
import model.EstadoPedido;
import model.EstadoSolicitud;
import model.LineaPedido;
import model.LineaSolicitud;
import model.MetaMensual;
import model.ObjetivoVendedor;
import model.Pedido;
import model.Presentacion;
import model.Producto;
import model.Provincia;
import model.Solicitud;
import model.Usuario;
import model.Vendedor;

/**
 *
 * @author UsuarioA
 */
public abstract class DBController {
    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory();

    public static int insertarArea(Area area){
        return daoFactory.getAreaDAO().insertar(area);
    }

    public static int actualizarArea(Area area){
        return daoFactory.getAreaDAO().actualizar(area);
    }


    public static int eliminarArea(int id_area){
        return daoFactory.getAreaDAO().eliminar(id_area);
    }

    public static ArrayList<Area> listarAreas(){
        return daoFactory.getAreaDAO().listar();
    }

    public static Area buscarAreaPorId(int id){
        return daoFactory.getAreaDAO().encontrarPorId(id);
    }

    public static String buscarAreaPorUsuario(Usuario usuario){
        return daoFactory.getUsuarioDAO().buscarArea(usuario);
    }

    public static int insertarUsuario(Usuario usuario){
        return daoFactory.getUsuarioDAO().insertar(usuario);
    }

    public static int actualizarUsuario(Usuario usuario){
        return daoFactory.getUsuarioDAO().actualizar(usuario);
    }

    public static int eliminarUsuario(int id_usuario){
        return daoFactory.getUsuarioDAO().eliminar(id_usuario);
    }

    public static ArrayList<Usuario> listarUsuarios(){
        return daoFactory.getUsuarioDAO().listar();
    }

    public static Usuario buscarUsuarioPorEmpleado(Empleado empleado){
        return daoFactory.getUsuarioDAO().buscarPorEmpleado(empleado);
    }
    public static int insertarEmpleado(Empleado empleado){
        return daoFactory.getEmpleadoDAO().insertar(empleado);
    }

    public static int actualizarEmpleado(Empleado empleado){
        return daoFactory.getEmpleadoDAO().actualizar(empleado);
    }

    public static int eliminarEmpleado(int id_empleado){
        return daoFactory.getEmpleadoDAO().eliminar(id_empleado);
    }

    public static ArrayList<Empleado> listarEmpleados(){
        return daoFactory.getEmpleadoDAO().listar();
    }

    public static ArrayList<Empleado> listarEmpleadosPorArea(Area area){
        return daoFactory.getEmpleadoDAO().listarPorArea(area);
    }

    public static Empleado buscarEmpleadoPorId(int id){
        return daoFactory.getEmpleadoDAO().encontrarPorId(id);
    }

    public static Empleado buscarEmpleadoPorCorreo(String correo){
        return daoFactory.getEmpleadoDAO().buscarEmpleadoPorCorreo(correo);
    }

    public static Empleado buscarEmpleadoPorDni(String dni){
        return daoFactory.getEmpleadoDAO().buscarEmpleadoPorDni(dni);
    }

    public static Empleado buscarEmpleadoPorNombre(String apellidoPaterno, String apellidoMaterno){
        return daoFactory.getEmpleadoDAO().buscarEmpleadoPorApellidos(apellidoPaterno,apellidoMaterno);
    }

    public static int insertarDatosGenerales(DatosGenerales datosGenerales){
        return daoFactory.getDatosGeneralesDAO().insertar(datosGenerales);
    }

    public static DatosGenerales encontrarDatosGeneralesPorFecha(Date fecha){
        return daoFactory.getDatosGeneralesDAO().encontrarPorFecha(fecha);
    }

    public static int insertarMetaMensual(MetaMensual metaMensual){
        return daoFactory.getMetaMensualDAO().insertar(metaMensual);
    }

    public static int actualizarMetaMensual(MetaMensual metaMensual){
        return daoFactory.getMetaMensualDAO().actualizar(metaMensual);
    }

    public static int eliminarMetaMensual(int id_meta_mensual){
        return daoFactory.getMetaMensualDAO().eliminar(id_meta_mensual);
    }

    public static ArrayList<MetaMensual> listarMetasMensuales(){
        return daoFactory.getMetaMensualDAO().listar();
    }

    public static int insertarDepartamento(Departamento departamento){
        return daoFactory.getDepartamentoDAO().insertar(departamento);
    }

    public static int actualizarDepartamento(Departamento departamento){
        return daoFactory.getDepartamentoDAO().actualizar(departamento);
    }

    public static int eliminarDepartamento(int id_departamento){
        return daoFactory.getDepartamentoDAO().eliminar(id_departamento);
    }
    
    public static Departamento buscarDepartamentoPorId(int id_departamento){
        return daoFactory.getDepartamentoDAO().encontrarPorId(id_departamento);
    }

    public static ArrayList<Departamento> listarDepartamentos(){
        return daoFactory.getDepartamentoDAO().listar();
    }

    public static int insertarProvincia(Provincia provincia){
        return daoFactory.getProvinciaDAO().insertar(provincia);
    }

    public static int actualizarProvincia(Provincia provincia){
        return daoFactory.getProvinciaDAO().actualizar(provincia);
    }

    public static int eliminarProvincia(int id_provincia){
        return daoFactory.getProvinciaDAO().eliminar(id_provincia);
    }
    
    public static ArrayList<Provincia> listarProvincias(){
        return daoFactory.getProvinciaDAO().listar();
    }
    
    public static Provincia buscarProvinciaPorId(int id_provincia){
        return daoFactory.getProvinciaDAO().encontrarPorId(id_provincia);
    }

    public static ArrayList<Provincia> listarProvinciasPorDepartamento(Departamento departamento){
        return daoFactory.getProvinciaDAO().listarPorDepartamento(departamento);
    }

    public static int insertarProducto(Producto producto){
        return daoFactory.getProductoDAO().insertar(producto);
    }

    public static int actualizarProducto(Producto producto){
        return daoFactory.getProductoDAO().actualizar(producto);
    }

    public static int eliminarProducto(int id_producto){
        return daoFactory.getProductoDAO().eliminar(id_producto);
    }
    

    public static ArrayList<Producto> listarProductosDisponibles(){
        return daoFactory.getProductoDAO().listarDisponibles();
    }
    
    public static ArrayList<Producto> listarProductosPorNombre(String filtro){
        return daoFactory.getProductoDAO().listarPorNombre(filtro);
    }

    public static Producto buscarProductoPorId(int id){
        return daoFactory.getProductoDAO().encontrarPorId(id);
    }

    public static Producto buscarProductoPorNombre(String nombre){
        return daoFactory.getProductoDAO().buscarProductoPorNombre(nombre);
    }

    public static int insertarObjetivoVendedor(ObjetivoVendedor objetivoVendedor){
        return daoFactory.getObjetivoVendedorDAO().insertar(objetivoVendedor);
    }

    public static int actualizarObjetivoVendedor(ObjetivoVendedor objetivoVendedor){
        return daoFactory.getObjetivoVendedorDAO().actualizar(objetivoVendedor);
    }

    public static int insertarCliente(Cliente cliente){
        return daoFactory.getClienteDAO().insertar(cliente);
    }

    public static int actualizarCliente(Cliente cliente){
        return daoFactory.getClienteDAO().actualizar(cliente);
    }

    public static Cliente buscarClientePorId(int id_cliente){
        return daoFactory.getClienteDAO().encontrarPorId(id_cliente);
    }
    
    public static Cliente buscarClientePorFiltro(String filtro){
        return daoFactory.getClienteDAO().buscarClientePorFiltro(filtro);
    }

    public static int insertarClienteVendedor(Cliente_Vendedor cliente_vendedor){
        return daoFactory.getClienteVendedorDAO().insertar(cliente_vendedor);
    }

    public static int eliminarClienteVendedor(int id_cliente_vendedor){
        return daoFactory.getClienteVendedorDAO().eliminar(id_cliente_vendedor);
    }
    
    public static Cliente_Vendedor buscarRelacionClienteVendedor(Cliente cliente, Vendedor vendedor){
        return daoFactory.getClienteVendedorDAO().buscarRelacion(cliente, vendedor);
    }

    public static ArrayList<Cliente> listarClientesPorVendedor(int id_vendedor){
        return daoFactory.getClienteVendedorDAO().listarClientesPorVendedor(id_vendedor);
    }

    public static ArrayList<Cliente> listarClientesPorNombre(String nombre){
        return daoFactory.getClienteDAO().listarClientesPorNombre(nombre);
    }

    public static int insertarPedido(Pedido pedido){
        return daoFactory.getPedidoDAO().insertar(pedido);
    }

    public static int actualizarPedido(Pedido pedido){
        return daoFactory.getPedidoDAO().actualizar(pedido);
    }

    public static int eliminarPedido(int id){
        return daoFactory.getPedidoDAO().eliminar(id);
    }

    public static ArrayList<Pedido> listarPedidosPorCliente(Cliente cliente){
        return daoFactory.getPedidoDAO().listarPorCliente(cliente);
    }

    public static ArrayList<Pedido> listarPedidosPorVendedor(Vendedor vendedor){
        return daoFactory.getPedidoDAO().listarPorVendedor(vendedor);
    }

    public static ArrayList<Pedido> listarPedidosPorEstadoDePedido(){
        return daoFactory.getPedidoDAO().listarPorEstadoDePedido();
    }

    public static ArrayList<LineaPedido> listarLineasDePedido(Pedido pedido){
        return daoFactory.getPedidoDAO().listarLineasPedido(pedido);
    }

    public static ArrayList<Pedido> listarPedidosPorVendedorPorRangoDeFechas(Vendedor vendedor,Date fechaIni, Date fechaFin){
        return daoFactory.getPedidoDAO().listarPorVendedorPorRangoDeFechas(vendedor,fechaIni,fechaFin);
    }

    public static ArrayList<LineaPedido> listarLineasPedidoEnRangoDeFechas(Date fechaIni, Date fechaFin){
        return daoFactory.getPedidoDAO().listarLineasPedidoEnRangoFechas(fechaIni, fechaFin);
    }


    public static int insertarSolicitud(Solicitud solicitud){
        return daoFactory.getSolicitudDAO().insertar(solicitud);
    }

    public static int actualizarSolicitud(Solicitud solicitud){
        return daoFactory.getSolicitudDAO().actualizar(solicitud);
    }

    public static int eliminarSolicitud(int id_solicitud){
        return daoFactory.getSolicitudDAO().eliminar(id_solicitud);
    }

    public static Solicitud buscarSolicitudPorId(int id_solicitud){
       return daoFactory.getSolicitudDAO().encontrarPorId(id_solicitud);
    }

    public static ArrayList<LineaSolicitud> listarLineasDeSolicitud(Solicitud solicitud){
        return daoFactory.getSolicitudDAO().listarLineasSolicitud(solicitud);
    }

    public static ArrayList<Solicitud> listarSolicitudes(){
        return daoFactory.getSolicitudDAO().listar();
    }

    public static ArrayList<Solicitud> listarSolicitudesPorEstadoDeSolicitud(EstadoSolicitud estadoSolicitud){
        return daoFactory.getSolicitudDAO().listarSolicitudesPorEstado(estadoSolicitud);
    }

    //Logica de negocio

    public static boolean validarLoginDeUsuario(String nombre, String contraseña){
        return daoFactory.getUsuarioDAO().validarUsuario(nombre, contraseña);
    }

    public static ArrayList<Producto> listarProductos(){
        return daoFactory.getProductoDAO().listar();
    }

    public static int insertarPresentacion(Presentacion presentacion){
        return daoFactory.getPresentacionDAO().insertar(presentacion);
    }
    
    public static int actualizarPresentacion(Presentacion presentacion){
        return daoFactory.getPresentacionDAO().actualizar(presentacion);
    }
    
    public static int eliminarPresentacion(int id){
        return daoFactory.getPresentacionDAO().eliminar(id);
    }
    
    public static ArrayList<Presentacion>listarPresentaciones(int id){
        return daoFactory.getPresentacionDAO().listarPresentaciones(id);
    }

    public static ArrayList<Cliente_Vendedor> listarClientesVendedorPorCliente(int id_cliente){
        return daoFactory.getClienteVendedorDAO().listarPorCliente(id_cliente);
    }

    
    public static Cliente_Vendedor buscarClienteVendedorPorId(int id){
        return daoFactory.getClienteVendedorDAO().encontrarPorId(id);
    }
    
    public static ArrayList<LineaSolicitud> listarLineasSolicitudesProducto(String nombre){
        return daoFactory.getSolicitudDAO().listarSolicitudesPorProducto(nombre);
    }

    public static Cliente buscarClientePorClienteVendedor(int id_cliente_vendedor){
        return daoFactory.getClienteVendedorDAO().encontrarClientePorClienteVendedor(id_cliente_vendedor);
    }
    
    public static ArrayList<Pedido> listarPedidos(){
        return daoFactory.getPedidoDAO().listar();
    }
    
    public static int actualizarLineaSolicitado(int id_linea){
        return daoFactory.getPedidoDAO().actualizarLineaPedidoSolicitado(id_linea);
    }
}

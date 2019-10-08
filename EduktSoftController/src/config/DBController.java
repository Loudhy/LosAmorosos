/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.ArrayList;
import java.util.Date;
import model.Area;
import model.DatosGenerales;
import model.Departamento;
import model.Empleado;
import model.MetaMensual;
import model.ObjetivoVendedor;
import model.Producto;
import model.Provincia;
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
    
    public static int insertarObjetivoVendedor(ObjetivoVendedor objetivoVendedor){
        return daoFactory.getObjetivoVendedorDAO().insertar(objetivoVendedor);
    }
    
    public static int actualizarObjetivoVendedor(ObjetivoVendedor objetivoVendedor){
        return daoFactory.getObjetivoVendedorDAO().actualizar(objetivoVendedor);
    }
    
    public static int insertarVendedor(Vendedor vendedor){
        return daoFactory.getVendedorDAO().insertar(vendedor);
    }
    
    public static int eliminarVendedor(int id_vendedor){
        return daoFactory.getVendedorDAO().eliminar(id_vendedor);
    }
  
}

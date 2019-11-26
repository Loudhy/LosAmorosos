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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logistica.ProductosSolicitud;
import logistica.aprobarSolicitudes;
import model.Area;
import model.Cliente;
import model.Cliente_Vendedor;
import model.DatosGenerales;
import model.Solicitud;
import model.Empleado;
import model.EstadoLineaPedido;
import model.EstadoPedido;
import model.LineaPedido;
import model.LineaSolicitud;
import model.MetaMensual;
import model.ObjetivoVendedor;
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
        Producto producto2 = DBController.buscarProductoPorId(producto.getId());
        if (producto.getFoto() == null){
            producto.setFoto(producto2.getFoto());
        }
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
    public ArrayList<Pedido> listarPedidoPorEstadoDePedido(@WebParam(name = "estadoPedido")EstadoPedido estado){
        return DBController.listarPedidosPorEstadoDePedido(estado);
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
        
        for(LineaPedido aux:pedido.getLineasPedido()){
            Producto producto = DBController.buscarProductoPorId(aux.getProducto().getId());
            if(aux.getCantidad() > producto.getStockEmpresa())
                aux.setEstadoLineaPedido(EstadoLineaPedido.No_disponible);
            else
                aux.setEstadoLineaPedido(EstadoLineaPedido.Disponible);           
            
            if((producto.getStockVendedor() - aux.getCantidad()) >= 0)
                   producto.setStockVendedor((producto.getStockVendedor() - aux.getCantidad()));
            else
                producto.setStockVendedor(0);
            this.actualizarProducto(producto);           
        }
        return DBController.insertarPedido(pedido);
    }
    
    @WebMethod(operationName = "eliminarRelacionClienteVendedor")
    public int eliminarRelacionClienteVendedor(@WebParam(name = "id_clienteVendedor") int relacion){
        return DBController.eliminarClienteVendedor(relacion);
    }
    
    @WebMethod(operationName = "insertarCliente")
    public int insertarCliente(@WebParam(name = "cliente") Cliente cliente){
        return DBController.insertarCliente(cliente);
    }
    
    @WebMethod(operationName = "actualizarCliente")
    public int actualizarCliente(@WebParam(name = "cliente") Cliente cliente){
        return DBController.actualizarCliente(cliente);
    }
    
    @WebMethod(operationName = "insertarRelacionClienteVendedor")
    public int insertarClienteVendedor(@WebParam(name = "cliente") Cliente cliente, @WebParam(name = "vendedor") Vendedor vendedor){
        Cliente_Vendedor relacion1 = new Cliente_Vendedor();
        relacion1.setCliente(cliente);
        relacion1.setVendedor(vendedor);
        return DBController.insertarClienteVendedor(relacion1);
    }

    @WebMethod(operationName = "listarCliente")
    public ArrayList<Cliente> listarClientesPorVendedor(@WebParam(name = "id_vendedor") int id){
        return DBController.listarClientesPorVendedor(id);
    }

    @WebMethod(operationName = "listarClientesPorFiltro")
    public ArrayList<Cliente> listarClientes(@WebParam(name = "nombreRuc") String nombreRuc, @WebParam(name = "filtro") String filtro){
        if (filtro.equals("NOMBRE")){
            return DBController.listarClientesPorNombre(nombreRuc);
        }
        else
            return DBController.listarClientesPorRUC(nombreRuc);
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
    
    @WebMethod(operationName = "insertarSolicitud")
    public int insertarSolicitud(@WebParam(name = "solicitud") Solicitud solicitud){
        Date today = Calendar.getInstance().getTime();
        solicitud.setFechaRegistro(today);
        int resultado =  DBController.insertarSolicitud(solicitud);
        ArrayList<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
        for(LineaSolicitud linea: solicitud.getLineasSolicitud()){
            linea.getLineaPedido().setEstadoLineaPedido(EstadoLineaPedido.Solicitado);
            linea.getLineaPedido().setFechaAtencion(today);
            lineasPedido.add(linea.getLineaPedido());
        }
        resultado = DBController.actualizarLineasPedido(lineasPedido);

        return resultado;
    }

    @WebMethod(operationName = "actualizarSolicitud")
    public int actualizarSolicitud(@WebParam(name = "solicitud") Solicitud solicitud){
        return DBController.actualizarSolicitud(solicitud);
    }
    
    @WebMethod(operationName = "agregarLineasSolicitud")
    public int actualizarLineasSolicitud(@WebParam(name = "solicitud") Solicitud solicitud,@WebParam(name = "lineas") ArrayList<LineaSolicitud> lineas){
        ArrayList<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
        ArrayList<LineaSolicitud> lineasSolicitud =  solicitud.getLineasSolicitud();
        for (LineaSolicitud linea: lineas){
            lineasPedido.add(linea.getLineaPedido());
            lineasSolicitud.add(linea);
        }
        this.actualizarLineasDePedido(lineasPedido, EstadoLineaPedido.Solicitado);
        solicitud.setLineasSolicitud(lineasSolicitud);
        
        return DBController.actualizarSolicitud(solicitud);
    }

    @WebMethod(operationName = "buscarClientePorId")
    public Cliente buscarClientePorId(@WebParam(name = "id_cliente") int id_cliente){
        return DBController.buscarClientePorId(id_cliente);
    }

    @WebMethod(operationName = "buscarClientePorFiltro")
    public ArrayList<Cliente> buscarClientePorFiltro(@WebParam(name = "filtro") String filtro){
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
            String rutaSubreporte = Servicio.class.getResource("/reports/SubreportePresentaciones.jasper").getPath();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = 
        DriverManager.getConnection(
          DBManager.url, DBManager.user, DBManager.password);
            HashMap hm = new HashMap();
            hm.put("SUBREPORT_DIR",rutaSubreporte);
            JasperPrint jp = 
                    JasperFillManager.fillReport(reporte,hm,con);
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;  
    }
    
    @WebMethod(operationName = "generarPdfReporteDeVentas")
    public byte[] generarPdfReporteDeVentas(@WebParam (name = "fechaIni") String fechaIni,
            @WebParam (name = "fechaFin") String fechaFin){
        byte[] arreglo = null;
        try{
            JasperReport reporte = 
                    (JasperReport) 
           JRLoader.loadObjectFromFile(
     Servicio.class.getResource(
     "/reports/ReporteVentas.jasper").getFile());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = 
        DriverManager.getConnection(
          DBManager.url, DBManager.user, DBManager.password);
            HashMap hm = new HashMap();
            hm.put("FECHA_INI", fechaIni);
            hm.put("FECHA_FIN", fechaFin);
            JasperPrint jp = 
                    JasperFillManager.fillReport(reporte,hm,con);
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;  
    }
    
    @WebMethod(operationName = "generarPdfReporteProductosVendidos")
    public byte[] generarPdfReporteProductosVendidos(@WebParam(name = "fechaIni") String fechaIni,
            @WebParam(name = "fechaFin") String fechaFin){
        byte[] arreglo = null;
        try{
            JasperReport reporte = 
                    (JasperReport) 
           JRLoader.loadObjectFromFile(
     Servicio.class.getResource(
     "/reports/ReporteProductoVendidosIntervalo.jasper").getFile());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = 
        DriverManager.getConnection(
          DBManager.url, DBManager.user, DBManager.password);
            HashMap hm = new HashMap();
            hm.put("FECHA_INICIO", fechaIni);
            hm.put("FECHA_FIN", fechaFin);
            JasperPrint jp = 
                    JasperFillManager.fillReport(reporte,hm,con);
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
    
    @WebMethod(operationName = "actualizarLineasDePedido")
    public int actualizarLineasDePedido(@WebParam(name = "lineasDePedido")ArrayList<LineaPedido> lineas, @WebParam(name = "filtro") EstadoLineaPedido estado){
        Date today = Calendar.getInstance().getTime();
        int cantidadTotal=0;
        for(LineaPedido linea:lineas){
            linea.setEstadoLineaPedido(estado);
            linea.setFechaAtencion(today);
            if(estado == EstadoLineaPedido.Aceptado){
                linea.setEstadoLineaPedido(estado);
                linea.getProducto().setStockEmpresa(linea.getProducto().getStockEmpresa()-linea.getCantidad());
            }
                
            else if(estado == EstadoLineaPedido.Rechazado){
                linea.setEstadoLineaPedido(estado);
                linea.getProducto().setStockVendedor(linea.getProducto().getStockVendedor()+linea.getCantidad());
            }
                
            if(estado == EstadoLineaPedido.Aceptado || estado == EstadoLineaPedido.Rechazado)
                DBController.actualizarProducto(linea.getProducto());
        }
        return DBController.actualizarLineasPedido(lineas);      
    }
    
    @WebMethod(operationName = "buscarSolicitudPorPedido")
    public Solicitud buscarSolicitudPorPedido(@WebParam(name = "pedido") Pedido pedido){
        return DBController.buscarSolicitudPorPedido(pedido);
    }
    
    @WebMethod(operationName = "actualizarLineaARechazado")
    public int actualizarLineaPedidoRechazado(@WebParam(name = "idLineaPedido")  int id_linea){
        return DBController.actualizarLineaRechazado(id_linea);
    }
    
    @WebMethod(operationName = "actualizarLineaAAceptado")
    public int actualizarLineaPedidoAceptado(@WebParam(name = "idLineaPedido")  int id_linea){
        return DBController.actualizarLineaAceptado(id_linea);
    }
    
    
    
    @WebMethod(operationName = "actualizarPedido")
    public int actualizarPedido(@WebParam(name = "pedido") Pedido pedido){
        return DBController.actualizarPedido(pedido);
    }
    
    @WebMethod(operationName = "rechazarPedido")
    public int rechazarPedido(@WebParam(name = "pedido") Pedido pedido){
        for(LineaPedido linea:pedido.getLineasPedido()){
            linea.setEstadoLineaPedido(EstadoLineaPedido.Rechazado);
        }
        pedido.setEstadoPedido(EstadoPedido.Rechazado);
        return DBController.actualizarPedido(pedido);
    }
    
    @WebMethod(operationName = "generarPdfReporteFactura")
    public byte[] generarPdfReporteFactura(@WebParam(name = "idPedido") int idPedido){
        byte[] arreglo = null;
        try{
            JasperReport reporte = 
                    (JasperReport) 
           JRLoader.loadObjectFromFile(
     Servicio.class.getResource(
     "/reports/ReporteFactura.jasper").getFile());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = 
        DriverManager.getConnection(
          DBManager.url, DBManager.user, DBManager.password);
            HashMap hm = new HashMap();
            hm.put("ID_PEDIDO", idPedido);
            JasperPrint jp = 
                    JasperFillManager.fillReport(reporte,hm,con);
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;
    }
    
    @WebMethod(operationName = "listarClienteVendedorPorFiltro")
    public ArrayList<Cliente> listarClienteVendedorPorFiltro(@WebParam(name = "id_vendedor") int id,
            @WebParam(name = "filtro") int filtro, @WebParam(name = "dato") String dato){
        if(filtro == 0){
            return DBController.listarClientesVendedorPorRUC(id, dato);
        }  
        else 
            return DBController.listarClientesVendedorPorNombre(id, dato);      
    }
    @WebMethod(operationName = "generarPdfReporteDeIngresos")
    public byte[] generarPdfReporteDeIngresos(@WebParam (name = "fechaIni") String fechaIni,
            @WebParam (name = "fechaFin") String fechaFin){
        byte[] arreglo = null;
        try{
            JasperReport reporte = 
                    (JasperReport) 
           JRLoader.loadObjectFromFile(
     Servicio.class.getResource(
     "/reports/ReporteIngresos.jasper").getFile());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = 
        DriverManager.getConnection(
          DBManager.url, DBManager.user, DBManager.password);
            HashMap hm = new HashMap();
            hm.put("FECHA_INI", fechaIni);
            hm.put("FECHA_FIN", fechaFin);
            JasperPrint jp = 
                    JasperFillManager.fillReport(reporte,hm,con);
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;  
    }
    
    @WebMethod(operationName = "insertarEmpleado")
    public int insertarEmpleado(@WebParam(name = "empleado") Empleado empleado){
        return DBController.insertarEmpleado(empleado);
    }
    
    @WebMethod(operationName = "actualizarEmpleado")
    public int actualizarEmpleado(@WebParam(name = "empleado")Empleado empleado){
        return DBController.actualizarEmpleado(empleado);
    }
    
    @WebMethod(operationName = "eliminarEmpleado")
    public int eliminarEmpleado(@WebParam(name = "idEmpleado") int idEmpleado){
        return DBController.eliminarEmpleado(idEmpleado);
    }
    
    @WebMethod(operationName = "listarTodosLosEmpleados")
    public ArrayList<Empleado> listarTodosLosEmpleados(){
        return DBController.listarTodosLosEmpleados();
    }
    
    @WebMethod(operationName = "darDeAltaAEmpleado")
    public int darDeAltaAEmpleado(@WebParam(name = "idEmpleado") int idEmpleado){
        return DBController.darDeAltaAEmpleado(idEmpleado);
    }
    
    @WebMethod(operationName = "insertarMetaMensual")
    public int insertarMetaMensual(@WebParam(name = "metaMensual")MetaMensual metaMensual){
        ArrayList<MetaMensual> metas = DBController.listarMetasMensuales();
        for (MetaMensual meta : metas){
            DBController.eliminarMetaMensual(meta.getId());
        }
        ArrayList<ObjetivoVendedor> objetivos = DBController.listarObjetivosVendedores();
        for(ObjetivoVendedor objetivo:objetivos){
            DBController.eliminarObjetivoVendedor(objetivo.getId());
        }
        return DBController.insertarMetaMensual(metaMensual);
    }
    
    @WebMethod(operationName = "buscarMetaMensualActiva")
    public MetaMensual buscarMetaMensualActiva(){
        return DBController.buscarMetaMensualActiva();
    }
    
    @WebMethod(operationName = "buscarEmpleadoPorCorreoONombreUsuario")
    public Empleado buscarEmpleadoPorCorreoONombreUsuario(@WebParam(name = "correoNombre") String correoNombre){
        return DBController.buscarEmpleadoPorCorreo(correoNombre);
    }
    
    @WebMethod(operationName = "buscarObjetivoVendedorPorVendedor")
    public ObjetivoVendedor buscarObjetivoVendedorPorVendedor(@WebParam(name = "vendedor") Vendedor vendedor){
        return DBController.buscarObjetivoVendedorPorVendedor(vendedor);
    }
    
    @WebMethod(operationName = "insertarObjetivoVendedor")
    public int insertarObjetivoVendedor(@WebParam(name = "objetivoVendedor") ObjetivoVendedor objetivo){
        return DBController.insertarObjetivoVendedor(objetivo);
    }
    
    @WebMethod(operationName = "actualizarObjetivoVendedor")
    public int actualizarObjetivoVendedor(@WebParam(name = "objetivoVendedor") ObjetivoVendedor objetivo){
        return DBController.actualizarObjetivoVendedor(objetivo);
    }
    
    @WebMethod(operationName = "calcularProgresoDeVendedor")
    public float calcularProgresoDeVendedor(@WebParam(name = "objetivoVendedor") ObjetivoVendedor objvendedor, @WebParam(name = "metaMensual") MetaMensual meta){
        ArrayList<Pedido> pedidos = DBController.listarPedidosPorVendedorPorRangoDeFechas(objvendedor.getVendedor(), meta.getFechaInicio(), meta.getFechaFin());
        float monto=0;
        for(Pedido pedido:pedidos){
            if(pedido.getEstadoPedido() == EstadoPedido.Pagado)
                monto+=pedido.getTotal();
        }
        
        float progreso = monto/objvendedor.getMonto();
        return progreso;
    }
    
    @WebMethod(operationName ="listarPedidosDeVendedorPorEstadoDePedido")
    public ArrayList<Pedido>listarPedidosDeVendedorPorEstadoDePedido(@WebParam(name="vendedor") Vendedor vendedor,@WebParam(name = "estadoPedido") EstadoPedido estado){
        return DBController.listarPedidosPorVendedorPorEstadoPedido(vendedor, estado);
    }
    
    @WebMethod(operationName = "listarPedidosPorVendedorPorCliente")
    public ArrayList<Pedido>listarPedidosPorVendedorPorCliente(@WebParam(name ="vendedor") Vendedor vendedor, @WebParam(name = "cliente") String filtro){
        return DBController.listarPedidosPorVendedorPorCliente(vendedor,filtro);
    }
    
    @WebMethod(operationName = "listarAreas")
    public ArrayList<Area> listarAreas(){
        return DBController.listarAreas();
    }
    
    @WebMethod(operationName = "listarTodosEmpleadosPorNombre")
    public ArrayList<Empleado> listarTodosEmpleadosPorNombre(@WebParam(name = "nombre") String nombre){
        return DBController.listarTodosEmpleadosPorNombre(nombre);
    }
    
    @WebMethod(operationName = "listarEmpleadosPorEstados")
    public ArrayList<Empleado> listarEmpleadosPorEstados(@WebParam(name = "active") int active){
        if (active == 1)
            return DBController.listarEmpleados();
        else
            return DBController.listarEmpleadosNoActivos();
    }
    
    @WebMethod(operationName = "actualizarPedidoConFechas")
    public int actualizarPedidoConFechas(@WebParam(name = "pedido")Pedido pedido, @WebParam(name = "opcion") int opcion){
        float monto=0;
        Date today = Calendar.getInstance().getTime();
        if(opcion == 1){
            for(LineaPedido linea:pedido.getLineasPedido()){
                if(linea.getEstadoLineaPedido() == EstadoLineaPedido.Aceptado)
                    monto+=linea.getSubtotal();
            }       
            pedido.setFechaFacturacion(today);
            pedido.setFacturado(monto);
            pedido.setEstadoPedido(EstadoPedido.Aceptado);
            DBController.actualizarPedidoConFacturacion(pedido);
        }
        else{
            pedido.setFechaPago(today);
            pedido.setEstadoPedido(EstadoPedido.Pagado);
            DBController.actualizarPedidoConPago(pedido);
            DatosGenerales datos = DBController.buscarDatosGeneralesPorId(1);
            int dias = (int)(pedido.getFechaFacturacion().getTime() - today.getTime());
            ArrayList<Cliente> clienteAux = DBController.buscarClientePorFiltro(pedido.getClienteVendedor().getCliente().getRuc());
            Cliente cliente = clienteAux.get(0);
            if (dias <= datos.getPlazoDePago()){
                
               if (cliente.getPuntaje() < 100){
                   if (cliente.getPuntaje() +5 <= 100)
                       cliente.setPuntaje(cliente.getPuntaje()+5);
                   else
                       cliente.setPuntaje(100);
                   DBController.actualizarCliente(cliente);
               }
                    
            }
            else{
                if (cliente.getPuntaje() > 0){
                    if (cliente.getPuntaje()-7 >= 0)
                        cliente.setPuntaje(cliente.getPuntaje()-7);
                    else
                        cliente.setPuntaje(0);
                    DBController.actualizarCliente(cliente);
                }
                
            }
        }
        ArrayList<LineaPedido> lineasRechazadas = new ArrayList<LineaPedido>();
        for (LineaPedido linea:pedido.getLineasPedido()){
            if (linea.getEstadoLineaPedido() == EstadoLineaPedido.Rechazado)
                lineasRechazadas.add(linea);
        }
        this.actualizarLineasDePedido(lineasRechazadas, EstadoLineaPedido.Rechazado);
        return DBController.actualizarPedido(pedido);     
    }
    
    @WebMethod(operationName = "listarPedidoPorVendedorPorClientePorEstadoDePedido")
    public ArrayList<Pedido> listarPedidosPorVendedorPorClientePorEstadoDePedido(@WebParam(name="vendedor") Vendedor vendedor, @WebParam(name="cliente") String filtro, @WebParam(name="estado") EstadoPedido estado){
        return DBController.listarPedidosPorVendedorPorClientePorEstado(vendedor, filtro, estado);
    }
    
    @WebMethod(operationName = "listarPedidoPorFiltroDeCliente")
    public ArrayList<Pedido> listarPedidosPorFiltroDeCliente(@WebParam(name ="filtro") String filtro){
        return DBController.listarPedidosPorFiltroDeCliente(filtro);
    }
    
    @WebMethod(operationName = "listarVendedoresPorFiltro")
    public ArrayList<Vendedor> listarVendedoresPorFiltro(@WebParam(name = "filtro") String filtro,@WebParam(name = "index") int index){
        listarVendedores lista = new listarVendedores();
        return lista.listarEmpleadosDeAreaVentasPorFiltro(filtro, index);
    }
}

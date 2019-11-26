/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBController;
import config.DBManager;
import dao.PedidoDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Cliente;
import model.EstadoLineaPedido;
import model.EstadoPedido;
import model.LineaPedido;
import model.Pedido;
import model.Producto;
import model.Vendedor;
/**
 *
 * @author alulab14
 */
public class PedidoMySQL implements PedidoDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Pedido pedido) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PEDIDO(?,?,?,?,?,?)} ");
            float total=0;
            for (LineaPedido aux: pedido.getLineasPedido()){
                aux.setFechaAtencion(pedido.getFechaRegistro());
                total += aux.getSubtotal();
            }
            pedido.setTotal(total);
            cs.setFloat("_TOTAL_PEDIDO", pedido.getTotal());
            cs.setInt("_ID_CLIENTE_VENDEDOR", pedido.getClienteVendedor().getId_cliente_vendedor());
            cs.setString("_ESTADO_PEDIDO",pedido.getEstadoPedido().toString());
            cs.setDate("_FECHA_REGISTRO", new java.sql.Date(pedido.getFechaRegistro().getTime()));
            cs.setBoolean("_ACTIVE",true);
            cs.executeUpdate();
            pedido.setId(cs.getInt("_ID_PEDIDO"));
            
            for (LineaPedido aux: pedido.getLineasPedido()){
                cs = con.prepareCall("{call INSERTAR_LINEA_PEDIDO(?,?,?,?,?,?,?,?)}");
                cs.setInt("_CANTIDAD",aux.getCantidad());
                cs.setInt("_ID_PEDIDO", pedido.getId());
                cs.setInt("_ID_PRODUCTO", aux.getProducto().getId());
                cs.setFloat("_SUBTOTAL",aux.getSubtotal());
                cs.setString("_ESTADO_LINEA_PEDIDO", aux.getEstadoLineaPedido().toString());
                cs.setDate("_FECHA_ATENCION", new java.sql.Date(aux.getFechaAtencion().getTime()));
                cs.setInt("_CANTIDAD_A_ATENDER",aux.getCantidadPorAtender());
                resultado=cs.executeUpdate();
                aux.setId(cs.getInt("_ID_LINEA_PEDIDO"));
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(Pedido pedido) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PEDIDO(?,?,?,?)}");
            System.out.println(pedido.getId());
            cs.setInt("_ID_PEDIDO",pedido.getId());
            System.out.println(pedido.getTotal());
            cs.setFloat("_TOTAL_PEDIDO", pedido.getTotal());
            System.out.println(pedido.getEstadoPedido().toString());
            cs.setString("_ESTADO_PEDIDO",pedido.getEstadoPedido().toString());
            System.out.println(pedido.getFechaRegistro());
            cs.setDate("_FECHA_REGISTRO",new java.sql.Date(pedido.getFechaRegistro().getTime()));
            resultado=cs.executeUpdate();
            actualizarLineasPedido(pedido.getLineasPedido());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public int actualizarLineaPedidoSolicitado(int id_linea) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_LINEA_PEDIDO_SOLICITADO(?)}");
            cs.setInt("_ID_LINEA_PEDIDO",id_linea);            
            resultado=cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizarLineaPedidoRechazado(int id_linea) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_LINEA_PEDIDO_RECHAZADO(?)}");
            cs.setInt("_ID_LINEA_PEDIDO",id_linea);            
            resultado=cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizarLineasPedido(ArrayList<LineaPedido> lineas) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
           
            for (LineaPedido aux: lineas){
                cs = con.prepareCall("{call ACTUALIZAR_LINEA_PEDIDO(?,?,?,?,?,?,?,?)}");
                System.out.println(aux.getId());
                System.out.println(aux.getCantidad());
                System.out.println(aux.getProducto().getId());
                System.out.println(aux.getSubtotal());
                System.out.println(aux.getEstadoLineaPedido());
                System.out.println(aux.getFechaAtencion());
                System.out.println(aux.getCantidadPorAtender());
                
                cs.setInt("_ID_LINEA_PEDIDO",aux.getId());
                cs.setInt("_CANTIDAD",aux.getCantidad());
                cs.setInt("_ID_PRODUCTO", aux.getProducto().getId());
                cs.setFloat("_SUBTOTAL",aux.getSubtotal());
                cs.setString("_ESTADO_LINEA_PEDIDO", aux.getEstadoLineaPedido().toString());
                cs.setDate("_FECHA_ATENCION", new java.sql.Date(aux.getFechaAtencion().getTime()));
                cs.setInt("_CANTIDAD_A_ATENDER",aux.getCantidadPorAtender());
                cs.setBoolean("_ACTIVE",aux.isActive());
                resultado=cs.executeUpdate();
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int id_pedido) {
        int resultado=0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PEDIDO(?)}");
            cs.setInt("_ID_PEDIDO",id_pedido);
            resultado=cs.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Pedido> listarPorVendedorPorRangoDeFechas(Vendedor vendedor,Date fechaIni, Date fechaFin) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_VENDEDOR_POR_FECHAS(?,?,?)}");
            cs.setInt("_ID_VENDEDOR", vendedor.getId_vendedor());
            cs.setDate("_FECHA_INI",new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_FECHA_FIN", new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido(true);
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                //pedido.getCliente_vendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setActive(rs.getBoolean("ACTIVE"));
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }
    
    @Override
    public ArrayList<Pedido> listarPorVendedorOrdenado(Vendedor vendedor, int orden) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDOS_POR_VENDEDOR_ORDENADO(?,?)}");
            cs.setInt("_ID_VENDEDOR",vendedor.getId());
            cs.setInt("_ORDEN",orden);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                
                Pedido pedido = new Pedido(true);              
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                pedido.setActive(rs.getBoolean("ACTIVE"));
                ArrayList<LineaPedido> lineas = listarLineasPedido(pedido);
                pedido.setLineasPedido(lineas);
                pedidos.add(pedido);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public ArrayList<Pedido> listarPorCliente(Cliente cliente) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_CLIENTE(?)}");
            cs.setInt("_ID_CLIENTE", cliente.getId());
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                Pedido pedido = new Pedido(true);
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setActive(rs.getBoolean("ACTIVE"));
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
            
            if (pedidos.size() == 0){
                pedidos = null;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;    }

    @Override
    public ArrayList<LineaPedido> listarLineasPedido(Pedido pedido) {
        ArrayList<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();

        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_LINEAS_PEDIDO(?)}");
            cs.setInt("_ID_PEDIDO", pedido.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                LineaPedido lineaPedido = new LineaPedido();
                lineaPedido.setCantidad(rs.getInt("CANTIDAD"));
                lineaPedido.setEstadoLineaPedido(EstadoLineaPedido.valueOf(rs.getString("ESTADO_LINEA_PEDIDO")));
                lineaPedido.setCantidadPorAtender(rs.getInt("CANTIDAD_A_ATENDER"));
                lineaPedido.setId(rs.getInt("ID_LINEA_PEDIDO"));
                lineaPedido.setSubtotal(rs.getFloat("SUBTOTAL"));
                SimpleDateFormat formatoFecha = new SimpleDateFormat();
                lineaPedido.getProducto().setId(rs.getInt("ID_PRODUCTO"));
                lineaPedido.getProducto().setNombre(rs.getString("NOMBRE_PRODUCTO"));
                lineaPedido.getProducto().setPrecioUnitario(rs.getFloat("PRECIO_UNITARIO"));
                lineaPedido.getProducto().setDescripcion(rs.getString("DESCRIPCION"));
                lineaPedido.getProducto().setStockEmpresa(rs.getInt("STOCK_EMPRESA"));
                lineaPedido.getProducto().setStockVendedor(rs.getInt("STOCK_VENDEDOR"));
                lineaPedido.getProducto().setActive(rs.getBoolean("ACTIVE"));
                lineasPedido.add(lineaPedido);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return lineasPedido;
    }

    @Override
    public ArrayList<LineaPedido> listarLineasPedidoEnRangoFechas(Date fechaIni, Date fechaFin) {
        ArrayList<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_LINEAS_PEDIDO_POR_RANGO_DE_FECHAS(?,?)}");
            cs.setDate("_FECHA_INI",new java.sql.Date(fechaIni.getTime()));
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                LineaPedido lineaPedido = new LineaPedido();
                lineaPedido.setCantidad(rs.getInt("CANTIDAD"));
                lineaPedido.setEstadoLineaPedido(EstadoLineaPedido.valueOf(rs.getString("ESTADO_LINEA_PEDIDO")));
                lineaPedido.setCantidadPorAtender(rs.getInt("CANTIDAD_A_ATENDER"));
                lineaPedido.setId(rs.getInt("ID_PEDIDO"));
                lineaPedido.setSubtotal(rs.getFloat("SUBTOTAL"));
                SimpleDateFormat formatoFecha = new SimpleDateFormat();
                java.util.Date fechaAtencion = new java.util.Date(rs.getDate("FECHA_ATENCION").getTime());
                String fechaAux = formatoFecha.format(fechaAtencion);
                lineaPedido.setFechaAtencion(formatoFecha.parse(fechaAux));
                lineaPedido.getProducto().setId(rs.getInt("ID_PRODUCTO"));
                lineaPedido.getProducto().setNombre(rs.getString("NOMBRE_PRODUCTO"));
                lineaPedido.getProducto().setPrecioUnitario(rs.getFloat("PRECIO_UNITARIO"));
                lineaPedido.getProducto().setDescripcion(rs.getString("DESCRIPCION"));
                lineaPedido.getProducto().setStockEmpresa(rs.getInt("STOCK_EMPRESA"));
                lineaPedido.getProducto().setStockVendedor(rs.getInt("STOCK_VENDEDOR"));
                lineaPedido.getProducto().setActive(rs.getBoolean("ACTIVE"));
                lineasPedido.add(lineaPedido);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }

        return lineasPedido;
    }

    @Override
    public ArrayList<LineaPedido> listarLineasPedidoPorProducto(Producto producto) {
        ArrayList<LineaPedido> lineas = new ArrayList<LineaPedido>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_LINEAS_PEDIDO_POR_PRODUCTO(?)}");
            cs.setInt("_ID_PRODUCTO", producto.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                LineaPedido linea = new LineaPedido();
                linea.setCantidad(rs.getInt("CANTIDAD"));
                SimpleDateFormat formatoFecha = new SimpleDateFormat();
                java.util.Date fechaAtencion = new java.util.Date(rs.getDate("FECHA_ATENCION").getTime());
                String fechaAux = formatoFecha.format(fechaAtencion);
                linea.setFechaAtencion(formatoFecha.parse(fechaAux));
                lineas.add(linea);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return lineas;
    }

    @Override
    public ArrayList<Pedido> listarPorVendedor(Vendedor vendedor) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_VENDEDOR(?)}");
            cs.setInt("_ID_VENDEDOR", vendedor.getId_vendedor());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido(true);              
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setActive(rs.getBoolean("ACTIVE"));
                ArrayList<LineaPedido> lineas = listarLineasPedido(pedido);
                pedido.setLineasPedido(lineas);
                pedidos.add(pedido);
            }
            
            if(pedidos.size() == 0){
                pedidos = null;
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public ArrayList<Pedido> listarPorEstadoDePedido(EstadoPedido estado) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_ESTADO(?)}");
            cs.setString("_ESTADO_PEDIDO", estado.toString());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));        
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));              
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));            
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRuc(rs.getString("RUC"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("RAZON_SOCIAL"));
                pedido.getClienteVendedor().getCliente().setPuntaje(rs.getInt("PUNTOS"));
                pedido.getClienteVendedor().getVendedor().setId(rs.getInt("ID_EMPLEADO"));
                pedido.getClienteVendedor().getVendedor().setId_vendedor(pedido.getClienteVendedor().getVendedor().getId());
                pedido.getClienteVendedor().getVendedor().setNombre(rs.getString("NOMBRE_EMPLEADO"));
                pedido.getClienteVendedor().getVendedor().setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                pedido.getClienteVendedor().getVendedor().setApellidoPaterno(rs.getString("APELLIDO_MATERNO"));
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURADO"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public Pedido encontrarPorId(int id) {
        Pedido pedido = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_PEDIDO_POR_ID(?)}");
            cs.setInt("_ID_PEDIDO", id);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                pedido = new Pedido();
                pedido.setId(id);
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));              
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                pedido.setActive(rs.getBoolean("ACTIVE"));
                pedido.setLineasPedido(listarLineasPedido(pedido));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedido;
    }

    @Override
    public ArrayList<Pedido> listar() {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDOS()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido(true);
                pedido.setId(rs.getInt("ID_PEDIDO"));        
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));              
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));            
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRuc(rs.getString("RUC"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("RAZON_SOCIAL"));
                pedido.getClienteVendedor().getCliente().setPuntaje(rs.getInt("PUNTOS"));
                pedido.getClienteVendedor().getVendedor().setId(rs.getInt("ID_EMPLEADO"));
                pedido.getClienteVendedor().getVendedor().setId_vendedor(pedido.getClienteVendedor().getVendedor().getId());
                pedido.getClienteVendedor().getVendedor().setNombre(rs.getString("NOMBRE_EMPLEADO"));
                pedido.getClienteVendedor().getVendedor().setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                pedido.getClienteVendedor().getVendedor().setApellidoPaterno(rs.getString("APELLIDO_MATERNO"));
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());}
        finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    
    
    @Override
    public int actualizarLineaPedidoAceptado(int id_linea) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_LINEA_PEDIDO_ACEPTADO(?)}");
            cs.setInt("_ID_LINEA_PEDIDO",id_linea);            
            resultado=cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Pedido> listarPorVendedorPorEstadoPedido(Vendedor vendedor, EstadoPedido estado) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_VENDEDOR_POR_ESTADO_PEDIDO(?,?)}");
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
            cs.setString("_ESTADO_PEDIDO", estado.toString());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));        
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));              
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));            
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRuc(rs.getString("RUC"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("RAZON_SOCIAL"));
                pedido.getClienteVendedor().getCliente().setPuntaje(rs.getInt("PUNTOS"));
                pedido.getClienteVendedor().setVendedor(vendedor);
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public ArrayList<Pedido> listarPorVendedorPorCliente(Vendedor vendedor, String filtro) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_VENDEDOR_POR_CLIENTE(?,?)}");
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
            cs.setString("_FILTRO", filtro);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));        
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));              
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));            
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRuc(rs.getString("RUC"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("RAZON_SOCIAL"));
                pedido.getClienteVendedor().getCliente().setPuntaje(rs.getInt("PUNTOS"));
                pedido.getClienteVendedor().setVendedor(vendedor);
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public int actualizarFechaFacturacion(Pedido pedido) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_FACTURACION_PEDIDO(?,?,?)}");
            cs.setInt("_ID_PEDIDO", pedido.getId());
            cs.setDate("_FECHA_FACTURACION",new java.sql.Date(pedido.getFechaFacturacion().getTime()));
            cs.setFloat("_MONTO_FACTURADO", pedido.getFacturado());
            resultado = cs.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        return resultado;
    }

    @Override
    public int actualizarFechaPagado(Pedido pedido) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_FECHA_PAGO(?,?)}");
            cs.setInt("_ID_PEDIDO", pedido.getId());
            cs.setDate("_FECHA_PAGO", new java.sql.Date(pedido.getFechaPago().getTime()));
            resultado = cs.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Pedido> listarPedidosPorVendedorPorClientePorEstadoDePedido(Vendedor vendedor, String filtro, EstadoPedido estado) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_VENDEDOR_POR_CLIENTE_POR_ESTADO(?,?,?)}");
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
            cs.setString("_FILTRO", filtro);
            cs.setString("_ESTADO_PEDIDO",estado.toString());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));        
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));              
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));            
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRuc(rs.getString("RUC"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("RAZON_SOCIAL"));
                pedido.getClienteVendedor().getCliente().setPuntaje(rs.getInt("PUNTOS"));
                pedido.getClienteVendedor().setVendedor(vendedor);
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public ArrayList<Pedido> listarPedidosPorFiltroDeCliente(String filtro) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_CLIENTE_POR_FILTRO(?)}");
            cs.setString("_FILTRO", filtro);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));        
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));              
                pedido.getClienteVendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));            
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRuc(rs.getString("RUC"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("RAZON_SOCIAL"));
                pedido.getClienteVendedor().getCliente().setPuntaje(rs.getInt("PUNTOS"));
                String fechaAux = formatoFecha.format(fechaNacimiento);
                
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                if(rs.getDate("FECHA_FACTURACION") != null){
                    fechaNacimiento = new java.util.Date(rs.getDate("FECHA_FACTURACION").getTime());
                    fechaAux = formatoFecha.format(fechaNacimiento);
                    pedido.setFechaFacturacion(formatoFecha.parse(fechaAux));
                    pedido.setFacturado(rs.getFloat("MONTO_FACTURACION"));
                    if(rs.getDate("FECHA_PAGO") != null){
                        fechaNacimiento = new java.util.Date(rs.getDate("FECHA_PAGO").getTime());
                        fechaAux = formatoFecha.format(fechaNacimiento);
                        pedido.setFechaPago(formatoFecha.parse(fechaAux));
                    }
                }
                
                pedido.setLineasPedido(listarLineasPedido(pedido));
                pedidos.add(pedido);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.EstadoLineaPedido;
import model.EstadoPedido;
import model.LineaPedido;
import model.Pedido;
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
                total += aux.getSubtotal();
            }
            pedido.setTotal(total);
            cs.setFloat("_TOTAL_PEDIDO", pedido.getTotal());
            cs.setInt("_ID_CLIENTE_VENDEDOR", pedido.getCliente_vendedor().getId_cliente_vendedor());
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
        }catch (SQLException ex) {
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
            cs.setInt("_ID_PEDIDO",pedido.getId());
            cs.setFloat("_TOTAL_PEDIDO", pedido.getTotal());
            cs.setString("_ESTADO_PEDIDO",pedido.getEstadoPedido().toString());
            cs.setDate("_FECHA_REGISTRO",new java.sql.Date(pedido.getFechaRegistro().getTime()));
            resultado=cs.executeUpdate();
            for (LineaPedido aux: pedido.getLineasPedido()){
                cs = con.prepareCall("{call ACTUALIZAR_LINEA_PEDIDO(?,?,?,?,?,?,?,?)}");    
                cs.setInt("_ID_LINEA_PEDIDO",aux.getId());
                cs.setInt("_CANTIDAD",aux.getCantidad());
                cs.setInt("_ID_PEDIDO", pedido.getId());
                cs.setInt("_ID_PRODUCTO", aux.getProducto().getId());
                cs.setFloat("_SUBTOTAL",aux.getSubtotal());
                cs.setString("_ESTADO_LINEA_PEDIDO", aux.getEstadoLineaPedido().toString());
                cs.setDate("_FECHA_ATENCION", new java.sql.Date(aux.getFechaAtencion().getTime()));
                cs.setInt("_CANTIDAD_A_ATENDER",aux.getCantidadPorAtender());
                resultado=cs.executeUpdate();
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
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
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
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
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                //pedido.getCliente_vendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.getCliente_vendedor().getCliente().setRuc(rs.getString("RUC"));
                pedido.getCliente_vendedor().getCliente().setRazonSocial(rs.getString("RAZON_SOCIAL"));
                pedido.getCliente_vendedor().getCliente().setCorreo(rs.getString("CORREO_CLIENTE"));
                pedido.getCliente_vendedor().getCliente().setDireccion(rs.getString("DIRECCION"));
                pedido.getCliente_vendedor().getCliente().setTelefono(rs.getString("TELEFONO_CLIENTE"));
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                pedido.setActive(rs.getBoolean("ACTIVE"));
                listarLineasPedido(pedido);
                pedidos.add(pedido);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PedidoMySQL.class.getName()).log(Level.SEVERE, null, ex);
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
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                //pedido.getCliente_vendedor().setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                pedido.getCliente_vendedor().getVendedor().setNombre(rs.getString("NOMBRE_EMPLEADO"));
                pedido.getCliente_vendedor().getVendedor().setApellidos(rs.getString("APELLIDOS_EMPLEADO"));
                pedido.getCliente_vendedor().getVendedor().setDni(rs.getString("DNI_EMPLEADO"));
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                pedido.setActive(rs.getBoolean("ACTIVE"));
                listarLineasPedido(pedido);
                pedidos.add(pedido);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PedidoMySQL.class.getName()).log(Level.SEVERE, null, ex);
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
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PedidoMySQL.class.getName()).log(Level.SEVERE, null, ex);
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
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PedidoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        return lineasPedido;
    }
    
}

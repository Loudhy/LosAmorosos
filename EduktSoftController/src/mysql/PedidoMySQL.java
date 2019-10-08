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
import java.util.ArrayList;
import model.Cliente;
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
            cs = con.prepareCall("{call INSERTAR_PEDIDO(?,?,?,?,?)} ");
            float total=0;
            for (LineaPedido aux: pedido.getLineasPedido()){
                total += aux.getCantidad() * aux.getSubtotal();
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
                cs.setString("_ESTADO_LINEA_PEDIDO", aux.getEstadoLineaPedido().toString());
                cs.setDate("_FECHA_ATENCION", new java.sql.Date(aux.getFechaAtencion().getTime()));
                cs.setBoolean("_ACTIVE", true);
                cs.executeUpdate();
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
            cs = con.prepareCall("{call ACTUALIZAR_PEDIDO(?,?,?)}");
            cs.setInt("_ID_PEDIDO",pedido.getId());
            cs.setFloat("_TOTAL_PEDIDO", pedido.getTotal());
            cs.setString("_ESTADO_PEDIDO",pedido.getEstadoPedido().toString());
            resultado=cs.executeUpdate();
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
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_VENDEDOR(?)");
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                
                pedidos.add(pedido);
            }
        }catch(SQLException ex){
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
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_CLIENTE(?)");
            cs.setInt("_ID_CLIENTE", cliente.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                
                pedidos.add(pedido);
            }
        }catch(SQLException ex){
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
            cs = con.prepareCall("{call LISTAR_PEDIDO_POR_CLIENTE(?)");
            cs.setInt("_ID_PEDIDO", pedido.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                LineaPedido lineaPedido = new LineaPedido();
                lineaPedido.setCantidad(rs.getInt("CANTIDAD"));
                
                lineasPedido.add(lineaPedido);
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return lineasPedido;   
    }
    
}

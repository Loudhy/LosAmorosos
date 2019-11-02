/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.ClienteVendedorDAO;
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
import model.Cliente_Vendedor;
import model.EstadoPedido;
import model.Pedido;
import model.Vendedor;

public class ClienteVendedorMySQL implements ClienteVendedorDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Cliente_Vendedor cliente_vendedor) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_CLIENTE_VENDEDOR(?,?,?,?)} ");
            cs.setInt("_ID_CLIENTE", cliente_vendedor.getCliente().getId());
            cs.setInt("_ID_VENDEDOR", cliente_vendedor.getVendedor().getId());
            cs.setBoolean("_ACTIVE", true);
            resultado = cs.executeUpdate();
            cliente_vendedor.setId_cliente_vendedor(cs.getInt("_ID_CLIENTE_VENDEDOR"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public ArrayList<Cliente> listarClientesPorVendedor(int id_vendedor) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            con.prepareCall("LISTAR_CLIENTES_POR_VENDEDOR(?)");
            cs.setInt("_ID_VENDEDOR", id_vendedor);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("ID_CLIENTE"));
                cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                cliente.setRuc(rs.getString("RUC"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setTelefono(rs.getString("TELEFONO_CLIENTE"));
                cliente.setCorreo(rs.getString("CORREO_CLIENTE"));
                cliente.getProvincia().setId(rs.getInt("ID_PROVINCIA"));
                cliente.getProvincia().setNombre(rs.getString("NOMBRE_PROVINCIA"));
                cliente.getProvincia().getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                cliente.getProvincia().getDepartamento().setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                clientes.add(cliente);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return clientes;
    }

    @Override
    public int eliminar(int id_cliente_vendedor) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_CLIENTE_VENDEDOR(?)} ");
            cs.setInt("_ID_CLIENTE_VENDEDOR", id_cliente_vendedor);
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public ArrayList<Pedido> listarPedidosEnRangoDeFechas(Date fechaIni, Date fechaFin, Vendedor vendedor) {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDOS_DE_VENDEDOR_POR_RANGO_DE_FECHAS(?,?,?)}");
            cs.setDate("_FECHA_INI", new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_FECHA_FIN",new java.sql.Date(fechaFin.getTime()));
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("NOMBRE_CLIENTE"));
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                String fechaAux = formatoFecha.format(fechaIngreso);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }
    
    
}

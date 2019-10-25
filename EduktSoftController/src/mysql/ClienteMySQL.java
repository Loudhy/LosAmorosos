  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.ClienteDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;
import model.Pedido;
import model.Vendedor;

/**
 *
 * @author UsuarioA
 */
public class ClienteMySQL implements ClienteDAO{

    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;

    @Override
    public int insertar(Cliente cliente) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_CLIENTE(?,?,?,?,?,?,?,?)} ");
            cs.setString("_RUC",cliente.getRuc());
            cs.setString("_RAZON_SOCIAL", cliente.getRazonSocial());
            cs.setString("_CORREO_CLIENTE",cliente.getCorreo());
            cs.setInt("_ID_PROVINCIA", cliente.getProvincia().getId());
            cs.setString("_DIRECCION", cliente.getDireccion());
            cs.setString("_TELEFONO_CLIENTE", cliente.getTelefono());
            cs.setBoolean("_ACTIVE", cliente.getActive());
            resultado = cs.executeUpdate();
            cliente.setId(cs.getInt("_ID_CLIENTE"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(Cliente cliente) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_CLIENTE(?,?,?,?,?,?,?)} ");
            cs.setInt("_ID_CLIENTE", cliente.getId());
            cs.setString("_RUC",cliente.getRuc());
            cs.setString("_RAZON_SOCIAL",cliente.getRazonSocial());
            cs.setString("_CORREO_CLIENTE", cliente.getCorreo());
            cs.setInt("_ID_PROVINCIA", cliente.getProvincia().getId());
            cs.setString("_TELEFONO_CLIENTE", cliente.getTelefono());
            cs.setString("_DIRECCION", cliente.getDireccion());
            resultado = cs.executeUpdate();           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Cliente> listarClientesPorVendedor(Vendedor vendedor) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            con.prepareCall("LISTAR_CLIENTES_POR_VENDEDOR(?)");
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
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
    public Cliente encontrarPorId(int id) {
        Cliente cliente = null;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            con.prepareCall("BUSCAR_CLIENTE_POR_ID(?)");
            cs.setInt("_ID_VENDEDOR", id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                cliente.setId(id);
                cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                cliente.setRuc(rs.getString("RUC"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setTelefono(rs.getString("TELEFONO_CLIENTE"));
                cliente.setCorreo(rs.getString("CORREO_CLIENTE"));
                cliente.getProvincia().setId(rs.getInt("ID_PROVINCIA"));
                cliente.getProvincia().setNombre(rs.getString("NOMBRE_PROVINCIA"));
                cliente.getProvincia().getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                cliente.getProvincia().getDepartamento().setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                cliente.setActive(true);
                cliente.getProvincia().setActive(true);
                cliente.getProvincia().getDepartamento().setActive(true);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return cliente;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_CLIENTE(?)}");
            cs.setInt("_ID_CLIENTE", id);
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            con.prepareCall("LISTAR_CLIENTES()");
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
                cliente.setActive(true);
                cliente.getProvincia().setActive(true);
                cliente.getProvincia().getDepartamento().setActive(true);
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
    public Pedido buscarUltimoPedido(int id_cliente) {
        Pedido pedido = null;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            con.prepareCall("{call BUSCAR_ULTIMO_PEDIDO(?)}");
            cs.setInt("_ID_CLIENTE", id_cliente);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                pedido = new Pedido();
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        return pedido;
    }


}

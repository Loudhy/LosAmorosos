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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;
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
            cs.setString("_CORREO",cliente.getCorreo());
            cs.setInt("_ID_PROVINCIA", cliente.getProvincia().getId());
            cs.setString("_TELEFONO_CLIENTE", cliente.getTelefono());
            cs.setString("_DIRECCION", cliente.getDireccion());
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
            cs.setString("_CORREO", cliente.getCorreo());
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
    public ArrayList<Cliente> listarPorVendedor(Vendedor vendedor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

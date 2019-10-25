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
import java.sql.SQLException;
import java.sql.Statement;
import model.Cliente_Vendedor;

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
    
    
    
}

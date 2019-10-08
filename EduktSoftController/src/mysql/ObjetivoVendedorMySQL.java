/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.ObjetivoVendedorDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import model.ObjetivoVendedor;

/**
 *
 * @author alulab14
 */
public class ObjetivoVendedorMySQL implements ObjetivoVendedorDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;

    @Override
    public int insertar(ObjetivoVendedor objetivoVendedor) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_OBJETIVO_VENDEDOR(?,?,?,?,?,?,?)} ");
            cs.setInt("_ID_META_MENSUAL", objetivoVendedor.getMetaMensual().getId());
            cs.setFloat("_MONTO",objetivoVendedor.getMonto());
            cs.setFloat("_COMISION",objetivoVendedor.getComision());
            cs.setFloat("_BONO", objetivoVendedor.getBono());
            cs.setInt("_ID_VENDEDOR", objetivoVendedor.getVendedor().getId());
            cs.setBoolean("_ACTIVE",objetivoVendedor.isActive());
            cs.executeUpdate();
            objetivoVendedor.setId(cs.getInt("_ID_OBJETIVO_VENDEDOR"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(ObjetivoVendedor objetivoVendedor) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_OBJETIVO_VENDEDOR(?,?,?,?,?,?)} ");
            cs.setInt("_ID_OBJETIVO_VENDEDOR",objetivoVendedor.getId());
            cs.setInt("_ID_META_MENSUAL",objetivoVendedor.getMetaMensual().getId());
            cs.setFloat("_MONTO", objetivoVendedor.getMonto());
            cs.setFloat("_COMISION",objetivoVendedor.getComision());
            cs.setFloat("_BONO", objetivoVendedor.getBono());
            cs.setInt("_ID_VENDEDOR", objetivoVendedor.getVendedor().getId());
            resultado = cs.executeUpdate();           
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.PresentacionDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Presentacion;

/**
 *
 * @author alulab14
 */
public class PresentacionMySQL implements PresentacionDAO {
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Presentacion presentacion) {
        int resultado = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PRESENTACION(?,?,?,?)} ");
            cs.setInt("_ID_PRODUCTO", presentacion.getId_producto());
            cs.setString("_DISEÑO",presentacion.getDiseño());
            cs.setBoolean("_ACTIVE", true);
            resultado=cs.executeUpdate();
            presentacion.setId(cs.getInt("_ID_PRESENTACION"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PresentacionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;   
    }

    @Override
    public Presentacion encontrarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Presentacion presentacion) {
        int resultado = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PRESENTACION(?,?)} ");
            cs.setInt("_ID_PRESENTACION", presentacion.getId());
            cs.setString("_DISEÑO",presentacion.getDiseño());
            resultado=cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PresentacionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PRESENTACION(?)} ");
            cs.setInt("_ID_PRESENTACION", id);
            resultado=cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PresentacionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Presentacion> listarPresentaciones(int id_producto) {
        ArrayList<Presentacion> presentaciones = new ArrayList<Presentacion>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PRESENTACION(?)}");
            cs.setInt("_ID_PRODUCTO", id_producto);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Presentacion presentacion = new Presentacion();
                presentacion.setDiseño(rs.getString("DISEÑO"));
                presentacion.setActive(true);
                presentaciones.add(presentacion);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PresentacionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return presentaciones;
    }

    @Override
    public ArrayList<Presentacion> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

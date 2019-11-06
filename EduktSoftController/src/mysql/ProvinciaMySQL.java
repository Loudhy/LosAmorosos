/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.ProvinciaDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Departamento;
import model.Provincia;

/**
 *
 * @author UsuarioA
 */
public class ProvinciaMySQL implements ProvinciaDAO {
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Provincia provincia) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PROVINCIA(?,?,?,?)} ");
            cs.setString("_NOMBRE_PROVINCIA", provincia.getNombre());
            cs.setInt("_ID_DEPARTAMENTO", provincia.getDepartamento().getId());
            cs.setBoolean("_ACTIVE",provincia.isActive());
            resultado = cs.executeUpdate();
            provincia.setId(cs.getInt("_ID_PROVINCIA"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    }

    @Override
    public int actualizar(Provincia provincia) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PROVINCIA(?,?,?)} ");
            cs.setInt("_ID_PROVINCIA",provincia.getId());
            cs.setString("_NOMBRE_PROVINCIA", provincia.getNombre());
            cs.setInt("_ID_DEPARTAMENTO", provincia.getDepartamento().getId());
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id_provincia) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PROVINCIA(?) ");
            cs.setInt("_ID_PROVINCIA", id_provincia);
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    }

    @Override
    public ArrayList<Provincia> listarPorDepartamento(Departamento departamento) {
        ArrayList<Provincia> provincias = new ArrayList<Provincia>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PROVINCIA_POR_DEPARTAMENTO(?)}");
            cs.setInt("_ID_DEPARTAMENTO", departamento.getId());
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Provincia provincia = new Provincia();
                provincia.setId(rs.getInt("ID_PROVINCIA"));
                provincia.setNombre(rs.getString("NOMBRE_PROVINCIA"));
                provincia.getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                provincia.getDepartamento().setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                provincia.setActive(rs.getBoolean("ACTIVE"));
                provincias.add(provincia);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return provincias;    
    }

    @Override
    public Provincia encontrarPorId(int id) {
        Provincia provincia = null;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_PROVINCIA_POR_ID()}");
            cs.setInt("_ID_PROVINCIA", id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                provincia = new Provincia();
                provincia.setId(id);
                provincia.setNombre(rs.getString("NOMBRE_PROVINCIA"));
                provincia.getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                provincia.getDepartamento().setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                provincia.setActive(rs.getBoolean("ACTIVE"));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return provincia; 
    }

    @Override
    public ArrayList<Provincia> listar() {
        ArrayList<Provincia> provincias = new ArrayList<Provincia>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PROVINCIAS()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Provincia provincia = new Provincia();
                provincia.setId(rs.getInt("ID_PROVINCIA"));
                provincia.setNombre(rs.getString("NOMBRE_PROVINCIA"));
                provincia.getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                provincia.setActive(rs.getBoolean("ACTIVE"));
                provincias.add(provincia);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return provincias; 
    }
    
}

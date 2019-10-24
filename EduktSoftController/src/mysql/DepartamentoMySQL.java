/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.DepartamentoDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Departamento;

/**
 *
 * @author UsuarioA
 */
public class DepartamentoMySQL implements DepartamentoDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Departamento departamento) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_DEPARTAMENTO(?,?,?)} ");
            cs.setString("_NOMBRE_DEPARTAMENTO", departamento.getNombre());
            cs.setBoolean("_ACTIVE",departamento.isActive());
            resultado = cs.executeUpdate();
            departamento.setId(cs.getInt("_ID_DEPARTAMENTO"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(Departamento departamento) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_DEPARTAMENTO(?,?)} ");
            cs.setInt("_ID_DEPARTAMENTO",departamento.getId());
            cs.setString("_NOMBRE_DEPARTAMENTO", departamento.getNombre());
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id_departamento) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_DEPARTAMENTO(?} ");
            cs.setInt("_ID_DEPARTAMENTO", id_departamento);
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Departamento> listar() {
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_DEPARTAMENTO()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Departamento departamento = new Departamento();
                departamento.setId(rs.getInt("ID_DEPARTAMENTO"));
                departamento.setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                departamento.setActive(rs.getBoolean("ACTIVE"));
                departamentos.add(departamento);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return departamentos;
    }

    @Override
    public Departamento encontrarPorId(int id) {
        Departamento departamento = null;
        return departamento;
    }
    
}

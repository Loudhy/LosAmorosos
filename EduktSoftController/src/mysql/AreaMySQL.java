/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.AreaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Area;

/**
 *
 * @author UsuarioA
 */
public class AreaMySQL implements AreaDAO {

    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Area area) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_AREA(?,?,?,?)} ");
            cs.setString("_NOMBRE_AREA", area.getNombre());
            cs.setInt("_CODIGO_AREA",area.getCodigo());
            cs.setBoolean("_ACTIVE", area.isActive());
            cs.registerOutParameter("_ID_AREA", java.sql.Types.INTEGER);
            resultado = cs.executeUpdate();
            area.setId(cs.getInt("_ID_AREA"));
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(Area area) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_AREA(?,?,?)}");
            cs.setInt("_ID_AREA", area.getId());
            cs.setString("_NOMBRE_AREA",area.getNombre());
            cs.setInt("_CODIGO_AREA", area.getCodigo());
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }


    @Override
    public int eliminar(int id_area) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_AREA(?)}");
            cs.setInt("_ID_AREA", id_area);
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Area> listar() {
        ArrayList<Area> areas = new ArrayList<Area>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_AREA()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Area area = new Area();
                area.setId(rs.getInt("ID_AREA"));
                area.setNombre(rs.getString("NOMBRE_AREA"));
                area.setCodigo(rs.getInt("CODIGO_AREA"));
                area.setActive(rs.getBoolean("ACTIVE"));
                areas.add(area);
            }
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return areas;
    }

    @Override
    public Area encontrarPorId(int id) {
        Area area = new Area();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_AREA_POR_ID(?)}");
            cs.setInt("_ID_AREA", id);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                area.setId(rs.getInt("ID_AREA"));
                area.setNombre(rs.getString("NOMBRE_AREA"));
                area.setCodigo(rs.getInt("CODIGO_AREA"));
                area.setActive(rs.getBoolean("ACTIVE"));
            }
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return area;
    }
    
   
}

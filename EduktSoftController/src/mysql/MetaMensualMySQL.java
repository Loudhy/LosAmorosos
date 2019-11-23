/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.MetaMensualDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.MetaMensual;

/**
 *
 * @author UsuarioA
 */
public class MetaMensualMySQL implements MetaMensualDAO {
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    
    @Override
    public int insertar(MetaMensual metaMensual) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_META_MENSUAL(?,?,?,?,?,?)} ");
            cs.setDate("_FECHA_INICIO", new java.sql.Date(metaMensual.getFechaInicio().getTime()));
            cs.setDate("_FECHA_FIN", new java.sql.Date(metaMensual.getFechaFin().getTime()));
            cs.setFloat("_CANTIDAD_OBJETIVO",metaMensual.getCantidadObjetivo());
            cs.setString("_DESCRIPCION_META_MENSUAL",metaMensual.getDescripcion());
            cs.setBoolean("_ACTIVE", metaMensual.isActive());
            resultado = cs.executeUpdate();
            metaMensual.setId(cs.getInt("_ID_META_MENSUAL"));
            resultado = metaMensual.getId();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(MetaMensual metaMensual) {
        int resultado = 0;
        try{  
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_META_MENSUAL(?,?,?,?,?)} ");
            cs.setInt("_ID_META_MENSUAL", metaMensual.getId());
            cs.setDate("_FECHA_INICIO", new java.sql.Date(metaMensual.getFechaInicio().getTime()));
            cs.setDate("_FECHA_FIN", new java.sql.Date(metaMensual.getFechaFin().getTime()));
            cs.setFloat("_CANTIDAD_OBJETIVO",metaMensual.getCantidadObjetivo());
            cs.setString("_DESCRIPCION_META_MENSUAL",metaMensual.getDescripcion());
            resultado = cs.executeUpdate();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id_meta_mensual) {
        int resultado = 0;
        try{  
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_META_MENSUAL(?} ");
            cs.setInt("_ID_META_MENSUAL", id_meta_mensual);
            resultado = cs.executeUpdate();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<MetaMensual> listar() {
        ArrayList<MetaMensual> metasMensuales = new ArrayList<MetaMensual>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_META_MENSUAL()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                MetaMensual metaMensual = new MetaMensual();
                java.util.Date fechaInicio = new java.util.Date(rs.getDate("FECHA_INICIO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaInicio);
                metaMensual.setFechaInicio(formatoFecha.parse(fechaAux));
                java.util.Date fechaLimite = new java.util.Date(rs.getDate("FECHA_FIN").getTime());
                fechaAux = formatoFecha.format(fechaLimite);
                metaMensual.setFechaFin(formatoFecha.parse(fechaAux));
                metaMensual.setCantidadObjetivo(rs.getFloat("CANTIDAD_OBJETIVO"));
                metaMensual.setDescripcion(rs.getString("DESCRIPCION_META_MENSUAL"));
                metaMensual.setActive(rs.getBoolean("ACTIVE"));
                metasMensuales.add(metaMensual);
            }
            
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return metasMensuales;
    }

    @Override
    public MetaMensual encontrarPorId(int id) {
        MetaMensual metasMensual = null;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_META_MENSUAL_POR_ID(?)}");
            cs.setInt("_ID_META_MENSUAL", id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                MetaMensual metaMensual = new MetaMensual();
                java.util.Date fechaInicio = new java.util.Date(rs.getDate("FECHA_INICIO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaInicio);
                metaMensual.setFechaInicio(formatoFecha.parse(fechaAux));
                java.util.Date fechaLimite = new java.util.Date(rs.getDate("FECHA_FIN").getTime());
                fechaAux = formatoFecha.format(fechaLimite);
                metaMensual.setFechaFin(formatoFecha.parse(fechaAux));
                metaMensual.setCantidadObjetivo(rs.getFloat("CANTIDAD_OBJETIVO"));
                metaMensual.setDescripcion(rs.getString("DESCRIPCION_META_MENSUAL"));
                metaMensual.setActive(rs.getBoolean("ACTIVE"));
            }
            
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return metasMensual;
    }

    @Override
    public MetaMensual buscarMetaMensualActiva() {
        MetaMensual metaMensual = null;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_META_MENSUAL_ACTIVA()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                metaMensual = new MetaMensual();
                metaMensual.setId(rs.getInt("ID_META_MENSUAL"));
                java.util.Date fechaInicio = new java.util.Date(rs.getDate("FECHA_INICIO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaInicio);
                metaMensual.setFechaInicio(formatoFecha.parse(fechaAux));
                java.util.Date fechaLimite = new java.util.Date(rs.getDate("FECHA_FIN").getTime());
                fechaAux = formatoFecha.format(fechaLimite);
                metaMensual.setFechaFin(formatoFecha.parse(fechaAux));
                metaMensual.setCantidadObjetivo(rs.getFloat("CANTIDAD_OBJETIVO"));
                metaMensual.setDescripcion(rs.getString("DESCRIPCION_META_MENSUAL"));
                metaMensual.setActive(rs.getBoolean("ACTIVE"));
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return metaMensual;
    }
    
}

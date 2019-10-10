/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.SolicitudDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EstadoLineaPedido;
import model.EstadoSolicitud;
import model.LineaSolicitud;
import model.Solicitud;

/**
 *
 * @author UsuarioA
 */
public class SolicitudMySQL implements SolicitudDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Solicitud solicitud) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_SOLICITUD(?,?,?,?,?,?,?)}");
            cs.setString("_ESTADO_SOLICITUD",solicitud.getEstadoSolicitud().toString());
            cs.setDate("_FECHA_REGISTRO", new java.sql.Date(solicitud.getFechaRegistro().getTime()));
            cs.setInt("_ID_LOGISTICO",solicitud.getLogistico().getId());
            cs.setInt("_ID_FACTURADOR",solicitud.getFacturador().getId());
            cs.setInt("_ID_PEDIDO",solicitud.getPedido().getId());
            cs.setBoolean("_ACTIVE",true);
            resultado = cs.executeUpdate();
            solicitud.setId(cs.getInt("_ID_SOLICITUD"));
            for (LineaSolicitud aux: solicitud.getLineasSolicitud()){
                cs = con.prepareCall("{call INSERTAR_LINEA_SOLICITUD(?,?,?,?,?,?)}");
                cs.setInt("_ID_SOLICITUD",solicitud.getId());
                cs.setInt("_ID_LINEA_PEDIDO",aux.getLineaPedido().getId());
                cs.setInt("_CANTIDAD",aux.getLineaPedido().getCantidadPorAtender());
                cs.setString("_ESTADO_SOLICITUD",aux.getEstadoSolicitud().toString());
                cs.setBoolean("_ACTIVE", true);
                resultado = cs.executeUpdate();
                aux.setId(cs.getInt("_ID_LINEA_SOLICITUD"));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }

    @Override
    public int actualizar(Solicitud solicitud) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_SOLICITUD(?,?,?,?,?,?)}");
            cs.setInt("_ID_SOLICITUD",solicitud.getId());
            cs.setString("_ESTADO_SOLICITUD",solicitud.getEstadoSolicitud().toString());
            cs.setDate("_FECHA_REGISTRO", new java.sql.Date(solicitud.getFechaRegistro().getTime()));
            cs.setInt("_ID_LOGISTICO",solicitud.getLogistico().getId());
            cs.setInt("_ID_FACTURADOR",solicitud.getFacturador().getId());
            cs.setInt("_ID_PEDIDO",solicitud.getPedido().getId());
            resultado = cs.executeUpdate();
            for (LineaSolicitud aux: solicitud.getLineasSolicitud()){
                cs = con.prepareCall("{call ACTUALIZAR_LINEA_SOLICITUD(?,?,?,?,?)}");
                cs.setInt("_ID_LINEA_SOLICITUD",aux.getId());
                cs.setInt("_ID_SOLICITUD",solicitud.getId());
                cs.setInt("_ID_LINEA_PEDIDO",aux.getLineaPedido().getId());
                cs.setInt("_CANTIDAD",aux.getLineaPedido().getCantidadPorAtender());
                cs.setString("_ESTADO_SOLICITUD",aux.getEstadoSolicitud().toString());
                resultado = cs.executeUpdate();
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }

    @Override
    public int eliminar(int id_solicitud) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_SOLICITUD(?)}");
            cs.setInt("_ID_SOLICITUD", id_solicitud);
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }

    @Override
    public ArrayList<LineaSolicitud> listarLineasSolicitud(Solicitud solicitud) {
        ArrayList<LineaSolicitud> lineas = new ArrayList<LineaSolicitud>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_LINEAS_SOLICITUD(?)}");
            cs.setInt("_ID_SOLICITUD", solicitud.getId());
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                LineaSolicitud linea = new LineaSolicitud();
                linea.setId(rs.getInt("ID_LINEA_SOLICITUD"));
                linea.setEstadoSolicitud(EstadoLineaPedido.valueOf(rs.getString("ESTADO_LINEA_SOLICITUD")));
                linea.getLineaPedido().setCantidad(rs.getInt("CANTIDAD"));
                linea.getLineaPedido().setCantidadPorAtender(rs.getInt("CANTIDAD_A_ATENDER"));
                linea.getLineaPedido().setEstadoLineaPedido(EstadoLineaPedido.valueOf(rs.getString("ESTADO_LINEA_PEDIDO")));
                linea.getLineaPedido().getProducto().setNombre(rs.getString("NOMBRE_PRODUCTO"));
                linea.getLineaPedido().getProducto().setDescripcion(rs.getString("DESCRIPCION"));
                lineas.add(linea);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return lineas;
    }

    @Override
    public ArrayList<Solicitud> listarSolicitudes() {
        ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_SOLICITUD()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Solicitud solicitud = new Solicitud();
                solicitud.setId(rs.getInt("ID_SOLICITUD"));
                solicitud.setEstadoSolicitud(EstadoSolicitud.valueOf(rs.getString("ESTADO_SOLICITUD")));
                java.util.Date fechaRegistro = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaRegistro);
                solicitud.setFechaRegistro(formatoFecha.parse(fechaAux));
                ArrayList<LineaSolicitud> lineas = listarLineasSolicitud(solicitud);
                solicitud.setLineasSolicitud(lineas);
                solicitudes.add(solicitud);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(SolicitudMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return solicitudes;
    }

    @Override
    public ArrayList<Solicitud> listarSolicitudesPorEstado(EstadoSolicitud estado) {
        ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_SOLICITUD(?)}");
            cs.setString("_ESTADO_SOLICITUD", estado.toString());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Solicitud solicitud = new Solicitud();
                solicitud.setId(rs.getInt("ID_SOLICITUD"));
                solicitud.setEstadoSolicitud(EstadoSolicitud.valueOf(rs.getString("ESTADO_SOLICITUD")));
                java.util.Date fechaRegistro = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaRegistro);
                solicitud.setFechaRegistro(formatoFecha.parse(fechaAux));
                solicitud.setLineasSolicitud(listarLineasSolicitud(solicitud));
                solicitudes.add(solicitud);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return solicitudes;
    }
}

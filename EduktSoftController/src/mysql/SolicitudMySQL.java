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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
            cs = con.prepareCall("{call INSERTAR_SOLICITUD(?,?)}");
            cs.setString("_ESTADO_SOLICITUD",solicitud.getEstadoSolicitud().toString());
            cs.setDate("_FECHA_REGISTRO", new java.sql.Date(solicitud.getFechaRegistro().getTime()));
            cs.setInt("_ID_LOGISTICO",solicitud.getLogistico().getId());
            cs.setInt("_ID_FACTURADOR",solicitud.getFacturador().getId());
            cs.setInt("_ID_PEDIDO",solicitud.getPedido().getId());
            resultado = cs.executeUpdate();
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }

    @Override
    public int actualizar(Solicitud solicitud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id_solicitud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LineaSolicitud> listarLineasSolicitud(Solicitud solicitud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

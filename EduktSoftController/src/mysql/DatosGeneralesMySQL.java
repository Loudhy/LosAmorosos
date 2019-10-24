/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.DatosGeneralesDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import model.DatosGenerales;

/**
 *
 * @author UsuarioA
 */
public class DatosGeneralesMySQL implements DatosGeneralesDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;

    @Override
    public int insertar(DatosGenerales datosGenerales) {

        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_DATOS_GENERALES(?,?,?,?,?,?,?,?)} ");
            cs.setFloat("_IGV",datosGenerales.getPlazoDePago());
            cs.setFloat("_SUELDO_MINIMO",datosGenerales.getSueldoMinimo());
            cs.setString("_CORREO_EMPRESA",datosGenerales.getCorreoEmpresa());
            cs.setString("_CONTRASEÑA_EMPRESA",datosGenerales.getContraseñaEmpresa());
            cs.setDate("_FECHA", new java.sql.Date(datosGenerales.getFecha().getTime()));
            cs.setInt("_PLAZO_DE_PAGO", datosGenerales.getPlazoDePago());
            cs.setBoolean("_ACTIVE", datosGenerales.isActive());
            datosGenerales.setId(cs.getInt("_ID_DATOS_GENERALES"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public DatosGenerales encontrarPorFecha(Date fecha) {
        DatosGenerales datosGenerales = null;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ENCONTRAR_DATOS_GENERALES_POR_FECHA(?)}");
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                datosGenerales = new DatosGenerales();
                datosGenerales.setId(rs.getInt("ID_DATOS_GENERALES"));
                datosGenerales.setIgv(rs.getFloat("IGV"));
                datosGenerales.setCorreoEmpresa(rs.getString("CORREO_EMPRESA"));
                datosGenerales.setContraseñaEmpresa(rs.getString("CONTRASEÑA_EMPRESA"));
                datosGenerales.setSueldoMinimo(rs.getFloat("SUELDO_MINIMO"));
                datosGenerales.setPlazoDePago(rs.getInt("PLAZO_DE_PAGO"));
                datosGenerales.setFecha(fecha);
                datosGenerales.setActive(rs.getBoolean("ACTIVE"));
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return datosGenerales;
    }

    @Override
    public DatosGenerales encontrarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(DatosGenerales objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DatosGenerales> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

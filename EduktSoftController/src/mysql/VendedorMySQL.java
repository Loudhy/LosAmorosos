/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.VendedorDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Area;
import model.Vendedor;

/**
 *
 * @author alulab14
 */
public class VendedorMySQL implements VendedorDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Vendedor vendedor) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?)} ");
            cs.setString("_DNI_EMPLEADO", vendedor.getDni());
            cs.setString("_NOMBRE_EMPLEADO", vendedor.getNombre());
            cs.setString("_APELLIDOS", vendedor.getApellidos());            
            cs.setDate("_FECHA_NACIMIENTO", new java.sql.Date(vendedor.getFechaNacimiento().getTime()));
            cs.setString("_TELEFONO_EMPLEADO",vendedor.getTelefono());
            cs.setString("_CORREO_EMPLEADO", vendedor.getCorreo());
            cs.setString("_ESTADO_CIVIL", vendedor.getEstadoCivil().name());
            cs.setFloat("_SUELDO", vendedor.getSueldo());
            cs.setInt("_ID_AREA",vendedor.getArea().getId());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(vendedor.getFechaIngreso().getTime()));
            cs.setBoolean("_ACTIVE", vendedor.isActive());
            resultado = cs.executeUpdate();
            vendedor.setId(cs.getInt("_ID_EMPLEADO"));
            cs = con.prepareCall("{call INSERTAR_VENDEDOR(?,?)} ");
            cs.setInt("_ID_EMPLEADO", vendedor.getId());
            cs.executeUpdate();
            vendedor.setId_vendedor(cs.getInt("_ID_VENDEDOR"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }


    @Override
    public int eliminar(int id_vendedor) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_VENDEDOR(?,?)}");
            cs.setInt("_ID_VENDEDOR",id_vendedor);
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.EmpleadoDAO;
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
import model.Area;
import model.Empleado;
import model.EstadoCivil;

/**
 *
 * @author UsuarioA
 */
public class EmpleadoMySQL implements EmpleadoDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Empleado empleado) {
        int resultado = 0;
        try{       
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?,?)} ");
            cs.setString("_DNI_EMPLEADO", empleado.getDni());
            cs.setString("_NOMBRE_EMPLEADO", empleado.getNombre());
            cs.setString("_APELLIDO_PATERNO", empleado.getApellidoPaterno());
            cs.setString("_APELLIDO_MATERNO",empleado.getApellidoMaterno());            
            cs.setDate("_FECHA_NACIMIENTO", new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            cs.setString("_TELEFONO_EMPLEADO",empleado.getTelefono());
            cs.setString("_CORREO_EMPLEADO", empleado.getCorreo());
            cs.setString("_ESTADO_CIVIL", empleado.getEstadoCivil().name());
            cs.setFloat("_SUELDO", empleado.getSueldo());
            cs.setInt("_ID_AREA",empleado.getArea().getId());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(empleado.getFechaIngreso().getTime()));
            cs.setBoolean("_ACTIVE", empleado.isActive());
            resultado = cs.executeUpdate();
            empleado.setId(cs.getInt("_ID_EMPLEADO"));
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int actualizar(Empleado empleado) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_EMPLEADO", empleado.getId());
            cs.setString("_DNI_EMPLEADO", empleado.getDni());
            cs.setString("_NOMBRE_EMPLEADO", empleado.getNombre());
            cs.setString("_APELLIDO_PATERNO", empleado.getApellidoPaterno());
            cs.setString("_APELLIDO_MATERNO",empleado.getApellidoMaterno());
            cs.setDate("_FECHA_NACIMIENTO", new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            cs.setString("_TELEFONO_EMPLEADO",empleado.getTelefono());
            cs.setString("_CORREO_EMPLEADO", empleado.getCorreo());
            cs.setString("_ESTADO_CIVIL", empleado.getEstadoCivil().name());
            cs.setFloat("_SUELDO", empleado.getSueldo());
            cs.setInt("_ID_AREA",empleado.getArea().getId());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(empleado.getFechaIngreso().getTime()));
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id_empleado) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_EMPLEADO(?)}");
            cs.setInt("_ID_EMPLEADO", id_empleado);
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Empleado> listar() {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_EMPLEADO()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("ID_EMPLEADO"));
                empleado.setDni(rs.getString("DNI_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                empleado.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                empleado.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                empleado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                empleado.setFechaNacimiento(formatoFecha.parse(fechaAux));
                empleado.getArea().setId(rs.getInt("ID_AREA"));
                empleado.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                empleado.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                empleado.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                empleado.setFechaIngreso(formatoFecha.parse(fechaAux));
                empleado.setActive(rs.getBoolean("ACTIVE"));
                empleados.add(empleado);
            }
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return empleados;
    }

    @Override
    public ArrayList<Empleado> listarPorArea(Area area) {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_EMPLEADO_POR_AREA(?)}");
            cs.setInt("_ID_AREA", area.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("ID_EMPLEADO"));
                empleado.setDni(rs.getString("DNI_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                empleado.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                empleado.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                empleado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                empleado.setFechaNacimiento(formatoFecha.parse(fechaAux));
                empleado.getArea().setId(rs.getInt("ID_AREA"));
                empleado.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                empleado.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                empleado.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                empleado.setFechaIngreso(formatoFecha.parse(fechaAux));
                empleado.setActive(rs.getBoolean("ACTIVE"));
                empleados.add(empleado);
            }
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return empleados;
    }

    @Override
    public Empleado buscarEmpleadoPorApellidos(String apellido_paterno,String apellido_materno) {
        Empleado empleado = null;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_EMPLEADO_POR_APELLIDOS(?,?)}");
            cs.setString("_APELIDO_PATERNO", apellido_paterno);
            cs.setString("_APELLIDO_MATERNO",apellido_materno);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                empleado = new Empleado();
                empleado.setId(rs.getInt("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                empleado.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                empleado.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                empleado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                empleado.setDni(rs.getString("DNI_EMPLEADO"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                empleado.setFechaNacimiento(formatoFecha.parse(fechaAux));
                empleado.getArea().setId(rs.getInt("ID_AREA"));
                empleado.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                empleado.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                empleado.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                empleado.setFechaIngreso(formatoFecha.parse(fechaAux));
                empleado.setActive(rs.getBoolean("ACTIVE"));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return empleado;
    }

    @Override
    public Empleado buscarEmpleadoPorCorreo(String correo) {
        Empleado empleado = null;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_EMPLEADO_POR_CORREO(?)}");
            cs.setString("_CORREO", correo);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                empleado = new Empleado();
                empleado.setId(rs.getInt("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                empleado.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                empleado.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                empleado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                empleado.setDni(rs.getString("DNI_EMPLEADO"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                empleado.setFechaNacimiento(formatoFecha.parse(fechaAux));
                empleado.getArea().setId(rs.getInt("ID_AREA"));
                empleado.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                empleado.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                empleado.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                empleado.setFechaIngreso(formatoFecha.parse(fechaAux));
                empleado.setActive(rs.getBoolean("ACTIVE"));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return empleado;    
    }

    @Override
    public Empleado buscarEmpleadoPorDni(String dni) {
        Empleado empleado = null;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_EMPLEADO_POR_DNI(?)}");
            cs.setString("_DNI", dni);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                empleado = new Empleado();
                empleado.setId(rs.getInt("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                empleado.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                empleado.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                empleado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                empleado.setDni(rs.getString("DNI_EMPLEADO"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                empleado.setFechaNacimiento(formatoFecha.parse(fechaAux));
                empleado.getArea().setId(rs.getInt("ID_AREA"));
                empleado.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                empleado.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                empleado.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                empleado.setFechaIngreso(formatoFecha.parse(fechaAux));
                empleado.setActive(rs.getBoolean("ACTIVE"));
            }
        }catch (SQLException | ParseException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return empleado;
    }

    @Override
    public Empleado encontrarPorId(int id) {
        Empleado empleado = null;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_EMPLEADO_POR_ID(?)}");
            cs.setInt("_ID_EMPLEADO", id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()){
                empleado = new Empleado();
                empleado.setId(rs.getInt("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                empleado.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                empleado.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                empleado.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                empleado.setDni(rs.getString("DNI_EMPLEADO"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                empleado.setFechaNacimiento(formatoFecha.parse(fechaAux));
                empleado.getArea().setId(rs.getInt("ID_AREA"));
                empleado.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                empleado.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                empleado.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                empleado.setFechaIngreso(formatoFecha.parse(fechaAux));
                empleado.setActive(rs.getBoolean("ACTIVE"));
            }
        }catch (SQLException | ParseException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        return empleado;
    }
    
}

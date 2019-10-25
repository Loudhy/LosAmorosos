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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Area;
import model.EstadoCivil;
import model.EstadoPedido;
import model.Pedido;
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
            cs.setString("_APELLIDO_PATERNO", vendedor.getApellidoPaterno());
            cs.setString("_APELLIDO_MATERNO",vendedor.getApellidoMaterno());
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

    @Override
    public ArrayList<Pedido> listarPedidosEnRangoDeFechas(Date fechaIni, Date fechaFin, Vendedor vendedor) {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_PEDIDOS_DE_VENDEDOR_POR_RANGO_DE_FECHAS(?,?,?)}");
            cs.setDate("_FECHA_INI", new java.sql.Date(fechaIni.getTime()));
            cs.setDate("_FECHA_FIN",new java.sql.Date(fechaFin.getTime()));
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("ID_PEDIDO"));
                pedido.setEstadoPedido(EstadoPedido.valueOf(rs.getString("ESTADO_PEDIDO")));
                pedido.setTotal(rs.getFloat("TOTAL_PEDIDO"));
                pedido.getClienteVendedor().getCliente().setId(rs.getInt("ID_CLIENTE"));
                pedido.getClienteVendedor().getCliente().setRazonSocial(rs.getString("NOMBRE_CLIENTE"));
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_REGISTRO").getTime());
                String fechaAux = formatoFecha.format(fechaIngreso);
                pedido.setFechaRegistro(formatoFecha.parse(fechaAux));
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(VendedorMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public Vendedor encontrarPorId(int id) {
        Vendedor vendedor = null;
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_VENDEDOR_POR_ID(?)}");
            cs.setInt("_ID_VENDEDOR",id);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                vendedor = new Vendedor();
                vendedor.setId_vendedor(id);
                vendedor.setId(rs.getInt("ID_EMPLEADO"));
                vendedor.setActive(true);
                vendedor.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                vendedor.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                vendedor.setDni(rs.getString("DNI_EMPLEADO"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                vendedor.setFechaNacimiento(formatoFecha.parse(fechaAux));
                vendedor.getUsuario().setNombre(rs.getString("NOMBRE_USUARIO"));
                vendedor.getUsuario().setContraseña(rs.getString("CONTRASEÑA"));
                vendedor.setTelefono(rs.getString("TELEFONO_EMPLEADO"));
                vendedor.setCorreo(rs.getString("CORREO_EMPLEADO"));
                vendedor.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                vendedor.setSueldo(rs.getFloat("SUELDO"));
                vendedor.getArea().setId(rs.getInt("ID_AREA"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                vendedor.setFechaIngreso(formatoFecha.parse(fechaAux));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(VendedorMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return vendedor;
    }

    @Override
    public int actualizar(Vendedor vendedor) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_ID_EMPLEADO", vendedor.getId());
            cs.setString("_DNI_EMPLEADO", vendedor.getDni());
            cs.setString("_NOMBRE_EMPLEADO", vendedor.getNombre());
            cs.setString("_APELLIDO_PATERNO", vendedor.getApellidoPaterno());
            cs.setString("_APELLIDO_MATERNO",vendedor.getApellidoMaterno());
            cs.setDate("_FECHA_NACIMIENTO", new java.sql.Date(vendedor.getFechaNacimiento().getTime()));
            cs.setString("_TELEFONO_EMPLEADO",vendedor.getTelefono());
            cs.setString("_CORREO_EMPLEADO", vendedor.getCorreo());
            cs.setString("_ESTADO_CIVIL", vendedor.getEstadoCivil().name());
            cs.setFloat("_SUELDO", vendedor.getSueldo());
            cs.setInt("_ID_AREA",vendedor.getArea().getId());
            cs.setDate("_FECHA_INGRESO", new java.sql.Date(vendedor.getFechaIngreso().getTime()));
            resultado = cs.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Vendedor> listar() {
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_EMPLEADO()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Vendedor vendedor = new Vendedor();
                vendedor.setId_vendedor(rs.getInt("ID_VENDEDOR"));
                vendedor.setId(rs.getInt("ID_EMPLEADO"));
                vendedor.setDni(rs.getString("DNI_EMPLEADO"));
                vendedor.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                vendedor.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                vendedor.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                vendedor.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                vendedor.setCorreo(rs.getString("CORREO_EMPLEADO"));
                vendedor.setTelefono(rs.getString("TELEFONO_EMPLEADO"));
                vendedor.getUsuario().setNombre(rs.getString("NOMBRE_USUARIO"));
                vendedor.getUsuario().setContraseña(rs.getString("CONTRASEÑA"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                vendedor.setFechaNacimiento(formatoFecha.parse(fechaAux));
                vendedor.getArea().setId(rs.getInt("ID_AREA"));
                vendedor.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                vendedor.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                vendedor.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                vendedor.setFechaIngreso(formatoFecha.parse(fechaAux));

                vendedores.add(vendedor);
            }
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return vendedores;
    }

}

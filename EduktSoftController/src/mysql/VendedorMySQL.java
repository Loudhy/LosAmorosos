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
                
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return vendedor;
    }

    @Override
    public int actualizar(Vendedor objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Vendedor> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBManager;
import dao.ClienteVendedorDAO;
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
import model.Cliente;
import model.Cliente_Vendedor;
import model.EstadoCivil;
import model.EstadoPedido;
import model.Pedido;
import model.Vendedor;

public class ClienteVendedorMySQL implements ClienteVendedorDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Cliente_Vendedor cliente_vendedor) {
        int resultado = 0;
        try{ 
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_CLIENTE_VENDEDOR(?,?,?,?)} ");
            cs.setInt("_ID_CLIENTE", cliente_vendedor.getCliente().getId());
            cs.setInt("_ID_VENDEDOR", cliente_vendedor.getVendedor().getId());
            cs.setBoolean("_ACTIVE", true);
            resultado = cs.executeUpdate();
            cliente_vendedor.setId_cliente_vendedor(cs.getInt("_ID_CLIENTE_VENDEDOR"));
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public ArrayList<Cliente> listarClientesPorVendedor(int id_vendedor) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_CLIENTES_POR_VENDEDOR(?)}");
            cs.setInt("_ID_VENDEDOR", id_vendedor);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("ID_CLIENTE"));
                cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                cliente.setRuc(rs.getString("RUC"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setTelefono(rs.getString("TELEFONO_CLIENTE"));
                cliente.setCorreo(rs.getString("CORREO_CLIENTE"));
                cliente.getProvincia().setId(rs.getInt("ID_PROVINCIA"));
                cliente.getProvincia().setNombre(rs.getString("NOMBRE_PROVINCIA"));
                cliente.getProvincia().getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                cliente.getProvincia().getDepartamento().setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                clientes.add(cliente);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return clientes;
    }

    @Override
    public int eliminar(int id_cliente_vendedor) {
        int resultado = 0;
        try{ 
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_CLIENTE_VENDEDOR(?)} ");
            cs.setInt("_ID_CLIENTE_VENDEDOR", id_cliente_vendedor);
            resultado = cs.executeUpdate();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    @Override
    public int eliminarConIds(int id_cliente, int id_vendedor) {
        int resultado = 0;
        try{ 
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_CLIENTE_VENDEDOR_CON_CLIENTE_Y_VENDEDOR(?,?)} ");
            cs.setInt("_ID_CLIENTE", id_cliente);
            cs.setInt("_ID_VENDEDOR", id_vendedor);
            resultado = cs.executeUpdate();
        }catch (Exception ex) {
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
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }

    @Override
    public ArrayList<Cliente_Vendedor> listarPorCliente(int id_cliente) {
        ArrayList<Cliente_Vendedor> clientesVendedor = new ArrayList<Cliente_Vendedor>();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call LISTAR_CLIENTE_VENDEDOR_POR_CLIENTE(?)}");
            cs.setInt("_ID_CLIENTE", id_cliente);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Cliente_Vendedor clienteVendedor = new Cliente_Vendedor();
                clienteVendedor.setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                clienteVendedor.getCliente().setId(rs.getInt("ID_CLIENTE"));
                clienteVendedor.getVendedor().setId(rs.getInt("ID_VENDEDOR"));
                clientesVendedor.add(clienteVendedor);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return clientesVendedor;
    }

    @Override
    public Cliente_Vendedor buscarRelacion(Cliente cliente, Vendedor vendedor) {
        Cliente_Vendedor clienteVendedor = new Cliente_Vendedor();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_RELACION_CLIENTE_VENDEDOR(?,?)}");
            cs.setInt("_ID_CLIENTE", cliente.getId());
            cs.setInt("_ID_VENDEDOR", vendedor.getId());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                clienteVendedor.setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                clienteVendedor.getCliente().setId(rs.getInt("ID_CLIENTE"));
                clienteVendedor.getVendedor().setId(rs.getInt("ID_VENDEDOR"));
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        return clienteVendedor;
    }

    @Override
    public Cliente_Vendedor encontrarPorId(int id) {
        Cliente_Vendedor clienteVendedor = new Cliente_Vendedor();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call BUSCAR_CLIENTE_VENDEDOR(?)}");
            cs.setInt("_ID_CLIENTE_VENDEDOR",id);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                clienteVendedor.setId_cliente_vendedor(rs.getInt("ID_CLIENTE_VENDEDOR"));
                clienteVendedor.getCliente().setId(rs.getInt("ID_CLIENTE"));
                clienteVendedor.getVendedor().setId(rs.getInt("ID_VENDEDOR"));
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return clienteVendedor;
    }

    @Override
    public Cliente encontrarClientePorClienteVendedor(int id_cliente_vendedor) {
        Cliente cliente = new Cliente();
        try{
            con = DriverManager.getConnection(DBManager.url,DBManager.user,DBManager.password);
            cs = con.prepareCall("{call ENCONTRAR_CLIENTE_POR_CLIENTE_VENDEDOR(?)}");
            cs.setInt("_ID_CLIENTE_VENDEDOR",id_cliente_vendedor);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                cliente.setId(rs.getInt("ID_CLIENTE"));
                cliente.setRuc(rs.getString("RUC"));
                cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setTelefono(rs.getString("TELEFONO_CLIENTE"));
                cliente.getProvincia().setId(rs.getInt("ID_PROVINCIA"));
                cliente.setCorreo(rs.getString("CORREO_CLIENTE"));
                cliente.setPuntaje(rs.getInt("PUNTOS"));
                cliente.setActive(rs.getBoolean("ACTIVE"));
            }
            
            
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return cliente;
    }

    @Override
    public Vendedor encontrarVendedorPorClienteVendedor(int id_cliente_vendedor) {
        Vendedor vendedor = new Vendedor(true);
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_EMPLEADO_POR_CLIENTE_VENDEDOR(?)}");
            cs.setInt("_ID_CLIENTE_VENDEDOR", id_cliente_vendedor);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                vendedor.setId(rs.getInt("ID_EMPLEADO"));
                vendedor.setId_vendedor(vendedor.getId());
                vendedor.setDni(rs.getString("DNI_EMPLEADO"));
                vendedor.setNombre(rs.getString("NOMBRE_EMPLEADO"));
                vendedor.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                vendedor.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                vendedor.setEstadoCivil(EstadoCivil.valueOf(rs.getString("ESTADO_CIVIL")));
                vendedor.setTelefono(rs.getString("TELEFONO_EMPLEADO"));
                vendedor.setCorreo(rs.getString("CORREO_EMPLEADO"));
                vendedor.setFoto(rs.getBytes("FOTO"));
                java.util.Date fechaNacimiento = new java.util.Date(rs.getDate("FECHA_NACIMIENTO").getTime());
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaAux = formatoFecha.format(fechaNacimiento);
                vendedor.setFechaNacimiento(formatoFecha.parse(fechaAux));
                vendedor.getArea().setId(rs.getInt("ID_AREA"));
                //vendedor.getArea().setNombre(rs.getString("NOMBRE_AREA"));
                vendedor.getArea().setCodigo(rs.getInt("CODIGO_AREA"));
                vendedor.getUsuario().setNombre(rs.getString("NOMBRE_USUARIO"));
                vendedor.getUsuario().setContraseña(rs.getString("CONTRASEÑA"));
                vendedor.setSueldo(rs.getFloat("SUELDO"));
                java.util.Date fechaIngreso = new java.util.Date(rs.getDate("FECHA_INGRESO").getTime());
                fechaAux = formatoFecha.format(fechaIngreso);
                vendedor.setFechaIngreso(formatoFecha.parse(fechaAux));
                vendedor.setActive(rs.getBoolean("ACTIVE"));
            }
            else
                vendedor = null;
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return vendedor;
    }

    @Override
    public ArrayList<Cliente> listarClientesPorVendedorRuc(int id_vendedor, String ruc) {
       ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_CLIENTE_VENDEDOR_POR_RUC(?,?)}");
            cs.setInt("_ID_VENDEDOR", id_vendedor);
            cs.setString("_RUC", ruc);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("ID_CLIENTE"));
                cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                cliente.setRuc(rs.getString("RUC"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setTelefono(rs.getString("TELEFONO_CLIENTE"));
                cliente.setCorreo(rs.getString("CORREO_CLIENTE"));
                cliente.setPuntaje(rs.getInt("PUNTOS"));
                cliente.getProvincia().setId(rs.getInt("ID_PROVINCIA"));
                cliente.getProvincia().setNombre(rs.getString("NOMBRE_PROVINCIA"));
                cliente.getProvincia().getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                cliente.getProvincia().getDepartamento().setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                clientes.add(cliente);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return clientes;
    }

    @Override
    public ArrayList<Cliente> listarClientesPorVendedorNombre(int id_vendedor, String nombre) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_CLIENTE_VENDEDOR_POR_NOMBRE(?,?)}");
            cs.setInt("_ID_VENDEDOR", id_vendedor);
            cs.setString("_RAZON_SOCIAL", nombre);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("ID_CLIENTE"));
                cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                cliente.setRuc(rs.getString("RUC"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setTelefono(rs.getString("TELEFONO_CLIENTE"));
                cliente.setCorreo(rs.getString("CORREO_CLIENTE"));
                cliente.setPuntaje(rs.getInt("PUNTOS"));
                cliente.getProvincia().setId(rs.getInt("ID_PROVINCIA"));
                cliente.getProvincia().setNombre(rs.getString("NOMBRE_PROVINCIA"));
                cliente.getProvincia().getDepartamento().setId(rs.getInt("ID_DEPARTAMENTO"));
                cliente.getProvincia().getDepartamento().setNombre(rs.getString("NOMBRE_DEPARTAMENTO"));
                clientes.add(cliente);
            }

        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return clientes;
    }

    @Override
    public int validarEliminacion(int id_cliente, int id_vendedor) {
        int resultado = 0;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call VALIDAR_ELIMINACION(?,?)}");
            cs.setInt("_ID_CLIENTE", id_cliente);
            cs.setInt("_ID_VENDEDOR", id_vendedor);
            ResultSet rs = cs.executeQuery();
            if (rs.next())
                return 0;
            else
                return 1;
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }
    
    
}

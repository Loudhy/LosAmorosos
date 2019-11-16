/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import config.DBController;
import config.DBManager;
import dao.ProductoDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Presentacion;
import model.Producto;

/**
 *
 * @author UsuarioA
 */
public class ProductoMySQL implements ProductoDAO{
    Connection con = null;
    Statement st = null;
    CallableStatement cs = null;
    @Override
    public int insertar(Producto producto) {
        int resultado = 0;
        try{  
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PRODUCTO(?,?,?,?,?,?,?,?)} ");
            cs.setString("_NOMBRE_PRODUCTO",producto.getNombre());
            cs.setInt("_STOCK_EMPRESA", producto.getStockEmpresa());
            cs.setInt("_STOCK_VENDEDOR", producto.getStockVendedor());
            cs.setFloat("_PRECIO_UNITARIO",producto.getPrecioUnitario());
            cs.setString("_DESCRIPCION",producto.getDescripcion());
            cs.setBytes("_FOTO", producto.getFoto());
            cs.setBoolean("_ACTIVE", true);
            resultado = cs.executeUpdate();
            producto.setId(cs.getInt("_ID_PRODUCTO"));
            for(Presentacion m:producto.getPresentaciones()){
                cs = con.prepareCall("{call INSERTAR_PRESENTACION(?,?,?,?)} ");
                cs.setInt("_ID_PRODUCTO", producto.getId());
                cs.setString("_DISEÑO",m.getDiseño());
                cs.setBoolean("_ACTIVE", true);
                resultado=cs.executeUpdate();
                m.setId(cs.getInt("_ID_PRESENTACION"));
                m.setId_producto(producto.getId());
                DBController.insertarPresentacion(m);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }

    @Override
    public int actualizar(Producto producto) {
        int resultado = 0;
        try{  
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PRODUCTO(?,?,?,?,?,?,?)} ");
            cs.setInt("_ID_PRODUCTO", producto.getId());
            cs.setString("_NOMBRE_PRODUCTO",producto.getNombre());
            cs.setInt("_STOCK_EMPRESA", producto.getStockEmpresa());
            cs.setInt("_STOCK_VENDEDOR", producto.getStockVendedor());
            cs.setFloat("_PRECIO_UNITARIO",producto.getPrecioUnitario());
            cs.setBytes("_FOTO",producto.getFoto());
            cs.setString("_DESCRIPCION",producto.getDescripcion());
            resultado = cs.executeUpdate();
            for(Presentacion m:producto.getPresentaciones()){
                DBController.actualizarPresentacion(m);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id_producto) {
        int resultado = 0;
        try{  
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PRODUCTO(?) ");
            cs.setInt("_ID_PRODUCTO", id_producto);
            resultado = cs.executeUpdate();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }

    @Override
    public ArrayList<Producto> listarDisponibles() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PRODUCTO_DISPONIBLE()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE_PRODUCTO"));
                producto.setPrecioUnitario(rs.getFloat("PRECIO_UNITARIO"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setStockEmpresa(rs.getInt("STOCK_EMPRESA"));
                producto.setStockVendedor(rs.getInt("STOCK_VENDEDOR"));
                producto.setActive(rs.getBoolean("ACTIVE"));
                productos.add(producto);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return productos;
    }

    @Override
    public Producto buscarProductoPorNombre(String nombre) {
        Producto producto = new Producto();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_PRODUCTO_POR_NOMBRE(?)}");
            cs.setString("_NOMBRE_PRODUCTO", nombre);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                producto.setId(rs.getInt("ID_PRODUCTO"));
                producto.setNombre(nombre);
                producto.setPrecioUnitario(rs.getFloat("PRECIO_UNITARIO"));
                producto.setStockEmpresa(rs.getInt("STOCK_EMPRESA"));
                producto.setStockVendedor(rs.getInt("STOCK_VENDEDOR"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setActive(true);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return producto;
    }

    @Override
    public Producto encontrarPorId(int id) {
        Producto producto = null;
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_PRODUCTO_POR_ID(?)}");
            cs.setInt("_ID_PRODUCTO", id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                producto = new Producto();
                producto.setId(id);
                producto.setNombre(rs.getString("NOMBRE_PRODUCTO"));
                producto.setPrecioUnitario(rs.getFloat("PRECIO_UNITARIO"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setStockEmpresa(rs.getInt("STOCK_EMPRESA"));
                producto.setStockVendedor(rs.getInt("STOCK_VENDEDOR"));
                producto.setActive(rs.getBoolean("ACTIVE"));
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        
        return producto;
    }

    @Override
    public ArrayList<Producto> listar() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PRODUCTOS()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE_PRODUCTO"));
                producto.setPrecioUnitario(rs.getFloat("PRECIO_UNITARIO"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setStockEmpresa(rs.getInt("STOCK_EMPRESA"));
                producto.setStockVendedor(rs.getInt("STOCK_VENDEDOR"));
                producto.setFoto(rs.getBytes("FOTO"));
                producto.setActive(rs.getBoolean("ACTIVE"));
                productos.add(producto);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return productos;
    }
    
    @Override
    public ArrayList<Producto> listarPorNombre(String nombre){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PRODUCTOS_POR_NOMBRE(?)}");
            cs.setString("_NOMBRE_PRODUCTO", nombre);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE_PRODUCTO"));
                producto.setPrecioUnitario(rs.getFloat("PRECIO_UNITARIO"));
                producto.setStockEmpresa(rs.getInt("STOCK_EMPRESA"));
                producto.setStockVendedor(rs.getInt("STOCK_VENDEDOR"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setFoto(rs.getBytes("FOTO"));
                producto.setActive(true);
                productos.add(producto);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return productos;
    }

    
    
}



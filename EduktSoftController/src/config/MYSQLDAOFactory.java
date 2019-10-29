/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import dao.AreaDAO;
import dao.ClienteDAO;
import dao.ClienteVendedorDAO;
import dao.DatosGeneralesDAO;
import dao.DepartamentoDAO;
import dao.EmpleadoDAO;
import dao.MetaMensualDAO;
import dao.ObjetivoVendedorDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dao.ProvinciaDAO;
import dao.SolicitudDAO;
import dao.UsuarioDAO;
import mysql.AreaMySQL;
import mysql.ClienteMySQL;
import mysql.ClienteVendedorMySQL;
import mysql.DatosGeneralesMySQL;
import mysql.DepartamentoMySQL;
import mysql.EmpleadoMySQL;
import mysql.MetaMensualMySQL;
import mysql.ObjetivoVendedorMySQL;
import mysql.PedidoMySQL;
import mysql.ProductoMySQL;
import mysql.ProvinciaMySQL;
import mysql.SolicitudMySQL;
import mysql.UsuarioMySQL;

/**
 *
 * @author UsuarioA
 */
public class MYSQLDAOFactory extends DAOFactory {
    public MYSQLDAOFactory(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
           System.out.println(ex.getMessage());
        }
    }

    @Override
    public AreaDAO getAreaDAO() {
        return new AreaMySQL();
    }
    
    public UsuarioDAO getUsuarioDAO(){
        return new UsuarioMySQL();
    }

    public EmpleadoDAO getEmpleadoDAO(){
        return new EmpleadoMySQL();
    }

    @Override
    public DatosGeneralesDAO getDatosGeneralesDAO() {
        return new DatosGeneralesMySQL();
    }

    @Override
    public MetaMensualDAO getMetaMensualDAO() {
        return new MetaMensualMySQL();
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new DepartamentoMySQL(); 
    }
    
    public ProvinciaDAO getProvinciaDAO(){
        return new ProvinciaMySQL();
    }
    
    public ProductoDAO getProductoDAO(){
        return new ProductoMySQL();
    }

    @Override
    public ObjetivoVendedorDAO getObjetivoVendedorDAO() {
        return new ObjetivoVendedorMySQL();
    }


    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteMySQL();
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        return new PedidoMySQL();
    }

    @Override
    public ClienteVendedorDAO getClienteVendedorDAO() {
        return new ClienteVendedorMySQL();
    }

    @Override
    public SolicitudDAO getSolicitudDAO() {
        return new SolicitudMySQL();
    }
}

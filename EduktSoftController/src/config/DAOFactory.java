/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import dao.AreaDAO;
import dao.DatosGeneralesDAO;
import dao.DepartamentoDAO;
import dao.EmpleadoDAO;
import dao.MetaMensualDAO;
import dao.ObjetivoVendedorDAO;
import dao.ProductoDAO;
import dao.ProvinciaDAO;
import dao.UsuarioDAO;
import dao.VendedorDAO;

/**
 *
 * @author UsuarioA
 */
public abstract class DAOFactory {
    public abstract AreaDAO getAreaDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract EmpleadoDAO getEmpleadoDAO();
    public abstract DatosGeneralesDAO getDatosGeneralesDAO();
    public abstract MetaMensualDAO getMetaMensualDAO();
    public abstract DepartamentoDAO getDepartamentoDAO();
    public abstract ProvinciaDAO getProvinciaDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract ObjetivoVendedorDAO getObjetivoVendedorDAO();
    public abstract VendedorDAO getVendedorDAO();
    
    public static DAOFactory getDAOFactory(){
        if (DBManager.url.contains("mysql"))
            return new MYSQLDAOFactory();
        else
            return null;
    }

}

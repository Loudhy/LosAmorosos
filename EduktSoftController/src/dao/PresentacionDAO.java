/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Presentacion;

/**
 *
 * @author alulab14
 */
public interface PresentacionDAO extends CrudDAO<Presentacion>{
   ArrayList<Presentacion> listarPresentaciones(int id_producto);
}

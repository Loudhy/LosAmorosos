/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Area;




/**
 *
 * @author UsuarioA
 */
public interface AreaDAO {
    int insertar(Area area);
    int actualizar(Area area);
    int eliminar(int id_area);
    ArrayList<Area> listar();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.MetaMensual;

/**
 *
 * @author UsuarioA
 */
public interface MetaMensualDAO {
    int insertar(MetaMensual metaMensual);
    int actualizar(MetaMensual metaMensual);
    int eliminar(int id_meta_mensual);
    ArrayList<MetaMensual> listar();
}

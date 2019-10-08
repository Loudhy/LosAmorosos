/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Departamento;
import model.Provincia;

/**
 *
 * @author UsuarioA
 */
public interface ProvinciaDAO {
    int insertar(Provincia provincia);
    int actualizar(Provincia provincia);
    int eliminar(int id_provincia);
    ArrayList<Provincia> listarPorDepartamento(Departamento departamento);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Departamento;

/**
 *
 * @author UsuarioA
 */
public interface DepartamentoDAO {
    int insertar(Departamento departamento);
    int actualizar(Departamento departamento);
    int eliminar(int id_departamento);
    ArrayList<Departamento> listar();
}

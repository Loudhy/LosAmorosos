/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.ArrayList;

/**
 *
 * @author alulab14
 */
public interface CrudDAO<T> {
    int insertar(T objeto);
    T encontrarPorId(int id);
    int actualizar(T objeto);
    int eliminar(int id);
    ArrayList<T> listar();
}

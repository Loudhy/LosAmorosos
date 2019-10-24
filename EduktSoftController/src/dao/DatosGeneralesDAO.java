/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.Date;
import model.DatosGenerales;

/**
 *
 * @author UsuarioA
 */
public interface DatosGeneralesDAO extends CrudDAO<DatosGenerales> {
    DatosGenerales encontrarPorFecha(Date fecha);
}

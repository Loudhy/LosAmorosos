/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import config.DBController;
import model.Usuario;

/**
 *
 * @author alulab14
 */
public class PantallaService {
    
    public void mostrarFormulario(Usuario user){
        String nombreArea;
        nombreArea=DBController.buscarAreaPorUsuario(user);
        switch(nombreArea){
            case "Comercial":
                //retornar formulario comercial
                break;
            case "Facturacion":
                //retornar formulario facturacion
                break;
            case "Vendedor":
                //retornar formulario vendedor
                break;  
            case "Logistica":
                //retornar formulario logistica
                break;
            case "Administracion":
                //retonar formulario administracion
                break;        
        }
    }
    
    
}

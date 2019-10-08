/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eduktsoft;
import config.DBController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Area;
import model.Empleado;
import static model.EstadoCivil.Soltero;
import model.Usuario;

/**
 *
 * @author UsuarioA
 */
public class EduktSoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // PROBANDO AREAS
        /*Area area1 = new Area("ADMINISTRADOR",1);
        DBController.insertarArea(area1);
        area1.setNombre("COMERCIAL");
        area1.setCodigo(2);
        DBController.actualizarArea(area1);
        DBController.eliminarArea(area1.getId());
        
        Area area2 = new Area("FACTURADOR",1);
        DBController.insertarArea(area2);
        ArrayList<Area> areas = new ArrayList<Area>();
        areas = DBController.listarAreas();
        
        for (Area aux :areas){
            System.out.println(aux.getNombre());
        }*/
        
        //PROBANDO EMPLEADOS
        /*Area area1 = new Area("ADMINISTRADOR",1);
        DBController.insertarArea(area1);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Empleado empleado1 = new Empleado("76272879","Juan Diego","Villegas",
                formatoFecha.parse("1999-01-29"),"2852878","caricato665@gmail.com",Soltero,2500,area1,
                formatoFecha.parse("2016-09-16"));
    
        DBController.insertarEmpleado(empleado1);
        
        Usuario usuario1 = new Usuario("caricato","abcd1234",empleado1);
        DBController.insertarUsuario(usuario1);*/
        
        Area area1 = new Area("ADMINISTRADOR",1);
        area1.setId(1);
        ArrayList<Empleado> empleados;
        empleados = DBController.listarEmpleadosPorArea(area1);
        for (Empleado aux : empleados){
            System.out.println(aux.getNombre());
            System.out.println(aux.getEstadoCivil().toString());
            System.out.println(aux.getFechaIngreso().toString());
            System.out.println(aux.getArea().getNombre());
        }
    }
    
}
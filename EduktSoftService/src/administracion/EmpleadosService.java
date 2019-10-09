package administracion;

import config.DBController;
import java.util.ArrayList;
import model.Area;
import model.Empleado;

public class EmpleadosService {
    String filtro = "Default";    
    public ArrayList<Empleado> listarEmpleados(String filtro, Area area){
        ArrayList<Empleado> empleados = new ArrayList<>();
        if (filtro == "Default"){
            empleados = DBController.listarEmpleados();            
        }
        if (filtro == "Area"){
            empleados = DBController.listarEmpleadosPorArea(area);            
        }
        /*if (filtro == "Nombre"){
            empleados = DBController
        }*/
        return empleados;
    }
    
    public int cantidadActivos(){
        ArrayList<Empleado> empleados = DBController.listarEmpleados();        
        return empleados.size();
    }
    public int editarEmpleado(Empleado empleado){
        int resultado = 0;
        resultado = DBController.actualizarEmpleado(empleado);
        return resultado;        
    }
}

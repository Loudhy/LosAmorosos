/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercial;

import config.DBController;
import java.util.ArrayList;
import model.Area;
import model.Empleado;
import model.Vendedor;

/**
 *
 * @author alulab14
 */
public class listarVendedores {
    public listarVendedores(){
        
    }
    
    public ArrayList<Vendedor> listarEmpleadosDeAreaVentas(){
        Area area = DBController.buscarAreaPorId(3);
        ArrayList<Empleado> empleados = DBController.listarEmpleadosPorArea(area);
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
        for(Empleado empleado: empleados){
            Vendedor vendedor = new Vendedor(empleado.getDni(),empleado.getNombre(),empleado.getApellidoPaterno(),
            empleado.getApellidoMaterno(),empleado.getFechaNacimiento(),empleado.getTelefono(),empleado.getCorreo(),empleado.getEstadoCivil(),empleado.getSueldo(), area, empleado.getFechaIngreso());
            vendedor.setUsuario(empleado.getUsuario());
            vendedor.setFoto(empleado.getFoto());
            vendedor.setArea(area);
            vendedores.add(vendedor);
        }
        
        return vendedores;
    }
}

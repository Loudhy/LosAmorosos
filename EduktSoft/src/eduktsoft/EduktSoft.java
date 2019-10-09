/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eduktsoft;
import administracion.PasswordService;
import config.DBController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Area;
import model.Cliente;
import model.Cliente_Vendedor;
import model.Departamento;
import model.Empleado;
import static model.EstadoCivil.Soltero;
import static model.EstadoLineaPedido.Pendiente;
import model.EstadoPedido;
import model.LineaPedido;
import model.Pedido;
import model.Producto;
import model.Provincia;
import model.Usuario;
import model.Vendedor;

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
        }
        */
        //PROBANDO EMPLEADOS
        /*Area area1 = new Area("ADMINISTRADOR",1);
        DBController.insertarArea(area1);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Empleado empleado1 = new Empleado("76272879","Juan Diego","Villegas",
                formatoFecha.parse("1999-01-29"),"2852878","caricato665@gmail.com",Soltero,2500,area1,
                formatoFecha.parse("2016-09-16"));
    
        DBController.insertarEmpleado(empleado1);
        
        Usuario usuario1 = new Usuario("caricato","abcd1234",empleado1);
        DBController.insertarUsuario(usuario1);
        Area area2 = new Area("COMERCIAL",2);
        DBController.insertarArea(area1);
        DBController.insertarArea(area2);
        Departamento departamento1 = new Departamento("LIMA");
        DBController.insertarDepartamento(departamento1);
        Provincia provincia1 = new Provincia("BARRANCA",departamento1);
        DBController.insertarProvincia(provincia1);
        
        Cliente cliente1 = new Cliente("76272879","Librerias GAA","2839255","Av Lima 231","libreriasGaa@gmail.com",provincia1);
        DBController.insertarCliente(cliente1);
        
        Producto producto1 = new Producto(100,15,"Rompecabezas Buzz","Rompecabezas para niÃ±os");
        Producto producto2 = new Producto(200,20,"Rompecabezas Woody","Rompecabezas para niÃ±os");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        
        DBController.insertarProducto(producto1);
        DBController.insertarProducto(producto2);
        
        LineaPedido linea1 = new LineaPedido(producto1,50,Pendiente,format1.parse("2019-10-08"));
        LineaPedido linea2 = new LineaPedido(producto2,70,Pendiente,format1.parse("2019-10-08"));
        Pedido pedido1 = new Pedido();
        pedido1.insertarLineaPedido(linea2);
        pedido1.insertarLineaPedido(linea1);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Vendedor vendedor1 = new Vendedor("76272879","Juan Diego","Villegas",
                formatoFecha.parse("1999-01-29"),"2852878","caricato665@gmail.com",Soltero,2500,area2,
                formatoFecha.parse("2016-09-16"));
        
        DBController.insertarVendedor(vendedor1);
        Cliente_Vendedor relacion1 = new Cliente_Vendedor(cliente1,vendedor1);
        DBController.insertarClienteVendedor(relacion1);
        pedido1.setCliente_vendedor(relacion1);
        pedido1.setFechaRegistro(format1.parse("2019-10-08"));
        pedido1.setEstadoPedido(EstadoPedido.Pendiente);
        DBController.insertarPedido(pedido1);
        
        
        ArrayList<Pedido> pedidos = DBController.listarPedidosPorCliente(cliente1);
        for (Pedido aux: pedidos){
            String nombre = aux.getCliente_vendedor().getVendedor().getNombre();
            String apellidos = aux.getCliente_vendedor().getVendedor().getApellidos();
            String dni = aux.getCliente_vendedor().getVendedor().getDni();
            System.out.println(nombre+" "+apellidos+" "+dni);
        }
        */
        Area area1 = new Area("ADMINISTRADOR",1);
        DBController.insertarArea(area1);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Empleado empleado1 = new Empleado("76272879","Juan Diego","Villegas",
                formatoFecha.parse("1999-01-29"),"2852878","a2016731@pucp.edu.pe",Soltero,2500,area1,
                formatoFecha.parse("2016-09-16"));
    
        DBController.insertarEmpleado(empleado1);
        
        PasswordService password = new PasswordService();
        password.enviarCorreo("a2016731@pucp.edu.pe");
    }
    
}
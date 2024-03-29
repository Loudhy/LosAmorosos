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
import model.Solicitud;
import model.LineaSolicitud;
import reportes.VentasService;
import model.EstadoSolicitud;
import java.util.Collections;
import java.util.Comparator;
import model.Area;
import model.EstadoCivil;
/**
 *
 * @author UsuarioA
 */
public class EduktSoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        //Probando productos----Imp 2
        Producto prod1 = new Producto(50,15.3f,"Aros apilables","Juego didactico");
        Producto prod2 = new Producto(30,20.6f,"Rompecabeza 30 fichas Gusano","Rompecabeza");
        Producto prod3 = new Producto(40,18.9f,"Tren de madera","Juego didactico");
        DBController.insertarProducto(prod1);
        System.out.println("Producto insertado correctamente");
        DBController.insertarProducto(prod2);
        System.out.println("Producto insertado correctamente");
        DBController.insertarProducto(prod3);
        System.out.println("Producto insertado correctamente");
          
        //Probando lista de productos ----- Imp 3
        //Algoritmo para listar los productos acumulados en la interfaz
        //de Logistica
        Departamento departamento1 = new Departamento("LIMA");
        DBController.insertarDepartamento(departamento1);
        Provincia provincia1 = new Provincia("BARRANCA",departamento1);
        DBController.insertarProvincia(provincia1);
        
        Cliente cliente1 = new Cliente("76272879","Librerias GAA","2839255","Av Lima 231","libreriasGaa@gmail.com",provincia1);
        DBController.insertarCliente(cliente1);
        
        Producto producto1 = new Producto(100,15,"Rompecabezas Buzz","Rompecabezas para niÃ±os");
        Producto producto2 = new Producto(200,20,"Rompecabezas Woody","Rompecabezas para niÃ±os");
        DBController.insertarProducto(producto1);
        DBController.insertarProducto(producto2);
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        LineaPedido linea1 = new LineaPedido(producto1,50,Pendiente,format1.parse("2019-10-08"));
        LineaPedido linea2 = new LineaPedido(producto2,70,Pendiente,format1.parse("2019-10-08"));
        Pedido pedido1 = new Pedido();
        pedido1.insertarLineaPedido(linea2);
        pedido1.insertarLineaPedido(linea1);
        Area area2 = new Area("COMERCIAL",2);
        DBController.insertarArea(area2);
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
        
        Pedido pedido2 = new Pedido();
        pedido2.insertarLineaPedido(linea1);
        pedido2.insertarLineaPedido(linea2);
        pedido2.setCliente_vendedor(relacion1);
        pedido2.setFechaRegistro(format1.parse("2019-10-08"));
        pedido2.setEstadoPedido(EstadoPedido.Pendiente);
        DBController.insertarPedido(pedido2);
        
        LineaSolicitud ls1 = new LineaSolicitud();
        ls1.setCantidad(50);
        ls1.setLineaPedido(linea1);
        ls1.setEstadoSolicitud(Pendiente);
        LineaSolicitud ls2 = new LineaSolicitud();
        ls2.setCantidad(70);
        ls2.setLineaPedido(linea2);
        ls2.setEstadoSolicitud(Pendiente);
        Solicitud sol1 = new Solicitud();
        sol1.getLineasSolicitud().add(ls1);
        sol1.getLineasSolicitud().add(ls2);
        sol1.setFechaRegistro(formatoFecha.parse("2015-05-26"));
        sol1.setPedido(pedido1);
        sol1.setEstadoSolicitud(EstadoSolicitud.Pendiente);
        
        Area area1 = new Area("ADMINISTRACION",1);
        Area area3 = new Area("LOGISTICA",5);
        DBController.insertarArea(area1);
        DBController.insertarArea(area3);
        Empleado empleado1 = new Empleado("76272879","Juan Diego","Villegas",
                formatoFecha.parse("1999-01-29"),"2852878","a20151593@pucp.edu.pe",Soltero,2500,area1,
                formatoFecha.parse("2016-09-16"));
        Empleado empleado2 = new Empleado("15441","ASD FGD","SACFVFAS",
                formatoFecha.parse("1999-01-29"),"256","caricato665@gmail.com",Soltero,2500,area3,
                formatoFecha.parse("2016-09-16"));
        DBController.insertarEmpleado(empleado2);
        DBController.insertarEmpleado(empleado1);
        sol1.setLogistico(empleado2);
        sol1.setFacturador(empleado1);
        DBController.insertarSolicitud(sol1);
        
        LineaSolicitud ls3 = new LineaSolicitud();
        ls3.setCantidad(50);
        ls3.setLineaPedido(linea1);
        ls3.setEstadoSolicitud(Pendiente);
        LineaSolicitud ls4 = new LineaSolicitud();
        ls4.setCantidad(70);
        ls4.setLineaPedido(linea2);
        ls4.setEstadoSolicitud(Pendiente);
        Solicitud sol2 = new Solicitud();
        sol2.getLineasSolicitud().add(ls3);
        sol2.getLineasSolicitud().add(ls4);
        sol2.setFechaRegistro(formatoFecha.parse("2015-05-26"));
        sol2.setPedido(pedido2);
        sol2.setEstadoSolicitud(EstadoSolicitud.Pendiente);
        sol2.setLogistico(empleado2);
        sol2.setFacturador(empleado1);
        DBController.insertarSolicitud(sol2);
        
        
    
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        ArrayList<LineaPedido> lineasPedido = new ArrayList<>();
        //Crear un metodo con listar Solicitudes
        solicitudes = DBController.listarSolicitudes();
        for(Solicitud aux : solicitudes){
            ArrayList<LineaSolicitud> lineas = new ArrayList<>();
            lineas = aux.getLineasSolicitud();
            for(LineaSolicitud m : lineas){
                int flag = 0;
                for(LineaPedido lin : lineasPedido){
                    if(lin.getProducto().getNombre().equals(m.getLineaPedido().getProducto().getNombre())){
                        lin.setCantidad(lin.getCantidad()+ 
                              m.getLineaPedido().getCantidad());
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0){
                    lineasPedido.add(m.getLineaPedido());
                }
            }      
        }
        for(LineaPedido lin : lineasPedido){
            String nombre;
            int cant;
            cant = lin.getCantidad();
            nombre = lin.getProducto().getNombre();
            System.out.println(nombre);
            System.out.println(cant);
            System.out.println("---"); 
        }

        //Probando listado Card Views Productos----Imp 6
        //Asumiendo que el procedimiento almacenado incluye 
        //solo los productos activos
        ArrayList<Producto> productos;
        productos = DBController.listarProductosDisponibles();
        //Ordenar productos segun su nombre
        Collections.sort(productos, new Comparator<Producto>(){
            @Override
            public int compare(Producto p1, Producto p2) {
                return (p1.getNombre()).compareTo((p2.getNombre()));
            }
        });
        for(Producto m : productos){
            System.out.println(m);
        }
        // Para poder filtrar por nombre y codigo, crear procedimientos 
        // almacenados.

        //Probando registrar un cliente ----Imp 7
        Provincia prov = new Provincia();
        prov.setId(1);
        Cliente cli1 = new Cliente("1564789","Chupetin Trujillo SAC",
                "986254639","Mz P2 - Gamarra","chupTruj@gmail.com",
                prov);
        Cliente cli2 = new Cliente("1651512","Cachina EIRL",
                "5326598","Mz L2 - Materiales","cachina52@gmail.com",
                prov);
        DBController.insertarCliente(cli1);
        System.out.println("Cliente registrado correctamente");
        DBController.insertarCliente(cli2);
        System.out.println("Cliente registrado correctamente");
        //Crear el procedimiento almacenado de editar los clientes.


        //Probando devolver un area a un empleado --- Imp 1
                
        EstadoCivil ec = null;       
        Empleado emp1 = new Empleado("77208434","Gianmarco","Fernandez Murga",
                        formatoFecha.parse("05-09-1997"),"993935124",
                        "a20140667@pucp.pe",ec.Casado,1500f,area1,formatoFecha.parse("09-10-2019"));
        DBController.insertarEmpleado(emp1);
        Usuario user1 = new Usuario("TrillMage","abcd1234",emp1);
        DBController.insertarUsuario(user1);
        String nombreArea;
        nombreArea=DBController.buscarAreaPorUsuario(user1);
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
        
        PasswordService pass = new PasswordService();
        pass.enviarCorreo("a20151593@pucp.edu.pe");
        VentasService ventas = new VentasService();
        ventas.graphPorVendedor(formatoFecha.parse("1999-01-29"), formatoFecha.parse("3000-01-29"), vendedor1);

    }
}


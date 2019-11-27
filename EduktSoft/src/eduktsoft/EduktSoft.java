/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eduktsoft;
import administracion.LogginUsuarioService;
import administracion.PasswordService;
import comercial.ReporteProductosDisponibles;
import comercial.listarVendedores;
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
import static model.EstadoLineaPedido.Solicitado;
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
import logistica.ProductosSolicitud;
import model.Area;
import model.EstadoCivil;
import model.EstadoLineaPedido;
import model.EstadoLineaSolicitud;
import model.EstadoPedido;
import model.MetaMensual;
import model.ObjetivoVendedor;
import model.Presentacion;
import ventas.MejoresProductosService;
/**
 *
 * @author UsuarioA
 */
public class EduktSoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
//        Pedido pedido = DBController.buscarPedidoPorId(27);
//        ArrayList<LineaPedido> lineasAEvaluar = DBController.listarTodasLasLineasDePedido(pedido);
//        ArrayList<LineaPedido> lineas = new ArrayList<LineaPedido>();
//        float monto = 0;
//        for (LineaPedido linea:lineasAEvaluar){
//            if(!linea.isActive()){
//                Producto producto = DBController.buscarProductoPorId(linea.getProducto().getId());
//                producto.setStockVendedor(linea.getProducto().getStockVendedor()+linea.getCantidad());
//                DBController.actualizarProducto(producto);
//                monto+=linea.getCantidad();
//                lineas.add(linea);           
//            }              
//        }
        Pedido pedido = DBController.buscarPedidoPorId(27);
        float monto = 50.0f;
        DBController.actualizarMontoPedido(pedido, pedido.getTotal()-monto);
        //Vendedor vendedor = DBController.encontrarVendedorPorClienteVendedor(1);
        //int resultado = DBController.actualizarEmpleado(vendedor);
        //ArrayList<Pedido> pedidos = DBController.listarPedidosPorEstadoDePedido(EstadoPedido.Aceptado);
//Pedido pedido = DBController.buscarPedidoPorId(5);
        //int resultado = DBController.eliminarLineasDePedido(pedido.getLineasPedido());
    //DBController.eliminarClienteVendedorConClienteYVendedor(34, 1);
        //ArrayList<Pedido> pedidos = DBController.listarPedidosPorEstadoDePedido(EstadoPedido.En_proceso);
        //ProductosSolicitud producto = new ProductosSolicitud();
        //ArrayList<ProductosSolicitud> productos2 = producto.sacarCantidadAcumuladaPorNombre("rompe");
        /*Producto producto = DBController.buscarProductoPorId(8);
        Area area = new Area();
        area.setId(3);
        ArrayList<Empleado> empleados = DBController.listarEmpleadosPorAreaPorDni(area, "7");
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1);
        vendedor.setId_vendedor(1);
        ArrayList<Pedido> pedidos;/*
        //pedidos = DBController.listarPedidosPorVendedor(vendedor);
        //pedidos = DBController.listarPedidosPorEstadoDePedido(EstadoPedido.Pendiente);
       // pedidos = DBController.listarPedidosPorVendedorPorCliente(vendedor, "El");
        //pedidos = DBController.listarPedidosPorVendedorPorClientePorEstado(vendedor, "El", EstadoPedido.En_proceso);
        //pedidos = DBController.listarPedidosPorVendedor(vendedor);
        pedidos = DBController.listarPedidos();
        /*MetaMensual meta = DBController.buscarMetaMensualActiva();
        MetaMensual meta2 = new MetaMensual();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        meta2.setFechaInicio(format1.parse("2019-11-20"));
        meta2.setFechaFin(format1.parse("2019-11-20"));
        meta2.setDescripcion("META INTENTO");
        meta2.setCantidadObjetivo(12000);
        DBController.insertarMetaMensual(meta2);
        MetaMensual meta3 = DBController.buscarMetaMensualActiva();*/
        //MetaMensual meta = DBController.buscarMetaMensualPorId(2);
        //Vendedor vendedor = new Vendedor();
        //vendedor.setId(1);
        //ObjetivoVendedor objetivo = DBController.buscarObjetivoVendedorPorVendedor(vendedor);
        //Solicitud solicitud = DBController.buscarSolicitudPorId(1);
        //ProductosSolicitud productosSoli = new ProductosSolicitud();
        //ArrayList<ProductosSolicitud> prod = productosSoli.sacarCantidadAcumuladaDeSolicitud();
        //ArrayList<Pedido> lineas = DBController.listarPedidosPorEstadoDePedido();
        /*Area area = DBController.buscarAreaPorId(3);
        Cliente cliente = DBController.buscarClientePorId(1);
        Empleado empleado1 = DBController.buscarEmpleadoPorId(1);
        Vendedor vendedor1 = new Vendedor(empleado1.getDni(), empleado1.getNombre(), empleado1.getApellidoPaterno(),empleado1.getApellidoMaterno(),empleado1.getFechaNacimiento(),empleado1.getTelefono(),empleado1.getCorreo(),empleado1.getEstadoCivil(),empleado1.getSueldo(),area,empleado1.getFechaIngreso());
        vendedor1.setId(empleado1.getId());
        Cliente_Vendedor clienteVend = DBController.buscarRelacionClienteVendedor(cliente, vendedor1);
        //Producto prod1 = new Producto(50,15.3f,"Aros Rojos","Juego didactico");
        //DBController.insertarProducto(prod1);
        /*Producto prod1 = new Producto(50,15.3f,"Aros apilables","Juego didactico");
        ArrayList<Presentacion> presentaciones = new ArrayList<Presentacion>();
        
        Presentacion pres1 = new Presentacion("Diseño1");
        presentaciones.add(pres1);
        prod1.setPresentaciones(presentaciones);
        DBController.insertarProducto(prod1);
        /*try{
            Departamento departamento1 = new Departamento();
            departamento1.setId(19);
            departamento1.setNombre("PIURA");

            Provincia provincia1 = new Provincia();
            provincia1.setId(19);
            provincia1.setDepartamento(departamento1);
            provincia1.setNombre("PIURA");

            Cliente cliente1 = DBController.buscarClientePorId(1);


            Departamento departamento2 = new Departamento();
            departamento2.setId(14);
            departamento2.setNombre("LIMA");

            Provincia provincia2 = new Provincia();
            provincia2.setId(14);
            provincia2.setDepartamento(departamento1);
            provincia2.setNombre("CAJATAMBO");
        
            Cliente cliente2 = DBController.buscarClientePorId(2);
            Producto producto1 = DBController.buscarProductoPorId(10);
            Producto producto2 = DBController.buscarProductoPorId(11);
            Producto producto3 = DBController.buscarProductoPorId(12);
            Producto producto4 = DBController.buscarProductoPorId(13);
            Producto producto5 = DBController.buscarProductoPorId(14);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            LineaPedido linea1 = new LineaPedido(producto1,30,EstadoLineaPedido.Disponible,format1.parse("2019-11-06"));
            LineaPedido linea2 = new LineaPedido(producto2,20,EstadoLineaPedido.Disponible,format1.parse("2019-11-06"));
            Pedido pedido1 = new Pedido(format1.parse("2019-11-06"));
            pedido1.insertarLineaPedido(linea2);
            pedido1.insertarLineaPedido(linea1);
            Area area = DBController.buscarAreaPorId(3);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Empleado empleado1 = new Vendedor();
            empleado1 = DBController.buscarEmpleadoPorDni("75276938");
            Vendedor vendedor1 = new Vendedor(empleado1.getDni(), empleado1.getNombre(), empleado1.getApellidoPaterno(),empleado1.getApellidoMaterno(),empleado1.getFechaNacimiento(),empleado1.getTelefono(),empleado1.getCorreo(),empleado1.getEstadoCivil(),empleado1.getSueldo(),area,empleado1.getFechaIngreso());
            vendedor1.setId(empleado1.getId());
            Cliente_Vendedor relacion1 = DBController.buscarRelacionClienteVendedor(cliente1, vendedor1);
            if (relacion1 == null){
                DBController.insertarClienteVendedor(relacion1);
            }

            pedido1.setClienteVendedor(relacion1);
            pedido1.setFechaRegistro(format1.parse("2019-10-08"));
            pedido1.setEstadoPedido(EstadoPedido.Pendiente);
            DBController.insertarPedido(pedido1);
        }catch(ParseException ex){
            System.out.println(ex.getMessage());
        }
       

        /*
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
        Departamento departamento1 = new Departamento("LIMA","123456");
        DBController.insertarDepartamento(departamento1);
        Provincia provincia1 = new Provincia("BARRANCA",departamento1,"654345");
        DBController.insertarProvincia(provincia1);
        
        Cliente cliente1 = new Cliente("76272879","Librerias GAA","2839255","Av Lima 231","libreriasGaa@gmail.com",provincia1);
        DBController.insertarCliente(cliente1);
        
        Producto producto1 = new Producto(100,15,"Rompecabezas Buzz","Rompecabezas para niÃ±os");
        Producto producto2 = new Producto(200,20,"Rompecabezas Woody","Rompecabezas para niÃ±os");
        DBController.insertarProducto(producto1);
        DBController.insertarProducto(producto2);
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        LineaPedido linea1 = new LineaPedido(producto1,50,EstadoLineaPedido.Solicitado,format1.parse("2019-10-08"));
        LineaPedido linea2 = new LineaPedido(producto2,70,EstadoLineaPedido.Solicitado,format1.parse("2019-10-08"));
        Pedido pedido1 = new Pedido();
        pedido1.insertarLineaPedido(linea2);
        pedido1.insertarLineaPedido(linea1);
        Area area2 = new Area("COMERCIAL",2);
        DBController.insertarArea(area2);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Vendedor vendedor1 = new Vendedor("76272879","Juan Diego","Villegas", "Diaz",
                formatoFecha.parse("1999-01-29"),"2852878","caricato665@gmail.com",Soltero,2500,area2,
                formatoFecha.parse("2016-09-16"));
        DBController.insertarEmpleado(vendedor1);
        Cliente_Vendedor relacion1 = new Cliente_Vendedor(cliente1,vendedor1);
        DBController.insertarClienteVendedor(relacion1);
        pedido1.setClienteVendedor(relacion1);
        pedido1.setFechaRegistro(format1.parse("2019-10-08"));
        pedido1.setEstadoPedido(EstadoPedido.Pendiente);
        DBController.insertarPedido(pedido1);
        
        Pedido pedido2 = new Pedido();
        pedido2.insertarLineaPedido(linea1);
        pedido2.insertarLineaPedido(linea2);
        pedido2.setClienteVendedor(relacion1);
        pedido2.setFechaRegistro(format1.parse("2019-10-08"));
        pedido2.setEstadoPedido(EstadoPedido.Pendiente);
        DBController.insertarPedido(pedido2);
        
        LineaSolicitud ls1 = new LineaSolicitud();
        ls1.setCantidad(50);
        ls1.setLineaPedido(linea1);
        ls1.setEstadoSolicitud(EstadoLineaSolicitud.Pendiente);
        LineaSolicitud ls2 = new LineaSolicitud();
        ls2.setCantidad(70);
        ls2.setLineaPedido(linea2);
        ls2.setEstadoSolicitud(EstadoLineaSolicitud.Pendiente);
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
        Empleado empleado1 = new Empleado("76272879","Juan Diego","Villegas", "Diaz",
                formatoFecha.parse("1999-01-29"),"2852878","a20151593@pucp.edu.pe",Soltero,2500,area1,
                formatoFecha.parse("2016-09-16"));
        Empleado empleado2 = new Empleado("15441","Gian","Guzman", "Tito",
                formatoFecha.parse("1999-01-29"),"256","caricato665@gmail.com",Soltero,2500,area3,
                formatoFecha.parse("2016-09-16"));
        DBController.insertarEmpleado(empleado2);
        DBController.insertarEmpleado(empleado1);
        DBController.insertarSolicitud(sol1);
        
        LineaSolicitud ls3 = new LineaSolicitud();
        ls3.setCantidad(50);
        ls3.setLineaPedido(linea1);
        ls3.setEstadoSolicitud(EstadoLineaSolicitud.Pendiente);
        LineaSolicitud ls4 = new LineaSolicitud();
        ls4.setCantidad(70);
        ls4.setLineaPedido(linea2);
        ls4.setEstadoSolicitud(EstadoLineaSolicitud.Pendiente);
        Solicitud sol2 = new Solicitud();
        sol2.getLineasSolicitud().add(ls3);
        sol2.getLineasSolicitud().add(ls4);
        sol2.setFechaRegistro(formatoFecha.parse("2015-05-26"));
        sol2.setPedido(pedido2);
        sol2.setEstadoSolicitud(EstadoSolicitud.Pendiente);
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
        Empleado emp1 = new Empleado("77208434","Gianmarco","Fernandez", "Murga",
                        formatoFecha.parse("05-09-1997"),"993935124",
                        "a20140667@pucp.pe",ec.Casado,1500f,area1,formatoFecha.parse("09-10-2019"));
        DBController.insertarEmpleado(emp1);
        Usuario user1 = new Usuario("TrillMage","abcd1234");
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
        ventas.graphPorVendedor(formatoFecha.parse("1999-01-29"), formatoFecha.parse("3000-01-29"), vendedor1);*/
        
//        Departamento departamento1 = new Departamento();
//        departamento1.setId(19);
//        departamento1.setNombre("PIURA");
//
//        Provincia provincia1 = new Provincia();
//        provincia1.setId(19);
//        provincia1.setDepartamento(departamento1);
//        provincia1.setNombre("PIURA");
//        
//        Cliente cliente1 = new Cliente("8738312","DOIT'S KIDS","942124570","AV. ANDRES AVELINO CACERES 147, PIURA","DOITPIRUANO666@HOTMAIL.COM",provincia1);
//        cliente1.setId(1);
//        
//        
////        Departamento departamento2 = new Departamento();
////        departamento2.setId(14);
////        departamento2.setNombre("LIMA");
////
////        Provincia provincia2 = new Provincia();
////        provincia2.setId(14);
////        provincia2.setDepartamento(departamento1);
////        provincia2.setNombre("CAJATAMBO");
//        
////        Cliente cliente2 = new Cliente("21564852","SARAGRAF SAC","942124570","CAJATAMBO 15195","SARA.GRAF.15@HOTMAIL.COM",provincia2);
////        cliente2.setId(2);
//        
//        Producto producto1 = new Producto(180,7.4f,"ARO APILABLE","JUEGO DIDACTICO ENFOCADO A LA COORDINACION ÓCULO-MANUAL Y A LA MEJORA DE LA PRECISION DE LOS MOVIMIENTOS AL TRATAR DE INTRODUCIR LOS AROS EN SU LUGAR.");
//        producto1.setId(10);
//        Producto producto2 = new Producto(180,2.5f,"PORTA PASADOS","JUEGO DIDACTICO ENFOCADO A FORTALECER LAS HABILDIADES MOTORAS Y A LA ESTIMULACION DE LA MEMORIA VISUAL Y LA CAPACIDAD PARA REALIZAR TARES COTIDIANAS COMO EL ATADO DE ZAPATOS.");
//        producto2.setId(11);
////        Producto producto3 = new Producto(180,8.5f,"PLANTADO CON DIFICULTAD","JUEGO DIDACTICO ENFOCADO A LA COORDINACION MANO-OJO Y PARA EL APRENDIZAJE DE NOCIONES ESPACIALES DE UBICACION Y POSICION DE MANERA LUDICA.");
////        producto3.setId(12);
////        Producto producto4 = new Producto(180,10.1f,"CUBO ABC 27","JUEGO DIDACTICO ENFOCADO AL APRENDIZAJE DEL ALFABETO Y LOS NUMEROS Y PERMITE HORAS DE DIVERSION APILANDO Y CONSTRUYENDO.");
////        producto4.setId(13);
////        Producto producto5 = new Producto(180,7.2f,"XILOFONO","JUEGO DIDACTICO ENFOCADO A ESTIMULAR LA AUDICION Y PROPORCIONAR ESTIMULACION VISUAL AL ASOCIAR EL RECONOCIMIENTO DE LAS NOTAS MUSICALES A LOS COLORES");
////        producto5.setId(14);
//        
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//        LineaPedido linea1 = new LineaPedido(producto1,30,EstadoLineaPedido.Disponible,format1.parse("2019-11-06"));
//        LineaPedido linea2 = new LineaPedido(producto2,20,EstadoLineaPedido.Disponible,format1.parse("2019-11-06"));
//        Pedido pedido1 = new Pedido();
//        pedido1.getLineasPedido().add(linea2);
//        pedido1.getLineasPedido().add(linea1);
//        Area area = new Area();
//        area.setId(3);
//        area.setCodigo(3);
//        area.setNombre("VENTAS");
//
//        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
//        Vendedor vendedor1 = new Vendedor("75276938","JOSSLAM","CHAVARRY","ANANCA",
//                formatoFecha.parse("1999-10-12"),"986524712","JOSSLAM.CHAVARRY@EDUKT.COM",Soltero,1200,area,
//                formatoFecha.parse("2017-02-05"));
//        vendedor1.setId_vendedor(1);
//        vendedor1.setId(1);
//
//        Cliente_Vendedor relacion1 = new Cliente_Vendedor();
//        relacion1.setCliente(cliente1);
//        relacion1.setVendedor(vendedor1);
//        relacion1.insertarPedido(pedido1);
//        DBController.insertarClienteVendedor(relacion1);
//        
//        
//        pedido1.setClienteVendedor(relacion1);
//        pedido1.setFechaRegistro(format1.parse("2019-10-08"));
//        pedido1.setEstadoPedido(EstadoPedido.Pendiente);
//        DBController.insertarPedido(pedido1);
        
//        ArrayList<LineaSolicitud> lineas = new ArrayList<LineaSolicitud>();
//        lineas = DBController.listarLineasSolicitudesProducto("MARACAS");
//        
//        int a = 1;
        //ArrayList<Pedido> ped = new ArrayList<Pedido>();
        //ped = DBController.listarPedidosPorEstadoDePedido(EstadoPedido.Pendiente);
        //for(Pedido lin : ped){
          //  System.out.println(lin.getId());
        //}
        
        
    }
}


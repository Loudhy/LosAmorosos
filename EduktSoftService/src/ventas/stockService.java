package ventas;

import config.DBController;
import model.Area;
import model.Empleado;
import model.EstadoSolicitud;
import model.LineaPedido;
import model.LineaSolicitud;
import model.Pedido;
import model.Producto;
import model.Solicitud;

public class stockService {
    public int generarPedido(Pedido pedido){        
        int resultado = 0;
        for(LineaPedido aux : pedido.getLineasPedido()){      
            Producto producto = new Producto();                        
            producto = DBController.buscarProductoPorNombre(producto.getNombre());                        
            if(producto.getStockVendedor() < aux.getCantidad()){                
                producto.setStockVendedor(0);
            }
            else{              
                producto.setStockVendedor(producto.getStockVendedor() - aux.getCantidad());                
            }
            DBController.actualizarProducto(producto);             
        }        
        return resultado;
    }    
    
}


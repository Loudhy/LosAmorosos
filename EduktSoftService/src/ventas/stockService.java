package ventas;

import config.DBController;
import model.LineaPedido;
import model.Pedido;
import model.Producto;

public class stockService {
    public int generarPedido(Pedido pedido){        
        int resultado = 0;
        for(LineaPedido aux : pedido.getLineasPedido()){      
            Producto producto = new Producto();
            producto = DBController.buscarProductoPorNombre(producto.getNombre());
            if(producto.getStockVendedor() < aux.getCantidad()){
                //GENERAR SOLICITUD(aux);
                producto.setStockVendedor(0);
            }
            else{              
                producto.setStockVendedor(producto.getStockVendedor() - aux.getCantidad());
                producto.setStockEmpresa(producto.getStockVendedor() - aux.getCantidad());                
            }
            DBController.actualizarProducto(producto);             
        }        
        return resultado;
    }
    
}


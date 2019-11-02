/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import config.DBController;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import model.Cliente_Vendedor;
import model.Producto;

/**
 *
 * @author alulab14
 */
public class MejoresProductosService {
    public MejoresProductosService(){
        
    }
    
    public ArrayList<Producto> listarTresMejoresProductosPorCliente(int id_cliente){
        ArrayList<Cliente_Vendedor> clientesVendedor = DBController.listarClientesVendedorPorCliente(id_cliente);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Map<Integer,Integer> mapa = new HashMap<Integer,Integer>();
        for (Cliente_Vendedor aux : clientesVendedor){
           DBController.actualizarMapa(mapa, aux.getId_cliente_vendedor());
        }
        
        Map<Integer, Integer> result = mapa.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (i == 3) break;
            Producto producto = DBController.buscarProductoPorId(entry.getKey());
            productos.add(producto);
            i+=1;
        }
        
        return productos;
    }
}

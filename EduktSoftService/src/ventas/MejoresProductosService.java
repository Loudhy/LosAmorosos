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
import model.Cliente;
import model.Producto;
import model.Pedido;

/**
 *
 * @author alulab14
 */
public class MejoresProductosService {
    public MejoresProductosService(){

    }

    public ArrayList<Producto> listarTresMejoresProductosPorCliente(int id_cliente){
        Cliente cliente = DBController.buscarClientePorId(id_cliente);
        ArrayList<Pedido> pedidos = DBController.listarPedidosPorCliente(cliente);
        Map<Integer, Integer> mapa = new HashMap<Integer, Integer>();
        for(Pedido pedido: pedidos ){
            ArrayList<LineaPedido> lineasPedido = DBController.listarLineasDePedido(pedido);
            for (LineaPedido lineaPedido:lineasPedido){
                if(mapa.contains(lineaPedido.getProducto().getId())){
                  mapa.put(key,map.get(key) + lineaPedido.getCantidadPorAtender());
                }
                else{
                  mapa.put(lineaPedido.getProducto().getId()), lineaPedido.getCantidadPorAtender())
                }
            }
        }

        Map<Integer, Integer> result = mapa.entrySet().stream()
                .sorted(Map.Entry.comparingByValueComparator.reverseOrder()))
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

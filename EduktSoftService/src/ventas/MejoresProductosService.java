/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import config.DBController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import javafx.util.Pair;
import model.Cliente;
import model.LineaPedido;
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
        ArrayList<Producto> productos = new ArrayList<Producto>();
        for(Pedido pedido: pedidos ){
            ArrayList<LineaPedido> lineasPedido = DBController.listarLineasDePedido(pedido);
            for (LineaPedido lineaPedido:lineasPedido){
                if(mapa.containsKey(lineaPedido.getProducto().getId())){
                  mapa.put(lineaPedido.getProducto().getId(),mapa.get(lineaPedido.getProducto().getId()) + lineaPedido.getCantidadPorAtender());
                }
                else{
                  mapa.put(lineaPedido.getProducto().getId(), lineaPedido.getCantidadPorAtender());
                }
            }
        }
        
        List<Pair<Integer, Integer>> lista = 
                    mapa.entrySet()
                   .stream()
                   .sorted(Comparator.comparing(e -> -e.getValue()))
                   .map(e -> new Pair<>(e.getKey(), e.getValue()))
                   .collect(Collectors.toList());
        
        Collections.sort(lista, Collections.reverseOrder());
       
        for (int i = 0; i < 3; i++){
            Pair<Integer,Integer> par = lista.get(i);
            Producto producto = DBController.buscarProductoPorId(par.getKey());
            productos.add(producto);
        }
        
        return productos;
    }
}

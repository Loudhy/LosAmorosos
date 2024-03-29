package reportes;
import config.DBController;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import model.Pedido;
import model.Vendedor;

public class VentasService {
    public void graphPorVendedor(Date fechaInicio, Date fechaFin, Vendedor vendedor){
        JFreeChart grafico = null;
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidos = DBController.listarPedidosPorVendedorPorRangoDeFechas(vendedor,fechaInicio,fechaFin);
        for(Pedido aux : pedidos){
            //int dato = (int)aux.getTotal();            
            datos.addValue(aux.getTotal(),"Grafica 1","");                        
        }
        grafico = ChartFactory.createLineChart("Grafica Prueba", "Instancia de Venta", "Cantidad vendida",datos ,PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cPanel = new ChartPanel(grafico);
        JFrame informacion = new JFrame("Grafica");
        informacion.getContentPane().add(cPanel);
        informacion.pack();
        informacion.setVisible(true);
    }
}

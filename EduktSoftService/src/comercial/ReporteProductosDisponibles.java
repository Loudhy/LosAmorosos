/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercial;

import config.DBManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.jws.WebParam;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author alulab14
 */
public class ReporteProductosDisponibles {
    public ReporteProductosDisponibles(){
        
    }
    
    public byte[] devolverPdf(){
        byte[] arreglo = null;
        try{
            String file = "D:/Users/alulab14.INF.000/Documents/EjemploEnvioPDFaC/ServidorJava/LP2SoftServices/src/java/pe/edu/pucp/lp2soft/reports/PrimerReporte.jasper";
            Object aux = JRLoader.loadObjectFromFile(file);
            JasperReport reporte = 
                    (JasperReport) 
           JRLoader.loadObjectFromFile(
     ReporteProductosDisponibles.class.getResource(
     "/reportes/ReporteProductosDisponibles.jasper").getFile());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = 
        DriverManager.getConnection(
          DBManager.url, DBManager.user, DBManager.password);
            JasperPrint jp = 
                    JasperFillManager.fillReport(reporte,null,con);
            arreglo = JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return arreglo;
    }
}

package comercial;

import config.DBController;
import model.ObjetivoVendedor;

public class comisionService {
    public int actualizarObjetivos(ObjetivoVendedor objetivo){
        int resultado = 0;
        resultado = DBController.actualizarObjetivoVendedor(objetivo);        
        return resultado;                
    }    
}

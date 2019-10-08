package administracion;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class PasswordService {
    // ESTE METODO DEVOLVERA 1 EN CASO DE QUE EL CORREO DESTINATARIO DEVUELVA
    // UN REGISTRO DE EMPLEADO VALIDO EN OTRO CASO DEVOLVERÁ 0
    public int enviarCorreo(String correoDestinatario){
        /*
        Empleado empleado = new Empleado(); 
       `empleado = DBController.BuscarPorCorreo(correoDestinatario);        
        if (empleado.getNombre() == NULL)
            return 0;
        */        
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true"); 
        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEmpresa = "edukt.empresarial@gmail.com";
        String contrasena = "tupiaboys1234";
        String asunto = "RENOVACION DE CONTRASEÑA";
        String mensaje = "MUYS BUENAS, NOMBRE DEL EMPLEADO, ESTA ES TU NUEVA CONTRASEÑA." ;
        
        MimeMessage mail = new MimeMessage(sesion);
        try { 
            mail.setFrom(new InternetAddress(correoEmpresa));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEmpresa,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            JOptionPane.showMessageDialog(null, "Listo, revise su correo");
        } catch (AddressException ex) {
            Logger.getLogger(PasswordService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PasswordService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}

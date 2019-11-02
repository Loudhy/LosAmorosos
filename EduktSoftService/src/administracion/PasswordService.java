package administracion;

import config.DBController;
import java.nio.charset.Charset;
import java.security.SecureRandom;
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
import model.Empleado;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import model.Usuario;

public class PasswordService {
    // ESTE METODO DEVOLVERA 1 EN CASO DE QUE EL CORREO DESTINATARIO DEVUELVA
    // UN REGISTRO DE EMPLEADO VALIDO EN OTRO CASO DEVOLVERÁ 0
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }        
        return builder.toString();
    }
    
    public int enviarCorreo(String correoDestinatario){              
        Empleado empleado = new Empleado();
        empleado = DBController.buscarEmpleadoPorCorreo(correoDestinatario);        
        if (empleado.getNombre() == null)
            return 0;        
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true"); 
        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEmpresa = "edukt.empresarial@gmail.com";
        String contrasena = "tupiaboys1234";
        String asunto = "RENOVACION DE CONTRASEÑA";
       
        String password = "";
        password = randomAlphaNumeric(6);
        String mensaje = "Muy buenas," + empleado.getNombre() + ", esta es tu nueva contraseña" + "\n" + password;
        Usuario usuario = DBController.buscarUsuarioPorEmpleado(empleado);
        usuario.setContraseña(password);
        DBController.actualizarUsuario(usuario);
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
            
            //JOptionPane.showMessageDialog(null, "Listo, correo enviado");
        } catch (AddressException ex) {
            System.out.println(ex.getMessage());
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return 1;
    }
}

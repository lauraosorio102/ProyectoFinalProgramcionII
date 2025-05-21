package co.edu.uniquindio.reservasuq.utils;

import co.edu.uniquindio.reservasuq.config.Correo;
import jakarta.activation.DataSource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EnvioEmail {

    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) {


        Email email = EmailBuilder.startingBlank()
                .from(Correo.CORREO)
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, Correo.CORREO, Correo.CONTRASENA)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void enviarNotificacionImagen(String destinatario, String asunto, String mensaje, DataSource ds) {


        Email email = EmailBuilder.startingBlank()
                .from(Correo.CORREO)
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .withEmbeddedImage("qr-code", ds)
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, Correo.CORREO, Correo.CONTRASENA)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

package support;

public class Correo {
    public static void enviarCorreo(String asunto, String mensaje, boolean esExitoso) {
        //final String remitente = "automatizacion.gys@gmail.com"; // Correo que mandara el mensaje
        //final String clave = "dtko epfw vqlw lmdg"; // Clave de aplicación
        //final String destinatario = "fernando.luyo@gestionysistemas.com"; // Correo del destinatario

    //    final String remitente = "";
    //    final String clave = "";
    //    final String destinatario = "";
//
    //    Properties props = new Properties();
    //    props.put("mail.smtp.auth", "true");
    //    props.put("mail.smtp.starttls.enable", "true");
    //    props.put("mail.smtp.host", "smtp.gmail.com");
    //    props.put("mail.smtp.port", "587");
//
    //    Session session = Session.getInstance(props, new Authenticator() {
    //        protected PasswordAuthentication getPasswordAuthentication() {
    //            return new PasswordAuthentication(remitente, clave);
    //        }
    //    });
//
    //    try {
    //        Message mensajeCorreo = new MimeMessage(session);
    //        mensajeCorreo.setFrom(new InternetAddress(remitente));
    //        mensajeCorreo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
    //        mensajeCorreo.setSubject(asunto);
    //        mensajeCorreo.setText(mensaje);
//
    //        Transport.send(mensajeCorreo);
    //        System.out.println("Correo enviado correctamente.");
//
    //    } catch (MessagingException e) {
    //        e.printStackTrace();
    //        System.out.println("Error al enviar Correo: " + e.getMessage());
    //    }
    }
}
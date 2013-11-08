package br.com.maps.labrador.mail.actor;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import br.com.maps.labrador.mail.domain.Email;

public class EmailConverterActor {

    public EmailConverterActor() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

    }

    /**
     * {@inheritDoc}
     */
    public Message convert(Email email) {
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = null;
        Message message = null;

        try {
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(email.getCorpo());
            multipart.addBodyPart(messageBodyPart);

            if (email.getAnexo() != null) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(email.getAnexo());
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(email.getAnexo().getName());
                multipart.addBodyPart(messageBodyPart);
            }

            // message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress("labrador@gmail.com"));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(email.getDestinatarios()));
            message.setSubject(email.getAssunto());
            // message.setText("");
            message.setContent(multipart);
        } catch (MessagingException e) {
            throw new RuntimeException("Não foi possível converter o email");
        }

        return message;
    }
}

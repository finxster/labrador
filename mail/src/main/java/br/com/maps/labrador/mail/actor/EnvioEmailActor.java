package br.com.maps.labrador.mail.actor;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

import jmine.tec.utils.notifier.mail.MailSender;
import br.com.maps.labrador.mail.domain.Email;

/**
 * Actor responsável por disparar email
 * 
 * @author fabio.sakiyama
 * @date Nov 8, 2013
 */
public class EnvioEmailActor {

    private MailSender mailSender;

    public void enviaEmail(Email email) {
        try {
            Message message = new EmailConverterActor().convert(email);
            System.out.println("Enviando e-mail...");
            Transport.send(message);
            System.out.println("Email enviado.");
        } catch (MessagingException e) {
        }
    }

    /**
     * @return the mailSender
     */
    public MailSender getMailSender() {
        return this.mailSender;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

}

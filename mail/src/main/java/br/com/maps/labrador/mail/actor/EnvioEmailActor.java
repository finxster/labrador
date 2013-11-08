package br.com.maps.labrador.mail.actor;

import jmine.tec.utils.notifier.mail.MailSender;

/**
 * Actor responsável por disparar email
 * 
 * @author fabio.sakiyama
 * @date Nov 8, 2013
 */
public class EnvioEmailActor {

    private MailSender mailSender;

    public void enviaEmail() {

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

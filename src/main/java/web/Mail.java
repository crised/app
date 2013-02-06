package web;


import model.User;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ResourceBundle;



public class Mail {


    static final Logger log = Logger.getLogger(Mail.class);

    @Inject
    private ResourceBundle rB;



    @Resource(mappedName = "java:jboss/mail/gmail")
    javax.mail.Session mailSession;


    public String SendMail(User user, String link){


        try {


            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("admin@drip.cl")); //from

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getId()));

            message.setSubject(rB.getString("mail.confirmLink"));

            message.setText(rB.getString("mail.message")
            + "\n \n" + link);

            Transport.send(message);

            log.info(rB.getString("mail.sent") + user.getId());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}

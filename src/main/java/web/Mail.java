package web;


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


@Named
public class Mail {


    static final Logger log = Logger.getLogger(Mail.class);

    @Inject
    private ResourceBundle resourceBundle;










    @Resource(mappedName = "java:jboss/mail/gmail")
    javax.mail.Session mailSession;

    /*Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");    */

    public void SendMail(){

        log.info("hello");
        log.info(resourceBundle.getObject("title)");
        try {


            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("crised@gmail.com")); //from
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("crised@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

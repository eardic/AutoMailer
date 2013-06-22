/*
 * Creator : Emre Ardic
 * Date : 22.06.2013
 * 
 * Simple Mail Sender
 * 
 */
package eardic.mailer.src;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Emre Ardic
 */
public class AutoMailer
{

    /**
     *
     * @param host like smtp.gmail.com
     * @param subject is the subject of mail
     * @param data is the body of mail
     * @param from is user name
     * @param pass is password
     * @param to is the mail of person
     * @return
     */
    public static boolean SendMail(String host, String subject, String data,
            String from, String pass, String to)
    {
        try
        {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true"); // added this line
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(data);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}

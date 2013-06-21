/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automailer;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author user
 */
public class AutoMailer
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            String host = "smtp.gmail.com";
            String from = "emreardic1";
            String pass = "ardicem14";
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true"); // added this line
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            String[] to =
            {
                "emreardic1@gmail.com",
                 "emreardic1@gmail.com",
                  "emreardic1@gmail.com"
            }; // added this line

            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++)
            { // changed from a while loop
                toAddress[i] = new InternetAddress(to[i]);
            }
            System.out.println(Message.RecipientType.TO);

            for (int i = 0; i < toAddress.length; i++)
            { // changed from a while loop
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject("sending in a group");
            message.setText("Welcome to JavaMail");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

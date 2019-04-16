package utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
    private static Logger logger = LogManager.getLogger(EmailUtility.class);
    public static void sendEmail(String host, String port,
                                 final String userName, final String password, String toAddress,
                                 String subject, String message) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message

        Message msg = new MimeMessage(session);

        try {
            msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "Q"));
        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to encode email subject to UTF-8", e);
            msg.setSubject(subject);
        }

        msg.setFrom(new InternetAddress(userName));
//        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        String[] recipients = toAddress.split(", ");
        InternetAddress[] mailAddress_TO = new InternetAddress [recipients.length] ;
        for(int i=0;i<recipients.length;i++){
            mailAddress_TO[i] = new InternetAddress(recipients[i]);
        }
        msg.addRecipients(Message.RecipientType.TO, mailAddress_TO);
        msg.setSentDate(new Date());
        msg.setContent(message, "text/plain; charset=UTF-8");
        // sends the e-mail
        Transport.send(msg);
        logger.info("Email sent to: "+toAddress);
    }
}
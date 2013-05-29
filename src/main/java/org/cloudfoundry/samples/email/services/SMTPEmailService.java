package org.cloudfoundry.samples.email.services;

import org.apache.log4j.Logger;
import org.cloudfoundry.samples.email.domain.EmailMessage;
import org.cloudfoundry.samples.email.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

@Service
public class SMTPEmailService implements EmailService {

    private static Logger logger = Logger.getLogger("services");

    @Autowired
    private EmailCredentials credentials;

    @Override
    public Status send(EmailMessage msg) {
        try {
            Session mailSession = createMailSession();

            MimeMessage message = createMessage(msg, mailSession);

            Transport transport = mailSession.getTransport();
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException e) {
            logger.error(e);
            return new Status(true, e.getMessage());
        }
        return new Status();
    }

    private MimeMessage createMessage(EmailMessage msg, Session mailSession) throws MessagingException {
        MimeMessage message = new MimeMessage(mailSession);

        Multipart multipart = new MimeMultipart("alternative");

        BodyPart part = new MimeBodyPart();
        part.setText(msg.getBody());

        multipart.addBodyPart(part);

        message.addRecipient(Message.RecipientType.TO, createEmailAddress(msg.getToName(), msg.getToAddress()));
        message.setFrom(createEmailAddress(msg.getFromName(), msg.getFromAddress()));
        message.setSubject(msg.getSubject());
        message.setContent(multipart);
        return message;
    }

    private Session createMailSession() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", credentials.getHost());
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();

        Session mailSession = Session.getDefaultInstance(props, auth);
        mailSession.setDebug(true);

        return mailSession;
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(credentials.getUserName(), credentials.getPassword());
        }
    }

    private InternetAddress createEmailAddress(String realName, String address) throws AddressException {
        return new InternetAddress(realName + "<" + address + ">");
    }
}


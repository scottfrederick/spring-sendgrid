package org.cloudfoundry.samples.email.services;

import org.apache.log4j.Logger;
import org.cloudfoundry.samples.email.domain.EmailMessage;
import org.cloudfoundry.samples.email.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            logger.error(e);
            return new Status(true, e.getMessage());
        }
        return new Status();
    }

    private MimeMessage createMessage(EmailMessage msg, Session mailSession) throws MessagingException {
        MimeMessage message = new MimeMessage(mailSession);

        addBody(msg, message);

        addAddresses(msg, message);

        message.setFrom(createEmailAddress(msg.getFromAddress()));
        message.setSubject(msg.getSubject());

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

    private void addBody(EmailMessage msg, MimeMessage message) throws MessagingException {
        Multipart multipart = new MimeMultipart("alternative");

        BodyPart part = new MimeBodyPart();
        part.setText(msg.getBody());

        multipart.addBodyPart(part);
        message.setContent(multipart);
    }

    private void addAddresses(EmailMessage msg, MimeMessage message) throws MessagingException {
        message.addRecipient(Message.RecipientType.TO, createEmailAddress(msg.getToAddress()));

        if (!StringUtils.isEmpty(msg.getCcAddress())) {
            message.addRecipient(Message.RecipientType.CC, createEmailAddress(msg.getCcAddress()));
        }

        if (!StringUtils.isEmpty(msg.getBccAddress())) {
            message.addRecipient(Message.RecipientType.BCC, createEmailAddress(msg.getBccAddress()));
        }
    }

    private InternetAddress createEmailAddress(String address) throws AddressException {
        return new InternetAddress(address);
    }
}


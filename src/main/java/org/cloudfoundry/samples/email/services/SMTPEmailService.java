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
import java.util.List;
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
        } catch (Exception e) {
            logger.error(e);
            return new Status(true, e.getMessage());
        }
        return new Status();
    }

    private MimeMessage createMessage(EmailMessage msg, Session mailSession) {
        MimeMessage message = new MimeMessage(mailSession);

        addBody(msg, message);

        addAddresses(msg.getToAddresses(), message, Message.RecipientType.TO);
        addAddresses(msg.getCcAddresses(), message, Message.RecipientType.CC);
        addAddresses(msg.getBccAddresses(), message, Message.RecipientType.BCC);

        addFromAddress(msg.getFromAddress(), message);
        addSubject(msg.getSubject(), message);

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

    private void addBody(EmailMessage msg, MimeMessage message) {
        try {
            Multipart multipart = new MimeMultipart("alternative");

            BodyPart part = new MimeBodyPart();
            part.setText(msg.getBody());

            multipart.addBodyPart(part);
            message.setContent(multipart);
        } catch (MessagingException e) {
            throw new RuntimeException("Error adding e-mail body:" + e.getMessage());
        }
    }

    private void addSubject(String subject, MimeMessage message) {
        try {
            message.setSubject(subject);
        } catch (MessagingException e) {
            throw new RuntimeException("Error adding e-mail subject:" + e.getMessage());
        }
    }

    private void addAddresses(List<String> addresses, MimeMessage message, Message.RecipientType type) {
        for (String address : addresses) {
            if (!StringUtils.isEmpty(address)) {
                try {
                    message.addRecipient(type, createEmailAddress(address));
                } catch (MessagingException e) {
                    throw new RuntimeException("Error adding e-mail address \"" + address + "\":" + e.getMessage());
                }
            }
        }
    }

    private void addFromAddress(String address, MimeMessage message) {
        try {
            message.setFrom(createEmailAddress(address));
        } catch (MessagingException e) {
            throw new RuntimeException("Error adding e-mail address \"" + address + "\":" + e.getMessage());
        }
    }

    private InternetAddress createEmailAddress(String address) throws AddressException {
        return new InternetAddress(address);
    }
}


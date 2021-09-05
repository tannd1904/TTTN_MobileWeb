package com.tannd.commercemanager.services;

import com.tannd.commercemanager.template.EmailObject;
import com.tannd.commercemanager.template.EmailTemplate;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    final Configuration configuration;
    final JavaMailSender javaMailSender;

    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    //    boolean sendEmail(String subject, String message, String to);
    public void sendEmail(EmailObject user) throws MessagingException, TemplateException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject("Welcome To Tan");
        helper.setTo(user.getEmail());

        String emailContent = getEmailContent(user);
        helper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);
    }

    String getEmailContent(EmailObject user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();

        model.put("user", user);
        configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    public boolean sendEmailWithAttachment(String title, String content, String to) throws Exception {
        boolean foo = false; // Set the false, default variable "foo", we will allow it after sending code process email
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject("Welcome To Tan");
            helper.setTo(to);
            EmailTemplate emailTemplate = new EmailTemplate(title, content);
            helper.setText(emailTemplate.toEmail(), true);

            String fileName = "6.jpg";
            File serverFile = new File (context.getRealPath("/Images/Product"+File.separator+fileName));
            FileSystemResource file = new FileSystemResource(serverFile);
            helper.addAttachment("product.jpg", file);
            javaMailSender.send(mimeMessage);
        }catch(Exception e){
            System.err.println("Sending mail error..." + e);
        }
        return foo; // and return foo variable
    }

    public boolean sendEmailWithInline(String title, String content, String to) throws Exception {
        boolean foo = false; // Set the false, default variable "foo", we will allow it after sending code process email
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject("Welcome To Tan");
            helper.setTo(to);
            EmailTemplate emailTemplate = new EmailTemplate(title, content);
            helper.setText(emailTemplate.toEmail(), true);

            String fileName = "6.jpg";
            File serverFile = new File (context.getRealPath("/Images/Product"+File.separator+fileName));
            FileSystemResource file = new FileSystemResource(serverFile);
            helper.addInline("product", file);
            javaMailSender.send(mimeMessage);
        }catch(Exception e){
            System.err.println("Sending mail error..." + e);
        }
        return foo; // and return foo variable
    }

    @Autowired
    ServletContext context;
}

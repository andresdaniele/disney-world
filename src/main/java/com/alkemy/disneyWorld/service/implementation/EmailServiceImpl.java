package com.alkemy.disneyWorld.service.implementation;

import com.alkemy.disneyWorld.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.disneyWorld.email.sender}")     //application.properties values
    private String emailSender;

    @Override
    public void sendWelcomeEmailTo(String to) {

        //Data extracted from SendGrid documentation.

        String apiKey = env.getProperty("EMAIL_API_KEY");  //Add API key provided for SendGrid to environment variables.

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", "Welcome to Alkemy Disney World!");      //type and content of the message.
        String subject = "Alkemy Disney World";

        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch ( IOException ex) {
            System.out.println("Error trying to send email");
        }

    }
}
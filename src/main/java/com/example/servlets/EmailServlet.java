package com.example.servlets;

import java.io.IOException;
import java.util.Properties;

//import javax.mail.*;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

@SuppressWarnings("serial")
//@WebServlet("add")
public class EmailServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get User Inputs from the HTML Form
		String to = req.getParameter("to");
		String subject = req.getParameter("subject");
		String messageText = req.getParameter("message");
		
		System.out.println("to: " + to);	
		System.out.println("Subject: " + subject);	
		System.out.println("Message: " + messageText);	
		
		
		// SMTP Email - SEND
		String host = "smtp.gmail.com";  // Activate MFA > Manage Account > App Password > "uixs wogx hyoz awqh"
		String port = "25";
		
		// ÉQUIPE COURRIEL HÉRITAGE-LEGACY EMAIL TEAM
		// OTMAIL.hc-sc.gc.ca     [External Apps]      // Outlook Exchange   // Outlook
		// brightx.hc-sc.gc.ca    [Internal Apps]      // whitelist: SAD-APTOM-DEV

		String username = "astit103@gmail.com";
		String password = "uixs wogx hyoz awqh";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		// 1- SESSION
		System.out.println("Creating Session...");	
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			}
        );
		System.out.println("Session Created...");
		
		// 2- Try Send an Email
		try {
			// 2- MESSAGE
			System.out.print("Trying to Send an Email...");
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
			
			// 3- SEND
			System.out.print("Transporting the Email...");
			Transport.send(message);
			
			// 4- SUCCESS MESSAGE
			System.out.print("IT WORKED !!!");
			req.setAttribute("successMessage", "Email sent successfully!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("email.html");
			dispatcher.forward(req, resp);

			//resp.getWriter().println("Email was sent Successfully");
			
		}catch(MessagingException e) {
			System.out.println("Exception occured: " + e);
		}
		
 
		
	}
	
}


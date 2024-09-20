package br.com.fiap.techchallenge.tablereservation.infrastructure.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.fiap.techchallenge.tablereservation.application.gateways.EmailGateway;

public class EmailGatewayImpl implements EmailGateway {

	@Value("reservation.email-noreply")
	private String emailFrom;
	
	private final JavaMailSender mailSender;
	
	public EmailGatewayImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String email, String title, String text) {
		var message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(email);
		message.setSubject(title);
		message.setText(text);
		mailSender.send(message);
	}

	
}

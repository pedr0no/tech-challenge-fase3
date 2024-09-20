package br.com.fiap.techchallenge.tablereservation.application.gateways;

public interface EmailGateway {
	
	public void sendEmail(String email, String title, String text);

}

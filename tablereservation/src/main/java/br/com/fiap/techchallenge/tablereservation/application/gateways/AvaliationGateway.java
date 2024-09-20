package br.com.fiap.techchallenge.tablereservation.application.gateways;

import java.util.List;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;

public interface AvaliationGateway {
	
	public Avaliation createAvaliation(Avaliation avaliation);
	
	public Avaliation searchAvaliationById(String idAvaliation);
	
	List<Avaliation> searchAllAvaliationForRestaurant(String restaurantName);

}

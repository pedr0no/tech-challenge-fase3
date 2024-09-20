package br.com.fiap.techchallenge.tablereservation.application.usecases;

import java.util.List;

import br.com.fiap.techchallenge.tablereservation.application.gateways.AvaliationGateway;
import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;

public class AvaliationOperationsCollection {
	
	private final AvaliationGateway avaliationGateway;

	public AvaliationOperationsCollection(AvaliationGateway avaliationGateway) {
		this.avaliationGateway = avaliationGateway;
	}
	
	public Avaliation createAvaliation(Avaliation avaliation) {
		return avaliationGateway.createAvaliation(avaliation);
	}
	
	public Avaliation searchAvaliationById(String idAvaliation) {
		return avaliationGateway.searchAvaliationById(idAvaliation);
	}
	
	public List<Avaliation> searchAvaliationsForRestaurant(String restaurantName) {
		return avaliationGateway.searchAllAvaliationForRestaurant(restaurantName);
	}
}

package br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.techchallenge.tablereservation.domain.entity.Avaliation;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.dto.AvaliationDTO;
import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.avaliation.dto.CreatedAvaliationDTO;

public class AvaliationMapper {

	// Métodos que mapeiam de Domain para DTO

	public CreatedAvaliationDTO avaliationToCreatedAvaliationDTO(Avaliation avaliation) {
		return new CreatedAvaliationDTO(avaliation.getId());
	}

	public AvaliationDTO avaliationToAvaliationDTO(Avaliation avaliation) {
		return new AvaliationDTO(avaliation.getClientEmail(), avaliation.getRestaurantName(), avaliation.getStars(),
				avaliation.getComment());
	}

	public List<AvaliationDTO> listAvaliationToListAvaliationDTO(List<Avaliation> avaliations) {
		var list = new ArrayList<AvaliationDTO>();
		avaliations.forEach(a -> {
			var avaliation = avaliationToAvaliationDTO(a);
			list.add(avaliation);
		});
		return list;
	}

	// Métodos que mapeiam de DTO para Domain

	public Avaliation avaliationDTOToAvaliation(AvaliationDTO avaliationDTO) {
		return new Avaliation(avaliationDTO.getClientEmail(), avaliationDTO.getRestaurantName(),
				avaliationDTO.getStars(), avaliationDTO.getComment());
	}

}

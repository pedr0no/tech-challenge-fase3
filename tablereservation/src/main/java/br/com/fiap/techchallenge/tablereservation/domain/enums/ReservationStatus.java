package br.com.fiap.techchallenge.tablereservation.domain.enums;

import org.springframework.http.HttpStatus;

import br.com.fiap.techchallenge.tablereservation.infrastructure.controller.exception.BusinessException;

public enum ReservationStatus {

	SCHEDULED, CANCELED, FINISHED;

	public static String getMessage(ReservationStatus status) {
		if (status.equals(FINISHED))
			return "Finalizada";
		if (status.equals(CANCELED))
			return "Cancelada";
		throw new BusinessException(HttpStatus.BAD_REQUEST, "Não há mensagem para o status informado");
	}

}

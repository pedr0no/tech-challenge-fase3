package br.com.fiap.techchallenge.tablereservation.infrastructure.persistence.entity;

import br.com.fiap.techchallenge.tablereservation.domain.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableEntity {
	
	private TableStatus status;

}

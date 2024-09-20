package br.com.fiap.techchallenge.tablereservation.domain.entity;

import java.util.Objects;

import br.com.fiap.techchallenge.tablereservation.domain.enums.TableStatus;

public class Table {
	
	private TableStatus status;

	public Table() {
		super();
		this.status = TableStatus.AVAILABLE;
	}

	public Table(TableStatus status) {
		super();
		this.status = status;
	}

	public TableStatus getStatus() {
		return status;
	}

	public void setStatus(TableStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		return status == other.status;
	}
	
}
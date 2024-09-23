package br.com.fiap.techchallenge.tablereservation.domain.entity;

import br.com.fiap.techchallenge.tablereservation.domain.enums.TableStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScheduleReservation {

	private LocalDate date;  // Adicionando a data para representar um Schedule completo
	private LocalTime hour;
	private List<Table> tables;

	public ScheduleReservation() {
		super();
	}

	public ScheduleReservation(LocalDate date, LocalTime hour, int size) {
		super();
		this.date = date;
		this.hour = hour;
		this.tables = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			this.tables.add(new Table(TableStatus.AVAILABLE));
		}
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	/**
	 * Método para verificar se há mesas disponíveis no horário.
	 *
	 * @return true se houver pelo menos uma mesa disponível, false caso contrário.
	 */
	public boolean hasAvailableTables() {
		return tables.stream().anyMatch(table -> table.getStatus() == TableStatus.AVAILABLE);
	}

	/**
	 * Método para reservar uma mesa.
	 *
	 * @return true se a reserva foi feita com sucesso, false se não houver mesas disponíveis.
	 */
	public boolean reserveTable() {
		for (Table table : tables) {
			if (table.getStatus() == TableStatus.AVAILABLE) {
				table.setStatus(TableStatus.RESERVERD);
				return true;  // Reserva feita com sucesso
			}
		}
		return false;  // Não há mesas disponíveis
	}

	/**
	 * Método para liberar mesas reservadas.
	 */
	public void releaseReservedTables() {
		tables.stream()
				.filter(table -> table.getStatus() == TableStatus.RESERVERD)
				.forEach(table -> table.setStatus(TableStatus.AVAILABLE));
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, hour, tables);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleReservation other = (ScheduleReservation) obj;
		return Objects.equals(date, other.date) && Objects.equals(hour, other.hour) && Objects.equals(tables, other.tables);
	}

}

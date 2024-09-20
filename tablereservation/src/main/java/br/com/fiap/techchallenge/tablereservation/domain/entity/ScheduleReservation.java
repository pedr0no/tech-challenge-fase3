package br.com.fiap.techchallenge.tablereservation.domain.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScheduleReservation {

	private LocalTime hour;
	private List<Table> tables;

	public ScheduleReservation() {
		super();
	}

	public ScheduleReservation(LocalTime hour, int size) {
		super();
		this.hour = hour;
		this.tables = new ArrayList<>(size);
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

	@Override
	public int hashCode() {
		return Objects.hash(hour, tables);
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
		return Objects.equals(hour, other.hour) && Objects.equals(tables, other.tables);
	}

}
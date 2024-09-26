package br.com.fiap.techchallenge.tablereservation.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class OpeningHoursDetails {

	private LocalDate day;
	private LocalTime openingTime;
	private LocalTime closingTime;
	private List<ScheduleReservation> scheduleOfReservation;

	public OpeningHoursDetails() {
		super();
	}

	public OpeningHoursDetails(LocalDate day, LocalTime openingTime, LocalTime closingTime,
			List<ScheduleReservation> scheduleOfReservation) {
		super();
		this.day = day;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.scheduleOfReservation = scheduleOfReservation;
	}

    public OpeningHoursDetails(String s, String time, String time1) {
    }

    public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public List<ScheduleReservation> getScheduleOfReservation() {
		return scheduleOfReservation;
	}

	public void setScheduleOfReservation(List<ScheduleReservation> scheduleOfReservation) {
		this.scheduleOfReservation = scheduleOfReservation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(closingTime, day, openingTime, scheduleOfReservation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpeningHoursDetails other = (OpeningHoursDetails) obj;
		return Objects.equals(closingTime, other.closingTime) && Objects.equals(day, other.day)
				&& Objects.equals(openingTime, other.openingTime)
				&& Objects.equals(scheduleOfReservation, other.scheduleOfReservation);
	}

}
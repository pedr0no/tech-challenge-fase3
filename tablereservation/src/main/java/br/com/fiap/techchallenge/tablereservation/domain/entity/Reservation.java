package br.com.fiap.techchallenge.tablereservation.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Reservation {

	private String id;
	private String restaurantName;
	private String clientEmail;
	private LocalTime timeReservation;
	private LocalDate dayOfReservation;
	private Integer quantityOfPeople;

	public Reservation() {
		super();
	}

	public Reservation(String restaurantName, String clientEmail, LocalTime timeReservation, LocalDate dayOfReservation,
			Integer quantityOfPeople) {
		this.restaurantName = restaurantName;
		this.clientEmail = clientEmail;
		this.timeReservation = timeReservation;
		this.dayOfReservation = dayOfReservation;
		this.quantityOfPeople = quantityOfPeople;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public LocalTime getTimeReservation() {
		return timeReservation;
	}

	public void setTimeReservation(LocalTime timeReservation) {
		this.timeReservation = timeReservation;
	}

	public LocalDate getDayOfReservation() {
		return dayOfReservation;
	}

	public void setDayOfReservation(LocalDate dayOfReservation) {
		this.dayOfReservation = dayOfReservation;
	}

	public Integer getQuantityOfPeople() {
		return quantityOfPeople;
	}

	public void setQuantityOfPeople(Integer quantityOfPeople) {
		this.quantityOfPeople = quantityOfPeople;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientEmail, dayOfReservation, id, quantityOfPeople, restaurantName, timeReservation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(clientEmail, other.clientEmail)
				&& Objects.equals(dayOfReservation, other.dayOfReservation) && Objects.equals(id, other.id)
				&& Objects.equals(quantityOfPeople, other.quantityOfPeople)
				&& Objects.equals(restaurantName, other.restaurantName)
				&& Objects.equals(timeReservation, other.timeReservation);
	}

}
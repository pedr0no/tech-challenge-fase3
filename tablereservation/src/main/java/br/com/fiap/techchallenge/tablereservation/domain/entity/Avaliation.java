package br.com.fiap.techchallenge.tablereservation.domain.entity;

import java.util.Objects;

public class Avaliation {

	private String id;
	private String clientEmail;
	private String restaurantName;
	private Double stars;
	private String comment;

	public Avaliation() {
		super();
	}
	
	public Avaliation(String id, String clientEmail, String restaurantName, Double stars, String comment) {
		super();
		this.id = id;
		this.clientEmail = clientEmail;
		this.restaurantName = restaurantName;
		this.stars = stars;
		this.comment = comment;
	}

 	public Avaliation(String clientEmail, String restaurantName, Double stars, String comment) {
		super();
		this.clientEmail = clientEmail;
		this.restaurantName = restaurantName;
		this.stars = stars;
		this.comment = comment;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Double getStars() {
		return stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientEmail, comment, id, stars);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliation other = (Avaliation) obj;
		return Objects.equals(clientEmail, other.clientEmail) && Objects.equals(comment, other.comment)
				&& Objects.equals(id, other.id) && Objects.equals(stars, other.stars);
	}

}
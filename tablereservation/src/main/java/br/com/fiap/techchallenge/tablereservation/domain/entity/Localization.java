package br.com.fiap.techchallenge.tablereservation.domain.entity;

import java.util.Objects;

public class Localization {

	private String street;
	private Integer number;
	private String neighborhood;
	private String city;
	private String cep;
	
	public Localization() {
		super();
	}

	public Localization(String street, Integer number, String neighborhood, String city, String cep) {
		super();
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.city = city;
		this.cep = cep;
	}

    public Localization(String ruaExemplo, String s, String sãoPaulo, String sp, int i) {
    }

    public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, city, neighborhood, number, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localization other = (Localization) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(city, other.city)
				&& Objects.equals(neighborhood, other.neighborhood) && Objects.equals(number, other.number)
				&& Objects.equals(street, other.street);
	}

}

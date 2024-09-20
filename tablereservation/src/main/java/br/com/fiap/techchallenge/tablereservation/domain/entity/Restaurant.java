package br.com.fiap.techchallenge.tablereservation.domain.entity;

import java.util.List;
import java.util.Objects;

public class Restaurant {

	private String id;
	private String name;
	private Localization localization;
	private String cuisine;
	private int quantityPeoplePerTables;
	private int quantityOfTables;
	private List<OpeningHoursDetails> openingHours;

	public Restaurant() {
		super();
	}

	public Restaurant(String name, Localization localization, String cuisine, int quantityPeoplePerTables,
			int quantityOfTables, List<OpeningHoursDetails> openingHours) {
		super();
		this.name = name;
		this.localization = localization;
		this.cuisine = cuisine;
		this.quantityPeoplePerTables = quantityPeoplePerTables;
		this.quantityOfTables = quantityOfTables;
		this.openingHours = openingHours;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Localization getLocalization() {
		return localization;
	}

	public void setLocalization(Localization localization) {
		this.localization = localization;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public int getQuantityPeoplePerTables() {
		return quantityPeoplePerTables;
	}

	public void setQuantityPeoplePerTables(int quantityPeoplePerTables) {
		this.quantityPeoplePerTables = quantityPeoplePerTables;
	}

	public int getQuantityOfTables() {
		return quantityOfTables;
	}

	public void setQuantityOfTables(int quantityOfTables) {
		this.quantityOfTables = quantityOfTables;
	}

	public List<OpeningHoursDetails> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHoursDetails> openingHours) {
		this.openingHours = openingHours;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cuisine, id, localization, name, openingHours, quantityOfTables, quantityPeoplePerTables);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return Objects.equals(cuisine, other.cuisine) && Objects.equals(id, other.id)
				&& Objects.equals(localization, other.localization) && Objects.equals(name, other.name)
				&& Objects.equals(openingHours, other.openingHours) && quantityOfTables == other.quantityOfTables
				&& quantityPeoplePerTables == other.quantityPeoplePerTables;
	}
	
}

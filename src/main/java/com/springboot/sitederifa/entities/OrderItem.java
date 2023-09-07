package com.springboot.sitederifa.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.sitederifa.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;
	private Double price;

	@JsonProperty("price")
	public String getFormattedPrice() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
		return "R$ " + decimalFormat.format(price);
	}

	private Set<Integer> generatedNumbers = new HashSet<>();

	public OrderItem() {
	}

	public OrderItem(Order order, Raffle raffle, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setRaffle(raffle);
		this.quantity = quantity;
		this.price = price;
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	public Raffle getRaffle() {
		return id.getRaffle();
	}

	public void setRaffle(Raffle raffle) {
		id.setRaffle(raffle);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSubTotal() {
		return price * quantity;
	}

	@JsonProperty("subTotal")
	public String getFormattedSubTotal() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
		return "R$ " + decimalFormat.format(getSubTotal());
	}

	public Set<Integer> getGeneratedNumbers() {
		return generatedNumbers;
	}

	public void setGeneratedNumbers(Set<Integer> generatedNumbers) {
		this.generatedNumbers = generatedNumbers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}

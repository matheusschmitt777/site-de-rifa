package com.springboot.sitederifa.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.springboot.sitederifa.entities.Order;
import com.springboot.sitederifa.entities.Raffle;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "raffle_id")
	private Raffle raffle;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Raffle getRaffle() {
		return raffle;
	}

	public void setRaffle(Raffle raffle) {
		this.raffle = raffle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, raffle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(raffle, other.raffle);
	}
}

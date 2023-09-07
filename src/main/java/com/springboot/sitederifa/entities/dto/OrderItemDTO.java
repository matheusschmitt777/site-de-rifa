package com.springboot.sitederifa.entities.dto;

public class OrderItemDTO {

	private Long id;
	private Long orderId;
	private Long raffleId;
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getRaffleId() {
		return raffleId;
	}

	public void setRaffleId(Long raffleId) {
		this.raffleId = raffleId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

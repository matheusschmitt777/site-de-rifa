package com.springboot.sitederifa.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.sitederifa.entities.enums.RaffleStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_raffle")
public class Raffle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private Integer quantity;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
	private Instant momentCreated;
	
	private Integer raffleStatus;
	
	public Raffle() {
	}

	public Raffle(Long id, Integer quantity, String name, String description, Double price, String imgUrl, Instant momentCreated,  RaffleStatus raffleStatus) {
		this.id = id;
		this.quantity = quantity;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.momentCreated = momentCreated;
		setRaffleStatus(raffleStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Instant getMomentCreated() {
		return momentCreated;
	}

	public void setMomentCreated(Instant momentCreated) {
		this.momentCreated = momentCreated;
	}
	
	public RaffleStatus getRaffleStatus() {
		return RaffleStatus.valueOf(raffleStatus);
	}

	public void setRaffleStatus(RaffleStatus raffleStatus) {
		if (raffleStatus != null) {
			this.raffleStatus = raffleStatus.getCode();
		}
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
		Raffle other = (Raffle) obj;
		return Objects.equals(id, other.id);
	}
}

package com.springboot.sitederifa.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.sitederifa.entities.enums.ClientStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	private String file;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
	private Instant momentCreated;
	
	private Integer clientStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public Client() {
	}

	public Client(Long id, String name, String phone, String file, Instant momentCreated, ClientStatus clientStatus) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.file = file;
		this.momentCreated = momentCreated;
		setClientStatus(clientStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Instant getMomentCreated() {
		return momentCreated;
	}

	public void setMomentCreated(Instant momentCreated) {
		this.momentCreated = momentCreated;
	}
	
	public ClientStatus getClientStatus() {
		return ClientStatus.valueOf(clientStatus);
	}

	public void setClientStatus(ClientStatus clientStatus) {
		if (clientStatus != null) {
			this.clientStatus = clientStatus.getCode();
		}
	}

	public List<Order> getOrders() {
		return orders;
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
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
}
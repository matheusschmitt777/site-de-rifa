package com.springboot.sitederifa.entities.enums;

public enum RaffleStatus {

	OPEN(1),
	CLOSE(2);

	private int code;

	private RaffleStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RaffleStatus valueOf(int code) {
		for (RaffleStatus value : RaffleStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid RaffleStatus code");
	}
}

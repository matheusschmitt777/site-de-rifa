package com.springboot.sitederifa.entities.enums;

public enum ClientStatus {
	
	TRUE(1),
	FALSE(2);

	private int code;

	private ClientStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ClientStatus valueOf(int code) {
		for (ClientStatus value : ClientStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid ClientStatus code");
	}
}

package com.vv.aes;

public enum AesEnum {
	KEY128(128),KEY192(192),KEY256(256);
	
	private AesEnum(int value) {
		this.value = value;
	}
	
	private int value;	

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
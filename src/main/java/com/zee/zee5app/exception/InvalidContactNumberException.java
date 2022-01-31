package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class InvalidContactNumberException extends Exception {
	
	public InvalidContactNumberException(String message) {
		super(message);
	}
}

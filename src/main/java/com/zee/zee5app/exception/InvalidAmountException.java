package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class InvalidAmountException extends Exception{

	public InvalidAmountException(String message) {
		super(message);
	}
}

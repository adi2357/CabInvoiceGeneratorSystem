package com.bridgelabz.cabinvoicegenerator;

public class InvoiceException extends Exception {
	public enum ExceptionType {
		INVALID_RIDE_TYPE
	}

	ExceptionType type;

	public InvoiceException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}

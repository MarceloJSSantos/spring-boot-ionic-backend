package com.marcelojssantos.cursomc.services.exceptions;

public class DataIntegrityVolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//construtor
	public DataIntegrityVolationException(String msg) {
		super(msg);
	}
	
	public DataIntegrityVolationException(String msg, Throwable causa) {
		super(msg, causa);
	}
}

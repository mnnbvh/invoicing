package com.xu.exception;

import org.springframework.stereotype.Service;

@Service
public class DataAccessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}

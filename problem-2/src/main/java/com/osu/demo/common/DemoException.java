package com.osu.demo.common;

/**
 * The Class DemoException.
 * 
 * @author Vinod Arroju
 */
public class DemoException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The code. */
	ErrorCode code;
	
	/**
	 * Instantiates a new demo exception.
	 *
	 * @param code the {@code ErrorCode}
	 * @param e the {@code Exception}
	 */
	public DemoException(ErrorCode code,Exception e) {
		super(e);
		this.code=code;
	}
	
	/**
	 * Instantiates a new demo exception.
	 *
	 * @param code the {@code ErrorCode}
	 * @param message the {@code String}
	 */
	public DemoException(ErrorCode code,String message) {
		super(message);
		this.code=code;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the {@code ErrorCode}
	 */
	public ErrorCode getCode() {
		return this.code;
	}
}

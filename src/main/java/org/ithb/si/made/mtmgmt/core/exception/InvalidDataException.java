/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.exception;

/**
 *
 * @author gde.satrigraha
 */
public class InvalidDataException extends RuntimeException {

	/**
	 * Creates a new instance of
	 * <code>InvalidDataException</code> without detail message.
	 */
	public InvalidDataException() {
	}

	/**
	 * Constructs an instance of
	 * <code>InvalidDataException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public InvalidDataException(String msg) {
		super(msg);
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(Throwable cause) {
		super(cause);
	}
}

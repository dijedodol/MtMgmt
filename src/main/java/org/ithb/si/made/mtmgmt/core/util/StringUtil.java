/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.util;

/**
 *
 * @author Uyeee
 */
public class StringUtil {

	public static String formatStackTrace(String message, StackTraceElement[] stackTraces) {
		//add the class name and any message passed to constructor
		final StringBuilder result = new StringBuilder("Current StackTrace: " + message);
		final String NEW_LINE = System.getProperty("line.separator");
		result.append(NEW_LINE);

		//add each element of the stack trace
		for (StackTraceElement element : stackTraces) {
			result.append(element);
			result.append(NEW_LINE);
		}
		return result.toString();
	}

	public static String formatStackTrace(StackTraceElement[] stackTraces) {
		//add the class name and any message passed to constructor
		final StringBuilder result = new StringBuilder("Current StackTrace:");
		final String NEW_LINE = System.getProperty("line.separator");
		result.append(NEW_LINE);

		//add each element of the stack trace
		for (StackTraceElement element : stackTraces) {
			result.append(element);
			result.append(NEW_LINE);
		}
		return result.toString();
	}
}

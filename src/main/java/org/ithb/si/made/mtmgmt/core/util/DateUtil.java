/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Uyeee
 */
public class DateUtil {

	private static final ThreadLocal<DateFormat> DATE_FORMATTER = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	public static String format(Date d) {
		return DATE_FORMATTER.get().format(d);
	}

	public static Date parse(String s) throws ParseException {
		return DATE_FORMATTER.get().parse(s);
	}
}

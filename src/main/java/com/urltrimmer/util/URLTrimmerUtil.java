package com.urltrimmer.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

public class URLTrimmerUtil {

	private static final Logger logger = Logger.getLogger(URLTrimmerUtil.class);

	/*
	 * Method returns the stack trace of exception in string format. Used for
	 * logging of exception.
	 */
	public static String getExceptionDescriptionString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	public static double distance(double lat1, double lon1, double lat2,
			double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return (dist);
	}

	public static String validateUrl(String url) {
		if (null == url) {
			return "Please provide URL to be searched";

		} else if ("".equals(url)) {

			return "Please provide a valid URL";

		} else if (url.length() > 500) {

			return "Please provide a valid URL with length less than 500 characters";

		}

		boolean isValid = true;
		if (!url.contains("http://")) {
			isValid = false;

		}
		if (!isValid) {
			if (!url.contains("https://")) {
				isValid = false;
			} else {
				isValid = true;
			}
		}
		if (!isValid) {
			return "Please provide a valid URL";
		}
		return null;
	}
}

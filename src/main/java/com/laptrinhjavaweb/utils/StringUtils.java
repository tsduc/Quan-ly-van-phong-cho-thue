package com.laptrinhjavaweb.utils;

public class StringUtils {
	public static boolean isNullOrEmpty(String str) {
		if(str != null && !str.equals("")) {
			return false;
		}
		return true;
	}

}

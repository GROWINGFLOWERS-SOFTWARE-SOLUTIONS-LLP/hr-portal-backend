package com.gfss.hr_portal_backend.utils;

public class IdGenerateUtil {

public static	String convertObjectIdToNumeric(String objectId) {
		StringBuilder numericId = new StringBuilder();
		for (char ch : objectId.toCharArray()) {
			if (Character.isDigit(ch)) {
	            numericId.append(ch);
	        } else if (Character.isLetter(ch)) {
	            numericId.append((int) ch);
	        }
		}
		return numericId.toString();
	}
}

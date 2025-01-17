package shop.mammastore.common;

import java.util.regex.Pattern;

public class RegExp {
	public static final int REGEXP_ID = 1;
	public static final int REGEXP_PWD = 2;
	public static final int REGEXP_NAME = 3;
	public static final int REGEXP_NUMBER = 4;
	public static final int REGEXP_EMAIL = 5;
	public static final int REGEXP_PHONE = 6;
	public static final int REGEXP_CTGRY_NM = 7;

	public static boolean isValidExp(String data, int type) {
		boolean isValid = false;
		if (isEmpty(data)) {
			return isValid;
		}
		switch (type) {
		case REGEXP_ID:
			isValid = Pattern.matches("^[a-z0-9]{4,16}$", data);
			break;
		case REGEXP_PWD:
			isValid = Pattern.matches("^[a-zA-Z0-9!@#$%^&*]{4,20}$", data);
			break;
		case REGEXP_NAME:
			isValid = Pattern.matches("^[가-힣]{2,8}$", data);
			break;
		case REGEXP_NUMBER:
			isValid = Pattern.matches("^[0-9]*$", data);
			break;
		case REGEXP_EMAIL:
			isValid = Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", data);
			break;
		case REGEXP_PHONE:
			isValid = Pattern.matches("^[01]{2,2}[0-9]{8,9}$", data);
			break;
		case REGEXP_CTGRY_NM:
			isValid = Pattern.matches("^[0-9a-zA-Z가-힣]{1,10}$", data);
			break;
		}
		
			
		return isValid;
	}

	public static boolean isEmpty(String data) {
		if (data == null || data.equals("")) {
			return true;
		} else {
			return false;
		}
	}
}

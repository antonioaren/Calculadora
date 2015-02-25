package presenter;

public class StringUtils {
		
	public static String remove_right_char(String text) {
		int numChars = text.length();
		
		if (numChars != 0) {
			return text.substring(0, numChars - 1);
		} else {
			return "";
		}		
	}

	public static Integer toInteger (String text) throws Exception {
	   Integer number = Integer.parseInt(text);
	   return number;
	}
	
}

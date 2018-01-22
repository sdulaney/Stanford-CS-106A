/*
 * File: AddCommas.java
 * -------------------
 * This program displays large numbers
 * by using commas to separate digits into 
 * groups of three.
 */

import acm.program.*;

public class AddCommas extends ConsoleProgram {
	
	public void run() {
		while (true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) {
				break;
			}
			println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String digits) {
		String result = "";
		int len = digits.length();
		int numDigits = 0;
		for (int i = len - 1; i >= 0; i--) {
			result = digits.charAt(i) + result;
			numDigits++;
			if (((numDigits % 3) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		return result;
	}
}

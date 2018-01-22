/*
 * File: DeleteCharacters.java
 * -------------------
 * This program removes all occurrences
 * of a character from a string.
 */

import acm.program.*;

public class DeleteCharacters extends ConsoleProgram {

	public void run() {
		println(removeAllOccurrences("This is a test", 't'));
		println(removeAllOccurrences("Summer is here!", 'e'));
		println(removeAllOccurrences("---0---", '-'));
	}
	
	public String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ch) {
				result += str.charAt(i);
			}
		}
		return result;
	}
}

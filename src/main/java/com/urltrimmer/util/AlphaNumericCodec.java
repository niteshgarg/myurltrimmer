package com.urltrimmer.util;

/**
 * Bijective function that will take an integer and map it to an alphanumeric
 * string.
 * 
 * <pre>
 * 0 -> a
 * 1 -> b
 * ...
 * 25 -> z
 * ...
 * 52 -> 0
 * 61 -> 9
 * </pre>
 * 
 * @see http://stackoverflow.com/questions/742013/how-to-code-a-url-shortener
 * @see https://gist.github.com/1073996
 * 
 */
public class AlphaNumericCodec implements Codec {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final int ALPHABET_LENGTH = ALPHABET.length();
	private static final char[] CHARSET = ALPHABET.toCharArray();

	public String encode(int number) {
		int i = number;
		if (i == 0) {
			return Character.toString(CHARSET[0]);
		}

		StringBuilder stringBuilder = new StringBuilder();
		while (i > 0) {
			int remainder = i % ALPHABET_LENGTH;
			i /= ALPHABET_LENGTH;
			stringBuilder.append(CHARSET[remainder]);
		}
		return stringBuilder.reverse().toString();
	}

	@Override
	public int decode(String s) {
		int i = 0;
		char[] chars = s.toCharArray();
		for (char c : chars) {
			i = i * ALPHABET_LENGTH + ALPHABET.indexOf(c);
		}
		return i;
	}

}

package com.nvidia.cosmos.cloud.common;

import java.util.Random;
import java.util.UUID;

/**
 * @author pbatta
 *
 */
public class IdGenerator {
	public static String createId() {
		UUID uuid = java.util.UUID.randomUUID();
		return uuid.toString();
	}
	public static String getRandomName() { 
		return makeRandomString(6, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONPQRSTVUWXYZ", 3, "0123456789");
	}
	public static String getRandomPhone() {
		return makeRandomString(1, "8", 9, "012345679");
	}
	public static String getRandomThreeDigit() {
		return makeRandomString(2, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONPQRSTVUWXYZ", 1, "012345679");
	}
	private static String makeRandomString(int numOfAlpha, String alpha,
			int numOfNumeric, String numeric) throws IllegalArgumentException {

		try {

			String strs[] = { alpha, numeric };
			int[] numOf = { numOfAlpha, numOfNumeric };

			//  int alphaSize = alpha.length();
			//  int numericSize = numeric.length();

			int total = numOfAlpha + numOfNumeric;

			StringBuffer sb = new StringBuffer(total);

			Random r = new Random();

			for (int i = 0; i < total;) {

				// Pick which string to use, this will method will return 0 or 1
				int index = r.nextInt(2);			

				// See how many remaining characters from this string are required.
				int remaining = numOf[index];				
				
				if (remaining > 0) {
					String str = strs[index];
					sb.append(str.charAt(r.nextInt(str.length())));
					numOf[index] = remaining - 1;
					++i;
				}
			}

			return sb.toString();

		} catch ( IllegalArgumentException e) {
		
			

			throw e;
		}
	}
}

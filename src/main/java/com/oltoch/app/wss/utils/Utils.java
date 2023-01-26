package com.oltoch.app.wss.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random random = new SecureRandom();

	public String generateUserId(int length) {
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {

		StringBuilder out = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			String alphabets = "01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
			out.append(alphabets.charAt(random.nextInt(alphabets.length())));
		}
		return new String(out);
	}

}

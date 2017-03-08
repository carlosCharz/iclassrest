package com.wedevol.iclass.core.util;

import java.security.SecureRandom;
import java.util.UUID;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * Crypto Util Class
 * 
 * @author charz
 * 
 */
public class CryptoUtil {

	private static TimeBasedGenerator TB_GENERATOR = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	private static RandomBasedGenerator RD_GENERATOR = Generators.randomBasedGenerator();
	private static final String AVAILABLE_LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom RANDOM = new SecureRandom();

	public static UUID getRandomBasedUUID() {
		return RD_GENERATOR.generate();
	}

	public static String getRandomBasedUUIDString() {
		return getRandomBasedUUID().toString()
									.replace("-", "");
	}

	public static UUID getTimeBasedUUID() {
		return TB_GENERATOR.generate();
	}

	public static String getTimeBasedUUIDString() {
		return getTimeBasedUUID().toString()
									.replace("-", "");
	}

	public static String randomString(int size) {
		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < size; i++)
			sb.append(AVAILABLE_LETTERS.charAt(RANDOM.nextInt(AVAILABLE_LETTERS.length())));
		return sb.toString();
	}

}

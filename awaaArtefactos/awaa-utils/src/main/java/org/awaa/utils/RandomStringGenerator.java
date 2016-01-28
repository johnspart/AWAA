package org.awaa.utils;

import java.util.Calendar;
import java.util.Random;

public class RandomStringGenerator {
	private int length;
	private final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final Random rn = new Random();

	public RandomStringGenerator(int length) {
		if (length <= 0)
			throw new IllegalArgumentException("Length no puede ser menor o igual a 0");

		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		if (length <= 0)
			throw new IllegalArgumentException("Length no puede ser menor o igual a 0");
		this.length = length;
	}

	public String getRandomString() {
		StringBuilder sb = new StringBuilder(this.length);
		for (int i = 0; i < this.length; i++)
			sb.append(alphabet.charAt(rn.nextInt(alphabet.length())));

		return sb.toString();
	}

	public String getRandomStringAppendTime() {
		StringBuilder sb = new StringBuilder(this.length);
		sb.append(Calendar.getInstance().getTimeInMillis());
		for (int i = sb.length(); i < this.length; i++)
			sb.append(alphabet.charAt(rn.nextInt(alphabet.length())));

		return sb.toString();
	}
}

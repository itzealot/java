package com.apusic.sicp.util.service.impl;

import com.apusic.sicp.util.service.NumberGenerator;

public class DefaultNumberGeneratorImpl implements NumberGenerator {
	private int lastFixLength;
	private char padChar;

	public DefaultNumberGeneratorImpl(int lastFixLength, char padChar) {
		super();
		this.lastFixLength = lastFixLength;
		this.padChar = padChar;
	}

	public String generateNextNumber(String prefix, String lastfix) {
		return prefix + pad(this.padChar, lastFixLength, lastfix);
	}

	private String pad(char padchar, int size, String lastfix) {
		if (lastfix.length() > size) {
			throw new IllegalArgumentException(lastfix + " is too long!");
		}
		if (lastfix.length() == size) {
			return lastfix;
		}
		StringBuffer sbPads = new StringBuffer(256);
		sbPads.append("");
		for (int i = 0; i < size - lastfix.length(); i++) {
			sbPads.append(padchar);
		}
		return sbPads.toString() + lastfix;
	}

	public String getLatestNumber(String prefix, String lastfix) {
		throw new UnsupportedOperationException("");
	}

}

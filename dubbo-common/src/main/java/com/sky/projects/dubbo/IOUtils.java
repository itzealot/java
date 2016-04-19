package com.sky.projects.dubbo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class IOUtils {
	private static final int BUFFER_SIZE = 1024 * 8;

	private IOUtils() {
	}

	public static long write(InputStream is, OutputStream os) throws IOException {
		return write(is, os, BUFFER_SIZE);
	}

	public static long write(InputStream is, OutputStream os, int bufferSize) throws IOException {
		int read;
		long total = 0;
		byte[] buff = new byte[bufferSize];

		while (is.available() > 0) {
			read = is.read(buff, 0, buff.length);
			if (read > 0) {
				os.write(buff, 0, read);
				total += read;
			}
		}

		return total;
	}

	public static String read(Reader reader) throws IOException {
		StringWriter writer = new StringWriter();

		try {
			write(reader, writer);
			return writer.getBuffer().toString();
		} finally {
			writer.close();
		}
	}

	public static long write(Writer writer, String string) throws IOException {
		Reader reader = new StringReader(string);
		try {
			return write(reader, writer);
		} finally {
			reader.close();
		}
	}

	public static long write(Reader reader, Writer writer) throws IOException {
		return write(reader, writer, BUFFER_SIZE);
	}

	public static long write(Reader reader, Writer writer, int bufferSize) throws IOException {
		int read;
		long total = 0;
		char[] buf = new char[BUFFER_SIZE];
		while ((read = reader.read(buf)) != -1) {
			writer.write(buf, 0, read);
			total += read;
		}
		return total;
	}

	public static String[] readLines(File file) throws IOException {
		if (file == null || !file.exists() || !file.canRead())
			return new String[0];

		return readLines(new FileInputStream(file));
	}

	public static String[] readLines(InputStream is) throws IOException {
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String line;
			while ((line = reader.readLine()) != null)
				lines.add(line);
			return lines.toArray(new String[0]);
		} finally {
			reader.close();
		}
	}

	public static void writeLines(OutputStream os, String[] lines) throws IOException {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
		try {
			for (String line : lines)
				writer.println(line);
			writer.flush();
		} finally {
			writer.close();
		}
	}

	public static void writeLines(File file, String[] lines) throws IOException {
		if (file == null)
			throw new IOException("File is null.");

		writeLines(new FileOutputStream(file), lines);
	}

	public static void appendLines(File file, String[] lines) throws IOException {
		if (file == null)
			throw new IOException("File is null.");

		writeLines(new FileOutputStream(file, true), lines);
	}

}
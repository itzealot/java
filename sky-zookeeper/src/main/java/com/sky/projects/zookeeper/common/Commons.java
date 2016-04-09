package com.sky.projects.zookeeper.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.curator.utils.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;

public final class Commons {
	private static final Logger log = LoggerFactory.getLogger(Commons.class);

	private Commons() {
	}

	public static final String BASE = "";
	public static final char SEPARATOR = '/';
	public static final String ROOT_PATH = BASE + SEPARATOR;

	public static <T extends Serializable> byte[] serialize(T t) {
		checkNotNull(t, "t can not be null");

		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
		} catch (IOException e) {
			log.error("serialize an object has an error.", e);
		} finally {
			close(bos, oos);
		}

		return bos.toByteArray();
	}

	public static <T extends Serializable> void writeInto(T t, String file) {
		checkNotNull(t, "t can not be null");
		checkNotNull(file, "fileName can not be null");

		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
		} catch (IOException e) {
			log.error("write an object into file has an error.", e);
		} finally {
			close(fos, oos);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T readFrom(byte[] data) {

		checkNotNull(data, "data can not be null");

		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = null;
		T obj = null;
		try {
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			log.error("read an object from byte array has an error.", e);
		} finally {
			close(ois, bis);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T readFrom(String file) {
		checkNotNull(file, "fileName can not be null");

		FileInputStream bis = null;
		ObjectInputStream ois = null;
		T obj = null;

		try {
			bis = new FileInputStream(file);
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			log.error("read an object from file's name has an error.", e);
		} finally {
			close(ois, bis);
		}

		return obj;
	}

	public static Iterable<String> spilit(String source, String spiliter) {
		checkNotNull(source, "source can not be null");

		return Splitter.on(spiliter).trimResults().omitEmptyStrings().split(source);
	}

	public static void checkPath(String path) {
		PathUtils.validatePath(path);
	}

	public static void close(AutoCloseable... clos) {
		if (clos != null) {
			for (int i = 0; i < clos.length; i++) {
				try {
					if (clos[i] != null) {
						clos[i].close();
					}
				} catch (Exception e) {
					log.error("close the connection error.", e);
				}
			}
		}
	}
}

package com.apusic.skynet.zookeeper.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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

	/**
	 * To serialize the Object to byte array.
	 * 
	 * @param t
	 * @return
	 */
	public static <T extends Serializable> byte[] serialize(T t) {

		checkNotNull(t, "t can not be null");

		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
			log.error("serialize an object has an error.", e);
		} finally {
			close(bos, oos);
		}
		return bos.toByteArray();
	}

	/**
	 * To write the Object into file by file's name
	 * 
	 * @param t
	 * @param fileName
	 */
	public static <T extends Serializable> void write(T t, String fileName) {
		checkNotNull(t, "t can not be null");
		checkNotNull(fileName, "fileName can not be null");

		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
			log.error("write an object into file has an error.", e);
		} finally {
			close(fos, oos);
		}
	}

	/**
	 * To read object from byte array.
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T read(byte[] data) {

		checkNotNull(data, "data can not be null");

		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = null;
		T obj = null;
		try {
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			//
			e.printStackTrace();
			log.error("read an object from byte array has an error.", e);
		} finally {
			close(ois, bis);
		}
		return obj;
	}

	/**
	 * To read object from file by file's name.
	 * 
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T read(String fileName) {

		checkNotNull(fileName, "fileName can not be null");

		FileInputStream bis = null;
		ObjectInputStream ois = null;
		T obj = null;
		try {
			bis = new FileInputStream(fileName);
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			//
			e.printStackTrace();
			log.error("read an object from file's name has an error.", e);
		} finally {
			close(ois, bis);
		}
		return obj;
	}

	/**
	 * To split the String
	 * 
	 * @param source
	 * @param spiliter
	 * @return
	 */
	public static Iterable<String> spilit(String source, String spiliter) {
		checkNotNull(source, "source can not be null");

		return Splitter.on(spiliter).trimResults().omitEmptyStrings().split(source);
	}

	/**
	 * 获取路径的子路径
	 * 
	 * @param path
	 * @return
	 */
	public static String subPath(String path) {
		checkNotNull(path, "path can not be null");

		String source = path;
		if (source.charAt(0) == Commons.SEPARATOR) {
			source = source.substring(1);
		}

		int index = source.indexOf(Commons.SEPARATOR);

		// don't have sub path
		if (index == -1) {
			return "";
		}
		return source.substring(index);
	}

	public static String getParentPath(String path) {
		if (path == null || Commons.ROOT_PATH.equals(path)) {
			return null;
		}
		String source = path;

		if (source.charAt(source.length() - 1) == Commons.SEPARATOR) {
			source = path.substring(0, source.length() - 1);
		}
		int index = source.lastIndexOf(Commons.ROOT_PATH);

		if (index == 0) {
			return Commons.ROOT_PATH;
		}
		return source.substring(0, index);
	}

	/**
	 * 获取路径中第一个目录名称
	 * 
	 * @param path
	 * @return
	 */
	public static String firstDirName(String path) {
		String source = path;

		if (source.charAt(0) == Commons.SEPARATOR) {
			source = source.substring(1);
		}

		int index = source.indexOf(Commons.SEPARATOR);

		if (index == -1) {
			return path;
		}
		return source.substring(0, index);
	}

	/**
	 * 获取路径中目录名称
	 * 
	 * @param path
	 * @return
	 */
	public static String dirName(String path) {
		String source = path;

		if (source.charAt(source.length() - 1) == Commons.SEPARATOR) {
			source = path.substring(0, source.length() - 1);
		}
		int index = source.lastIndexOf(Commons.ROOT_PATH);
		return source.substring(index + 1);
	}

	/**
	 * 获取路径中第一个路径名称
	 * 
	 * @param path
	 * @return
	 */
	public static String firstPath(String path) {
		String source = firstDirName(path);

		if (source.charAt(0) == Commons.SEPARATOR) {
			return source;
		}

		return Commons.ROOT_PATH + source;
	}

	/**
	 * 是否包含多个路径
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isMulityPath(String path) {
		String source = subPath(path);

		if ("".equals(source) || "/".equals(source)) {
			return false;
		}

		return true;
	}

	/**
	 * To close the connections
	 * 
	 * @param clos
	 */
	public static void close(Closeable... clos) {
		if (clos != null) {
			for (int i = 0; i < clos.length; i++) {
				try {
					if (clos[i] != null) {
						clos[i].close();
					}
				} catch (IOException e) {
					log.error("close the connection error.", e);
				}
			}
		}
	}
}

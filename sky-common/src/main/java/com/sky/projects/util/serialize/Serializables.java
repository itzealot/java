package com.sky.projects.util.serialize;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.sky.projects.util.common.Closeables.close;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化工具类
 * 
 * @author zt
 */
public final class Serializables {
	private static final Logger LOG = LoggerFactory.getLogger(Serializables.class);

	/**
	 * To serialize the Object to byte array.
	 * 
	 * 序列化一个对象
	 * 
	 * @param t
	 * @return
	 */
	public static <T extends Serializable> byte[] serialize(T t) {
		checkNotNull(t, "t can not be null");

		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] bytes = null;

		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
			bytes = bos.toByteArray();
		} catch (IOException e) {
			LOG.error("serialize an object error.", e);
		} finally {
			close(bos, oos);
		}

		return bytes;
	}

	/**
	 * To write the Object into file
	 * 
	 * 序列化一个对象到文件
	 * 
	 * @param t
	 * @param file
	 */
	public static <T extends Serializable> void write(T t, File file) {
		checkNotNull(t, "t can not be null");
		checkNotNull(file, "file can not be null");

		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
		} catch (IOException e) {
			LOG.error("write an object into file error, path : {}, {}", file.getAbsolutePath(), e);
		} finally {
			close(fos, oos);
		}
	}

	/**
	 * To read object from byte array.
	 * 
	 * 从序列化的数组中读取对象
	 * 
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T read(byte[] bytes) {
		checkNotNull(bytes, "bytes can not be null");

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		T obj = null;

		try {
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			LOG.error("read an object from bytes error.", e);
		} finally {
			close(ois, bis);
		}

		return obj;
	}

	/**
	 * To read object from file by file's name.
	 * 
	 * 从文件中读取序列化对象
	 * 
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T read(File file) {
		checkNotNull(file, "file can not be null");

		FileInputStream bis = null;
		ObjectInputStream ois = null;
		T obj = null;

		try {
			bis = new FileInputStream(file);
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			LOG.error("read an object from file error, path : {}, {}", file.getAbsolutePath(), e);
		} finally {
			close(ois, bis);
		}

		return obj;
	}

	private Serializables() {
	}

}

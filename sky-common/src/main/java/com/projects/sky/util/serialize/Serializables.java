package com.projects.sky.util.serialize;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.projects.sky.util.common.Closeables;

/**
 * 序列化工具类
 * 
 * @author zt
 */
public final class Serializables {

	private Serializables() {
	}

	/**
	 * 序列化对象为字节数组
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
		} finally {
			Closeables.close(bos, oos);
		}

		return bos.toByteArray();
	}

	/**
	 * 将序列化的对象写入文件中
	 * 
	 * @param t
	 * @param file
	 */
	public static <T extends Serializable> void write(T t, String file) {
		checkNotNull(t, "t can not be null");
		checkNotNull(file, "fileName can not be null");

		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} finally {
			Closeables.close(fos, oos);
		}
	}

	/**
	 * 从序列化的字节数组中读取对象并返回，读取失败，则返回null
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
			// TODO
			e.printStackTrace();
		} finally {
			Closeables.close(ois, bis);
		}

		return obj;
	}

	/**
	 * 从序列化的文件中读取对象并返回，读取失败，则返回null
	 * 
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T read(String file) {
		checkNotNull(file, "fileName can not be null");

		FileInputStream bis = null;
		ObjectInputStream ois = null;

		T obj = null;

		try {
			bis = new FileInputStream(file);
			ois = new ObjectInputStream(bis);
			obj = (T) ois.readObject();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			Closeables.close(ois, bis);
		}

		return obj;
	}
}

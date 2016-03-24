package com.projects.sky.zookeeper.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * The Common serialize or deserialize util named Commons
 * 
 * @author zt
 * @date 2016年1月5日
 */
public final class Commons {

	private Commons() {
	}

	/**
	 * To serialize <T> to bytes
	 * 
	 * @param t
	 * @return
	 */
	public static <T> byte[] serialize(T t) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
			return bos.toByteArray();
		} catch (IOException e) {
			// TODO
		} finally {
			close(oos, bos);
		}
		return null;
	}

	/**
	 * To deserialize to <T> from bytes
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] data) {
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
			close(ois, bis);
		}
		return obj;
	}

	/**
	 * 连接路径
	 * 
	 * @param path
	 * @return
	 */
	public static String joinPath(String... path) {
		StringBuffer stringBuffer = new StringBuffer();
		for (String str : path) {
			stringBuffer.append(str);
		}
		return stringBuffer.toString();
	}

	/**
	 * 把code转换为路径
	 * 
	 * @param code
	 * @return
	 */
	public static String getPathByCode(String code) {
		return code.replace("-", "/");
	}

	/**
	 * 根据路径获取code
	 * 
	 * @param root
	 * @param path
	 * @return
	 */
	public static String getCodeByPath(String root, String path) {
		return path.replace(root, "").replace("/", "-");
	}

	public static void close(OutputStream... outs) {
		if (outs != null) {
			try {
				for (OutputStream out : outs) {
					if (out != null) {
						out.close();
					}
				}
			} catch (IOException e) {
				// TODO
			}
		}
	}

	public static void close(OutputStream out, InputStream in, Reader reader,
			Writer writer) {
		try {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
		} catch (IOException e) {
			// TODO
		}
	}

	public static void close(InputStream... ins) {
		if (ins != null) {
			try {
				for (InputStream in : ins) {
					if (in != null) {
						in.close();
					}
				}
			} catch (IOException e) {
				// TODO
			}
		}
	}
}

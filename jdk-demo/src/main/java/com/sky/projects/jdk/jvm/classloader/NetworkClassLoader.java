package com.sky.projects.jdk.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 因此通过覆盖loadClass或findClass方法来实现自定义的方式加载类
 * 
 * @author zealot
 */
public class NetworkClassLoader extends ClassLoader {

	private String host;
	private int port;

	public NetworkClassLoader(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = loadClassData(name);

		if (bytes == null) {
			throw new ClassNotFoundException("findClass error, className:" + name);
		}

		return defineClass(name, bytes, 0, bytes.length);
	}

	/**
	 * 根据 host, port, className 下载对应的 .class 文件，并生成字节数组
	 * 
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name) {
		InputStream is = null;

		try {
			String path = className2Path(name);
			URL url = new URL(path);
			byte[] buff = new byte[1024 * 4];
			int len = -1;
			is = url.openStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while ((len = is.read(buff)) != -1) {
				baos.write(buff, 0, len);
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private String className2Path(String name) {
		return host + ":" + port + "/" + name.replace(".", "/") + ".class";
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
}
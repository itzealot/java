package com.zt.test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描包下的所有类
 * 
 * @author a
 *
 */
public class ScanClassInPackageUtil {

	public static void main(String[] args) throws Exception {

		/**
		 * 通过类的Class对象获取Package对象
		 */
		Package pck = ScanClassInPackageUtil.class.getPackage();

		// 获取包名
		String packname = pck.getName();
		System.out.println("package's name : " + packname);

		// 返回要扫描包的Set集合，其集合中的元素为Class对象
		Set<Class<?>> clazzs = findFileClass(packname.substring(0, packname.indexOf(".")));
		System.out.println();

		// 遍历所有Set 集合
		for (Class<?> clazz : clazzs) {
			System.out.println("name : " + clazz.getName());
			System.out.println("annotation's length : " + clazz.getAnnotations().length);

			// 输出superClass的名称
			if (clazz.getSuperclass() != null) {
				System.out.println("SuperClass name : " + clazz.getSuperclass().getName());
			}
			System.out.println();
		}
	}

	/**
	 * 返回要扫描包的Set集合，其集合中的元素为Class对象
	 * 
	 * @param packName
	 *            要扫描的包名
	 * @return
	 */
	public static Set<Class<?>> findFileClass(String packName) {
		Set<Class<?>> clazzs = new LinkedHashSet<Class<?>>();

		// 使用'/'代替'.'运运算符
		String packageDirName = packName.replace('.', '/');
		Enumeration<URL> dirs = null;
		try {

			// 获取当前线程的类加载器并获取包目录下的所有资源URL的枚举
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

			// 遍历资源URL的枚举列表
			while (dirs.hasMoreElements()) {

				// 获取下一个URL枚举节点
				URL url = dirs.nextElement();

				// 获取url的协议
				String protocol = url.getProtocol();

				// 扫描file包中的类
				if ("file".equals(protocol)) {

					// 获取文件路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

					// 获取文件中的Class
					getFileClass(packName, filePath, clazzs);
				} else if ("jar".equals(protocol)) {// 扫描jar包中的类
					JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();

					// 获取Jar包中的Class并添加到Set集合clazzs中
					getJarClass(jarFile, packageDirName, clazzs);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return clazzs;
	}

	/**
	 * 获取文件中的class并添加到Set集合clazzs中
	 * 
	 * @param packName
	 *            包名
	 * @param filePath
	 *            文件路径
	 * @param clazzs
	 *            Set集合
	 */
	public static void getFileClass(String packName, String filePath, Set<Class<?>> clazzs) {

		// 创建目录
		File dir = new File(filePath);
		if (!dir.exists() || !dir.isDirectory()) {
			System.out.println("包目录不存在!");
			return;
		}

		// 通过FileFilter类来过滤文科与目录
		File[] dirFiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				boolean acceptDir = file.isDirectory();// 接受dir目录
				boolean acceptClass = file.getName().endsWith(".class");// 接受class文件
				return acceptDir || acceptClass;
			}
		});

		// 遍历文件列表
		for (File file : dirFiles) {

			//
			if (file.isDirectory()) {
				getFileClass(packName + "." + file.getName(), file.getAbsolutePath(), clazzs);
			} else {

				// 获取.java的类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {

					// 形成类名(包名.类名)
					Class<?> clazz = Thread.currentThread().getContextClassLoader()
							.loadClass(packName + "." + className);

					// 添加到Set集合中
					clazzs.add(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取jar中的class
	 * 
	 * @param jarFile
	 * @param filepath
	 * @param clazzs
	 * @throws IOException
	 */
	public static void getJarClass(JarFile jarFile, String filepath, Set<Class<?>> clazzs) throws IOException {
		List<JarEntry> jarEntryList = new ArrayList<JarEntry>();
		Enumeration<JarEntry> enumes = jarFile.entries();
		while (enumes.hasMoreElements()) {
			JarEntry entry = (JarEntry) enumes.nextElement();
			// 过滤出满足我们需求的东西
			if (entry.getName().startsWith(filepath) && entry.getName().endsWith(".class")) {
				jarEntryList.add(entry);
			}
		}
		for (JarEntry entry : jarEntryList) {
			String className = entry.getName().replace('/', '.');
			className = className.substring(0, className.length() - 6);
			try {
				clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(className));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
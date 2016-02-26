package com.zt.test.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * ZIP压缩工具类
 * 
 * @author leen
 * 
 */
public class Zips {

	static final int BUFFER = 1024;

	public static void compressToZip(String dirpath, String savaAsName, List<File> files) {
		ZipOutputStream zos = null;
		try {
			File dir = new File(dirpath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String zipFileFullName = dirpath + File.separator + savaAsName;
			zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileFullName)));
			for (File file : files) {
				Zips.addToZip(zos, file, file.getName());
			}
			zos.flush();
		} catch (FileNotFoundException e) {
			System.out.println("compress file error!");

			throw new RuntimeException("压缩文件过程中发生错误！", e);
		} catch (IOException e) {
			System.out.println("compress file error!");

			throw new RuntimeException("压缩文件过程中发生错误！", e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					// TODO
					System.out.println("元数据文件打包时关闭输入流失败！");
				}
			}
		}
	}

	private static void addToZip(ZipOutputStream out, File file, String base) {
		if (file.isDirectory()) {// 文件夹，递归
			base = base.length() == 0 ? "" : base + File.separator;
			File[] tempFiles = file.listFiles();
			for (int i = 0; i < tempFiles.length; i++) {
				addToZip(out, tempFiles[i], base + tempFiles[i].getName());
			}
		} else {// 文件，压缩
			byte[] buff = new byte[BUFFER];
			int bytesRead = -1;
			ZipEntry entry = new ZipEntry(base);
			InputStream in = null;
			try {
				out.putNextEntry(entry);
				in = new BufferedInputStream(new FileInputStream(file));
				while (-1 != (bytesRead = in.read(buff, 0, buff.length))) {
					out.write(buff, 0, bytesRead);
				}
			} catch (FileNotFoundException e) {
				System.out.println("压缩文件时发生错误！");
			} catch (IOException e) {
				System.out.println("压缩文件时发生错误！");
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO
						System.out.println("元数据文件打包时关闭输入流失败！");
					}
				}
			}
		}
	}

	public static boolean hasFile(File file) {
		if (file.isDirectory()) {
			File[] tempFiles = file.listFiles();
			for (int i = 0; i < tempFiles.length; i++) {
				if (hasFile(tempFiles[i])) {
					return true;
				}
			}
		} else {
			return true;
		}

		return false;
	}

}

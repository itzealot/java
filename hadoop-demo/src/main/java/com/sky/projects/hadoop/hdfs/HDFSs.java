package com.sky.projects.hadoop.hdfs;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDFSs {

	private static final Logger LOG = LoggerFactory.getLogger(HDFSs.class);

	public static final int BUFFER_SIZE = 1024;

	public static final int BUFFER_SIZE_10X = BUFFER_SIZE * 10;

	public static Configuration getConfig(String hdfsName) {
		Configuration conf = new Configuration();
		
		conf.set("fs.default.name", hdfsName);

		return conf;
	}

	public static Configuration getConfig() {
		return new Configuration();
	}

	/**
	 * 在HDFS上创建目录
	 * 
	 * @param path
	 * @return
	 */
	public static boolean mkdirs(String path, String hdfsName) {
		FileSystem hdfs = null;

		try {
			hdfs = FileSystem.get(getConfig(hdfsName));
			Path dfs = new Path(path);
			hdfs.mkdirs(dfs);

			return true;
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 从HDFS上读取文件保持到本地
	 * 
	 * @param hdfs
	 *            hdfs文件目录
	 * @param dest
	 *            本地文件
	 */
	public static void readFrom(String hdfs, String dest) {
		FileSystem fs = null;
		FSDataInputStream hdfsInStream = null;
		OutputStream out = null;

		try {
			fs = FileSystem.get(URI.create(hdfs), getConfig());

			hdfsInStream = fs.open(new Path(hdfs));

			out = new FileOutputStream(dest);

			byte[] ioBuffer = new byte[BUFFER_SIZE];
			int len = -1;

			while ((len = hdfsInStream.read(ioBuffer)) != -1) {
				out.write(ioBuffer, 0, len);
			}
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} finally {
			close(out, hdfsInStream, fs);
		}

	}

	private static void close(Closeable... closeables) {
		if (closeables != null) {
			int len = closeables.length;

			for (int i = 0; i < len; i++) {
				if (closeables[i] != null) {
					try {
						closeables[i].close();
					} catch (IOException e) {
						// TODO
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void deleteFrom(String hdfs) {
		URI uri = URI.create(hdfs);
		FileSystem fs = null;

		try {
			fs = FileSystem.get(uri, getConfig());
			fs.deleteOnExit(new Path(uri));
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} finally {
			close(fs);
		}
	}

	/**
	 * 遍历HDFS上的文件和目录
	 * 
	 * @param hdfs
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> getDirectoryFrom(String hdfs) {
		URI uri = URI.create(hdfs);
		List<String> lists = new ArrayList<String>();

		FileSystem fs = null;
		try {
			fs = FileSystem.get(uri, getConfig());
			FileStatus fileList[] = fs.listStatus(new Path(uri));

			int size = fileList.length;

			for (int i = 0; i < size; i++) {
				lists.add(fileList[i].getPath().getName());

				// 获取文件大小
				fileList[i].getLen();
			}
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} finally {
			close(fs);
		}

		return lists;
	}

	/**
	 * 移动
	 * 
	 * @param oldPath
	 *            原来存放的路径
	 * @param newPath
	 *            移动到的路径
	 */
	public static boolean moveFileName(String oldPath, String newPath) {
		FileSystem fs = null;

		try {
			// 下载到服务器本地
			String tempdir = "/home/hadoop/temp" + File.separator + getTempDir();

			File tempFilePath = new File(tempdir);

			if (!tempFilePath.exists()) {
				tempFilePath.mkdir();
			}

			boolean downFlag = downFrom(oldPath, tempdir);

			// 删除源文件
			URI uri = URI.create(oldPath);
			fs = FileSystem.get(uri, getConfig());
			Path hdfs = new Path(uri);

			fs.delete(hdfs, false);

			// 从服务器本地传到新路径
			newPath = newPath + oldPath.substring(oldPath.lastIndexOf("/"));
			boolean uplodFlag = sendTo(tempdir, newPath);

			// 应该要 delete temp

			if (downFlag && uplodFlag) {
				return true;
			}

		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			close(fs);
		}

		return false;
	}

	/**
	 * 上传，将本地文件copy到hdfs系统中
	 * 
	 * @param localSrc
	 *            本地的文件地址，即文件的路径
	 * @param hdfsDest
	 *            存放在hdfs的文件地址
	 */
	public static boolean sendTo(String localSrc, String hdfsDest) {
		InputStream in = null;
		FileSystem fs = null;

		try {
			in = new BufferedInputStream(new FileInputStream(localSrc));

			URI uri = URI.create(hdfsDest);

			fs = FileSystem.get(uri, getConfig());

			OutputStream out = fs.create(new Path(uri), new Progressable() {
				public void progress() {
					// System.out.println("上传完一个设定缓存区大小容量的文件！");
				}
			});

			// 连接两个流，形成通道，使输入流向输出流传输数据,
			IOUtils.copyBytes(in, out, BUFFER_SIZE_10X, true);

			return true;
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} finally {
			close(fs);
		}

		return false;
	}

	/**
	 * 下载,将hdfs文件下载到本地磁盘
	 * 
	 * @param localDest
	 *            本地的文件地址，即文件的路径
	 * @param hdfsSrc
	 *            存放在hdfs的文件地址
	 */
	public static boolean downFrom(String hdfsSrc, String localDest) {
		FileSystem fs = null;

		try {
			fs = FileSystem.get(URI.create(hdfsSrc), getConfig());
			Path hdfs = new Path(hdfsSrc);
			Path local = new Path(localDest);

			fs.copyToLocalFile(hdfs, local);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fs);
		}
		return false;
	}

	public static String getTempDir() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 将HDFS上文件重新命名
	 * 
	 * @param conf
	 * @param src
	 * @param dst
	 * @return
	 * @throws IOException
	 */
	public static boolean renameFrom(Configuration conf, String src, String dst) throws IOException {
		boolean returnFlag = false;
		FileSystem fs = null;

		try {
			fs = FileSystem.get(conf);

			Path srcPath = new Path(src);
			Path dstPath = new Path(dst);

			if (fs.exists(srcPath)) {
				if (fs.exists(dstPath)) {
					fs.delete(dstPath, true);
				} else if (!fs.exists(dstPath.getParent())) {
					fs.mkdirs(dstPath.getParent());
				}

				returnFlag = fs.rename(srcPath, dstPath);
			} else {
				returnFlag = true;
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			close(fs);
		}

		return returnFlag;
	}

	/**
	 * 文件检测并删除
	 * 
	 * @param path
	 * @param conf
	 * @return
	 */
	public static boolean checkAndDel(final String path, Configuration conf) {
		Path dstPath = new Path(path);
		FileSystem hdfs = null;

		try {
			hdfs = dstPath.getFileSystem(conf);
			if (hdfs.exists(dstPath)) {
				hdfs.delete(dstPath, true);
			} else {
				return true;
			}
		} catch (IOException ie) {
			LOG.error("checkAndDel error:  " + ie.getMessage());
			return false;
		} finally {
			close(hdfs);
		}

		return true;
	}

	public static void setReplication(String hadoopFs, String inputPath, short repNum) {
		String hadoopOwner = "anonymous";
		System.setProperty("HADOOP_USER_NAME", hadoopOwner);
		Configuration conf = new Configuration();

		conf.set("fs.defaultFS", hadoopFs);
		conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);

			Path path = new Path(inputPath);

			if (fs.exists(path)) {
				FileStatus[] fileStatus = fs.listStatus(path);
				if (fileStatus != null) {
					for (FileStatus eleFs : fileStatus) {
						fs.setReplication(eleFs.getPath(), repNum);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("setReplication error , inputPath is " + inputPath, e);
		} finally {
			close(fs);
		}
	}

}

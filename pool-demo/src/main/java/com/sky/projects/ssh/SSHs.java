package com.sky.projects.ssh;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public final class SSHs {

	private static final int SSH_PORT = 22;

	private SSHs() {
	}

	/**
	 * 获取 SFTP 连接
	 * 
	 * @param host
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static Connection connectionOf(String host, String password) throws RuntimeException {
		Connection conn = null;
		boolean isAuthed = false;

		try {
			conn = new Connection(host, SSH_PORT);
			conn.connect();

			// 认证
			isAuthed = conn.authenticateWithPassword("root", password);
		} catch (Exception e) {
			throw new RuntimeException("登陆" + host + "失败， 验证信息user:root，password:" + password, e);
		}

		if (!isAuthed) {
			throw new RuntimeException("登陆" + host + "失败， 验证信息user:root，password:" + password);
		}

		return conn;
	}

	/**
	 * 根据执行的 cmd 返回相应的结果
	 * 
	 * @param host
	 * @param password
	 * @param cmd
	 * @return
	 */
	public static String resultOf(String host, String password, String cmd) {
		Session session = null;
		InputStream in = null;
		Connection conn = null;
		StringBuffer buffer = null;
		BufferedReader br = null;

		try {
			conn = connectionOf(host, password);

			session = conn.openSession();

			session.execCommand(cmd);

			in = new StreamGobbler(session.getStdout());

			br = new BufferedReader(new InputStreamReader(in));

			String line = null;

			buffer = new StringBuffer();

			while ((line = br.readLine()) != null) {
				buffer.append(line.trim());
				buffer.append("\n");
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			close(session, conn, br);
		}

		return buffer.toString();
	}

	private static void close(Session session, Connection conn, Closeable clo) {
		if (clo != null) {
			try {
				clo.close();
			} catch (IOException e) {
				// TODO
			}
		}

		if (session != null)
			session.close();

		if (conn != null)
			conn.close();
	}
}

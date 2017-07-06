package com.sky.projects.jdk.ssh;

import java.util.Map;

import com.google.common.collect.Maps;

import io.parallec.core.ParallecResponseHandler;
import io.parallec.core.ParallelClient;
import io.parallec.core.ParallelTaskBuilder;
import io.parallec.core.ResponseOnSingleTask;

/**
 * 
 * @author zealot
 *
 */
public final class SSHContext {
	private ParallelTaskBuilder taskBuilder;

	private SSHContext() {
		// 初始化 ParallelTaskBuilder
		taskBuilder = new ParallelClient().prepareSsh().setConcurrency(150);
	}

	public static SSHContext getInstance() {
		return SSHContextNest.instance;
	}

	private static class SSHContextNest {
		private static SSHContext instance = new SSHContext();
	}

	/**
	 * 发送SSH命令,
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param command
	 * @return map for:status:状态码,content:请求返回内容
	 */
	public Map<String, String> sendSSHCommand(String host, Integer port, String username, String password,
			String command) {
		Map<String, String> result = Maps.newHashMap();
		taskBuilder.setTargetHostsFromString(host).setSshPort(port).setSshUserName(username).setSshPassword(password)
				.setSshCommandLine(command).execute(new ParallecResponseHandler() {
					@Override
					public void onCompleted(ResponseOnSingleTask res, Map<String, Object> responseContext) {
						result.put("status", res.getStatusCode());
						result.put("content", res.getResponseContent());
					}
				});
		return result;
	}

}

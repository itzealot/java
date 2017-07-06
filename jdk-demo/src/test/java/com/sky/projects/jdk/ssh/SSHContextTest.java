package com.sky.projects.jdk.ssh;

import com.sky.projects.jdk.ssh.SSHContext;

import io.parallec.core.FilterRegex;
import io.parallec.core.ParallelClient;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SSHContextTest extends TestCase {

	public SSHContextTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(SSHContextTest.class);
	}

	public void testSendSSHCommand() {
		String host = "";
		Integer port = 22;
		String username = "";
		String password = "";
		String command = "df -h; uptime";
		System.out.println(SSHContext.getInstance().sendSSHCommand(host, port, username, password, command));
	}

	public void testHttp() {
		ParallelClient pc = new ParallelClient();

		pc.prepareHttpGet("/validateInternals.html").setConcurrency(100)
				.setTargetHostsFromString("www.parallec.io www.jeffpei.com www.restcommander.com")
				.execute((res, responseContext) -> {
					String cpu = new FilterRegex(".*<td>CPU-Usage-Percent</td>\\s*<td>(.*?)</td>.*")
							.filter(res.getResponseContent());
					System.out.println("cpu:" + cpu + ", host: " + res.getHost());
				});

		pc.releaseExternalResources();
	}
}

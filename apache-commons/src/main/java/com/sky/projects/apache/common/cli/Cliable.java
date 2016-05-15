package com.sky.projects.apache.common.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public interface Cliable {

	/**
	 * 初始化 Options
	 * 
	 * @return
	 */
	public Options initOptions();

	/**
	 * 校验 CommandLine
	 * 
	 * @param commandLine
	 * @return
	 */
	public boolean validateOptions(CommandLine commandLine);

	/**
	 * 启动 CommandLine
	 * 
	 * @param commandLine
	 */
	public void start(CommandLine commandLine);
}
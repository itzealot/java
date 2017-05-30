package com.sky.projects.apache.common.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public interface Cliable {

	/**
	 * 初始化 Options
	 * 
	 * @return
	 */
	Options initOptions();

	/**
	 * 校验 CommandLine
	 * 
	 * @param commandLine
	 * @return
	 */
	boolean validateOptions(CommandLine commandLine);

	/**
	 * 启动 CommandLine
	 * 
	 * @param commandLine
	 */
	void start(CommandLine commandLine);
}
package com.sky.projects.apache.common.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.junit.Test;

public class TestCliOptions {

	@Test
	public void test() {
		String[] arg = { "-h", "-c", "config.xml" };
		parseOptions(arg);
	}

	/**
	 * 解析相应的参数
	 * 
	 * @param args
	 */
	public static void parseOptions(String[] args) {
		Options options = new Options();

		// 添加 -h 参数
		Option opt = new Option("h", "help", false, "Print help");
		opt.setRequired(false);
		options.addOption(opt);

		// 添加 -c 参数
		opt = new Option("c", "configFile", true, "Name server config properties file");
		opt.setRequired(false);
		options.addOption(opt);

		// 添加 -p 参数
		opt = new Option("p", "printConfigItem", false, "Print all config item");
		opt.setRequired(false);
		options.addOption(opt);

		HelpFormatter hf = new HelpFormatter();
		hf.setWidth(110);
		CommandLine commandLine = null;
		CommandLineParser parser = new PosixParser();

		try {
			// 解析参数
			commandLine = parser.parse(options, args);

			// 操作中
			if (commandLine.hasOption('h')) {
				// 打印使用帮助
				hf.printHelp("testApp", options, true);
			}

			// 打印opts的名称和值
			System.out.println("--------------------------------------");

			Option[] opts = commandLine.getOptions();
			if (opts != null) {
				for (Option opt1 : opts) {
					// 获取 longOpt
					String name = opt1.getLongOpt();

					// 获取 optionValue
					String value = commandLine.getOptionValue(name);
					System.out.println(name + " =>" + value);
				}
			}
		} catch (ParseException e) {
			hf.printHelp("testApp", options, true);
		}
	}
}
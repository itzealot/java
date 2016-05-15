package com.sky.projects.apache.common.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.junit.Test;

/**
 * Apache Commons CLI library为用户提供了一个解释命令行的API.它在解释命令行时主要有三个状态，即：定义、解释和询问交互。
 * 
 * 综合使用场景为: <br />
 * gmkdir -h --可以查看该命令的帮助信息；
 * 
 * gmkdir -v testmkdir 或 gmkdir testmkdir -v
 * 
 * gmkdir -v -p testmkdir/test1/test2/test3 或 gmkdir testmkdir/test1/test2/test3
 * -v -p
 * 
 * gmkdir -O test.txt testmkdir -v 或 gmkdir testmkdir -O test.txt -v
 * 
 * gmkdir --block-size=10 testmkdir -v 或 gmkdir testmkdir -v --block-size=10
 * 
 * gmkdir --block-size=10 testmkdir/test1/test2/test3 -p
 * 
 * @author zt
 *
 */
public class Mkdir {

	@Test
	public void testMkdir() {
		String[] args1 = { "gmkdir", "-v" };
		makdir(args1);

		String[] args = { "gmkdir", "-h" };
		makdir(args);
	}

	public static void makdir(String[] args) {
		Options opt = new Options();
		opt.addOption("p", false, "no error if existing, " + "make parent directories as needed.");
		opt.addOption("v", "verbose", false, "explain what is being done.");

		OptionBuilder.withArgName("file");
		OptionBuilder.hasArg();
		OptionBuilder.withDescription("search for buildfile towards the root of the filesystem and use it");
		opt.addOption(OptionBuilder.create("O"));

		// build
		OptionBuilder.withLongOpt("block-size");
		OptionBuilder.withDescription("use SIZE-byte blocks");
		OptionBuilder.withValueSeparator('=');
		OptionBuilder.hasArg();
		opt.addOption(OptionBuilder.create());

		opt.addOption("h", "help", false, "print help for the command.");

		String formatstr = "gmkdir [-p][-v/--verbose][--block-size][-h/--help] DirectoryName";

		HelpFormatter formatter = new HelpFormatter();
		CommandLineParser parser = new PosixParser();
		CommandLine cl = null;

		try {
			// 处理Options和参数
			cl = parser.parse(opt, args);
		} catch (ParseException e) {
			formatter.printHelp(formatstr, opt); // 如果发生异常，则打印出帮助信息
		}

		// 如果包含有-h或--help，则打印出帮助信息
		if (cl.hasOption("h")) {
			HelpFormatter hf = new HelpFormatter();
			hf.printHelp(formatstr, "", opt, "");
			return;
		}

		// 判断是否有-p参数
		if (cl.hasOption("p")) {
			System.out.println("has p");
		}

		// 判断是否有-v或--verbose参数
		if (cl.hasOption("v")) {
			System.out.println("has v");
		}

		// 获取参数值，这里主要是 DirectoryName
		String[] str = cl.getArgs();
		int length = str.length;
		System.out.println("length= " + length);
		System.out.println("Str[0]= " + str[0]);

		// 判断是否含有block-size参数
		if (cl.hasOption("block-size")) {
			// print the value of block-size
			System.out.println("block-size=" + cl.getOptionValue("block-size"));
		}
	}
}

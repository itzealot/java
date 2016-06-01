package com.sky.projects.hadoop.demo.news;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper 即进行映射阶段，映射 key 及 value 初始值
 * 
 * @author zt
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final IntWritable one = new IntWritable(1);

	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// 行内容
		String line = value.toString();

		StringTokenizer token = new StringTokenizer(line);

		// 对文本中的一行进行单词的统计
		while (token.hasMoreTokens()) {

			// 设置单词的 Text 中作为 key
			word.set(token.nextToken());

			// 设置单词的初始计数值为 1
			// set KeyOut and ValueOut
			context.write(word, one);
		}
	}
}
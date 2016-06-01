package com.sky.projects.hadoop.demo.news;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 合并阶段,根据 key 与 value合并为新的 key 与 value
 * 
 * @author zt
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;

		for (IntWritable value : values) {
			sum += value.get();
		}

		// set the keyOut and valueOut
		context.write(key, new IntWritable(sum));
	}
}
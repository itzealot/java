package com.sky.projects.hadoop.demo.old;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/***
 * class WordCountReducer extends MapReduceBase implements Reducer<Text,
 * IntWritable, Text, IntWritable>
 * 
 * @author zt
 *
 */
public class WordCountReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

	/**
	 * method : reduce
	 * 
	 * @param output
	 *            OutputCollector<Text, IntWritable> output
	 * @param key
	 *            Text key
	 * @param values
	 *            Iterator<IntWritable> values
	 */
	@Override
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
			Reporter reporter) throws IOException {
		int sum = 0;

		// 遍历所有的 values 即 Key : Value 中的Value,即单词统计计数
		while (values.hasNext()) {
			sum += values.next().get();
		}

		// 将统计好的和根据 OutputCollector 对象的collect 方法与 key 进行收集
		output.collect(key, new IntWritable(sum));
	}

}
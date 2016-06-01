package com.sky.projects.hadoop.demo.old;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * WordCountMapper extends MapReduceBase implements Mapper<LongWritable, Text,
 * Text, IntWritable>
 * 
 * MapReduceBase类:实现了Mapper和Reducer接口的基类（其中的方法只是实现接口，而未作任何事情） Mapper接口：
 * WritableComparable接口：实现WritableComparable的类可以相互比较。所有被用作key的类应该实现此接口。 Reporter
 * 则可用于报告整个应用的运行进度，本例中未使用。
 * 
 */
public class WordCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	/**
	 * LongWritable, IntWritable, Text 均是 Hadoop 中实现的用于封装 Java 数据类型的类，这些类实现了
	 * WritableComparable 接口， 都能够被串行化从而便于在分布式环境中进行数据交换，你可以将它们分别视为long,int,String
	 * 的替代品。
	 */
	// 整数1
	private final static IntWritable one = new IntWritable(1);

	// 单词
	private Text word = new Text();

	/**
	 * 1. Mapper接口中的map方法： void map(K1 key, V1 value, OutputCollector
	 * <K2,V2> output, Reporter reporter) 映射一个单个的输入k/v对到一个中间的k/v对
	 * 输出对不需要和输入对是相同的类型，输入对可以映射到0个或多个输出对。<br />
	 * 
	 * 2. OutputCollector接口：收集Mapper和Reducer输出的 <k,v>对。<br />
	 * 
	 * 3. OutputCollector接口的collect(k, v)方法:增加一个(k,v)对到output
	 */
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		// 1. To get line by Text object
		String line = value.toString();

		// 2. 使用 StringTokenizer 对读入的数据进行分割
		StringTokenizer tokenizer = new StringTokenizer(line);

		// 3. 遍历 StringTokenizer object
		while (tokenizer.hasMoreTokens()) {
			// 4. 获取分割出的子串，并设置到 Text object 中
			word.set(tokenizer.nextToken());

			// 5. 将处理好的 Text 对象通过 OutputCollector 对象的 collect 方法收集，即单词数+1
			output.collect(word, one);
		}
	}
}
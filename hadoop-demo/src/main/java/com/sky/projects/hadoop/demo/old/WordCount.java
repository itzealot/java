package com.sky.projects.hadoop.demo.old;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

/**
 * WordCount demo run.<br />
 * 
 * 
 * @author a
 *
 */
public class WordCount {

	public static void main(String[] args) throws Exception {
		/**
		 * JobConf: map/reduce的job配置类，向hadoop框架描述map-reduce执行的工作.<br />
		 * 构造方法：JobConf()、JobConf(Class exampleClass)、JobConf(Configuration
		 * conf)等
		 */
		JobConf conf = new JobConf(WordCount.class);

		// 设置一个用户定义的job名称
		conf.setJobName("wordcount");

		// 为 job 的输出数据设置 Key 类
		conf.setOutputKeyClass(Text.class);

		// 为 job 输出设置 value 类
		conf.setOutputValueClass(IntWritable.class);

		// 为job设置 Mapper 类
		conf.setMapperClass(WordCountMapper.class);

		// 为job设置 Combiner 类
		conf.setCombinerClass(WordCountReducer.class);

		// 为job设置 Reducer 类
		conf.setReducerClass(WordCountReducer.class);

		// 为 map-reduce 任务设置 InputFormat 实现类
		conf.setInputFormat(TextInputFormat.class);

		// 为 map-reduce 任务设置 OutputFormat 实现类
		conf.setOutputFormat(TextOutputFormat.class);

		/**
		 * InputFormat 描述 map-reduce中对job的输入定义 setInputPaths():为map-reduce
		 * job设置路径数组作为输入列表 setInputPath()：为map-reduce job设置路径数组作为输出列表
		 */
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		// 运行一个job
		JobClient.runJob(conf);
	}
}
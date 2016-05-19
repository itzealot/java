package com.sky.projects.spark.streaming;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import scala.Tuple2;

/**
 * 流式处理Demo
 * 
 * @author zt
 *
 */
@SuppressWarnings("serial")
public class StreamProcessDemo extends KryoSerializer {
	private static final long serialVersionUID = 1L;

	private Map<String, Broadcast<String>> broadcastMap = null;
	
	private String zkConn = null;
	private String groupId = null;
	
	private static String STREAM_TYPE = "";
	
	public StreamProcessDemo(SparkConf conf, Map<String, Broadcast<Map<String, String>>> broadcastRedisMap,
			Map<String, Broadcast<String>> broadcastMap) {
		super(conf);
		this.broadcastMap = broadcastMap;
		init(broadcastRedisMap);
	}
	
	private void init(Map<String, Broadcast<Map<String, String>>> broadcastRedisMap){
		final Broadcast<String> groupId = broadcastMap.get("");
		
		this.zkConn = "";
		this.groupId = groupId.getValue();
	}

	public void deal(JavaStreamingContext context,
		Map<String, String> kafkaParams, Map<String, Integer> topicMap,
		int groupByKeyNum, Map<Integer, Long> partionAndOffset) {
		
		JavaDStream<String[]> stream = kafkaLoad(context, kafkaParams, topicMap, partionAndOffset);
		
		persistent(stream);
		
		persistent2(stream);

//		JavaDStream<String[]> filterDealStream = filterDealStream(stream);
		
		JavaDStream<String[]> uniqRelation = analysis(stream);
		persistent3(uniqRelation);

		JavaDStream<String[]> uniqCertification = analysis2(stream);
		persistent4(uniqCertification);
		
		JavaDStream<String[]> uniqCertTrack = analysis3(stream);
		persistent5(uniqCertTrack);
	}

	private JavaDStream<String[]> kafkaLoad(JavaStreamingContext context,
			Map<String, String> kafkaParams, Map<String, Integer> topicMap, Map<Integer, Long> partionAndOffset) {
		
		/*
		 * spark 1.3.0但是不记录zk
		 * 
		 * Set<String> fjTopicSet = new HashSet<String>();
		for (String topic :topicMap.keySet()) {
			fjTopicSet.add(topic);
		}
		JavaPairDStream<String, String> sourceStream = 
			     KafkaUtils.createDirectStream(jssc, String.class, String.class,
							StringDecoder.class, StringDecoder.class, kafkaParams, 
							fjTopicSet);
		JavaDStream<String[]> splitStream = sourceStream
				.flatMap(new FlatMapFunction<Tuple2<String, String>, String[]>() {
					public Iterable<String[]> call(Tuple2<String, String> tuple) {
						String[] messages = tuple._2.split("\002");
						List<String[]> lines = new ArrayList<String[]>(
								messages.length);
						for (int i = 0; i < messages.length; i++) {

							String[] currentLine = messages[i].split("\\|");
							lines.add(currentLine);
						}
						return lines;
					}
				});*/
		
		/*
		 * spark 1.2.0版本的写法
		JavaPairInputDStream<String, String> sourceStream = KafkaUtils
				.createStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicMap, StorageLevel.MEMORY_AND_DISK_SER());*/

		String topic = null;
		for (String t :topicMap.keySet()) {
			topic = t;
		}
		/*
		 * JavaInputDStream<String> org.apache.spark.streaming.kafka.KafkaUtils.createDirectStream(
		 * 	JavaStreamingContext context,		# JavaStreamingContext
		 * 	Class<K> key,						# Key
		 * 	Class<V> value,						# Value
		 * 	Class<KD> keyDecoder,				# Key Decoder
		 * 	Class<VD> valueDecoder,				# ValueDecoder
		 * 	Class<R> recordClass,				# RecordClass
		 * 	Map<String, String> kafkaParams,	# KafkaParams
		 * 	Map<TopicAndPartition, Long> leaders,
		 * 	Function<MessageAndMetadata<String, String>, String> messageHandler
		 * )
		 * 
		 * MessageAndMetadata : 消息及元数据
		 */
		JavaDStream<String> sourceStream =  KafkaUtils.createDirectStream(context, String.class, String.class,
			StringDecoder.class, StringDecoder.class, String.class,kafkaParams, 
			StreamHelper.topicOffsetToMap(zkConn, groupId, topic, partionAndOffset),
			new Function<MessageAndMetadata<String, String>, String>() {
				@Override
				public String call(MessageAndMetadata<String, String> msgAndMd) throws Exception {
					// 返回 kafka 消息
					return msgAndMd.message();
				}
			});
		
		StreamHelper.updateOffsetToZk(sourceStream, STREAM_TYPE, zkConn, groupId);
		
		/*
		 * 首先将 kafka 渠道的原始数据解析成需要的单条记录返回
		 * 
		 * flatMap : 将一条数据转换为多条数据
		 * 
		 * JavaDStream<T>.flatMap(FlatMapFunction<T, T[]>)
		 * 
		 * public Iterable<T[]> call(T current)
		 */
		JavaDStream<String[]> splitStream = sourceStream.flatMap(
			new FlatMapFunction<String, String[]>() {
				@Override
				public Iterable<String[]> call(String json) {
					// TODO
					String[] messages = json.split("\002");
					List<String[]> lines = new ArrayList<String[]>(messages.length);
					
					for (int i = 0; i < messages.length; i++) {
						String[] currentLine = messages[i].split("\\|");
						lines.add(currentLine);
					}
					
					return lines;
				}
			});

		/*
		 * filter: 过滤算子，如果返回 true 表明数据有效；否则数据无效，即过滤掉
		 * 
		 * JavaDStream<T>.filter(Function<T, Boolean>)
		 * 
		 * public Boolean call(T current)
		 */
		JavaDStream<String[]> filterStream = splitStream.filter(
			new Function<String[], Boolean>() {
				@Override
				public Boolean call(String[] currentLine) throws Exception {
					// TODO
					return true;
				}
			});

		/*
		 * map 算子，将原有的数据转换为相应同类型的数据
		 * 
		 * JavaDStream<T>.map(Function<T, T>)
		 * 
		 * public T call(T current)
		 */
		JavaDStream<String[]> dealStream = filterStream.map(
			// Function<T, R>; method: R call(T t)
			new Function<String[], String[]>() {
				@Override
				public String[] call(String[] message) throws Exception {
					// TODO
					return message;
				}
			});

		return dealStream;
	}
	
	public  JavaDStream<String[]> filterStream(JavaDStream<String[]> stream){
		JavaDStream<String[]> filterStream = stream.filter(
			new Function<String[], Boolean>() {
				@Override
				public Boolean call(String[] currentLine) throws Exception {
					// TODO
					return true;
				}
			});
		
		return filterStream;
	}
	
	private JavaDStream<String[]> analysis(JavaDStream<String[]> stream) {
		JavaDStream<String[]> flatStream = stream.flatMap(
			new FlatMapFunction<String[], String[]>() {
				public Iterable<String[]> call(String[] message) {
					// TODO
					return new ArrayList<String[]>();
				}
			});

		
		/*
		 * mapToPair: 将 T 数据转为 <Key, Value> 形式
		 * 
		 * JavaDStream<K, V>.filter(PairFunction<T, K, V>)
		 * 
		 * public Tuple2<K, V> call(T t)
		 * 
		 * PairFunction<T, K, V>
		 */
		JavaPairDStream<String, String[]> pairStream = flatStream
			.mapToPair(new PairFunction<String[], String, String[]>() {
				public Tuple2<String, String[]> call(String[] str) {
					// TODO
					return new Tuple2<String, String[]>(str[0], str);
				}
			});

		/*
		 * 对含有kv值得非经日志关系按key值聚合
		 * 
		 * JavaPairDStream<K, V>.reduceByKey(Function2<V, V, V>)
		 * 
		 * Function2<T1, T2, R>
		 * 
		 * public R call(T1 v1, T2 v2)
		 */
		JavaPairDStream<String, String[]> reduceStream = pairStream.reduceByKey(
	    	new Function2<String[], String[], String[]>() {
	    		@Override
	    		public String[] call(String[] relationA, String[] relationB) {
	    			// TODO
	    			return relationA == null ? relationB : relationA;
	    		}
	    	}, 5);
				
		/*
		 * 
		 * 
		 * JavaDStream<T>.mapPartitions(FlatMapFunction<Iterator<Tuple2<T, T[]>>, T[]>)
		 * 
		 * public Iterable<T[]> call(Iterator<Tuple2<T, T[]>> current)
		 */
		JavaDStream<String[]> partitionStream = reduceStream.mapPartitions(
			new FlatMapFunction<Iterator<Tuple2<String, String[]>>, String[]>(){
				@Override
				public Iterable<String[]> call(Iterator<Tuple2<String, String[]>> iterMessage)
						throws Exception {
					// TODO
					return new ArrayList<String[]>();
				}
			});

		return partitionStream;
	}

	/**
	 * 数据持久化
	 * 
	 * 遍历JavaDstream<T> 的每一个 JavaRDD<T>
	 * JavaDstream<T>.foreachRDD(Function<JavaRDD<T>, Void>)
	 * public Void call(JavaRDD<T>)
	 * 
	 * 遍历 JavaRDD<T> 的每一个分区 Partition
	 * JavaRDD<T>.foreachPartition(VoidFunction<Iterator<T>>)
	 * public void call(Iterator<T> iterator)
	 * 
	 */
	private void persistent3(JavaDStream<String[]> stream) {
		stream.foreachRDD(new Function<JavaRDD<String[]>, Void>() {
			@Override
			public Void call(JavaRDD<String[]> rdd) {
				rdd.foreachPartition(new VoidFunction<Iterator<String[]>>() {
					@Override
					public void call(Iterator<String[]> iterator) throws Exception {
						// TODO
					}
				});

				return null;
			}
		});
	}

	private JavaDStream<String[]> analysis2(JavaDStream<String[]> stream) {
		JavaDStream<String[]> flatStream = stream.flatMap(new FlatMapFunction<String[], String[]>() {
			public Iterable<String[]> call(String[] message) {
				// TODO
				return new ArrayList<String[]>();
			}
		});

		JavaPairDStream<String, String[]> mapStream = flatStream.mapToPair(
			new PairFunction<String[], String, String[]>() {
				public Tuple2<String, String[]> call(String[] str) {
					// TODO
					return new Tuple2<String, String[]>(str[0], str);
				}
			});

		JavaPairDStream<String, String[]> reduceStream = mapStream.reduceByKey(
			new Function2<String[], String[], String[]>(){
				@Override
				public String[] call(String[] certificationA, String[] certificationB){
					// TODO
					return certificationA == null ? certificationB : certificationA;
				}
			 }, 5);

		
		JavaDStream<String[]> partitionStream = reduceStream.mapPartitions(
			new FlatMapFunction<Iterator<Tuple2<String, String[]>>, String[]>(){
				@Override
				public Iterable<String[]> call(Iterator<Tuple2<String, String[]>> iterator)
						throws Exception {
					// TODO
					return  new ArrayList<String[]>();
				}
			});
		
		return partitionStream;
	}

	private  void persistent4(JavaDStream<String[]> stream){
		stream.foreachRDD(new Function<JavaRDD<String[]>, Void>() {
			@Override
			public Void call(JavaRDD<String[]> rdd) {
				rdd.foreachPartition(new VoidFunction<Iterator<String[]>>() {
					@Override
					public void call(Iterator<String[]> iterator) throws Exception {
						//TODO
					}
				});

				return null;
			}
		});
	}
	
	private  JavaDStream<String[]> analysis3(JavaDStream<String[]> stream){
		JavaDStream<String[]> track = stream.flatMap(new FlatMapFunction<String[], String[]>() {
			@Override
			public Iterable<String[]> call(String[] message) {
				// TODO
				return new ArrayList<>();
			}
		});
		
		// w01身份轨迹按指定字段构建K值用于去重
		JavaPairDStream<String, String[]> certTrack = track.mapToPair(new PairFunction<String[], String, String[]>() {
			@Override
			public Tuple2<String, String[]> call(String[] str) {
				// TODO
				return new Tuple2<String, String[]>(str[0], str);
			}
		});
		
		JavaPairDStream<String, String[]> reduceStream = certTrack.reduceByKey(
		      new Function2<String[], String[], String[]>() {
		        @Override
		        public String[] call(String[] CertTrackA, String[] CertTrackB) {
		        	// TODO
		        	return CertTrackA == null ? CertTrackB : CertTrackA;
		        }
		    }, 5);
		
		
		JavaDStream<String[]> partitionStream = reduceStream.mapPartitions(new FlatMapFunction<Iterator<Tuple2<String, String[]>>, String[]>(){
			@Override
			public Iterable<String[]> call(Iterator<Tuple2<String, String[]>> iterMessage)
					throws Exception {
				// TODO
				return new ArrayList<String[]>();
			}
		});
		
		return partitionStream;
	}
	
	private  void persistent5(JavaDStream<String[]> stream){
		stream.foreachRDD(new Function<JavaRDD<String[]>, Void>() {
			@Override
			public Void call(JavaRDD<String[]> rdd) {
				rdd.foreachPartition(new VoidFunction<Iterator<String[]>>() {
					@Override
					public void call(Iterator<String[]> str) throws Exception {
						// TODO
					}
				});

				return null;
			}
		});
	}	
	
	private void persistent2(JavaDStream<String[]> stream) {
		JavaDStream<String[]> mapStream = stream.map(new Function<String[], String[]>() {
			@Override
			public String[] call(String[] str) {
				// TODO
				return str;
			}
		});

		mapStream.foreachRDD(new Function<JavaRDD<String[]>, Void>() {
			@Override
			public Void call(JavaRDD<String[]> rdd) {
				rdd.foreachPartition(new VoidFunction<Iterator<String[]>>() {
					@Override
					public void call(Iterator<String[]> str) throws Exception {
						// TODO
					}
				});
				
				return null;
			}
		});
	}

	private void persistent(JavaDStream<String[]> stream) {
		stream.foreachRDD(new Function<JavaRDD<String[]>, Void>() {
			@Override
			public Void call(JavaRDD<String[]> rdd) {
				rdd.foreachPartition(new VoidFunction<Iterator<String[]>>() {
					@Override
					public void call(Iterator<String[]> str) throws Exception {
						// TODO
					}
				});
				
				return null;
			}
		});
	}
	
}

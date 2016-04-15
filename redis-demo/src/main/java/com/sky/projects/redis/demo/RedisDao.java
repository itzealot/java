package com.sky.projects.redis.demo;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

/**
 * redis访问类
 * 
 * @author zt
 */
public class RedisDao {
	private static final int REDIS_CONNECT_LATENCY = 10000;
	private JedisPool jedisPool = null;

	private String redisIp = null;
	private String redisPassw = null;
	private int redisPort;
	private int redisKeyKeepDay = 1;

	private RedisDao(String serversInfo, int database) {
		System.out.println("serversInfo:" + serversInfo + "; database:" + database);
		String[] splits = serversInfo.split("|");
		if (splits.length < 2) {
		}
		redisIp = splits[0];
		try {
			redisPort = Integer.parseInt(splits[1]);
		} catch (NumberFormatException e) {
		}

		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxTotal(JRedisPoolConfig.MAX_ACTIVE);
		// config.setMaxIdle(JRedisPoolConfig.MAX_IDLE);
		// config.setMaxWait(JRedisPoolConfig.MAX_WAIT);
		// config.setTestOnBorrow(JRedisPoolConfig.TEST_ON_BORROW);
		// config.setTestOnReturn(JRedisPoolConfig.TEST_ON_RETURN);
		if (splits.length == 3) {
			redisPassw = splits[2];
			jedisPool = new JedisPool(config, redisIp, redisPort, REDIS_CONNECT_LATENCY, redisPassw, database);
		} else {
			jedisPool = new JedisPool(config, redisIp, redisPort, REDIS_CONNECT_LATENCY, null, database);
		}
	}

	private static Map<Integer, RedisDao> redisDaoMap = null;

	private static void initRedisDaoMap(String serversInfo) {
		redisDaoMap = new HashMap<Integer, RedisDao>();
		redisDaoMap.put(0, new RedisDao(serversInfo, 0));
		redisDaoMap.put(1, new RedisDao(serversInfo, 1));
		redisDaoMap.put(2, new RedisDao(serversInfo, 2));
		redisDaoMap.put(3, new RedisDao(serversInfo, 3));
	}

	/**
	 * 获取JedisUtil实例
	 * 
	 * @return
	 */
	public static RedisDao getInstance(String serversInfo) {
		RedisDao redisDao = null;

		if (redisDaoMap == null) {
			initRedisDaoMap(serversInfo);
		}

		if (redisDaoMap != null) {
			redisDao = redisDaoMap.get(0);
		}

		return redisDao;
	}

	/**
	 * 获取JedisUtil实例
	 * 
	 * @return
	 */
	public static RedisDao getInstance(String serversInfo, int database) {
		RedisDao redisDao = null;
		if (redisDaoMap == null) {
			initRedisDaoMap(serversInfo);
		}

		if (redisDaoMap != null) {
			redisDao = redisDaoMap.get(database);
		}

		return redisDao;
	}

	public JedisPool getPool() {
		return jedisPool;
	}

	public int getRedisKeyKeepDay() {
		return redisKeyKeepDay;
	}

	public void setRedisKeyKeepDay(int redisKeyKeepDay) {
		this.redisKeyKeepDay = redisKeyKeepDay;
	}

	/**
	 * 批量获取redis Hash类型中多个haskKey是否存在
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 所有命中结果，0表示命中，1表示未命中
	 */
	public List<Integer> hgetPipeline(final String redisKey, final List<String> hashKeyList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;

		List<Integer> flags = new ArrayList<Integer>();

		try {
			Pipeline pipeline = jedis.pipelined();

			for (String hashKey : hashKeyList) {
				Response<Boolean> flag = pipeline.hexists(redisKey, hashKey);
				if (flag.get()) {
					flags.add(0);
				} else {
					flags.add(1);
				}
			}

			pipeline.sync();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			eTime = System.currentTimeMillis();

			jedis.close();

			long time = eTime - sTime;

			if (time > 10000) {
				// TODO
				System.out.println("Time is : " + time);
			}
		}

		return flags;
	}

	/**
	 * 批量获取redis Hash类型中多个haskKey是否存在
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 返回获取的结果集合，集合大小与hashKeyList一致，有值返回值，无值返回null
	 */
	public List<String> hgetPipelineResult(final String redisKey, final List<String> hashKeyList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;

		List<String> results = new ArrayList<String>();

		try {
			Pipeline pipeline = jedis.pipelined();

			for (String hashKey : hashKeyList) {
				Response<String> flag = pipeline.hget(redisKey, hashKey);
				results.add(flag.get());
			}

			pipeline.sync();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			eTime = System.currentTimeMillis();

			jedis.close();

			long time = eTime - sTime;

			if (time > 10000) {
				// TODO
				System.out.println("Time is : " + time);
			}
		}

		return results;
	}

	/**
	 * 批量更新redis Hash类型中多个haskKey的信息
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @param hashValueList
	 *            hashKey对应值
	 */
	public void hsetPipelineMac(final String redisKeyPerfix, final List<String> hashKeyList,
			final List<String> hashValueList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;

		try {
			Pipeline pipeline = jedis.pipelined();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				String hashValue = hashValueList.get(i);
				String[] hashKeyArr = hashKey.split("-");

				pipeline.hset(redisKeyPerfix + hashKeyArr[0] + hashKeyArr[1] + hashKeyArr[2].substring(0, 1),
						hashKeyArr[2].substring(1, 2) + hashKeyArr[3] + hashKeyArr[4] + hashKeyArr[5], hashValue);
			}

			pipeline.sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eTime = System.currentTimeMillis();
			jedis.close();

			long time = eTime - sTime;

			if (time > 10000) {
				// TODO
				System.out.println("Time is : " + time);
			}
		}
	}

	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] shortToBytes(short n) {
		ByteBuffer b = ByteBuffer.allocate(2);
		b.putShort(0, n);
		return b.array();
	}

	public static byte[] short2byte(short res) {
		byte[] targets = new byte[4];
		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		return targets;
	}

	/**
	 * 批量更新redis Hash类型中多个haskKey的信息
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @param hashValueList
	 *            hashKey对应值
	 */
	public void hsetPipelineNew(final String redisKey, final List<String> hashKeyList,
			final List<String> hashValueList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			Pipeline pipeline = jedis.pipelined();
			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				String hashValue = hashValueList.get(i);
				pipeline.hset(redisKey, hashKey, hashValue);
			}
			pipeline.sync();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		} finally {
			eTime = System.currentTimeMillis();

			jedis.close();

			long time = eTime - sTime;

			if (time > 10000) {
				// TODO
				System.out.println("Time is : " + time);
			}
		}
	}

	/**
	 * 批量更新redis Hash类型中多个haskKey的信息
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @param hashValueList
	 *            hashKey对应值
	 */
	public void hsetPipelineNew(final String redisKey, final List<String> hashKeyList, final List<String> hashValueList,
			final int seconds) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		
		try {
			Pipeline pipeline = jedis.pipelined();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				String hashValue = hashValueList.get(i);
				pipeline.hset(redisKey, hashKey, hashValue);
			}

			pipeline.expire(redisKey, seconds);

			pipeline.sync();
		} catch (Exception e) {
			// TODO
		} finally {
			eTime = System.currentTimeMillis();

			jedis.close();

			long time = eTime - sTime;

			if (time > 10000) {
				// TODO
				System.out.println("Time is : " + time);
			}
		}

	}

	/**
	 * 批量更新redis Hash类型中多个haskKey的信息 不存在就新增，存在就更新
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @param hashValueList
	 *            hashKey对应值
	 * @return 未匹配上hashKey的下标集合
	 */
	public List<Integer> hsetPipeline(final String redisKey, final List<String> hashKeyList,
			final List<String> hashValueList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		List<Integer> noExistIndexList = new ArrayList<Integer>();
		List<Response<Boolean>> resList = new ArrayList<Response<Boolean>>();
		try {
			Pipeline pipeline = jedis.pipelined();
			for (String hashKey : hashKeyList) {
				Response<Boolean> flag = pipeline.hexists(redisKey, hashKey);
				resList.add(flag);
			}
			pipeline.sync();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				String hashValue = hashValueList.get(i);
				Response<Boolean> flag = resList.get(i);
				if (!flag.get()) {
					noExistIndexList.add(i);
					pipeline.hset(redisKey, hashKey, hashValue);
				}
			}
			pipeline.sync();

			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

			return null;
		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
		return noExistIndexList;

	}

	/**
	 * 批量更新redis Hash类型中多个haskKey的信息
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 未匹配上hashKey的下标集合，匹配上hashKey的下标集合
	 */
	public Map<String, List<Integer>> hsetPipelineUpdate(final String redisKey, final List<String> hashKeyList,
			final List<String> hashValueList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		Map<String, List<Integer>> returnMap = new HashMap<String, List<Integer>>(2);
		List<Integer> noExistIndexList = new ArrayList<Integer>();
		List<Integer> existIndexList = new ArrayList<Integer>();
		List<Response<Boolean>> resList = new ArrayList<Response<Boolean>>();
		try {
			Pipeline pipeline = jedis.pipelined();
			for (String hashKey : hashKeyList) {
				Response<Boolean> flag = pipeline.hexists(redisKey, hashKey);
				resList.add(flag);
			}
			pipeline.sync();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				String hashValue = hashValueList.get(i);
				Response<Boolean> flag = resList.get(i);
				if (!flag.get()) {
					noExistIndexList.add(i);
					HashValueObject ho = new HashValueObject(hashValue);
					pipeline.hset(redisKey, hashKey,
							(ho.getTimeAppearNum() + "|" + ho.getLastSiteNo() + "|" + ho.getLatestTime()));

				} else {
					existIndexList.add(i);
				}
			}

			// 更新已经存在的key
			List<Response<String>> valueList = new ArrayList<Response<String>>();
			for (int j = 0; j < existIndexList.size(); j++) {
				String hashKey = hashKeyList.get(existIndexList.get(j));
				Response<String> valueResponse = pipeline.hget(redisKey, hashKey);
				valueList.add(valueResponse);
			}
			pipeline.sync();

			for (int i = 0; i < existIndexList.size(); i++) {
				String hashKey = hashKeyList.get(existIndexList.get(i));
				String hashValue = hashValueList.get(existIndexList.get(i));
				String redisValue = valueList.get(i).get();
				HashValueObject ho = new HashValueObject(hashValue);
				if (null == redisValue || "".equals(redisValue)) {
					pipeline.hset(redisKey, hashKey,
							(ho.getTimeAppearNum() + "|" + ho.getLastSiteNo() + "|" + ho.getLatestTime()));
				} else {
					String[] valueArray = redisValue.split("\\|");
					int count = Integer.parseInt(ho.getTimeAppearNum());
					if (valueArray.length != 3) {
						count = count + Integer.parseInt(redisValue);
					} else {
						count = count + Integer.parseInt(valueArray[0]);
					}
					pipeline.hset(redisKey, hashKey,
							(String.valueOf(count) + "|" + ho.getLastSiteNo() + "|" + ho.getLatestTime()));
				}
			}

			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

			return null;
		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
		returnMap.put("exist", existIndexList);
		returnMap.put("noExist", noExistIndexList);
		return returnMap;
	}

	/**
	 * 批量更新redis Hash类型中多个haskKey的信息
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 未匹配上hashKey的下标集合，匹配上hashKey的下标集合
	 */
	public Map<String, List<Integer>> hsetPipelineFRelation(final String redisKey, final List<String> hashKeyList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		Map<String, List<Integer>> returnMap = new HashMap<String, List<Integer>>(2);
		List<Integer> noExistIndexList = new ArrayList<Integer>();
		List<Integer> existIndexList = new ArrayList<Integer>();
		List<Response<Boolean>> resList = new ArrayList<Response<Boolean>>();
		try {
			Pipeline pipeline = jedis.pipelined();
			for (String hashKey : hashKeyList) {
				Response<Boolean> flag = pipeline.hexists(redisKey, hashKey);
				resList.add(flag);
			}
			pipeline.sync();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				long hashValue = 1l;
				Response<Boolean> flag = resList.get(i);
				if (!flag.get()) {
					noExistIndexList.add(i);
					pipeline.hset(redisKey, hashKey, String.valueOf(hashValue));

				} else {
					existIndexList.add(i);
					pipeline.hincrBy(redisKey, hashKey, hashValue);
				}
			}
			pipeline.sync();

			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

			return null;
		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
		returnMap.put("exist", existIndexList);
		returnMap.put("noExist", noExistIndexList);
		return returnMap;
	}

	/**
	 * 批量更新redis Hash类型中多个haskKey的信息
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 未匹配上hashKey的下标集合，匹配上hashKey的下标集合
	 * @更新已经存在的key的value
	 */

	public void hsetPipelineExist(final String redisKey, final List<String> hashKeyList,
			final List<String> hashValueList, final List<Integer> existIndexList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			Pipeline pipeline = jedis.pipelined();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				String hashValue = hashValueList.get(i);

				Response<String> valueResponse = pipeline.hget(redisKey, hashKey);
				pipeline.sync();
				String value = valueResponse.get();
				HashValueObject ho = new HashValueObject(hashValue);
				if (null != value) {
					String[] valueArray = value.split("\\|");
					int count = Integer.parseInt(ho.getTimeAppearNum());
					if (valueArray.length != 3) {
						count = count + Integer.parseInt(value);
					} else {
						count = count + Integer.parseInt(valueArray[0]);
					}
					pipeline.hset(redisKey, hashKey,
							(String.valueOf(count) + "|" + ho.getLastSiteNo() + "|" + ho.getLatestTime()));
				} else {
					pipeline.hset(redisKey, hashKey,
							(ho.getTimeAppearNum() + "|" + ho.getLastSiteNo() + "|" + ho.getLatestTime()));
				}
			}

			pipeline.sync();
			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
	}

	/**
	 * 批量更新计数器
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 未匹配上hashKey的下标集合
	 */
	public void hIncrPipeline(final String redisKey, final List<String> hashKeyList, final List<Long> hashValueList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			Pipeline pipeline = jedis.pipelined();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				long hashValue = hashValueList.get(i);
				pipeline.hincrBy(redisKey, hashKey, hashValue);
			}
			pipeline.sync();

			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
	}

	/**
	 * 批量更新计数器
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 未匹配上hashKey的下标集合
	 */
	public void hIncrPipeline(final String redisKey, final List<String> hashKeyList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			Pipeline pipeline = jedis.pipelined();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				String hashKey = hashKeyList.get(i);
				pipeline.hincrBy(redisKey, hashKey, 1l);
			}
			pipeline.sync();

			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
	}

	/**
	 * 批量获取redis Hash类型中多个haskKey是否存在
	 * 
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return 返回获取的结果集合，集合大小与hashKeyList一致，有值返回值，无值返回null
	 */
	public List<Boolean> hgetPipelineExist(final String redisKey, final List<String> hashKeyList) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		List<Boolean> resultList = new ArrayList<Boolean>();
		List<Response<Boolean>> resList = new ArrayList<Response<Boolean>>();
		try {
			Pipeline pipeline = jedis.pipelined();
			for (String hashKey : hashKeyList) {
				Response<Boolean> flag = pipeline.hexists(redisKey, hashKey);
				resList.add(flag);
			}
			pipeline.sync();

			for (int i = 0, size = hashKeyList.size(); i < size; i++) {
				Response<Boolean> flag = resList.get(i);
				resultList.add(flag.get());
			}
			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

			return null;
		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
		return resultList;
	}

	public long hlen(final String redisKey) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		long count = 0;
		try {
			count = jedis.hlen(redisKey);

			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
		return count;
	}

	public void del(final String redisKey) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			jedis.del(redisKey);
			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
	}

	public void hset(final String redisKey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			jedis.hset(redisKey, key, value);
			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
	}

	/**
	 * 批量获取redis Hash类型所有记录
	 * 
	 * @param key
	 *            redis中的key
	 * @return
	 */
	public Map<String, String> hgetAllPipeline(final String redisKey) {
		Jedis jedis = jedisPool.getResource();
		Map<String, String> returnMap = null;
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			Pipeline pipeline = jedis.pipelined();
			Response<Map<String, String>> tmpRet = pipeline.hgetAll(redisKey);
			pipeline.sync();
			returnMap = tmpRet.get();
			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

			return null;
		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
		return returnMap;
	}

	/**
	 * 批量获取redis Hash类型所有记录
	 * 
	 * @param key
	 *            redis中的key
	 * @return
	 */
	public List<Entry<String, String>> hscan(final String redisKey, long maxSize) {
		Jedis jedis = jedisPool.getResource();
		List<Entry<String, String>> returnEntry = new ArrayList<Entry<String, String>>();
		long sTime = System.currentTimeMillis();
		long eTime = 0;
		try {
			ScanParams params = new ScanParams();
			params.count(1000);
			String cursor = "0";
			long sum = 0;
			do {
				ScanResult<Map.Entry<String, String>> result = jedis.hscan(redisKey, cursor, params);
				cursor = result.getStringCursor();
				List<Entry<String, String>> resultEntry = result.getResult();
				sum += resultEntry.size();
				returnEntry.addAll(resultEntry);
			} while ((!"0".equals(cursor)) && (sum < maxSize));

			eTime = System.currentTimeMillis();
		} catch (Exception e) {
			eTime = System.currentTimeMillis();

			return null;
		} finally {
			jedis.close();
			if (eTime - sTime > 10000) {
			}
		}
		return returnEntry;
	}

	/**
	 * 批量获取redis Hash类型中多个haskKey的信息
	 *
	 * @param key
	 *            redis中的key
	 * @param hashKeyList
	 *            多个hashKey
	 * @return
	 */
	/*
	 * public List<Pair<String, String>> hmgetPipeline(final String redisKey,
	 * final List<String> hashKeyList) { Jedis jedis = jedisPool.getResource();
	 * List<Response<String>> tmpRet = new LinkedList<Response<String>>(); long
	 * sTime = System.currentTimeMillis(); long eTime = 0; try { Pipeline
	 * pipeline = jedis.pipelined(); for (String hashKey : hashKeyList) {
	 * tmpRet.add(pipeline.hget(redisKey, hashKey)); } pipeline.sync(); eTime =
	 * System.currentTimeMillis(); } catch (Exception e) { eTime =
	 * System.currentTimeMillis(); jedisPool.returnBrokenResource(jedis);
	 * 
	 * return null; } finally { jedisPool.returnResource(jedis); if (eTime -
	 * sTime > 10000) { LOG.info("hmgetPipeline time: " + (eTime-sTime) + "ms");
	 * } } List<Pair<String, String>> ret = new LinkedList<Pair<String,
	 * String>>(); for (int i = 0, size = hashKeyList.size(); i < size; i++) {
	 * ret.add(new Pair<String, String>(hashKeyList.get(i),
	 * tmpRet.get(i).get())); } return ret;
	 * 
	 * }
	 */
}
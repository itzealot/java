package com.surfilter.mass.thread;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.surfilter.mass.config.RedisMacConf;
import com.surfilter.mass.redis.pool.RedisPool;

import redis.clients.jedis.Jedis;

/**
 * 多线程执行 Redis mac key 替换
 * 
 * @author zealot
 *
 */
public class RedisMacConsumer implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(RedisMacConsumer.class);

	private static final int CACHE_SIZE = 1000;
	private Jedis jedis;
	private BlockingQueue<String> queue;
	private RedisMacConf conf;
	private RedisPool pool;

	public RedisMacConsumer(RedisMacConf conf, BlockingQueue<String> queue, Jedis jedis, RedisPool pool) {
		this.queue = queue;
		this.jedis = jedis;
		this.conf = conf;
		this.pool = pool;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {
			List<String> keys = Lists.newArrayListWithCapacity(CACHE_SIZE);

			// 从阻塞队列中消费
			int size = queue.drainTo(keys, CACHE_SIZE);

			LOG.debug("receive keys is : {}", size);

			if (size > 0) {
				for (int i = 0; i < size; i++) {
					String key = keys.get(i);

					// 只处理旧的 key
					if (conf.getOldMacPrefixLen() != conf.getNewMacPrefixLen()
							&& key.length() == (conf.getOldMacPrefixLen() + 2)) {
						putAndDel(key);
					}
				}
			} else {
				break;
			}
		}

		pool.returnResource(jedis);
	}

	/**
	 * 根据 Redis key 获取所有 hash 数据并更新，最后删除旧的 Redis key 数据
	 * 
	 * @param key
	 */
	public void putAndDel(String key) {
		Map<String, String> map = jedis.hgetAll(key);

		// 遍历 map 的所有 Key
		Set<String> fields = map.keySet();

		int index = conf.getNewMacPrefixLen() - conf.getOldMacPrefixLen();

		for (String field : fields) {
			// 获取对应的值
			String value = map.get(field);

			String newKey = "";
			String newField = "";

			if (index > 0) {
				newKey = key + field.substring(0, index);
				newField = field.substring(index);
			} else {
				// index < 0
				newKey = key.substring(0, key.length() + index);
				newField = key.substring(key.length() + index) + field;
			}

			LOG.debug("newKey={}, newField={}", newKey, newField);

			// 设置新值
			jedis.hset(newKey, newField, value);
		}

		jedis.del(key);
	}
}

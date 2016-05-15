package com.surfilter.mass.mac;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.surfilter.mass.config.RedisMacConf;

import redis.clients.jedis.Jedis;


/**
 * 单线程执行
 * 
 * @author zealot
 *
 */
public class RedisMacTransfer {

	private static final Logger LOG = LoggerFactory.getLogger(RedisMacTransfer.class);

	private Jedis jedis;
	private RedisMacConf conf = RedisMacConf.getInstance();

	public RedisMacTransfer() {
		jedis = new Jedis(conf.getHost(), conf.getPort());
	}

	public void execute() {
		Set<String> keys = jedis.keys(conf.getDealMacPartern());

		for (String key : keys) {
			// is old mac key
			if (conf.getOldMacPrefixLen() != conf.getNewMacPrefixLen()
					&& key.length() == (conf.getOldMacPrefixLen() + 2)) {
				putAndDel(key);
			}
		}
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

		for (String field : fields) {
			// 获取对应的值
			String value = map.get(field);

			int index = conf.getNewMacPrefixLen() - conf.getOldMacPrefixLen();

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

			LOG.debug("newKey={}, newField={}, value={}", newKey, newField, value);

			// 设置新值
			jedis.hset(newKey, newField, value);
		}

		jedis.del(key);
	}

}

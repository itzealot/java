package com.zt.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author zengtao 2015-5-9 <br />
 *
 */
public class MapUtil {

	/**
	 * To get the Map<K,V> map's values. <br />
	 * 以集合的方式返回Map集合的所有值 <br />
	 * 如果map为null、key为null或者map中不包含key，则返回null；否则返回所有值的集合<br />
	 * 
	 * @param map
	 * @param key
	 * @return If the map or the key is null or the map can't contain the key,
	 *         then return null; else return the map's values Collection<Object>
	 */
	public static <K, V> Collection<V> getValues(Map<K, V> map) {
		return map == null ? null : map.values();
	}

	/**
	 * To validate the map.<br />
	 * If the map is null or is empty then return false; else return true.<br />
	 * 
	 * @param map
	 * @return If the map is null or is empty then return false; else return
	 *         true.
	 */
	public static <K, V> boolean isValidateMap(Map<K, V> map) {
		if (map == null || map.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the key.<br />
	 * 校验key是否有效<br />
	 * 
	 * @param map
	 * @param key
	 * @return If the map or the key is null or the map can't contain the key,
	 *         then return false; else return true.
	 */
	public static <K, V> boolean isValidateKey(Map<K, V> map, Object key) {
		if (map == null || key == null || !map.containsKey(key)) {
			return false;
		}
		return true;
	}

	/**
	 * To get the value by key.<br />
	 * 
	 * @param map
	 * @param key
	 * @return If the map or the key is null or the map can't contain the key,
	 *         then return null; else return the map's value where the key is
	 *         equals :key.
	 */
	public static <K, V> V getValueByKey(Map<K, V> map, K key) {
		if (isValidateKey(map, key)) {
			return map.get(key);
		}
		return null;
	}

	/**
	 * For JDK 1.4 or above.<br />
	 * To use the map's entrySet method and its iterator method to for each the
	 * Map and return the Object String.
	 * 
	 * @param map
	 * @return the Object String like {..{key : value}, {key : value}..}
	 */
	public static <K, V> String iteratorMap(Map<K, V> map) {
		if (!isValidateMap(map)) {
			return "";
		}

		// To init the buffer
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		// To init the iterator
		Iterator<Entry<K, V>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<K, V> entry = it.next();

			// To get the key and value by entry
			K key = entry.getKey();
			V value = entry.getValue();

			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			if (it.hasNext()) {
				buffer.append(", ");
			}
		}
		buffer.append("}");
		return buffer.toString();
	}

	/**
	 * For JDK 1.5 or above.<br />
	 * To use the map's entrySet method to for each the Map and return the
	 * Object String.
	 * 
	 * @param map
	 * @return the Object String like {..{key : value}, {key : value}..}
	 */
	public static <K, V> String entryFEachMap(Map<K, V> map) {
		if (!isValidateMap(map)) {
			return "";
		}

		// To init the buffer
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		for (Map.Entry<K, V> entry : map.entrySet()) {

			K key = entry.getKey();
			V value = entry.getValue();

			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			buffer.append(", ");
		}
		return buffer.substring(0, buffer.length() - 2) + "}";
	}

	/**
	 * To use the map's keySet method and its iterator method to for each the
	 * Map and return the Object String.
	 * 
	 * @param map
	 * @return the Object String like {..{key : value}, {key : value}..}
	 */
	public static <K, V> String keySetIteratorMap(Map<K, V> map) {
		if (!isValidateMap(map)) {
			return "";
		}

		// To init the buffer
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		for (Iterator<K> it = map.keySet().iterator(); it.hasNext();) {
			K key = it.next();
			V value = map.get(key);

			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			buffer.append(", ");
		}
		return buffer.substring(0, buffer.length() - 2) + "}";
	}

	/**
	 * To use the map's keySet method to for each the Map and return the Object
	 * String.
	 * 
	 * @param map
	 * @return the Object String like {..{key : value}, {key : value}..}
	 */
	public static <K, V> String keySetFEachMap(Map<K, V> map) {
		if (!isValidateMap(map)) {
			return "";
		}

		// To init the buffer
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		for (K key : map.keySet()) {

			V value = map.get(key);
			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			buffer.append(", ");
		}
		return buffer.substring(0, buffer.length() - 2) + "}";
	}
}

package com.zt.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.zt.util.ValidateUtil;

/**
 * 
 * @author zengtao
 *
 */
public class SetUtil {

	/**
	 * To validate the Set.<br />
	 * If the set object is null or is empty then return false; else return
	 * true.<br />
	 * 
	 * @param set
	 * @return true or false.
	 */
	public static <E> boolean isValidate(Set<E> set) {
		if (set == null || set.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * To iterator the Set and return the String that was splited by ','.<br />
	 * 
	 * @param set
	 * @return the Set Collection's String that was splited by ','.
	 */
	public static <E> String iterator(Set<E> set) {
		StringBuffer buffer = new StringBuffer();

		// Iterator the Set Collection
		Iterator<E> it = set.iterator();
		while (it.hasNext()) {
			E object = it.next();
			buffer.append(object.toString());
			buffer.append(", ");
		}
		return buffer.substring(0, buffer.length() - 2);
	}

	/**
	 * To for each the Set and return the String that was splited by ','.
	 * 
	 * @param set
	 * @return the Set Collection's String that was splited by ','.
	 */
	public static <E> String forEachSet(Set<E> set) {
		StringBuffer buffer = new StringBuffer();
		for (E e : set) {
			buffer.append(e.toString());
			buffer.append(", ");
		}
		return buffer.toString();
	}

	/**
	 * To get List From Set<br />
	 * 将set集合中的内容转换为List集合<br />
	 * 
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> List<? extends T> getListFromSet(Set<? extends T> t) {
		if (ValidateUtil.isValidate(t)) {
			List<T> lists = new ArrayList<T>();
			@SuppressWarnings("unchecked")
			Iterator<T> it = (Iterator<T>) t.iterator();

			while (it.hasNext()) {
				lists.add(it.next());
			}
			return lists;
		}
		return null;
	}

	/**
	 * To get Set from List.<br />
	 * 将List集合中的内容转换为set集合<br />
	 * 
	 * @param t
	 * @return
	 */
	public static <T> Set<? extends T> getSetFromList(List<? extends T> t) {
		if (!ValidateUtil.isValidate(t)) {
			return null;
		}
		Set<T> set = new HashSet<T>();

		for (T tt : t) {
			set.add(tt);
		}
		return set;
	}
}

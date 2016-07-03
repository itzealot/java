package com.projects.sky.util.base;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.projects.sky.util.SkyFilter;

/**
 * Collection Util
 * 
 * @author zt
 */
public final class Collections2 {

	/**
	 * Collection is validate true or false.
	 * 
	 * 集合是否有效，有效则返回 true；否则返回 false
	 * 
	 * @param collection
	 * @return 有效则返回 true；否则返回 false
	 */
	public static <E> boolean validate(Collection<E> collection) {
		return collection != null && !collection.isEmpty();
	}

	public static <T> List<T> collection2List(Collection<T> collection) {
		checkNotNull(collection, "collection can not be null");

		return new ArrayList<>(collection);
	}

	public static <T> List<T> collection2List(Collection<T> collection, SkyFilter<T> filter) {
		checkNotNull(collection, "collection can not be null");
		checkNotNull(filter, "filter can not be null");

		List<T> lists = Lists.newArrayList();

		for (T t : collection) {
			if (filter.accept(t)) {
				lists.add(t);
			}
		}

		return lists;
	}

	public static <T> Set<T> collection2Set(Collection<T> collection) {
		return new HashSet<>(collection);
	}

	public static <T> Set<T> collection2Set(Collection<T> collection, SkyFilter<T> filter) {
		checkNotNull(collection, "collection can not be null");
		checkNotNull(filter, "filter can not be null");

		Set<T> set = Sets.newHashSet();

		for (T t : collection) {
			if (filter.accept(t)) {
				set.add(t);
			}
		}

		return set;
	}

	private Collections2() {
	}
}

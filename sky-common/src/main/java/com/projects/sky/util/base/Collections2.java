package com.projects.sky.util.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Collections2 {

	public static <E> boolean validate(Collection<E> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}

		return true;
	}

	public static <E> String stringOf(Collection<E> collection, String split, boolean flag) {
		if (!validate(collection)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		Iterator<E> iterator = collection.iterator();
		int length = 0;
		String splitDefault = ", ";

		if (split != null || split == "") {
			splitDefault = split;
		}

		length = splitDefault.length();

		while (iterator.hasNext()) {
			E element = iterator.next();
			buffer.append(element.toString());
			buffer.append(splitDefault);
		}

		String str = buffer.substring(0, buffer.length() - length);

		if (flag) {
			str += "\n";
		}

		return str;
	}

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

	public static <E> String forEach(Set<E> set) {
		StringBuffer buffer = new StringBuffer();

		for (E e : set) {
			buffer.append(e.toString());
			buffer.append(", ");
		}

		return buffer.toString();
	}

	public static <T> List<? extends T> transform(Set<? extends T> t) {
		List<T> lists = new ArrayList<T>();

		if (validate(t)) {
			@SuppressWarnings("unchecked")
			Iterator<T> it = (Iterator<T>) t.iterator();

			while (it.hasNext()) {
				lists.add(it.next());
			}
		}

		return lists;
	}

	public static <T> Set<? extends T> transform(List<? extends T> lists) {
		if (!validate(lists)) {
			return null;
		}

		Set<T> set = new HashSet<T>();

		for (T obj : lists) {
			set.add(obj);
		}

		return set;
	}
}

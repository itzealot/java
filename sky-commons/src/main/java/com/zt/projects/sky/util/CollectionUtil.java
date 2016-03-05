package com.zt.projects.sky.util;

import java.util.Collection;
import java.util.Iterator;

public class CollectionUtil {

	/**
	 * To validate the Collection<E> object collection
	 * 
	 * @param collection
	 * @return
	 */
	public static <E> boolean isValidate(Collection<E> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * To get the result String from Collection<E> that was divided by String
	 * split.<br />
	 * 
	 * @param collection
	 *            the Collection<E> Object collection
	 * @param split
	 *            the divide String.To divide the Collection<E> Object
	 *            collection's element
	 * @param flag
	 *            the flag append a line.
	 * @return
	 */
	public static <E> String getStringFromCollection(Collection<E> collection,
			String split, boolean flag) {
		if (!isValidate(collection)) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		Iterator<E> iterator = collection.iterator();
		int length = 0;
		String splitDefault = ", ";

		/**
		 * If it's null splitDefault is equals ", "; else splitDefault is equals
		 * the String split
		 */
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
}

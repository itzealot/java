package com.projects.sky.util.ref;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Sort Util
 * 
 * @author zt
 *
 */
public final class Sorts {
	private Sorts() {
	}

	/**
	 * 根据某个字段进行排序，默认是 升序.<br />
	 * To sort Collection by fieldName and sortType default is ASC.<br />
	 * 
	 * @param collection
	 *            Collection<T>
	 * @param fieldName
	 *            String
	 * @param sortType
	 *            SortType
	 */
	public static <T> void sort(Collection<T> collection, final String fieldName, final SortType sortType) {
		check(collection, fieldName);

		List<T> list = new ArrayList<T>();
		for (T t : collection) {
			list.add(t);
		}

		common(list, fieldName, sortType);
	}

	/**
	 * Collection<T> 根据某个字段进行排序，默认是 升序.<br />
	 * To sort Collection by fieldName and sortType default is ASC.<br />
	 * 
	 * @param collection
	 *            Collection<T>
	 * @param fieldName
	 *            String
	 * @param sortType
	 *            String
	 */
	public static <T> void sort(Collection<T> collection, final String fieldName, final String sortType) {
		check(collection, fieldName);

		List<T> list = new ArrayList<T>();
		for (T t : collection) {
			list.add(t);
		}
		common(list, fieldName, sortType);
	}

	/**
	 * List<T> 根据某个字段进行排序，默认是 ASC.<br />
	 * To sort List by fieldName and sortType default is ASC.<br />
	 * 
	 * @param list
	 *            要排序的数组
	 * @param fieldName
	 *            排序的字段
	 * @param SortType
	 *            升序：ASC，降序：DESC
	 */
	public static <T> void sort(List<T> list, final String fieldName, final SortType sortType) {
		check(list, fieldName);
		common(list, fieldName, sortType);
	}

	/**
	 * List<T> 根据某个字段进行排序，默认是 ASC.<br />
	 * To sort List by fieldName and sortType default is ASC.<br />
	 * 
	 * @param list
	 *            要排序的数组
	 * @param fieldName
	 *            String: 排序的字段
	 * @param SortType
	 *            String, 升序：ASC, asc; 降序：DESC, desc
	 */
	public static <T> void sort(List<T> list, final String fieldName, final String sortType) {
		check(list, fieldName);
		common(list, fieldName, sortType);
	}

	/**
	 * 根据Class对象，返回对应的应用类型，用于处理八个非引用类型.<br />
	 * 
	 * @since JDK 1.6
	 * @param className
	 * @return
	 */
	public static Class<?> refClass(Class<?> clazz) {
		checkNotNull("The class is null.", clazz);

		switch (clazz.getSimpleName().toLowerCase()) {
		case "boolean":
			return Boolean.class;
		case "byte":
			return Byte.class;
		case "short":
			return Short.class;
		case "int":
			return Integer.class;
		case "long":
			return Long.class;
		case "float":
			return Float.class;
		case "double":
			return Double.class;
		case "char":
			return Character.class;
		}

		return clazz;
	}

	/**
	 * 根据类型名称调用compare方法比较获得比较后的值.<br />
	 * To get the compare result by typeName and value.<br />
	 * 
	 * @param typeName
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static int result(String typeName, Object v1, Object v2) {
		int result = 0;
		switch (typeName) {
		case "byte":
			result = Byte.compare((byte) v1, (byte) v2);
			break;
		case "boolean":
			result = Boolean.compare((boolean) v1, (boolean) v2);
			break;
		case "char":
			result = Character.compare((char) v1, (char) v2);
			break;
		case "short":
			result = Short.compare((short) v1, (short) v2);
			break;
		case "int":
			result = Integer.compare((int) v1, (int) v2);
			break;
		case "long":
			result = Long.compare((long) v1, (long) v2);
			break;
		case "float":
			result = Float.compare((float) v1, (float) v2);
			break;
		case "double":
			result = Double.compare((double) v1, (double) v2);
			break;
		}
		return result;
	}

	/**
	 * The Sort common method
	 * 
	 * @param list
	 *            List<T>
	 * @param fieldName
	 *            String
	 * @param sortType
	 *            String
	 */
	private static <T> void common(List<T> list, final String fieldName, final String sortType) {
		Collections.sort(list, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				Field field = null;
				Object v1 = null;
				Object v2 = null;
				int result = 0;
				boolean isAsc = (sortType == null || "".equals(sortType.trim()) || "asc".equalsIgnoreCase(sortType));
				try {
					Class<?> clazz = o1.getClass();
					field = clazz.getDeclaredField(fieldName); // 获取成员变量
					field.setAccessible(true);// 设置成可访问状态

					// 获取field的值
					v1 = field.get(o1);
					v2 = field.get(o2);

					// 调用对象的compareTo()方法比较大小
					Method method = field.getType().getDeclaredMethod("compareTo", new Class[] { field.getType() });
					method.setAccessible(true);
					result = (Integer) method.invoke(v1, new Object[] { v2 });

					return isAsc ? result : result * (-1);
				} catch (Exception e) {
					String filedType = field.getType().getSimpleName().toLowerCase();
					result = result(filedType, v1, v2);
				}
				return isAsc ? result : result * (-1);
			}
		});
	}

	/**
	 * The Sort common method
	 * 
	 * @param list
	 *            List<T>
	 * @param fieldName
	 *            String
	 * @param sortType
	 *            SortType
	 */
	private static <T> void common(List<T> list, final String fieldName, final SortType sortType) {
		if (sortType == null) {
			common(list, fieldName, "");
		} else {
			common(list, fieldName, sortType.getLabel());
		}
	}

	/**
	 * To check List<T> list and String fieldName not null.
	 * 
	 * @param list
	 * @param fieldName
	 */
	private static <T> void check(List<T> list, final String fieldName) {
		checkNotNull("The list is null.", list);
		checkNotNull("The filedName is null.", fieldName);
	}

	/**
	 * To check List<T> list and String fieldName not null.
	 * 
	 * @param list
	 * @param fieldName
	 */
	private static <T> void check(Collection<T> collection, final String fieldName) {
		checkNotNull("The collection is null.", collection);
		checkNotNull("The filedName is null.", fieldName);
	}
}
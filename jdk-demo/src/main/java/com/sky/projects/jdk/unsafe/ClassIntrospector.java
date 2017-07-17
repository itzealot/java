package com.sky.projects.jdk.unsafe;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象信息
 * 
 * @author zealot
 */
@SuppressWarnings("restriction")
public class ClassIntrospector {

	private static final sun.misc.Unsafe unsafe = UnsafeHelper.unsafe();

	/**
	 * Size of any Object reference
	 */
	private static final int objectRefSize = unsafe.arrayIndexScale(Object[].class);

	/**
	 * Sizes of all primitive values(key=原始数据类型的Class对象,value=字节数)
	 */
	private static final Map<Class<?>, Integer> primitiveSizes;

	static {
		primitiveSizes = new HashMap<Class<?>, Integer>(10);
		primitiveSizes.put(byte.class, 1);
		primitiveSizes.put(char.class, 2);
		primitiveSizes.put(int.class, 4);
		primitiveSizes.put(long.class, 8);
		primitiveSizes.put(float.class, 4);
		primitiveSizes.put(double.class, 8);
		primitiveSizes.put(boolean.class, 1);
	}

	/**
	 * Get object information for any Java object. Do not pass primitives to
	 * this method because they will boxed and the information you will get will
	 * be related to a boxed version of your value.
	 *
	 * @param obj
	 *            Object to introspect
	 * @return Object info
	 * @throws IllegalAccessException
	 */
	public ObjectInfo introspect(final Object obj) throws IllegalAccessException {
		try {
			return introspect(obj, null);
		} finally { // clean visited cache before returning in order to make
			// this object reusable
			visited.clear();
		}
	}

	/**
	 * we need to keep track of already visited objects in order to support
	 * cycles in the object graphs
	 */
	private IdentityHashMap<Object, Boolean> visited = new IdentityHashMap<Object, Boolean>(100);

	/**
	 * use Field type only if the field contains null. In this case we will at
	 * least know what's expected to be stored in this field. Otherwise, if a
	 * field has interface type, we won't see what's really stored in it.
	 * Besides, we should be careful about primitives, because they are passed
	 * as boxed values in this method (first arg is object) - for them we should
	 * still rely on the field type.
	 * 
	 * @param obj
	 *            对象
	 * @param f
	 * @return
	 * @throws IllegalAccessException
	 */
	private ObjectInfo introspect(final Object obj, final Field f) throws IllegalAccessException {
		boolean isPrimitive = f != null && f.getType().isPrimitive();
		boolean isRecursive = false; // will be set to true if we have already
		// seen this object
		if (!isPrimitive) {
			if (visited.containsKey(obj))
				isRecursive = true;
			visited.put(obj, true);
		}

		final Class<?> type = (f == null || (obj != null && !isPrimitive)) ? obj.getClass() : f.getType();
		int arraySize = 0;
		int baseOffset = 0;
		int indexScale = 0;
		if (type.isArray() && obj != null) {
			baseOffset = unsafe.arrayBaseOffset(type);
			indexScale = unsafe.arrayIndexScale(type);
			arraySize = baseOffset + indexScale * Array.getLength(obj);
		}

		final ObjectInfo root;
		if (f == null) {
			root = new ObjectInfo("", type.getCanonicalName(), getContents(obj, type), 0, getShallowSize(type),
					arraySize, baseOffset, indexScale);
		} else {
			final int offset = (int) unsafe.objectFieldOffset(f);
			root = new ObjectInfo(f.getName(), type.getCanonicalName(), getContents(obj, type), offset,
					getShallowSize(type), arraySize, baseOffset, indexScale);
		}

		if (!isRecursive && obj != null) {
			if (isObjectArray(type)) { // introspect object arrays
				final Object[] ar = (Object[]) obj;
				for (final Object item : ar)
					if (item != null)
						root.addChild(introspect(item, null));
			} else {
				for (final Field field : getAllFields(type)) {
					if ((field.getModifiers() & Modifier.STATIC) != 0) {
						continue;
					}
					field.setAccessible(true);
					root.addChild(introspect(field.get(obj), field));
				}
			}
		}

		root.sort(); // sort by offset
		return root;
	}

	/**
	 * get all fields for this class, including all superclasses fields
	 * 
	 * @param type
	 * @return
	 */
	private static List<Field> getAllFields(final Class<?> type) {
		if (type.isPrimitive())
			return Collections.emptyList();
		Class<?> cur = type;
		final List<Field> res = new ArrayList<Field>(10);
		while (true) {
			Collections.addAll(res, cur.getDeclaredFields());
			if (cur == Object.class)
				break;
			cur = cur.getSuperclass();
		}
		return res;
	}

	/**
	 * check if it is an array of objects. I suspect there must be a more
	 * API-friendly way to make this check.
	 * 
	 * @param type
	 * @return
	 */
	private static boolean isObjectArray(final Class<?> type) {
		if (!type.isArray()) // 不是数组类型
			return false;
		// 不是常用的数组类型
		if (type == byte[].class || type == boolean[].class || type == char[].class || type == short[].class
				|| type == int[].class || type == long[].class || type == float[].class || type == double[].class)
			return false;
		return true;
	}

	/**
	 * advanced toString logic
	 * 
	 * @param val
	 * @param type
	 * @return
	 */
	private static String getContents(final Object val, final Class<?> type) {
		if (val == null)
			return "null";
		if (type.isArray()) {
			if (type == byte[].class)
				return Arrays.toString((byte[]) val);
			else if (type == boolean[].class)
				return Arrays.toString((boolean[]) val);
			else if (type == char[].class)
				return Arrays.toString((char[]) val);
			else if (type == short[].class)
				return Arrays.toString((short[]) val);
			else if (type == int[].class)
				return Arrays.toString((int[]) val);
			else if (type == long[].class)
				return Arrays.toString((long[]) val);
			else if (type == float[].class)
				return Arrays.toString((float[]) val);
			else if (type == double[].class)
				return Arrays.toString((double[]) val);
			else
				return Arrays.toString((Object[]) val);
		}
		return val.toString();
	}

	/**
	 * obtain a shallow size of a field of given class (primitive or object
	 * reference size)
	 * 
	 * @param type
	 * @return
	 */
	private static int getShallowSize(final Class<?> type) {
		if (type.isPrimitive()) { // 原始数据类型
			final Integer res = primitiveSizes.get(type);
			return res != null ? res : 0;
		} else
			return objectRefSize;
	}
}
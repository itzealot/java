package com.zt.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * References a generic type.<br />
 * 获取父类的泛型
 * 
 * @param <T>
 *            the referenced type
 * @since 3.1.0
 */
public abstract class TypeReference<T> {

	// 类型
	private Type rawType;

	protected TypeReference() throws TypeException {
		Type superclass = getClass().getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new TypeException(
					"TypeHandler '" + getClass() + "' extends TypeReference but misses the type parameter. "
							+ "Remove the extension or add a type parameter to it.");
		}
		rawType = ((ParameterizedType) superclass).getActualTypeArguments()[0];
		// TODO remove this when Reflector is fixed to return Types
		if (rawType instanceof ParameterizedType) {
			rawType = ((ParameterizedType) rawType).getRawType();
		}
	}

	public final Type getRawType() {
		return rawType;
	}

	@Override
	public String toString() {
		return rawType.toString();
	}

}
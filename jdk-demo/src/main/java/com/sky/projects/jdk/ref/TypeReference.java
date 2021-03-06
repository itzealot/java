package com.sky.projects.jdk.ref;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
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
					"TypeHandler '" + getClass() + "' extends TypeReference but misses the type parameter.");
		}
		rawType = ((ParameterizedType) superclass).getActualTypeArguments()[0];

		if (rawType instanceof ParameterizedType) {
			rawType = ((ParameterizedType) rawType).getRawType();
		}
	}

	public final Type getRawType() {
		return rawType;
	}

}
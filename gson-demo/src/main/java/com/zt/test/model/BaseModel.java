package com.zt.test.model;

import java.io.Serializable;

/**
 * The base model BaseModel<T>
 * 
 * @author zt
 *
 * @param <T>
 */
public abstract class BaseModel<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5821247279331937419L;

	public abstract T getId();

	public abstract void setId(T id);

}

package com.sky.projects.ws.demo.model;

import java.io.Serializable;

public abstract class BaseModel<T> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public abstract T getId();

	public abstract void setId(T id);

}

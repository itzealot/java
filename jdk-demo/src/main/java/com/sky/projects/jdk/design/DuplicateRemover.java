package com.sky.projects.jdk.design;

import java.util.Collection;
import java.util.List;

/**
 * DuplicateRemover
 * 
 * @author zealot
 *
 * @param <T>
 */
public interface DuplicateRemover<T> {

	/**
	 * remove duplicate data
	 * 
	 * @param datas
	 * @return
	 */
	Collection<T> removeDuplicate(List<T> datas);
}

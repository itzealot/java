package com.sky.projects.jdk.design;

/**
 * DuplicateRemoverFactrory
 * 
 * @author zealot
 */
public interface DuplicateRemoverFactrory<T> {

	DuplicateRemover<T> getDuplicateRemover(AppContext context);

}

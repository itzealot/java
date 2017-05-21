package com.sky.projects.design.mw;

/**
 * 立方和实现类
 * 
 * @author zealot
 */
public class PlusWorker extends Worker<Integer> {

	@Override
	public Integer handle(Integer input) {
		return input * input * input;
	}
}

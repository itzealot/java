package com.sky.projects.design.structural.adapter;

/**
 * 适配器类(适配新增播放的功能)
 * 
 * @author zealot
 *
 */
public interface MediaAdapter {

	/**
	 * 获取播放器
	 * 
	 * @return
	 */
	NewPlayer getPlayer();

	AudioEnum getAudioEnum();

}

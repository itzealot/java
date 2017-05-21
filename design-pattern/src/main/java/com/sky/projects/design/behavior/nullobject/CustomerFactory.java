package com.sky.projects.design.behavior.nullobject;

import com.sky.projects.design.behavior.nullobject.impl.NullCustomer;
import com.sky.projects.design.behavior.nullobject.impl.RealCustomer;

/**
 * 客户容器工厂
 * 
 * @author zealot
 */
public class CustomerFactory {

	// 客户名称列表
	public static final String[] names = { "Rob", "Joe", "Julie" };

	/**
	 * 根据名称获取客户的客户对象.
	 * 
	 * @param name
	 * @return
	 */
	public static AbstractCustomer getCustomer(String name) {
		for (int i = 0; i < names.length; i++) {

			// If the name is exist then return RealCustomer object
			if (names[i].equalsIgnoreCase(name)) {
				return new RealCustomer(name);
			}
		}

		// return NullCustomer object
		return new NullCustomer();
	}
}
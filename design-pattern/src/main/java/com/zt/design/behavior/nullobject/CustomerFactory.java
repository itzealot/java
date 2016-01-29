package com.zt.design.behavior.nullobject;

public class CustomerFactory {

	public static final String[] names = { "Rob", "Joe", "Julie" };

	/**
	 * To get AbstractCustomer by name
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
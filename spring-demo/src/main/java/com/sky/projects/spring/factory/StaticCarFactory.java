package com.sky.projects.spring.factory;

import java.util.HashMap;
import java.util.Map;

import com.sky.projects.spring.entity.Car;

public class StaticCarFactory {
	private static Map<String, Car> cars = new HashMap<String, Car>();

	static {
		cars.put("Audi", new Car("Audi", "", 300000));
		cars.put("Ford", new Car("Ford", "", 400000));
	}

	public static Car getCar(String name) {
		return cars.get(name);
	}
}

package com.sky.projects.spring.factory.bean;

import org.springframework.beans.factory.FactoryBean;

import com.sky.projects.spring.entity.Car;

/**
 * 自定义的bean需要实现 FactoryBean<T>接口
 * 
 * @author zengtao
 *
 */
public class CarFactoryBean implements FactoryBean<Car> {

	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Car getObject() throws Exception {
		return new Car(brand, "", 500000);
	}

	public Class<?> getObjectType() {
		return Car.class;
	}

	public boolean isSingleton() {
		return true;
	}

}

package com.sky.projects.gson.model;

import java.util.List;

public class Results {
	private String currentCity;
	private List<Weather> weather_data;

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public List<Weather> getWeather_data() {
		return weather_data;
	}

	public void setWeather_data(List<Weather> weather_data) {
		this.weather_data = weather_data;
	}

	@Override
	public String toString() {
		return "Results [currentCity=" + currentCity + ", weather_data="
				+ weather_data + "]";
	}
}
package com.zt.test.model;

import java.lang.reflect.Type;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestStatus {
	String json = "{"
			+ "\"error\": 0,"
			+ "\"status\": \"success\","
			+ "\"date\": \"2014-05-10\","
			+ "\"results\": ["
			+ "{"
			+ "\"currentCity\": \"南京\","
			+ "\"weather_data\": ["
			+ "{"
			+ "\"date\": \"周六(今天, 实时：19℃)\","
			+ "\"dayPictureUrl\": \"http://api.map.baidu.com/images/weather/day/dayu.png\","
			+ "\"nightPictureUrl\": \"http://api.map.baidu.com/images/weather/night/dayu.png\","
			+ "\"weather\": \"大雨\","
			+ "\"wind\": \"东南风5-6级\","
			+ "\"temperature\": \"18℃\""
			+ "},"
			+ "{"
			+ "\"date\": \"周日\","
			+ "\"dayPictureUrl\": \"http://api.map.baidu.com/images/weather/day/zhenyu.png\","
			+ "\"nightPictureUrl\": \"http://api.map.baidu.com/images/weather/night/duoyun.png\","
			+ "\"weather\": \"阵雨转多云\"," + "\"wind\": \"西北风4-5级\","
			+ "\"temperature\": \"21 ~ 14℃\"" + "}" + "]" + "}" + "]" + "}";

	@Test
	public void fromJson() {
		Type type = new TypeToken<Status>() {
		}.getType();
		Status status = new Gson().fromJson(json, type);
		System.out.println(status);
	}
}

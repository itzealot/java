package com.zt.test.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * gson和其他现有java json类库最大的不同时gson需要序列化得实体类不需要使用 annotation 来标识需要序列化得字段，
 * 同时gson又可以通过使用 annotation 来灵活配置需要序列化的字段。
 * 
 * @author zt
 *
 */
public class TestPerson {

	private Gson gson = new Gson();

	@Test
	public void toJson() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 10; i++) {
			Person p = new Person();
			p.setId(new Long(i));
			p.setName("name" + i);
			p.setAge(i * 5);
			persons.add(p);
		}
		System.out.println(gson.toJson(persons));

		// To get the json String from String array
		String[] strs = { "a", "b" };
		System.out.println(gson.toJson(strs));
	}

	@Test
	public void fromJson() {
		String str = "{\"id\":1,\"name\":\"name1\",\"age\":1}";
		String json = "[{\"id\":0,\"name\":\"name0\",\"age\":0},{\"id\":1,\"name\":\"name1\",\"age\":5},{\"id\":2,\"name\":\"name2\",\"age\":10},{\"id\":3,\"name\":\"name3\",\"age\":15}]";

		// To convert to JOPO from Json String
		Person person = gson.fromJson(str, Person.class);
		System.out.println(person);

		/**
		 * To convert to List<T> from Json String.<br />
		 * TypeToken : gson 提供的数据类型转换器，可以支持各种数据集合类型转换
		 */
		System.out.println(gson.fromJson(json, new TypeToken<List<Person>>() {
		}.getType()));
	}
}

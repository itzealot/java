package com.zt.test.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zt.test.model.JsonBean.B;
import com.zt.test.model.JsonBean.C;

public class TestJosnBean {

	@Test
	public void fromJson() {
		Gson gson = new Gson();
		String json = "{\"a\":\"a\",\"b\":[{\"b1\":\"b1\",\"b2\":\"b2\"},{\"b1\":\"bb1\",\"b2\":\"bb2\"}],\"c\":{\"c1\":\"c1\",\"c2\":\"c2\"}}";
		java.lang.reflect.Type type = new TypeToken<JsonBean>() {
		}.getType();
		JsonBean jsonBean = gson.fromJson(json, type);
		System.out.println(jsonBean);
	}

	@Test
	public void toJson() {
		JsonBean jsonBean = new JsonBean();

		// To set A
		jsonBean.setA("a");

		// To set B
		List<B> bs = new ArrayList<B>();
		B b = new B();
		b.setB1("b1");
		b.setB2("b2");
		bs.add(b);
		B bb = new B();
		bb.setB1("bb1");
		bb.setB2("bb2");
		bs.add(bb);
		jsonBean.setB(bs);

		// To set C
		C c = new C();
		c.setC1("c1");
		c.setC2("c2");
		jsonBean.setC(c);

		System.out.println(new Gson().toJson(jsonBean));
	}
}

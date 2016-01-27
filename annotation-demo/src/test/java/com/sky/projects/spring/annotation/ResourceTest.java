package com.sky.projects.spring.annotation;

public class ResourceTest extends AbstractTest {

	public void testInjectArray() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 4, resource.getStrings().length);
	}

	public void testInjectArray2() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 3, resource.getStrings2().length);
	}

	public void testInjectList() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 4, resource.getLists().size());
	}

	public void testInjectSet() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 3, resource.getSets().size());
	}

	public void testInjectMap() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 2, resource.getMaps().size());
	}

	public void testInjectMessageArray() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 4, resource.getMessages().length);
	}

	public void testInjectMessageList() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 2, resource.getLsMessages().size());
	}

	public void testInjectMessageSet() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 3, resource.getSetMessages().size());
	}

	public void testInjectMessageMap() {
		Resource resource = context.getBean(Resource.class);
		assertEquals("size is not equal", 2, resource.getMapMessages().size());
	}
}

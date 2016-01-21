package com.apusic.skynet.zookeeper.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.curator.framework.api.CuratorWatcher;
import org.junit.Test;

import com.apusic.skynet.zookeeper.BaseJunitTest;
import com.apusic.skynet.zookeeper.api.ZkPath;
import com.apusic.skynet.zookeeper.api.ZkRoot;

public class ZkPathTest extends BaseJunitTest {

	private ZkRoot root = connection.getRoot();

	@Test
	public void testGetInstance() {
		assertNotEquals("connection is null", null, root);
	}

	@Test
	public void createPath() {
		String path = "/test";
		ZkPath p = root.createPath(path);
		assertNotEquals("path is null", null, p);
		assertEquals("path is not equal", path, p.getPath());
	}

	@Test
	public void getPath() {
		assertEquals("path is not /", "/", root.getPath());
	}

	@Test
	public void contain() {
		String path = "/testExist";
		assertEquals("path is not equal", true, root.contain("/"));

		path = "/testExist/test";

		root.makedir(path).setData(path);
		assertEquals("path is not equal", true, root.contain(path));

		path = "/testExist/test";
		assertEquals("path is not equal", true, root.contain(path));

		root.makedir(path).setData(path);
		assertEquals("path is not equal", true, root.contain(path));

		assertEquals("path is not equal", true, root.get("/").contain(path));
	}

	@Test
	public void makedir() {
		String path = "/testMakedir";

		if (!root.contain(path)) {
			root.makedir(path).setData(path);
		} else {
			root.setData(path, path);
		}

		String data = root.get(path).getData();
		assertEquals("data is not equal", path, data);
	}

	@Test
	public void setData() {
		String path = "/";
		root.setData(path);

		String data = root.getData();
		assertEquals("data is not equal", "/", data);
	}

	@Test
	public void parent() {
		assertEquals("parent is not equal", null, root.parent());

		String path = "/testGetParent";
		root.delete(path);

		root.createPath(path).create();
		assertEquals("parent is not equal", "/", root.get(path).parent().getPath());

		path = "/testGetParent/childrens/children1";
		root.delete(path);

		root.createPath(path).create();
		assertEquals("parent is not equal", "/testGetParent/childrens", root.get(path).parent().getPath());
	}

	@Test
	public void testChildren() {
		assertNotEquals("don't have children", root.children().size(), 0);
		assertNotEquals("don't have children", root.get("/").children().size(), 0);
	}

	@Test
	public void testDelete() {
		String file = "/testDelete";

		ZkPath path = root.makedir(file);
		ZkPath temp = path.makedir("/t1");

		temp = path.makedir("/t2");
		System.out.println(temp.getPath());

		temp = path.makedir("/t2/t2");
		System.out.println(temp.getPath());

		temp = path.makedir("/t2/t2/t2");
		System.out.println(temp.getPath());

		System.out.println(root.children());

		root.delete();
	}

	@Test
	public void testMakedirMulty() {
		String file = "/tett2/test/t1/t2/t3";

		ZkPath path = root.makedir(file);
		assertEquals("path not exist", path.getPath(), file);
		assertEquals("path not exist", root.get("/tett2/test").exists(), true);
		assertEquals("path not exist", root.get("/tett2/test/t1").exists(), true);
		assertEquals("path not exist", root.get("/tett2/test/t1/t2").exists(), true);
		assertEquals("path not exist", root.get("/tett2/test/t1/t2/t3").exists(), true);
		assertEquals("path not exist", root.get("/tett2/test/t1/t2/t3").exists(), true);

		// System.out.println(root.children());

		assertNotEquals("size is not equal", root.children().size(), 0);
		assertEquals("element is not equal", root.children().get(0), "tett2");

		root.delete("/tett2");
		assertEquals("size is not equal", root.children().size(), 0);
	}

	@Test
	public void testDeleteMore() {
		String path = "/test/test1/t1/t2/t3";

		root.makedir(path);

		path = "/test/test2/t1/t1/t1";
		root.makedir(path);

		System.out.println(root.children());
		System.out.println(root.get("/test").children());

		root.delete("/test");
		System.out.println(root.children());
	}

	@Test
	public void testGetPathBaseOnCurrent() {
		System.out.println(root.getPathBaseOnCurrent("/"));
		System.out.println(root.getPathBaseOnCurrent("/test"));
		System.out.println(root.getPathBaseOnCurrent("test"));
		System.out.println(root.get("/test").get("test").getPathBaseOnCurrent("test"));
	}

	@Test
	public void testWatcher() {
		String path = "/testWatcher";
		ZkPath obj = root.createAndSet(path, path);

		CuratorWatcher watcher = new ZkWatch<String>(obj);

		String result = root.getDataUsingWatcher(path, watcher);

		System.out.println("==========================================");
		System.out.println("result=" + result);
		System.out.println("==========================================");

		root.setData(path, new String("change the data"));
		System.out.println("==========================================");
		System.out.println("result=" + result);
		System.out.println("==========================================");

		root.delete(path);
	}
}

package com.sky.projects.zookeeper;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import com.sky.projects.zookeeper.ZkPath.PathFilter;
import com.sky.projects.zookeeper.ZkPath.ZkACL;
import com.sky.projects.zookeeper.ZkPath.ZkWatch;
import com.sky.projects.zookeeper.impl.ZkWatchImpl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ZkPathTest extends BaseJunitTest {

	public ZkPathTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	private ZkRoot root = connection.getRoot();

	public void testGetRoot() {
		assertNotEquals("connection is null", null, root);
		assertEquals("path is not /", "/", root.getPath());
	}

	public void testCreate() {
		String path = "/testCreate";
		ZkPath p = root.create(path);
		assertNotEquals("path is null", null, p);
		assertEquals("path is not equal", path, p.getPath());

		path = path + "/t1";
		p = root.create(path);
		assertEquals("path is not equal", path, p.getPath());

		ZkPath pp = p.create("/");
		assertEquals("path is not equal", path, pp.getPath());
		assertEquals("object is not equal", p, pp);
	}

	public void testGetName() {
		String path = "/testGetName";

		assertEquals("dir name is not equal", "", root.getName());

		ZkPath p = root.create(path, true);

		assertEquals("dir name is not equal", "testGetName", p.getName());
	}

	public void testGetParent() {
		assertEquals("not equal", null, root.getParent());

		String path = "/testGetParent";

		ZkPath p = root.create(path);

		ZkPath parent = p.getParent();
		assertEquals("not equal", parent, root);
		assertEquals("not equal", parent.getPath(), root.getPath());
	}

	public void testCreateWithPathAndData() {
		String path = "/testCreateWithPathAndData";

		ZkPath p = root.create(path, path);
		assertEquals("not equal", path, p.getPath());

		String data = p.getData();
		assertEquals("not equal", path, data);

		path = "/t1";

		ZkPath pp = p.create(path, path);
		assertEquals("not equal", path, pp.getData());
	}

	public void testCreateIsEphemeral() {
		String path = "/testCreateIsEphemeral";

		ZkPath p = root.create(path, false);
		assertEquals("is not equal", path, p.getPath());

		ZkPath pp = root.create(path, true);
		assertEquals("is not equal", path, pp.getPath());
	}

	public void testCreateSequential() {
		String defaultPath = "/aip-default-name-";

		ZkPath p = root.createSequential(false);
		assertEquals("is equal", defaultPath, p.getPath());

		ZkPath pp = root.createSequential(true);
		assertEquals("is equal", defaultPath, pp.getPath());

		pp = root.createSequential(false, defaultPath);
		assertEquals("is equal", defaultPath, pp.getPath());

		pp = root.createSequential(true, defaultPath);
		assertEquals("is equal", defaultPath, pp.getPath());

		String path = "/testCreateSequential";
		pp = root.createSequential(path, true, path, ZkACL.OPEN_ACL_UNSAFE);
		assertEquals("is equal", path, pp.getPath());
		assertEquals("is equal", path, pp.getData());

		pp = root.createSequential(path, false, null, null);
		assertEquals("is equal", path, pp.getPath());
		Object data = pp.getData();
		assertEquals("is equal", null, data);
	}

	public void testPathFilter() {
		String base = "/testPathFilter";
		ZkPath p[] = new ZkPath[8];

		for (int i = 0; i < 8; i++) {
			p[i] = root.create(base + i, base + i);
			p[i].mkdir();
		}

		PathFilter filter = new PathFilter() {
			@Override
			public boolean accpet(String path) {
				if (path.equals(base + 5) || path.equals(base + 2) || path.equals(base + 1))
					return true;
				return false;
			}
		};

		assertEquals("is not equal", 3, root.children(filter).size());

		for (int i = 0; i < 8; i++) {
			p[i].delete();
		}

		assertEquals("is not equal", 0, root.children(filter).size());
	}

	public void testCreateWithACL() {
		String path = "/testCreateSequential";

		ZkPath p = root.create(path, true, path, null);
		assertEquals("is not equal", path, p.getPath());

		p.mkdir();
		int size = root.children().size();
		assertNotEquals("is not equal", 0, size);

		p.delete();
		assertEquals("is not equal", size - 1, root.children().size());
	}

	public void testLoadData() {
		String path = "/testGetData";
		ZkPath p = root.create(path);

		String data = p.getData();
		assertEquals("is not equal", null, data);
		assertEquals("is not equal", false, p.exists());

		p.mkdir();
		assertEquals("is not equal", true, p.exists());

		p.setData(path);
		assertEquals("is not equal", path, p.getData());
		p.saveData();

		p.loadData();
		assertEquals("is not equal", path, p.getData());

		p.delete();
	}

	public void testSaveData() {
		String path = "/testSaveData";

		ZkPath p = root.create(path);

		String data = p.getData();

		assertEquals("is not equal", null, data);
		assertEquals("is not equal", false, p.exists());

		p.mkdir();

		assertEquals("is not equal", true, p.exists());
		p.setData(path);
		p.saveData();

		p.setData(null);
		data = p.getData();
		assertEquals("is not equal", null, data);

		p.loadData();
		data = p.getData();
		assertEquals("is not equal", path, data);

		p.delete();

	}

	public void testMkdirs() {
		String path = "/testMkdirs/t/t";

		ZkPath p = root.create(path, path);

		p.mkdirs();

		assertEquals("is not equal", path, p.getPath());
		assertEquals("is not equal", true, p.exists());

		assertEquals("is not equal", "t", p.getName());

		String data = p.getData();
		assertEquals("is not equal", path, data);

		p.delete();
	}

	public void testMkdir() {
		String path = "/testMkdir";

		ZkPath pp = root.createSequential(path, false, path, null);
		assertEquals("is equal", path, pp.getPath());

		pp.mkdir();
		assertNotEquals("is equal", path, pp.getPath());

		pp.delete();

		path = "/testMkdir2";
		pp = root.create(path, path);
		assertEquals("is equal", path, pp.getPath());

		pp.mkdir();
		assertEquals("is equal", path, pp.getPath());

		pp.delete();
	}

	public void testZkWatch() {
		String path = "/testZkWatch";

		ZkPath p = root.create(path);

		ZkWatch watch = new ZkWatchImpl();
		p.setWatch(watch);

		p.mkdir();

		p.setData(path);

		p.saveData();

		String data = p.getData();
		assertEquals("is equal", path, data);

		path = "/c1";

		ZkPath pp = p.create(path);
		pp.mkdir();
		assertEquals("is equal", 1, p.children().size());
		pp.delete();

		p.delete();
	}

	void display(List<ZkPath> lists) {
		for (ZkPath p : lists) {
			System.out.println(p.getPath());
		}
	}
}

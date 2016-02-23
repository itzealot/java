package com.apusic.skynet.zookeeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import com.apusic.skynet.zookeeper.ZkPath.PathFilter;

public class ZkPathTest extends BaseJunitTest {

	private ZkRoot root = connection.getRoot();

	@Test
	public void testGetInstance() {
		assertNotEquals("connection is null", null, root);
	}

	@Test
	public void testCreate() {
		String path = "/testCreate";
		ZkPath p = root.create(path);
		assertNotEquals("path is null", null, p);
		assertEquals("path is not equal", path, p.getPath());

		path = path + "/t1";
		p = root.create(path);
		assertEquals("path is not equal", path, p.getPath());

		p = p.create("/");
		assertEquals("path is not equal", path, p.getPath());
	}

	@Test
	public void testGetPath() {
		assertEquals("path is not /", "/", root.getPath());
	}

	@Test
	public void testGetName() {
		String path = "/testGetName";

		assertEquals("dir name is not equal", "", root.getName());

		ZkPath p = root.create(path, true);

		assertEquals("dir name is not equal", "testGetName", p.getName());

		p.delete();

		assertEquals("children size is not equal", 0, root.children().size());
	}

	@Test
	public void testGetParent() {
		assertEquals("not equal", null, root.getParent());

		String path = "/testGetParent";

		ZkPath p = root.create(path);

		ZkPath parent = p.getParent();
		assertEquals("not equal", parent, root);
		assertEquals("not equal", parent.getPath(), root.getPath());

		p.delete();
	}

	@Test
	public void testCreateWithPathAndData() {
		String path = "/testCreateWithPathAndData";

		ZkPath p = root.create(path, path);
		assertEquals("not equal", path, p.getPath());
		assertEquals("not equal", path, p.getData());

		path = "/t1";

		ZkPath pp = p.create(path, path);
		assertEquals("not equal", path, pp.getData());
		assertEquals("children size is not equal", 1, p.children().size());

		pp.delete();
		assertEquals("children size is not equal", 0, p.children().size());

		p.delete();
		assertEquals("children size is not equal", 0, root.children().size());
	}

	@Test
	public void testCreateIsEphemeral() {
		String path = "/testCreateIsEphemeral";

		ZkPath p = root.create(path, false);
		assertEquals("is not equal", path, p.getPath());

		p.delete();
	}

	@Test
	public void testCreateSequential() {
		String path = "/testCreateSequential";

		ZkPath p = root.create(path).createSequential(true);

		assertEquals("is not equal", path, p.getPath());

		p.delete();
	}

	@Test
	public void testCreateSequentialWithData() {
		String path = "/testCreateSequentialWithData";

		ZkPath p = root.create(path).createSequential(true, path);

		assertEquals("is not equal", path, p.getPath());

		path = "/testCreateSequentialWithData2";

		ZkPath p2 = root.create(path).createSequential(false, path);

		assertEquals("is not equal", path, p2.getPath());

		p2.delete();
		p.delete();
	}

	@Test
	public void testPathFilter() {
		String base = "/testPathFilter";
		ZkPath p[] = new ZkPath[8];

		for (int i = 0; i < 8; i++) {
			p[i] = root.create(base + i, base + i);
		}

		PathFilter filter = new PathFilter() {
			@Override
			public boolean accpet(String path) {
				if (path.equals(base + 5) || path.equals(base + 2) || path.equals(base + 1))
					return true;
				return false;
			}
		};

		display(root.children(filter));

		assertEquals("is not equal", 3, root.children(filter).size());

		for (int i = 0; i < 8; i++) {
			p[i].delete();
		}
		assertEquals("is not equal", 0, root.children(filter).size());
	}

	@Test
	public void testCreateWithACL() {
		String path = "/testCreateSequential";

		ZkPath p = root.create(path, true, path, null);
		assertEquals("is not equal", path, p.getPath());

		assertEquals("is not equal", 1, root.children().size());

		p.delete();

		assertEquals("is not equal", 0, root.children().size());
	}

	@Test
	public void testGetData() {
		String path = "/testGetData";
		ZkPath p = root.create(path);
		String data = p.getData();
		assertEquals("is not equal", null, data);
		assertEquals("is not equal", false, p.exists());

		p.mkdir();
		assertEquals("is not equal", true, p.exists());

		p.setData(path);
		data = p.getData();
		assertEquals("is not equal", path, data);

		p.delete();
	}

	@Test
	public void testMkdirs() {
		String path = "/testMkdirs/t/t";

		ZkPath p = root.create(path, path);
		assertEquals("is not equal", path, p.getPath());
		assertEquals("is not equal", true, p.exists());

		assertEquals("is not equal", "t", p.getName());

		String data = p.getData();
		assertEquals("is not equal", path, data);

		p.delete();
	}

	@Test
	public void test() {

	}

	@Test
	public void base() {
		display(root.children());
		assertEquals("is not equal", 0, root.children().size());
	}

	void display(List<ZkPath> lists) {
		for (ZkPath p : lists) {
			System.out.println(p.getPath());
		}
	}
}

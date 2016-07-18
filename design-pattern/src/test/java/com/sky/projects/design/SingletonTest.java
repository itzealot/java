package com.sky.projects.design;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sky.projects.design.creational.sigleton.serial.Singleton;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SingletonTest extends TestCase {
	private ObjectOutputStream oos;

	public SingletonTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(SingletonTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	public void testSerialSingleton() throws Exception {
		Singleton singleton = Singleton.getInstance();
		oos = new ObjectOutputStream(new FileOutputStream("Singleton.txt"));
		oos.writeObject(singleton);
		oos.close();

		Singleton instance = null;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Singleton.txt"));
		instance = (Singleton) in.readObject();
		in.close();

		assertEquals(instance, singleton);
	}
}

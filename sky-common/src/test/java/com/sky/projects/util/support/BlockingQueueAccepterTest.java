package com.sky.projects.util.support;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sky.projects.util.SkyAcceptable;
import com.sky.projects.util.support.BlockingQueueAccepter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BlockingQueueAccepterTest extends TestCase {
	public BlockingQueueAccepterTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(BlockingQueueAccepterTest.class);
	}

	public void testApp() throws InterruptedException {
		assertTrue(true);

		BlockingQueue<String> container = new LinkedBlockingQueue<>();
		SkyAcceptable<String> acceptable = new BlockingQueueAccepter<>(container);
		acceptable.accept("aa");
		System.out.println(container.take());
	}

}

package com.sky.projects.jdk.thread.atomic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import com.sky.projects.jdk.thread.Threads;

import net.sf.ehcache.util.NamedThreadFactory;

public class AtomicRefrenceTest {

	public static void main(String[] args) {
		ExecutorService consumer = Executors.newSingleThreadExecutor(new NamedThreadFactory("CONSUMER", false));

		AtomicReference<List<String>> ref = new AtomicReference<>();

		Set<NotifyListener> listeners = new HashSet<>();

		NotifyListener listener = new NotifyListener() {
			@Override
			public void notify(List<String> lists) {
				ref.set(lists);
			}
		};

		listeners.add(listener);

		// 提交任务获取
		consumer.execute(() -> {
			while (ref.get() == null) {
				Threads.sleep(100);
			}

			System.out.println("lists:" + ref.get());
		});

		notify(listeners, Arrays.asList("a", "b", "c"));
		consumer.shutdown();
	}

	private static void notify(Set<NotifyListener> listeners, List<String> lists) {
		for (NotifyListener listener : listeners) {
			listener.notify(lists);
		}
	}

	public static interface NotifyListener {

		void notify(List<String> lists);
	}

}

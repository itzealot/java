package com.sky.projects.jdk.spf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务访问API，是客户端用来获取服务的实例
 * 
 * @author zealot
 *
 */
public final class Services {

	// 常引用
	private static final Map<String, Provider> providers = new ConcurrentHashMap<>();

	public static final String DEFAULT_PROVIDER_NAME = "<def>";

	public static Map<String, Provider> registerDefaultProvider(Provider provider) {
		registerProvider(DEFAULT_PROVIDER_NAME, provider);

		return providers;
	}

	public static Map<String, Provider> registerProvider(String name, Provider provider) {
		providers.put(name, provider);

		return providers;
	}

	public static Service newInstance() {
		return newInstance(DEFAULT_PROVIDER_NAME);
	}

	public static Service newInstance(String name) {
		Provider provider = providers.get(name);

		if (provider == null) {
			throw new IllegalArgumentException("No provider register with name: " + name);
		}

		return provider.newService();
	}

	public static Map<String, Provider> getProviders() {
		return providers;
	}

	private Services() {
	}
}

package com.sky.projects.redis;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class Redis {
	private Jedis jedis = null;

	public Redis(Jedis jedis) {
		checkNotNull(jedis, "jedis must not be null");

		this.jedis = jedis;
	}

	public <T> T execute(RedisResultAction<T> action) {
		checkNotNull(action, "action must not be null");

		return action.action(jedis);
	}

	public <T> void execute(RedisNoResultAction action) {
		checkNotNull(action, "action must not be null");

		action.action(jedis);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> execute(PipelineResultAction<T> action) {
		checkNotNull(action, "action must not be null");

		Pipeline pipeline = jedis.pipelined();

		action.action(pipeline);

		return (List<T>) pipeline.syncAndReturnAll();
	}

	public void execute(PipelineNoResultAction action) {
		checkNotNull(action, "action must not be null");

		Pipeline pipeline = jedis.pipelined();

		action.action(pipeline);

		pipeline.sync();
	}

	public interface RedisAction<T> {
	}

	public interface RedisResultAction<T> extends RedisAction<T> {
		public T action(Jedis jedis);
	}

	public interface RedisNoResultAction extends RedisAction<Void> {
		public void action(Jedis jedis);
	}

	public interface PipelineResultAction<T> extends RedisAction<T> {
		public void action(Pipeline pipeline);
	}

	public interface PipelineNoResultAction extends RedisAction<Void> {
		public void action(Pipeline pipeline);
	}

	public Jedis getJedis() {
		return jedis;
	}

}

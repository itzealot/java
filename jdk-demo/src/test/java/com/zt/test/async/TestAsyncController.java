package com.zt.test.async;

import com.sky.projects.jdk.async.AsyncController;
import com.sky.projects.jdk.async.FutureContext;
import com.sky.projects.jdk.async.OutputResult;

public class TestAsyncController {

	public static void main(String[] args) {
		AsyncController controller = new AsyncController();

		// 启动异步计算
		controller.startAsyncCompution();

		// 启动异步计算结果输出线程，该线程扫描异步计算 Future 的状态，如果已经完成，则输出异步计算结果
		OutputResult output = new OutputResult();

		FutureContext<String> context = controller.getFutureContext();
		context.setService(controller.getService());

		output.setFutureContext(context);

		Thread resultThread = new Thread(output);
		resultThread.start();
	}
}

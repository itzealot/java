package com.projects.sky.zookeeper.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.projects.sky.zookeeper.service.InitService;
import com.projects.sky.zookeeper.service.impl.InitServiceImpl;

public class Client {

	public static void main(String[] args) {

		try {
			if (args.length > 0 && "--auto".equals(args[0])) {
				automatic(args);
			} else
				interact(args);
		} catch (Exception e) {
			System.out.println("catch exception:");
			e.printStackTrace();
			System.exit(1);
		}
	}

	static String command = "====== Zookeeper 初始化工具 \n" + "====== 命令说明 \n" + "====== connect host:port 连接zookeeper\n"
			+ "====== initRoot   第一次运行先初始化根节点\n" + "====== init file.xml   初始化组织机构\n" + "====== view 查看组织机构   \n"
			+ "====== clean 清除组织机构   \n" + "====== backup file.xml 备份组织机构   \n" + "====== help 获取命令帮助\n"
			+ "====== exit 退出程序 \n";

	/**
	 * 自动化处理系统。参数顺序如下：
	 * 
	 * <pre>
	 * --auto zkaddress command args
	 * </pre>
	 * 
	 * 其中command和args的参数和手工命令完全一致
	 * 
	 * @param args
	 */
	private static void automatic(String[] args) {
		if (args.length < 3) {
			printAutoHelp();
			System.exit(1);
		}
		// 连接 zk
		Client.connect(args[1]);
		try {
			// 检查参数
			if (Client.zooKeeper == null) {
				System.err.println("zookeeper connect fail, exit");
				System.exit(2);
			} else if ("initRoot".equals(args[2])) {
				client.initRoot();
			} else if ("view".equals(args[2])) {
				client.view();
			} else if ("clean".equals(args[2])) {
				client.clean();
			} else {
				if (args.length < 4) {
					printAutoHelp();
					System.exit(1);
				}
				if ("init".equals(args[2])) {
					client.init(args[3]);
				} else if ("backup".equals(args[2])) {
					client.backup(args[3]);
				} else {
					System.err.println("unknown command " + args[2]);
				}
			}
		} finally {
			Client.close();
		}
		System.out.println("======   exit 退出程序                                            ======\n");
		System.exit(0);
	}

	private static void printAutoHelp() {
		System.out
				.println("" + "automatic usage: --auto <host:port> command args\r\n" + "command: \r\n" + command + "");
	}

	public static void interact(String[] args) {

		System.out.printf(command);
		boolean flag = true;
		// String dir = System.getProperty("user.dir");
		while (flag) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String str = br.readLine();
				// 重定向的时候如果出现流结尾，则需要直接退出
				if (str == null) {
					System.err.println("read end of stream, exit");
					System.exit(1);
				}

				String[] cmd = str.split(" ");

				if ("help".equals(cmd[0])) {
					System.out.printf(command);
					continue;
				}

				if ("connect".equals(cmd[0])) {
					if (cmd.length < 2) {
						System.out.printf(command);
						continue;
					}
					Client.connect(cmd[1]);
					continue;
				}

				if ("exit".equals(cmd[0])) {
					br.close();
					Client.close();
					flag = false;
					System.out.println("======   exit 退出程序                                            ======\n");
					System.exit(0);
					break;
				}

				if (Client.zooKeeper == null) {
					System.out.printf("====== zookeeper未连接\n");
					System.out.printf("====== connect host:port 连接zookeeper\n");
					continue;
				}
				if ("view".equals(cmd[0])) {
					client.view();
					continue;
				}
				if ("initRoot".equals(cmd[0])) {
					client.initRoot();
					continue;
				}

				if ("init".equals(cmd[0])) {
					if (cmd.length < 2) {
						System.out.printf(command);
						continue;
					}
					client.init(cmd[1]);
					continue;
				}
				if ("backup".equals(cmd[0])) {
					if (cmd.length < 2) {
						System.out.printf(command);
						continue;
					}
					client.backup(cmd[1]);
					continue;
				}
				if ("clean".equals(cmd[0])) {
					client.clean();
					continue;
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
	}

	private int sessionTime = 15000;

	private static ZooKeeper zooKeeper;

	private static Client client;

	private Lock lock;

	private Condition condition;

	private static CountDownLatch countDownLatch;

	private ExecutorService executorService = Executors.newCachedThreadPool();

	private static InitService initService;

	private Client() {
		super();
		lock = new ReentrantLock();
		condition = lock.newCondition();
		countDownLatch = new CountDownLatch(1);
	}

	public static synchronized ZooKeeper connect(final String hostport) {
		try {
			if (zooKeeper == null) {
				client = new Client();
				client.connection(hostport);
			}
			countDownLatch.await();
			initService = new InitServiceImpl(zooKeeper);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		return zooKeeper;
	}

	public void backup(final String xml) {
		initService.backup(xml);
	}

	public void clean() {
		initService.clean();
	}

	public void view() {
		initService.view();
	}

	public void initRoot() {
		initService.initRoot();
	}

	public void init(final String xml) {

		initService.init(xml);
	}

	public static void close() {
		try {
			if (zooKeeper != null)
				zooKeeper.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void connection(final String hostPort) {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					while (true) {
						zooKeeper = new ZooKeeper(hostPort, sessionTime, new Watcher() {
							@Override
							public void process(WatchedEvent event) {

								if (event.getType() == Event.EventType.None) {
									switch (event.getState()) {
									case SyncConnected:
										System.out.println("===Zookeeper connected ===\n");
										countDownLatch.countDown();
										break;
									case Disconnected:
										System.out.println("===Zookeeper connection Disconnected ===\n");
									case Expired:
										lock.lock();
										System.out.println("===Zookeeper connection Timeout ===\n");
										condition.signal();
										lock.unlock();
										break;
									default:
										break;
									}
								}
							}
						});
						condition.await();
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
					condition.signal();
					countDownLatch = new CountDownLatch(1);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} finally {
					lock.unlock();
				}
			}
		});
	}

}

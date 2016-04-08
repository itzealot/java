package com.projects.sky.util.base;

import java.util.ResourceBundle;

public class ResourceBundles {
	/**
	 * 查找 baseName 下的.properties文件.如baseName 为com.zt.test.test，即查找包名为
	 * com.zt.test下的test.properties 文件中 键值为 key 的value值。
	 * 
	 * @param key
	 *            Properties 文件中的键
	 * @param baseName
	 *            资源的包名，它跟普通java类的命名规则完全一样：<br />
	 *            1) 区分大小写<br />
	 *            2) 扩展名 .properties 省略。就像对于类可以省略掉 .class扩展名一样<br />
	 *            3) 资源文件必须位于指定包的路径之下（位于所指定的classpath中）<br />
	 *            4) 假如你是在非Web项目中使用，则一定要写资源文件的路径，也就是包路径必须存在。如果是Web项目，不写包路径可以，
	 *            此时将资源文件放在WEB-INF\classes\目录下就可以。<br />
	 * @return 返回键为看 key 的 value 值
	 */
	public static String getString(String key, String baseName) {
		ResourceBundle prop = ResourceBundle.getBundle(baseName);
		return prop.getString(key);
	}

}

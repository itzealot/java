package com.sky.projects.jdk.global;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化资源绑定测试
 * 
 * 类的作用就是读取资源属性文件（properties），然后根据.properties文件的名称信息（本地化信息），匹配当前系统的国别语言信息（
 * 也可以程序指定），然后获取相应的properties文件的内容。
 * 
 * @author zealot
 */
public class TestResourceBundle {

	public static void main(String[] args) {
		// read myresXXX.properties

		// read myres_zh_CN.properties
		ResourceBundle resb1 = ResourceBundle.getBundle("myres", new Locale("zh", "CN"));
		System.out.println(resb1.getString("aaa"));

		// read myres_zh_CN.properties
		System.out.println(ResourceBundle.getBundle("myres").getString("aaa"));
		ResourceBundle resb2 = ResourceBundle.getBundle("myres", Locale.getDefault());
		System.out.println(resb2.getString("aaa"));

		// read myres_en_US.properties
		ResourceBundle resb3 = ResourceBundle.getBundle("myres", new Locale("en", "US"));
		System.out.println(resb3.getString("aaa"));
	}
}
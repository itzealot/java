package com.sky.projects.design.javassist;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	@SuppressWarnings("unchecked")
	public static void testJavassistDenfinClass(Class<Executor> clazz) throws Exception {
		// 创建类池,true为使用默认路径
		ClassPool classPool = new ClassPool(true);

		String className = clazz.getName();
		CtClass ctClass = classPool.makeClass(className + "JavassitProxy");

		// 添加接口,可选
		// ctClass.addInterface(classPool.get(RayTestInterface.class.getName()));

		// 添加超类
		ctClass.setSuperclass(classPool.get(clazz.getName()));

		// 添加默认构造函数
		ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));

		// 添加屬性
		ctClass.addField(CtField.make("public " + className + " real = new " + className + "();", ctClass));

		// 添加方法,里面进行动态代理logic
		ctClass.addMethod(CtNewMethod
				.make("public String execute(){ return \"before \" + real.execute() + \" after\";}", ctClass));

		Class<Executor> testClass = ctClass.toClass();
		Executor test = testClass.newInstance();
		test.execute();
	}

	public static interface Executor {
		public String execute();
	}

	public static void createJavassistBytecodeDynamicProxy(Class<?> clazz) throws Exception {
		ClassPool pool = new ClassPool();

		// 定义类名
		CtClass ct = pool.makeClass(clazz.getName() + "$Javassist");

		// 需要实现的接口
		ct.addInterface(pool.get(clazz.getName()));

		// 添加构造函数
		ct.addConstructor(CtNewConstructor.defaultConstructor(ct));

		// 添加类的信息，使用动态java代码
		ct.addField(CtField.make("public " + clazz.getName() + "real;", ct));
	}

	public static Object makeClass() throws Exception {
		// ClassPool pool = ClassPool.getDefault();
		// CtClass cc = pool.makeClass("Point");

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.sky.projects.proxy.dynamic.Proxy");
		CtMethod method = CtNewMethod.make("public Integer getInteger() { return null; }", cc);
		cc.addMethod(method);

		CtField f = new CtField(CtClass.intType, "i", cc);
		// point.addField(f);
		cc.addField(f);

		Class<?> clazz = cc.toClass();
		return clazz.newInstance();
	}

	public void test() throws Exception {
		/*
		 * 首先获取到class定义的容器ClassPool，通过它获取已经编译好的类(Compile time
		 * class)，并给这个类设置一个父类，而writeFile讲这个类的定义从新写到磁盘，以便后面使用
		 */
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("Point");

		cc.setSuperclass(pool.get("SuperClass"));
		cc.writeFile();

		// 由CtClass可以方便的获取字节码和加载字节码
		// byte[] b = cc.toBytecode();
		System.out.println(cc.toClass());
		Class<?> clazz = cc.toClass();
		System.out.println(clazz.newInstance());

		// 定义新类
		CtClass newClass = pool.makeClass("Point");
		System.out.println(newClass);
	}

	public static void main(String[] args) throws Exception {
		// 对已有代码每次move执行时做埋点
		ClassPool pool = ClassPool.getDefault();

		// 定义类
		CtClass cc = pool.makeClass("com.sky.projects.javassist.Point");

		// 创建字段
		CtField x = new CtField(CtClass.intType, "x", cc);
		CtField y = new CtField(CtClass.intType, "y", cc);

		cc.addField(x);
		cc.addField(y);

		CtMethod methodGetX = CtNewMethod.make("public Integer getX() { return x; }", cc);
		cc.addMethod(methodGetX);

		CtMethod methodSetX = CtNewMethod.make("public void setX(Integer x) { x = $1; }", cc);
		cc.addMethod(methodSetX);

		// 获取声明的方法
		CtMethod m = cc.getDeclaredMethod("getX");
		// 其中$1和$2表示调用栈中的第一和第二个参数
		m.insertBefore("{ System.out.println($1); System.out.println($2);}");

		Object instance = cc.toClass().newInstance();
		System.out.println(instance);

		cc.writeFile();
	}

	public ClassPool load() {
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(AppTest.class));
		return pool;
	}
	// static class Point {
	// int x, y;
	//
	// void move(int dx, int dy) {
	// x += dx;
	// y += dy;
	// }
	// }

}

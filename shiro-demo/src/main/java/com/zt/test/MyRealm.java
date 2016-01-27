package com.zt.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义 Realm 实现
 * 
 * @author zt
 *
 */
public class MyRealm implements Realm {

	/**
	 * 根据Token获取认证信息
	 */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {

		// 得到用户名
		String username = (String) token.getPrincipal();

		// 得到密码
		String password = new String((char[]) token.getCredentials());

		// 如果用户名错误
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();
		}

		// 如果密码错误
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException();
		}

		// 如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	/**
	 * 返回一个唯一的 Realm 名字
	 */
	@Override
	public String getName() {
		return "MyRealm";
	}

	/**
	 * 判断此 Realm 是否支持此 Token
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

}

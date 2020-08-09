package com.explore.galaxy.basic.consts.response;

public class Codes {
	/**
	 * @author HuangJq @Date 2019年5月8日下午2:52:01
	 * @Description 请求回应
	 * 
	 */

	/**
	 * 过期token
	 */
	public static final int CODE_TOKEN_OVERVUE = 100;
	/**
	 * 非法token
	 */
	public static final int CODE_TOKEN_INVALID = 102;
	/**
	 * 用户已登录
	 */
	public static final int CODE_INUSED_CODE = 104;
	/**
	 * 正确
	 */
	public static final int CODE_ALL_CORRECT = 200;
	/**
	 * 用户不存在
	 */
	public static final int CODE_USER_NOTEXIST = 202;
	/**
	 * 密码错误
	 */
	public static final int CODE_PASSWORD_WRONG = 204;

	public static final int CODE_ACCOUNT_LOCK = 206;

	public static final int CODE_FAIL_TIMES = 208;
}

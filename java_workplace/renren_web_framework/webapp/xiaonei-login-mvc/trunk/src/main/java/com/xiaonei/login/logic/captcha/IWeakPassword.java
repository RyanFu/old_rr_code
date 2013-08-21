package com.xiaonei.login.logic.captcha;

public interface IWeakPassword {

	/**
	 * is weak password
	 * @param userId user id
	 * @return true or false
	 */
	public boolean isWeakPassword(int userId);
}

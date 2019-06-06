package com.study.base.util;

import java.util.Random;

/**
 * 日志跟踪ID
 *
 */
public class SessionUtil {

	/**
	 * 为每个请求生成唯一的sessionId
	 *
	 * @return
	 */
	public static String getSessionId() {
		return System.currentTimeMillis() + "-" + String.format("%04d", new Random().nextInt(10000));
	}

}

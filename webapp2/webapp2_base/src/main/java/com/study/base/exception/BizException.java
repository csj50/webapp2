package com.study.base.exception;

import org.slf4j.MDC;
import com.study.base.common.Constants;

public class BizException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BizException(String errorCode) {
		super(errorCode);
		setLog();
	}

	public BizException(String errorCode, String... errorArgs) {
		super(errorCode, errorArgs);
		setLog();
	}

	public BizException(String errorCode, Throwable cause) {
		super(errorCode, cause);
		setLog();
	}

	public BizException(String errorCode, Throwable cause, String... errorMsg) {
		super(errorCode, cause, errorMsg);
		setLog();
	}

	// 逻辑ID
	private String logId;
	// 业务ID
	private String bizId;
	// Session ID
	private String sessionId;

	// 设置LogId
	public void setLog() {
		this.logId = MDC.get(Constants.LOG_ID);
		this.bizId = MDC.get(Constants.BIZ_ID);
		this.sessionId = MDC.get(Constants.SESSION_ID);
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}

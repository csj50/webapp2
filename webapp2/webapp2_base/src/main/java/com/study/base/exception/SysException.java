package com.study.base.exception;

public class SysException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SysException(String errorCode) {
		super(errorCode);
	}

	public SysException(String errorCode, String... errorArgs) {
		super(errorCode, errorArgs);
	}

	public SysException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public SysException(String errorCode, Throwable cause, String... errorMsg) {
		super(errorCode, cause, errorMsg);
	}

}

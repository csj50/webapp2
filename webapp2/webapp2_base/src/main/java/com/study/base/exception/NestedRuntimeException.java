package com.study.base.exception;

public abstract class NestedRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6972683557406217498L;

	public NestedRuntimeException() {
		super();
	}

	public NestedRuntimeException(Throwable cause) {
		super(cause);
	}

	public NestedRuntimeException(String message) {
		super(message);
	}

	public NestedRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getMessage() {
		Throwable cause = getCause();
		String message = super.getMessage();
		if (cause != null) {
			StringBuilder sb = new StringBuilder();
			if (message != null)
				sb.append(message).append("; ");
			sb.append("nested exception is ").append(cause);
			return sb.toString();
		}
		return message;
	}

	public Throwable getRootCause() {
		Throwable rootCause = null;
		for (Throwable cause = getCause(); cause != null && cause != rootCause; cause = cause.getCause())
			rootCause = cause;
		return rootCause;
	}
}

package com.study.base.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BaseException extends NestedRuntimeException {

	private static final long serialVersionUID = 7724815658604611711L;

	//static ResourceBundle bundle = ResourceBundle.getBundle("message", Locale.ENGLISH);

	protected String errorCode;

	protected String[] errorArgs;

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, String... errorArgs) {
		super();
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public BaseException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, Throwable cause, String... errorArgs) {
		super(cause);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public String getMessage() {
		String message = "";
		try {
			if (errorCode == null) {
				message = super.getMessage();
			} else {
				for (String str : errorArgs) {
					message = message + " " + str;
				}
				//message = bundle.getString(errorCode);
				//message = MessageFormat.format(message, (Object[]) errorArgs);

			}
		} catch (MissingResourceException mse) {
			message = "Can't get the message of the Error Code";
		}

		return message;
	}

	public String getStackMessage() {
		String message = "";
		Throwable cause = getCause();
		if (cause != null) {
			StringWriter writer = new StringWriter();
			PrintWriter pw = new PrintWriter(writer);
			cause.printStackTrace(pw);
			message = writer.toString();
		}
		return message;
	}

	public String getLocalizedMessage() {
		String message = getMessage();
		if (errorCode != null) {
			message = "<" + errorCode + ">" + message;
		}
		return message;
	}

	public String getErrorMsg() {
		return getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String[] getErrorArgs() {
		return errorArgs;
	}

	public void setErrorArgs(String[] errorArgs) {
		this.errorArgs = errorArgs;
	}

}

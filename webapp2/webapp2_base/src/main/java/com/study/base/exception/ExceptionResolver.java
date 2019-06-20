package com.study.base.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常捕获
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private static Logger log = LoggerFactory.getLogger(ExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (ex instanceof BizException && !isAjax(request)) {
			//业务异常
			BizException bizException=(BizException) ex;
			
			log.error("[业务异常]{}	{}",bizException.getLocalizedMessage(), bizException.getRootCause());
			log.error("errorCode:{}  errorMsg:{}", bizException.getErrorCode(), bizException.getErrorArgs());
			
			//其他处理
			
			return new ModelAndView("exceptionPage");
		} else {
			//其他异常
			log.error("其他异常: {}", ex.getMessage(), ex);
		}
		return new ModelAndView();
	}

	// 判断是否是Ajax请求
	private boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}

}

package com.study.base.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.study.base.common.Constants;
import com.study.base.util.SessionUtil;

public abstract class AbstractJmsReceiveTemplate extends MessageListenerAdapter {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void onReceive(Object param) {
        try {
            //日志跟踪
            MDC.put(Constants.SESSION_ID, SessionUtil.getSessionId());
            
            String slog = "==recive param==" + param;
            
            logger.info(slog);

            receive(param);
            
        } catch (Throwable e) {
            logger.error("系统异常：{}", e);
        } finally {
            MDC.remove(Constants.SESSION_ID);
        }
    }

    protected abstract void receive(Object param) throws Exception;
}

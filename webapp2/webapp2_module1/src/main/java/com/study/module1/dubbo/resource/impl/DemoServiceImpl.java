package com.study.module1.dubbo.resource.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.study.module1.dubbo.resource.DemoService;

public class DemoServiceImpl implements DemoService {

	@Override
	public String echo(String msg) {
		RpcContext rpcContext = RpcContext.getContext();
		// 获取当前服务配置信息
		String application = RpcContext.getContext().getUrl().getParameter("application");
		return String.format("Service [name :%s , port : %d] %s say : Hello,%s", 
				application, 
				rpcContext.getLocalPort(),
				rpcContext.getMethodName(), msg);
	}

}

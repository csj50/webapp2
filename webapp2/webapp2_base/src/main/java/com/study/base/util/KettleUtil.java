package com.study.base.util;

import java.util.Map;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.filerep.KettleFileRepository;
import org.pentaho.di.repository.filerep.KettleFileRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KettleUtil {

	private static final Logger logger = LoggerFactory.getLogger(KettleUtil.class);

	/**
	 * Kettle环境初始化
	 * 
	 * @param classpathDIR
	 * @return
	 * @throws KettleException
	 */
	private static KettleFileRepository kettleInit(String classpathDIR) throws KettleException {
		// 设置插件目录
		System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS", "E:/abc");
		// 或者
		// StepPluginType.getInstance().getPluginFolders().add(new PluginFolder("E:/abc", false, true));
		// 初始化插件，注册插件类型并加载它们各自的插件
		PluginRegistry.init();
		// 初始化客户端环境
		KettleEnvironment.init();
		String basedir = Thread.currentThread().getContextClassLoader().getResource(classpathDIR).getPath();
		// 资源库元对象
		KettleFileRepositoryMeta repoMeta = new KettleFileRepositoryMeta(null, null, null, basedir);
		// 文件形式的资源库
		KettleFileRepository fileRepo = new KettleFileRepository();
		fileRepo.init(repoMeta);
		return fileRepo;
	}

	/**
	 * 执行作业
	 * 
	 * @param initKettleParam
	 * @param kjbFileName
	 * @return
	 */
	public static boolean runKettleJob(Map<String, String> initKettleParam, String kjbFileName) {
		logger.info("runKettleJob: {}", kjbFileName);
		try {
			// 先加载资源库目录
			// 取resources下的kettle目录
			KettleFileRepository fileRepo = kettleInit("kettle");
			// 再加载kjb文件
			JobMeta jobMeta = fileRepo.loadJob(kjbFileName, null, null, null);
			// 作业
			Job job = new Job(null, jobMeta);
			// 初始化job参数，脚本中获取参数值：${variableName}
			if (initKettleParam != null) {
				for (String variableName : initKettleParam.keySet()) {
					job.setVariable(variableName, initKettleParam.get(variableName));
				}
			}
			// 执行作业
			job.start();
			// 等待作业执行完成
			job.waitUntilFinished();
			if (job.getErrors() > 0) {
				logger.info("runKettleJob执行失败:", kjbFileName);
			} else {
				logger.info("runKettleJob执行成功:", kjbFileName);
			}
			return true;
		} catch (Exception e) {
			logger.error("runKettleJob: {}", e);
			return false;
		}
	}

	/**
	 * 执行转换
	 * 
	 * @param initKettleParam
	 * @param ktrFileName
	 * @return
	 */
	public static boolean runKettleTransfer(Map<String, String> initKettleParam, String ktrFileName) {
		logger.info("runKettleTransfer: {}", ktrFileName);
		try {
			// 先加载资源库目录
			// ktr文件放在resources下的kettle目录
			KettleFileRepository fileRepo = kettleInit("kettle");
			// 再加载ktr文件
			TransMeta transMeta = fileRepo.loadTransformation(ktrFileName, null, null, false, null);
			// 转换
			Trans trans = new Trans(transMeta);
			// 初始化trans参数，脚本中获取参数值：${variableName}
			if (initKettleParam != null) {
				for (String variableName : initKettleParam.keySet()) {
					trans.setVariable(variableName, initKettleParam.get(variableName));
				}
			}
			// 执行转换
			trans.execute(null);
			// 等待转换执行结束
			trans.waitUntilFinished();
			if (trans.getErrors() > 0) {
				logger.info("runKettleTransfer执行失败: {}", ktrFileName);
			} else {
				logger.info("runKettleTransfer执行成功: {}", ktrFileName);
			}
			return true;
		} catch (Exception e) {
			logger.error("runKettleTransfer: {}", e);
			return false;
		}
	}

}

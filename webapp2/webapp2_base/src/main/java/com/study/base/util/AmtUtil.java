package com.study.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AmtUtil {

	/**
	 * 元转化为分
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String yuan2fen(String amount) throws Exception {
		DecimalFormat fmt = new DecimalFormat("######");
		fmt.setRoundingMode(RoundingMode.FLOOR);
		return fmt.format(new BigDecimal(amount).multiply(new BigDecimal(100)));
	}

	/**
	 * 分转化为元
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String fen2yuan(String amount) throws Exception {
		DecimalFormat fmt = new DecimalFormat("#####0.00");
		fmt.setRoundingMode(RoundingMode.FLOOR);
		return fmt.format(new BigDecimal(amount).divide(new BigDecimal(100)));
	}

	/**
	 * 金额格式化为两位
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String fmt2(String amount) throws Exception {
		return fmt(amount, "#####0.00");
	}

	/**
	 * 金额格式化
	 * 
	 * @param amount
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String fmt(String amount, String format) throws Exception {
		DecimalFormat fmt = new DecimalFormat(format);
		fmt.setRoundingMode(RoundingMode.FLOOR);
		return fmt.format(new BigDecimal(amount));
	}

}

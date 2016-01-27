package com.zt.test.util;

import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.base.Strings;

/**
 * 结构化标识的工具类 CodeUtil
 * 
 * 1). 组织机构代码: orgCode(初始化数据)
 * 
 * 2). 节点代码(唯一标识): orgCode-N
 * 
 * 3). 节点下的机器代码(唯一标识)：orgCode-N-M-机器id
 * 
 * 4). 节点下的服务代码(唯一标识): orgCode-N-S-服务id
 * 
 * 5). 请求方代码(唯一标识): orgCode-R-请求方id
 * 
 * @author zt
 *
 */
public class CodeUtil {
	/**
	 * 节点标记 "-N"
	 */
	public static final String NODE_SEPARATOR = "-N";
	/**
	 * 服务标记 "-S"
	 */
	public static final String SERVICE_SEPARATOR = "-S";
	/**
	 * 请求方标记 "-R"
	 */
	public static final String CONSUMER_SEPARATOR = "-R";
	/**
	 * 机器标记 "-M"
	 */
	public static final String MACHINE_SEPARATOR = "-M";
	/**
	 * 代码分隔符 "-"
	 */
	public static final String CODE_SEPARATOR = "-";

	/**
	 * 格式化id 的前补 0字符
	 */
	public static final char PAD_CHAR = '0';

	/**
	 * Long id 填充前缀字符，最小长度为minLength
	 * 
	 * @param id
	 *            Long
	 * @param padChar
	 *            填充符
	 * @param minLength
	 *            格式化后字符串最小长度
	 * @return
	 */
	private static String padStart(Long id, char padChar, int minLength) {
		checkNotNull(id);
		return Strings.padStart(String.valueOf(id), minLength, padChar);
	}

	/**
	 * 生成节点唯一标识，格式为orgCode-N
	 * 
	 * @param orgCode
	 *            组织机构代码
	 * @return
	 */
	public static String generateNodeCode(String orgCode) {
		checkNotNull(orgCode);
		return orgCode + NODE_SEPARATOR;
	}

	/**
	 * 生成机器代码，格式为orgCode-N-M-00001
	 * 
	 * @param orgCode
	 *            组织机构代码
	 * @param id
	 *            Machine's id
	 * @return
	 */
	public static String generateMachineCode(String orgCode, Long id) {
		String nodeCode = generateNodeCode(orgCode);
		String machineId = padStart(id, PAD_CHAR, 5);
		return nodeCode + MACHINE_SEPARATOR + CODE_SEPARATOR + machineId;
	}

	/**
	 * 生成服务代码，格式为orgCode-N-S-00001
	 * 
	 * @param orgCode
	 *            组织机构代码
	 * @param id
	 *            Service's id
	 * @return
	 */
	public static String generateServiceCode(String orgCode, Long id) {
		String nodeCode = generateNodeCode(orgCode);
		String serviceId = padStart(id, PAD_CHAR, 5);
		return nodeCode + SERVICE_SEPARATOR + CODE_SEPARATOR + serviceId;
	}

	/**
	 * 生成请求方代码，格式为orgCode-R-00001
	 * 
	 * @param orgCode
	 *            组织机构代码
	 * @param id
	 *            Service's id
	 * @return
	 */
	public static String generateConsumerCode(String orgCode, Long id) {
		String serviceId = padStart(id, PAD_CHAR, 5);
		return orgCode + CONSUMER_SEPARATOR + CODE_SEPARATOR + serviceId;
	}

	/**
	 * 从结构化的标识中获取组织机构代码，未找到返回空串""
	 * 
	 * @param code
	 *            结构化标识 String
	 * @return
	 */
	public static String getOrgCodeFrom(String code) {
		checkNotNull(code);
		int index = code.lastIndexOf(NODE_SEPARATOR);
		if (index == -1) {
			index = code.lastIndexOf(CONSUMER_SEPARATOR);
			if (index == -1) {
				return "";
			} else {
				return code.substring(0, index);
			}

		}
		return code.substring(0, index);
	}

	/**
	 * 判断字符串是否为数字串
	 * 
	 * @param source
	 * @return
	 */
	private static boolean isNumber(String source) {
		int length = source.length();
		for (int i = 0; i < length; i++) {
			if (!(source.charAt(i) >= '0' && source.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 根据分隔符从结构化的标识中获取 实体id，未找到分隔符返回0L
	 * 
	 * @param code
	 *            结构化标识 String
	 * @param spilit
	 *            分隔符
	 * @return
	 */
	private static Long getIdForm(String code, String spilit) {
		checkNotNull(code);
		checkNotNull(spilit);
		int index = code.lastIndexOf(spilit);
		Long result = 0L;
		if (index == -1) {
			return result;
		}
		String s = code.substring(index + spilit.length());
		if (Strings.isNullOrEmpty(s) || !isNumber(s)) {
			return result;
		}
		return new Long(s);
	}

	/**
	 * 从结构化的标识中获取 实体id
	 * 
	 * @param code
	 *            结构化标识 String
	 * @return
	 */
	public static Long getIdForm(String code) {
		return getIdForm(code, CODE_SEPARATOR);
	}
}

package net.framework.utils;

/**
 * 字符串工具类
 * 
 * @author chenqc
 *
 */
public class StringUtil {

	/** 认为是空的字词 */
	public final static String[] EMPTY_STRING_WORDS = { "undefined", "null" };

	/**
	 * 判断一个字符串是否为空 ("undefined", "null" ,null)默认都认为是空
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isEmptyString(String str) {
		if (str != null) {
			for (String s : EMPTY_STRING_WORDS) {
				if (s.equalsIgnoreCase(str)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmptyString(ts) ? null : ts;
	}

	public static String trimToEmpty(String str) {
		return str == null ? "" : str.trim();
	}

}

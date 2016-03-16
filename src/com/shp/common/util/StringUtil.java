package com.shp.common.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关方法
 */
public class StringUtil {

	public static String fillInChar(Object[] arr, String ch) {
		if ((arr == null) || (arr.length < 1)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(arr[i] + ch);
		}
		return sb.substring(0, sb.length() - ch.length());
	}

	public static String fillInChar(List list, String ch) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); ++i) {
			sb.append(list.get(i) + ch);
		}
		return sb.substring(0, sb.length() - ch.length());
	}

	public static boolean eq(String str1, String str2) {
		str1 = nullToString(str1);
		str2 = nullToString(str2);
		return str1.trim().toLowerCase().equalsIgnoreCase(str2.trim().toLowerCase());
	}

	public static String nullToString(String str) {
		if ((str == null) || (str.equals("null")) || (str.equals("NULL"))) {
			return "";
		}
		return str;
	}

	public static String trim(String s) {
		if (s == null) {
			return s;
		}
		if ((s.equals("undefined")) || (s.equalsIgnoreCase("null"))) {
			return "";
		}
		return s.trim();
	}

	public static boolean isNullOrBlank(String s) {
		if (s == null) {
			return true;
		}

		return (s.trim().equals(""));
	}

	public static boolean isNotEmpty(String str) {
		if (str == null) {
			return false;
		}

		return (!(str.equals("")));
	}

	public static String ObjectToString(Object obj) {
		if (obj == null) {
			return "";
		}
		String str = obj.toString();
		return nullToString(str);
	}

	public static String fillToNullString(String str, String fillStr) {
		if (str != null) {
			str = str.trim();
		}

		if ((str == null) || (str.equals("null")) || (str.equals("")) || (str.equals("NULL"))) {
			return fillStr;
		}
		return str;
	}

	public static String HtmlToText(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";

			Pattern p_script = Pattern.compile(regEx_script, 2);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");

			Pattern p_style = Pattern.compile(regEx_style, 2);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");

			Pattern p_html = Pattern.compile(regEx_html, 2);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	public static String getExceptionStack(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.toString() + "\r\n");
		for (StackTraceElement elem : e.getStackTrace()) {
			sb.append(elem + "\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr) {
		int i = 0;
		String TempStr = valStr;
		String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
		valStr = valStr + ",";
		while (valStr.indexOf(',') > 0) {
			returnStr[i] = valStr.substring(0, valStr.indexOf(','));
			valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

			i++;
		}
		return returnStr;
	}

}

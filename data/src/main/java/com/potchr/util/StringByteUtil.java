package com.potchr.util;

import java.nio.charset.Charset;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/21 10:59</p>
 */
public class StringByteUtil
{
	public static String subString(String string, int length)
	{
		return subString(string, length, "UTF-8");
	}

	public static String subString(String string, int length, String charset)
	{
		return subString(string, length, Charset.forName(charset));
	}

	/**
	 * 依据字节长度截取字符串
	 * @param string
	 * @param length
	 * @param charset
	 * @return
	 */
	public static String subString(String string, int length, Charset charset)
	{
		if (string == null || string.getBytes(charset).length <= length)
		{
			return string;
		}
		int maxBytesPerChar = "一".getBytes(charset).length;
		return subString("", string, length, maxBytesPerChar);
	}

	private static String subString(String newString, String oldString, int length, int maxBytesPerChar)
	{
		int neetLength = length - newString.getBytes().length;
		int tryLength = 1;
		if (neetLength < length)
		{
			String part = oldString.substring(0, tryLength);
			if (part.length() > neetLength)
			{
				return newString;
			} else
			{
				newString += part;
			}
		} else
		{
			String part = oldString.substring(0, tryLength = neetLength / maxBytesPerChar);
			newString += part;
		}
		int newByteLength = newString.getBytes().length;
		if (newByteLength < length)
		{
			newString = subString(newString, oldString.substring(tryLength), length, maxBytesPerChar);
		}
		return newString;
	}

	public static int getStringByteLength(String string)
	{
		return getStringByteLength(string, "UTF-8");
	}

	public static int getStringByteLength(String string, String charset)
	{
		return getStringByteLength(string, Charset.forName(charset));
	}

	/**
	 * 获取字符串字节长度
	 * @param string
	 * @param charset
	 * @return
	 */
	public static int getStringByteLength(String string, Charset charset)
	{
		return string == null ? 0 : string.getBytes(charset).length;
	}
}

package com.lab.sockettest.console.util;

public class BytesUtil {

	/**
	 * 截取数组
	 * 
	 * @param src
	 * @param begin
	 * @param count
	 * @return
	 */
	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] result = new byte[count];
		for (int i = 0; i < count; i++) {
			result[i] = src[i + begin];
		}
		return result;
	}

	/**
	 * 添加数组
	 * 
	 * @param result
	 * @param begin
	 * @param addBytes
	 * @return
	 */
	public static byte[] addBytes(byte[] result, int begin, byte[] addBytes) {
		if (result.length < begin + addBytes.length) {
			result = changeBytesLength(result, begin + addBytes.length);
		}
		for (int i = 0; i < addBytes.length; i++) {
			result[i + begin] = addBytes[i];
		}
		return result;
	}

	/**
	 * 修改bytes的长度
	 * 
	 * @param src
	 * @param length
	 * @return
	 */
	public static byte[] changeBytesLength(byte[] src, int length) {
		byte[] result = new byte[length];
		int minLength = length < src.length ? length : src.length;
		for (int i = 0; i < minLength; i++) {
			result[i] = src[i];
		}
		return result;
	}

	public static String bytesToString(byte[] src) {
		if (src == null) {
			return null;
		}
		String str = new String(src);
		return str;
	}

	/**
	 * 字节转int
	 * 
	 * @param src
	 * @return
	 */
	public static int bytesToInt(byte[] src) {
		int result = 0;
		for (int i = src.length - 1; i >= 0; i--) {
			result = src[i] * 256 ^ i;
		}
		return result;
	}

	public static byte[] stringToBytes(String str) {
		if (str == null) {
			return null;
		}
		byte[] byteArray = str.getBytes();
		return byteArray;
	}

	public static byte[] intToBytes(int src, int length) {
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			bytes[i] = (byte) (src >> ((3 - i) * 8));
		}
		return bytes;
	}

}

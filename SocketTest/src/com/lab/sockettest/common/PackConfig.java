package com.lab.sockettest.common;

import java.util.HashMap;
import java.util.Map;

import com.lab.sockettest.receive.BaseReceivePack;
import com.lab.sockettest.receive.RegisterPack;

/**
 * 数据包的配置
 * @author GongTengPangYi
 *
 */
public class PackConfig {

	private static Map<Byte, Map<Byte, Class<? extends BaseReceivePack>>> mainFunction = new HashMap<>();
	
	static {
		{
			Map<Byte, Class<? extends BaseReceivePack>> childFunction = new HashMap<>();
			childFunction.put((byte)01, RegisterPack.class);
			
			mainFunction.put((byte)01, childFunction);
		}
	}
	
	/**
	 * 获取请求包的class
	 * @param mainCode 主功能码
	 * @param childCode 次功能码
	 * @return class
	 */
	public static Class<? extends BaseReceivePack> getFunction(byte mainCode, byte childCode) {
		Map<Byte, Class<? extends BaseReceivePack>> childFunction = mainFunction.get(mainFunction);
		if (childFunction != null) {
			return childFunction.get(childFunction);
		}
		return null;
	}
	
}

package com.lab.sockettest.socket.parser;

import java.util.HashMap;
import java.util.Map;

import com.lab.sockettest.socket.receive.BaseReceivePack;
import com.lab.sockettest.socket.receive.RegisterRequest;

/**
 * 数据包的配置
 * @author GongTengPangYi
 *
 */
public class SocketConfig {

	private static Map<Byte, Map<Byte, Class<? extends BaseReceivePack>>> mainFunction = new HashMap<>();
	
	static {
		{
			Map<Byte, Class<? extends BaseReceivePack>> childFunction = new HashMap<>();
			childFunction.put((byte)01, RegisterRequest.class);
			childFunction.put((byte)02, RegisterRequest.class);
			childFunction.put((byte)04, RegisterRequest.class);
			childFunction.put((byte)05, RegisterRequest.class);
			childFunction.put((byte)06, RegisterRequest.class);
			
			mainFunction.put((byte)01, childFunction);
		}
		
		{
			Map<Byte, Class<? extends BaseReceivePack>> childFunction = new HashMap<>();
			childFunction.put((byte)01, RegisterRequest.class);
			childFunction.put((byte)02, RegisterRequest.class);
			childFunction.put((byte)03, RegisterRequest.class);
			childFunction.put((byte)04, RegisterRequest.class);
			
			mainFunction.put((byte)05, childFunction);
		}
	}
	
	/**
	 * 获取请求包的class
	 * @param mainCode 主功能码
	 * @param childCode 次功能码
	 * @return class
	 */
	public static Class<? extends BaseReceivePack> getFunction(byte mainCode, byte childCode) {
		Map<Byte, Class<? extends BaseReceivePack>> childFunction = mainFunction.get(mainCode);
		if (childFunction != null) {
			return childFunction.get(childCode);
		}
		return null;
	}
	
}

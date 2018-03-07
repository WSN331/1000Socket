package com.lab.sockettest.websocket;

public interface WSParser {

	/**
	 * 解包请求
	 * @param message
	 * @return
	 */
	WSRequest parse(String message);
}

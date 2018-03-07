package com.lab.sockettest.console;

import java.util.ArrayList;
import java.util.List;

import com.lab.sockettest.console.model.Terminal;
import com.lab.sockettest.socket.SocketStarterListener;

import shit.socket.ShitSocketServer;

/**
 * websocket和socket之间嫁接的核心协议
 * @author GongTengPangYi
 *
 */
public class API {

	/**
	 * 获取现在在线的服务器
	 * @return
	 */
	public static List<Terminal> terminalList() {
		List<Terminal> list = new ArrayList<>();
		ShitSocketServer server = SocketStarterListener.getTerminalServer();
		if (server != null && server.getShitSocketContext() != null) {
			for (String key : server.getShitSocketContext().keySet()) {
				Terminal terminal = new Terminal();
				terminal.setId(key);
				//TODO:
				list.add(terminal);
			}
		}
		return list;
	}
	
}

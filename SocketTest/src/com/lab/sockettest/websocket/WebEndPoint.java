package com.lab.sockettest.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.lab.sockettest.websocket.parser.JSONWSParser;

/**
 * 前端WebSocket交互，
 * @author GongTengPangYi
 *
 */
@ServerEndpoint("/web")
public class WebEndPoint {
	
	private static Map<String, Session> sessionMap = new HashMap<>();
	
	private static WSParser parser = new JSONWSParser();
	
	/**
	 * 注册WS
	 * @param key
	 * @param session
	 */
	public static void register(String key, Session session) {
		sessionMap.put(key, session);
	}
	
	public static void sendMessage(Session session, String message) throws IOException {
		if (session != null) {
			session.getBasicRemote().sendText(message);
		}
	}
	
	/**
	 * 发送消息
	 * @param key 接收者
	 * @param message 消息体
	 * @throws IOException
	 */
	public static void sendMessage(String key, String message) throws IOException {
		Session session = sessionMap.get(key);
		if (session != null) {
			session.getBasicRemote().sendText(message);
		}
	}
	
	/**
	 * 群发消息
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessageToAll(String message) throws IOException {
		for (String key : sessionMap.keySet()) {
			sendMessage(key, message);
		}
	}
	
	/**
	 * 发送响应
	 * @param key
	 * @param response
	 * @throws IOException
	 */
	public static void sendResponse(String key, WSResponse response) throws IOException {
		if (response != null) {			
			sendMessage(key, response.response());
		}
	}
	
	/**
	 * 群发响应
	 * @param response
	 * @throws IOException
	 */
	public static void sendResponseToAll(WSResponse response) throws IOException {
		if (response != null) {			
			sendMessageToAll(response.response());
		}
	}
	
	@OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
		WSRequest request = parser.parse(message);
		request.action(session);        
    }


    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }
}

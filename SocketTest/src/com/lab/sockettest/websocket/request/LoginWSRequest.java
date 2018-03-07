package com.lab.sockettest.websocket.request;

import java.io.IOException;

import javax.websocket.Session;

import com.lab.sockettest.console.API;

import net.sf.json.JSONArray;

public class LoginWSRequest extends BaseWSRequest {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void action(Session session) {
		System.out.println("login : " + name);
		
		try {
			session.getBasicRemote().sendText(JSONArray.fromObject(API.terminalList()).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

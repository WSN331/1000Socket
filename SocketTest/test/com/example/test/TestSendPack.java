package com.example.test;

import java.util.Date;

import net.sf.json.JSONObject;
import shit.socket.pack.Send;

public class TestSendPack {
	private String id;
	private Date time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Send
	public String send() {
		return JSONObject.fromObject(this).toString();
	}
	
//	@Send
//	public byte[] send() {
//		byte[] bs = {0xFFFFFFFF, 0xFFFFFFFF};
//		return bs;
//	}
}

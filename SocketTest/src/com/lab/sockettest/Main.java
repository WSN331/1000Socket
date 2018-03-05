package com.lab.sockettest;

import com.example.test.TestBytesPackParser;

import shit.socket.ShitSocketServer;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.core.StandardBytesSocketServer;

public class Main {
	
	
	public static void main(String args[]) {
		ShitSocketServer server = new StandardBytesSocketServer(new ShitSocketClientContext(), "utf-8", 5000, new TestBytesPackParser("com.example.test"), 512);
		server.start();
	}

}

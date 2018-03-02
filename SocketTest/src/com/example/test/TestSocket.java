package com.example.test;

import java.util.List;

import org.junit.Test;

import shit.helper.ShitReflectHelper;
import shit.socket.ShitSocketServer;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.core.StandardBytesSocketServer;
import shit.socket.core.StandardStringSocketServer;
import shit.socket.pack.parser.JSONPackParser;

public class TestSocket {

	public static void main(String[] args) {
		TestSocket test = new TestSocket();
		test.test2();
	}
	
	public void test1() {
		ShitSocketServer server = new StandardStringSocketServer(new ShitSocketClientContext(), "utf-8", 5000, new JSONPackParser("com.example.test"));
		server.start();
	}
	
	public void test2() {
		ShitSocketServer server = new StandardBytesSocketServer(new ShitSocketClientContext(), "utf-8", 5000, new TestBytesPackParser("com.example.test"), 512);
		server.start();
	}
	
	@Test
	public void testPackageReflect() {
		String packageName = "shit.socket";
		List<Class<?>> classList = ShitReflectHelper.getClasses(packageName);
		for (Class<?> clazz : classList) {
			System.out.println(clazz.getName());
		}
	}

}

package com;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.lab.sockettest.parser.MyPackParser;

import shit.socket.ShitSocketServer;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.core.StandardBytesSocketServer;

/**
 * Application Lifecycle Listener implementation class SocketStarterListener
 *
 */
@WebListener
public class SocketStarterListener implements ServletContextListener {

	private ShitSocketServer server;
	
    /**
     * Default constructor. 
     */
    public SocketStarterListener() {
    	server = new StandardBytesSocketServer(new ShitSocketClientContext(), "utf-8", 5000, new MyPackParser("com.lab.sockettest"), 1024);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
        server.stop();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
		server.start();
    }
	
}

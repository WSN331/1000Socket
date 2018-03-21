package com.lab.sockettest.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.lab.sockettest.socket.parser.MyPackParser;

import shit.socket.ShitSocketServer;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.core.StandardBytesSocketServer;

/**
 * Application Lifecycle Listener implementation class SocketStarterListener
 *
 */
@WebListener
public class SocketStarterListener implements ServletContextListener {

	private static ShitSocketServer terminalServer;
	
    public static ShitSocketServer getTerminalServer() {
		return terminalServer;
	}

	/**
     * Default constructor. 
     */
    public SocketStarterListener() {
    	terminalServer = new StandardBytesSocketServer(new ShitSocketClientContext(), "utf-8", 5000, new MyPackParser("com.lab.sockettest"), 1024);
    	terminalServer.setSoTimeOut(20000);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
        terminalServer.close();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
		terminalServer.start();
		
    }
	
}

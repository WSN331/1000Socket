package com.lab.sockettest.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.lab.sockettest.model.Dao;
import com.lab.sockettest.socket.parser.MyPackParser;

import shit.socket.ShitSocketServer;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.core.StandardBytesSocketServer;

/**
 * Application Lifecycle Listener implementation class SocketStarterListener
 *
 */
@WebListener
public class SocketStarterListener extends Dao {

	private static ShitSocketServer terminalServer;
	private static OnLineThread onLineThread;

    public static ShitSocketServer getTerminalServer() {
		return terminalServer;
	}

	/**
     * Default constructor.
     */
    public SocketStarterListener() {
    	terminalServer = new StandardBytesSocketServer(new ShitSocketClientContext(), "utf-8", 5000, new MyPackParser("com.lab.sockettest"), 1024);
    	terminalServer.setSoTimeOut(60000);

    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
    	super.contextDestroyed(arg0);
        terminalServer.close();
        onLineThread.stop();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {
    	super.contextInitialized(arg0);
		terminalServer.start();
		onLineThread = new OnLineThread();
		new Thread(onLineThread).start();
    }
	
}

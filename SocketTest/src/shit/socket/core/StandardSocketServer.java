package shit.socket.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import shit.socket.ShitSocketClient;
import shit.socket.ShitSocketServer;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.pack.PackParser;

public class StandardSocketServer extends ShitSocketServer {
	
	private ServerSocket serverSocket;
	
	private int port;
	
	public StandardSocketServer(ShitSocketClientContext shitSocketClientContext,
			String charset, int port, PackParser parser) {
		super(shitSocketClientContext, charset, parser);
		this.port = port;
	}

	public StandardSocketServer(ShitSocketClientContext shitSocketClientContext, String ioType,
			String charset, int port, PackParser parser) {
		super(shitSocketClientContext, ioType, charset, parser);
		this.port = port;
	}

	@Override
	protected void startInternal() {
		try {
			serverSocket = new ServerSocket(port);
			shitSocketClientContext.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void runInternal() {
		try {
			Socket clientSocket = serverSocket.accept();
			ShitSocketClient client = null;
			if (ioType != null && ioType.equals(IO_TYPE_STRING)) {				
				client = new StandardStringSocketClient(clientSocket, this);
			} else {
				client = new StandardBytesSocketClient(clientSocket, this);
			}
			client.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void initInternal() {
		
		
	}

}

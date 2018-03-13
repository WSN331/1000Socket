package shit.socket.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import shit.socket.ShitSocketClient;
import shit.socket.ShitSocketServer;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.pack.PackParser;

/**
 * 套接字服务端实现
 * @author GongTengPangYi
 *
 */
public abstract class StandardSocketServer extends ShitSocketServer {
	
	private ServerSocket serverSocket;
	
	private int port;
	
	public StandardSocketServer(ShitSocketClientContext shitSocketClientContext,
			String charset, int port, PackParser parser) {
		super(shitSocketClientContext, charset, parser);
		this.port = port;
		init();
	}


	@Override
	protected void startInternal() {
		try {
			serverSocket = new ServerSocket(port);
			shitSocketClientContext.start();
			System.out.println("the server port:" + port + " is start");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void closeInternal() {
		super.closeInternal();
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("the server is close");
	}


	@Override
	protected void runInternal() {
		try {
			Socket clientSocket = serverSocket.accept();
			ShitSocketClient<?> client = initClient(clientSocket);
			System.out.println("client connect....");
//			if (ioType != null && ioType.equals(IO_TYPE_STRING)) {				
//				client = new StandardStringSocketClient(clientSocket, this);
//			} else {
//				client = new StandardBytesSocketClient(clientSocket, this);
//			}
			client.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	protected abstract ShitSocketClient<?> initClient(Socket socket);

	@Override
	protected void initInternal() {
		
	}

}

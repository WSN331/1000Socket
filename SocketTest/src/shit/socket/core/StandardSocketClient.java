package shit.socket.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import shit.socket.ShitSocketClient;
import shit.socket.ShitSocketServer;

/**
 * 套接字的客户端的基本实现
 * @author GongTengPangYi
 *
 */
public abstract class StandardSocketClient extends ShitSocketClient {
	
	/**
	 * 套接字
	 */
	private Socket socket;
	
	/**
	 * 输出流
	 */
	private OutputStream os;
	
	protected ReceiveAction receiveAction;
	
	public StandardSocketClient(Socket socket, ShitSocketServer server) {
		super(server);
		this.socket = socket;
		receiveAction = new ReceiveAction(server.getParser());
		try {
			initInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化 
	 * @param inputStream
	 */
	public abstract void initInputStream(InputStream inputStream);

	@Override
	public void send(String message) {
		try {
			byte[] bytes = message.getBytes(shitSocketServer.getCharset());
			send(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 

	}
	
	/**
	 * 发送信息
	 * @param bytes
	 */
	@Override
	public void send(byte[] bytes) {
		if (os != null) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						os.write(bytes);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}).start();
		}
	}

	@Override
	protected void startInternal() {
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void stopInternal() {

	}

	@Override
	protected void closeInternal() {
		
	}

	@Override
	protected void initInternal() {

	}
	

}

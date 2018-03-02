package shit.socket.core;

import java.net.Socket;

import shit.socket.ShitSocketClient;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.pack.PackParser;

/**
 * 基于字节流的socket服务端
 * @author GongTengPangYi
 *
 */
public class StandardBytesSocketServer extends StandardSocketServer {
	/**
	 * 数据包的最长长度
	 */
	private int maxLength;

	public StandardBytesSocketServer(ShitSocketClientContext shitSocketClientContext, String charset, int port,
			PackParser parser) {
		super(shitSocketClientContext, charset, port, parser);
		this.maxLength = 1024;
	}
	
	public StandardBytesSocketServer(ShitSocketClientContext shitSocketClientContext, String charset, int port,
			PackParser parser, int maxLength) {
		super(shitSocketClientContext, charset, port, parser);
		this.maxLength = maxLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	protected ShitSocketClient<?> initClient(Socket socket) {
		return new StandardBytesSocketClient(socket, this);
	}

}

package shit.socket.core;

import java.net.Socket;

import shit.socket.ShitSocketClient;
import shit.socket.context.ShitSocketClientContext;
import shit.socket.pack.PackParser;


/**
 * 字符流的socket服务端
 * @author GongTengPangYi
 *
 */
public class StandardStringSocketServer extends StandardSocketServer {

	public StandardStringSocketServer(ShitSocketClientContext shitSocketClientContext, String charset, int port,
			PackParser parser) {
		super(shitSocketClientContext, charset, port, parser);
	}

	@Override
	protected ShitSocketClient<?> initClient(Socket socket) {
		return new StandardStringSocketClient(socket, this);
	}

}

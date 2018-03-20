package shit.socket.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import shit.helper.ShitReflectException;

/**
 * 基于字节流的socket客户端
 * 
 * @author GongTengPangYi
 *
 */
public class StandardBytesSocketClient extends StandardSocketClient<StandardBytesSocketServer> {

	private DataInputStream dis;

	private int maxLength = 1024;

	public StandardBytesSocketClient(Socket socket, StandardBytesSocketServer server) {
		super(socket, server);
		maxLength = server.getMaxLength();
	}

	@Override
	public void initInputStream(InputStream inputStream) {
		dis = new DataInputStream(new BufferedInputStream(inputStream));
	}

	@Override
	protected void runInternal() {
		byte[] bytes = new byte[maxLength];
		try {
			while (dis.read(bytes) != -1) {
				if (dis.available() == 0) {
					doSomething(bytes);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}

	private void doSomething(byte[] data) {
		System.out.println("\n\n");
		System.out.println(data + "**************************************************************************************************");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			receiveAction.parse(this, data);
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
	}

}

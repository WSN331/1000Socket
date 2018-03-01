package shit.socket.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import shit.helper.ShitReflectException;
import shit.socket.ShitSocketServer;

public class StandardBytesSocketClient extends StandardSocketClient {

	private DataInputStream dis;
	
	public StandardBytesSocketClient(Socket socket, ShitSocketServer server) {
		super(socket, server);
	}

	@Override
	public void initInputStream(InputStream inputStream) {
        dis = new DataInputStream(new BufferedInputStream(inputStream));
	}

	@Override
	protected void runInternal() {
		byte[] bytes = new byte[1024];
        try {
			while (dis.read(bytes) != -1) {
			    if (dis.available() == 0) { 
			        doSomething(bytes);
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doSomething(byte[] data) {
		System.out.println(data);
		try {
			receiveAction.parse(this, data);
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
	}

}

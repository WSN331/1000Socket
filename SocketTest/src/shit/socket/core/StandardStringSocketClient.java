package shit.socket.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import shit.helper.ShitReflectException;

/**
 * 字符流的socket客户
 * @author Administrator
 *
 */
public class StandardStringSocketClient extends StandardSocketClient<StandardStringSocketServer> {

	private String line;
	
	private BufferedReader br;
	
	public StandardStringSocketClient(Socket socket, StandardStringSocketServer server) {
		super(socket, server);
	}

	@Override
	public void initInputStream(InputStream inputStream) {
		br = new BufferedReader(new InputStreamReader(inputStream));
	}

	@Override
	protected void runInternal() {
		try {
			while(flag && (line = br.readLine()) != null) {				
				parseLine(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 解析
	 * @param line
	 */
	protected void parseLine(String line) {
		System.out.println(line);
		try {
			receiveAction.parse(this, line);
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
	}
}

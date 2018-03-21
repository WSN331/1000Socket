package shit.socket;

import shit.socket.context.ShitSocketClientContext;
import shit.socket.core.SendHelper;
import shit.socket.pack.PackParser;

/**
 * Socket总服务线程类的抽象类，声明了Socket服务线程类需要实现的操作
 * 
 * @author GongTengPangYi
 *
 */
public abstract class ShitSocketServer extends RunnableLifeCycle {

	/**
	 * SocketClient总容器
	 */
	protected ShitSocketClientContext shitSocketClientContext;

	/**
	 * 编码格式
	 */
	private String charset;
	
	/**
	 * 解析器
	 */
	protected PackParser parser;
	
	/**
	 * 设置读取的超时时间
	 */
	protected Integer soTimeOut;
		
	public ShitSocketServer(ShitSocketClientContext shitSocketClientContext, String charset, PackParser parser) {
		super();
		this.shitSocketClientContext = shitSocketClientContext;
		this.charset = charset;
		this.parser = parser;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public ShitSocketClientContext getShitSocketContext() {
		return shitSocketClientContext;
	}

	public void setShitSocketContext(ShitSocketClientContext shitSocketClientContext) {
		this.shitSocketClientContext = shitSocketClientContext;
	}

	public PackParser getParser() {
		return parser;
	}

	public void setParser(PackParser parser) {
		this.parser = parser;
	}

	public void setSoTimeOut(Integer soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	@Override
	protected void closeInternal() {
		shitSocketClientContext.close();
	}
	
	/**
	 * 向同服务的其他客户连接发送字节信息
	 * 
	 * @param key
	 *            其他客户的键
	 * @param data
	 *            发送的信息
	 */
	public void send(String key, byte[] data) {
		ShitSocketClient<?> client = getShitSocketContext().get(key);
		if (client != null) {
			client.send(data);
		}
	}
	
	/**
	 * 向同服务的其他客户连接发送信息
	 * 
	 * @param key
	 *            其他客户的键
	 * @param message
	 *            发送的信息
	 */
	public void send(String key, String message) {
		ShitSocketClient<?> client = getShitSocketContext().get(key);
		if (client != null) {
			client.send(message);
		}
	}

	/**
	 * 向同服务的其他客户连接发送数据包
	 * 
	 * @param key
	 *            其他客户的键
	 * @param pack
	 *            发送的数据包
	 */
	public void sendPack(String key, Object pack) {
		Object obj = SendHelper.getSend(pack);
		if (obj == null) {
			return;
		}
		if (obj instanceof String) {
			send(key, (String)obj);
		} else {
			send(key, (byte[])obj);
		}
	}
}

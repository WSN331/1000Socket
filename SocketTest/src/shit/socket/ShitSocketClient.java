package shit.socket;

import shit.socket.context.ShitSocketClientContext;
import shit.socket.core.SendHelper;

public abstract class ShitSocketClient<T extends ShitSocketServer> extends RunnableLifeCycle {

	/**
	 * SocketClient总容器
	 */
	protected T shitSocketServer;

	public ShitSocketClient(T shitSocketServer) {
		super();
		this.shitSocketServer = shitSocketServer;
	}

	/**
	 * 给当前客户发送字节信息
	 * 
	 * @param data
	 *            待发送的信息
	 */
	public abstract void send(byte[] data);

	/**
	 * 给当前客户发送信息
	 * 
	 * @param message
	 *            待发送的信息
	 */
	public abstract void send(String message);
	
	/**
	 * 向同服务的其他客户连接发送字节信息
	 * 
	 * @param key
	 *            其他客户的键
	 * @param data
	 *            发送的信息
	 */
	public void send(String key, byte[] data) {
		shitSocketServer.send(key, data);
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
		shitSocketServer.send(key, message);
	}

	/**
	 * 给当前客户发送数据包
	 * 
	 * @param pack
	 *            待发送的数据包
	 */
	public void sendPack(Object pack) {
		Object obj = SendHelper.getSend(pack);
		if (obj == null) {
			return;
		}
		if (obj instanceof String) {
			send((String)obj);
		} else {
			send((byte[])obj);
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
		shitSocketServer.sendPack(key, pack);
	}
	
	/**
	 * 注册到容器中以方便其他链接访问
	 */
	public void register(String key) {
		if (shitSocketServer != null) {
			ShitSocketClientContext context = shitSocketServer.getShitSocketContext();
			if (context != null) {
				context.set(key, this);
			}
		}
	}

}

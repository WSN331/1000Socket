package shit.socket.pack;

public interface PackParser {

	/**
	 * 解析接收到的消息获取数据包类
	 * @param message
	 * @return class
	 */
	public Class<?> parseClass(String message);
	
	/**
	 * 将数据解析成对象
	 * @param clazz
	 * @param message
	 * @return
	 */
	public Object parseObject(Class<?> clazz, String message);
	
	/**
	 * 解析接收到的字节消息获取数据包类
	 * @param data
	 * @return class
	 */
	public Class<?> parseClass(byte[] data);
	
	/**
	 * 将数据解析成对象
	 * @param clazz
	 * @param data
	 * @return
	 */
	public Object parseObject(Class<?> clazz, byte[] data);
	
}

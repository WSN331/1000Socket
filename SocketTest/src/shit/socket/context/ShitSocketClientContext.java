package shit.socket.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import shit.socket.ShitSocketClient;
import shit.socket.ShitSocketContext;

/**
 * 基本容器类
 * 
 * @author GongTengPangYi
 *
 */
@SuppressWarnings("rawtypes")
public class ShitSocketClientContext implements ShitSocketContext<ShitSocketClient> {

	Map<String, ShitSocketClient> clientMap;

	public ShitSocketClientContext() {
		super();
		init();
	}

	@Override
	public void close() {
		if (clientMap == null) {
			return;
		}
		for (String key : clientMap.keySet()) {
			clientMap.get(key).close();
		}
	}

	@Override
	public void start() {
		// TODO:
	}

	@Override
	public void init() {
		clientMap = new HashMap<>();
	}

	@Override
	public ShitSocketClient get(String key) {
		if (clientMap == null) {
			return null;
		}
		return clientMap.get(key);
	}

	@Override
	public void set(String key, ShitSocketClient value) {
		if (clientMap != null) {
			clientMap.put(key, value);
		}
	}

	@Override
	public Set<String> keySet() {
		return clientMap.keySet();
	}

}

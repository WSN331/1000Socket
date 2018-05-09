package com.lab.sockettest.websocket.request;

import javax.websocket.Session;

import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.bean.Device;
import com.lab.sockettest.model.bean.Version;
import com.lab.sockettest.socket.SocketStarterListener;
import com.lab.sockettest.socket.send.ServerAccordUpdateRequest;
import com.lab.sockettest.websocket.WebEndPoint;
import net.sf.json.JSONObject;

import java.io.IOException;

//{"method":"update","body":{"sessionKey":"20180314120008771", "deviceId": "dlkz000111111111"},"type":01}
public class UpdateWSRequest extends BaseWSRequest {
	
	private String sessionKey;
	
	private String deviceId;
	
	private Integer type;
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public void action(Session session) {
		ServerAccordUpdateRequest request = new ServerAccordUpdateRequest();
		Version version = BizFactory.getVersionBiz().findLastVersion();
		Device device = BizFactory.getDeviceBiz().findByDeviceId(deviceId);
		if (version != null) {
			if (device == null || !device.getVersion().equals(version.getVersion())) {
				request.setCount(version.getCount());
				request.setSize(version.getSize());
				request.setType(type);
				request.setVersion(version.getVersion());
				SocketStarterListener.getTerminalServer().sendPack(deviceId, request);
			} else {
				JSONObject jobj = new JSONObject();
				jobj.put("message", "已经是最新版本!!!");
				try {
					WebEndPoint.sendJSON(session, jobj, "alert");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}

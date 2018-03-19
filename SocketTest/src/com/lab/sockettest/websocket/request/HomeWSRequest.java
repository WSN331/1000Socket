package com.lab.sockettest.websocket.request;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.Session;

import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.biz.DeviceBiz;
import com.lab.sockettest.websocket.WebEndPoint;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//{"method":"home","body":{"pagerIndex":1}}
public class HomeWSRequest extends BaseWSRequest {
	
	private Integer pagerIndex;
	private String deviceId;

	public Integer getPagerIndex() {
		return pagerIndex;
	}

	public void setPagerIndex(Integer pagerIndex) {
		this.pagerIndex = pagerIndex;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public void action(Session session) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String sessionKey = format.format(now);
		WebEndPoint.register(sessionKey, session);
		try {
			JSONObject jobj = new JSONObject();
			jobj.put("sessionKey", sessionKey);
			DeviceBiz deviceBiz = BizFactory.getDeviceBiz();
			jobj.put("count", deviceBiz.findAll().size());
			System.out.println(deviceId);
			if (deviceId == null || deviceId.equals("")) {				
				jobj.put("deviceList", JSONArray.fromObject(deviceBiz.findByPager(pagerIndex)));
			} else {
				JSONArray jsonArray = new JSONArray();
				jsonArray.add(JSONObject.fromObject(deviceBiz.findByDeviceId(deviceId)));
				jobj.put("deviceList", jsonArray);
			}
			WebEndPoint.sendJSON(session, jobj, "home");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

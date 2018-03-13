package com.lab.sockettest.console.util;

import com.lab.sockettest.model.Dao;
import com.lab.sockettest.model.biz.DeviceBiz;
import com.lab.sockettest.model.biz.DeviceBizImpl;

public class BizUtil {
	
	private static DeviceBiz deviceBiz;
	
	public static DeviceBiz getDeviceBiz() {
		if (deviceBiz == null) {
			deviceBiz = new DeviceBizImpl(Dao.getSession());
		}
		return deviceBiz;
	}

}

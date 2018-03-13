package com.lab.sockettest.console.util;

import com.lab.sockettest.model.Dao;
import com.lab.sockettest.model.biz.DeviceBiz;
import com.lab.sockettest.model.biz.DeviceBizImpl;
import com.lab.sockettest.model.biz.VersionBiz;
import com.lab.sockettest.model.biz.VersionBizImpl;

public class BizUtil {
	
	private static DeviceBiz deviceBiz;
	private static VersionBiz versionBiz;
	
	public static DeviceBiz getDeviceBiz() {
		if (deviceBiz == null) {
			deviceBiz = new DeviceBizImpl(Dao.getSession());
		}
		return deviceBiz;
	}
	
	public static VersionBiz getVersionBiz() {
		if (versionBiz == null) {
			versionBiz = new VersionBizImpl(Dao.getSession());
		}
		return versionBiz;
	}

}

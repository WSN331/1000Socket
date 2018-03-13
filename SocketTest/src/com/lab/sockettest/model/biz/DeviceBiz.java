package com.lab.sockettest.model.biz;


import com.lab.sockettest.model.bean.Device;

public interface DeviceBiz extends BaseBiz<Device> {
	
	Device findByDeviceId(String deviceId);
	
}

package com.lab.sockettest.model.biz;

import java.util.List;

import com.lab.sockettest.model.bean.Device;

public interface DeviceBiz {

	Device save(Device device);
	
	Device update(Device device);
	
	boolean delete(Device device);
	
	List<Device> findAll();
	
	Device findByDeviceId(String deviceId);
	
}

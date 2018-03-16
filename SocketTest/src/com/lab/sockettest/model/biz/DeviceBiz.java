package com.lab.sockettest.model.biz;


import java.util.List;

import com.lab.sockettest.model.bean.Device;

public interface DeviceBiz extends BaseBiz<Device> {
	
	Device findByDeviceId(String deviceId);
	
	List<Device> findByPager(int pagerIndex);
	
}

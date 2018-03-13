package com.lab.sockettest.model.biz;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lab.sockettest.model.bean.Device;

import shit.db.ShitDBSession;
import shit.db.exception.ShitDBConfigureException;
import shit.db.exception.ShitDBJDBCException;
import shit.db.exception.ShitDBTranslateException;
import shit.db.exception.ShitDBWrongControlException;


public class DeviceBizImpl extends BaseBizImpl<Device> implements DeviceBiz {
	

	public DeviceBizImpl(ShitDBSession dbSession) {
		super(dbSession, Device.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Device findByDeviceId(String deviceId) {
		String shitQL = "select * from " + Device.class.getName() + 
				" o where o.deviceId = :deviceId order by o.id desc";		
		Map<String, Serializable> params = new HashMap<>(1);
		params.put("deviceId", deviceId);
		try {
			List<Device> list = (List<Device>) dbSession.query(Device.class, shitQL, params);
			if (list != null && list.size()>0) {
				return list.get(0);
			}
		} catch (ShitDBJDBCException e) {
			e.printStackTrace();
		} catch (ShitDBWrongControlException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		} catch (ShitDBTranslateException e) {
			e.printStackTrace();
		}
		return null;
	}

}

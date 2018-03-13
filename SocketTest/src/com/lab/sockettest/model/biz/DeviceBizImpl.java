package com.lab.sockettest.model.biz;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lab.sockettest.model.bean.Device;

import shit.db.ShitDBSession;
import shit.db.exception.ShitDBConfigureException;
import shit.db.exception.ShitDBExecuteException;
import shit.db.exception.ShitDBJDBCException;
import shit.db.exception.ShitDBTranslateException;
import shit.db.exception.ShitDBWrongControlException;


public class DeviceBizImpl implements DeviceBiz {
	
	ShitDBSession dbSession;

	public DeviceBizImpl(ShitDBSession dbSession) {
		super();
		this.dbSession = dbSession;
	}

	@Override
	public Device save(Device device) {
		try {
			return (Device) dbSession.save(device);
		} catch (ShitDBExecuteException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Device update(Device device) {
		try {
			return (Device) dbSession.update(device);
		} catch (ShitDBExecuteException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Device device) {
		try {
			dbSession.delete(device);
			return true;
		} catch (ShitDBExecuteException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> findAll() {
		try {
			return (List<Device>) dbSession.findAll(Device.class);
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

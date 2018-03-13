package com.lab.sockettest.model.bean;

import java.io.Serializable;
import java.util.Date;

import shit.db.table.ShitDBField;
import shit.db.table.ShitDBTable;

@ShitDBTable(name = "T_DEVICE", primaryKey = "id")
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5204181930534502325L;

	@ShitDBField(name = "C_ID", length = 11)
	private Integer id;
	@ShitDBField(name = "C_DEVICE_ID", length = 32)
	private String deviceId;
	@ShitDBField(name = "C_VERSION", length = 32)
	private String version;
	@ShitDBField(name = "C_CREATE_TIME")
	private Date createTime;
	@ShitDBField(name = "C_TYPE")
	private Integer type;
	@ShitDBField(name = "C_SWITCH_1")
	private Integer switch1;
	@ShitDBField(name = "C_SWITCH_2")
	private Integer switch2;
	@ShitDBField(name = "C_ONLINE")
	private Integer online;
	@ShitDBField(name = "C_LAST_HEART")
	private Date lastHeartTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSwitch1() {
		return switch1;
	}

	public void setSwitch1(Integer switch1) {
		this.switch1 = switch1;
	}

	public Integer getSwitch2() {
		return switch2;
	}

	public void setSwitch2(Integer switch2) {
		this.switch2 = switch2;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Date getLastHeartTime() {
		return lastHeartTime;
	}

	public void setLastHeartTime(Date lastHeartTime) {
		this.lastHeartTime = lastHeartTime;
	}

}

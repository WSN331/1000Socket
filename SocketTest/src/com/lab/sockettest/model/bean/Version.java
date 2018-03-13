package com.lab.sockettest.model.bean;

import shit.db.table.ShitDBField;
import shit.db.table.ShitDBTable;

@ShitDBTable(name = "T_VERSION", primaryKey = "id")
public class Version extends MyEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4484552317443263784L;

	@ShitDBField(name = "C_VERSION")
	private String version;
	
	@ShitDBField(name = "C_SIZE")
	private Integer size;
	
	@ShitDBField(name = "C_COUNT")
	private Integer count;
	
	@ShitDBField(name = "C_LOCATION")
	private String location;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}

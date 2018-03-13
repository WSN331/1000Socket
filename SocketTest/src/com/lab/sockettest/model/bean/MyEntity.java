package com.lab.sockettest.model.bean;

import java.io.Serializable;

import shit.db.table.ShitDBField;

public class MyEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5385097079710766774L;
	@ShitDBField(name = "C_ID", length = 11)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

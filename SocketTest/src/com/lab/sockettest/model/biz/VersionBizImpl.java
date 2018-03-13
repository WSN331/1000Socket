package com.lab.sockettest.model.biz;

import java.util.List;

import com.lab.sockettest.model.bean.Version;

import shit.db.ShitDBSession;

public class VersionBizImpl extends BaseBizImpl<Version> implements VersionBiz {

	public VersionBizImpl(ShitDBSession dbSession) {
		super(dbSession, Version.class);
	}

	@Override
	public Version findLastVersion() {
		List<Version> versionList = findAll();
		if (versionList != null && versionList.size() > 0) {
			return versionList.get(0);
		}
		return null;
	}

}

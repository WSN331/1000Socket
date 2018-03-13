package com.lab.sockettest.model.biz;

import com.lab.sockettest.model.bean.Version;

public interface VersionBiz extends BaseBiz<Version> {
	
	Version findLastVersion();

}

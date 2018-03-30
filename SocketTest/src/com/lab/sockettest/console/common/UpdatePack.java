package com.lab.sockettest.console.common;

import com.lab.sockettest.console.util.BytesUtil;
import com.lab.sockettest.model.bean.Version;
import shit.helper.ShitFileHelper;

/**
 *
 */
public class UpdatePack {

    private Version latestVersion;

    private byte[] latestVersionPack;

    private static int packSize = 512;

    public UpdatePack(Version latestVersion) {
        this.latestVersion = latestVersion;
        latestVersionPack = ShitFileHelper.read(latestVersion.getLocation());
    }


    public void setPackSize(int packSize1) {
        packSize = packSize1;
    }

    public int packLength() {
        return latestVersionPack.length;
    }

    public int packCount() {
        return (int) Math.ceil(packLength() / packSize);
    }

    public byte[] getOnePack(int index) {
        int begin = (index - 1) * packSize;
        int count = index == packCount() ? packCount() - begin : packSize;
        return BytesUtil.subBytes(latestVersionPack, begin, count);
    }
}

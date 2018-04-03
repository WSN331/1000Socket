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
        int packLength = packLength();
        double result = ((double) packLength) / packSize;
        result = Math.ceil(result);
        return (int) result;
    }

    public byte[] getOnePack(int index) {
        int begin = (index) * packSize;
        int count = index == packCount()-1 ? packLength() - begin : packSize;
        return BytesUtil.subBytes(latestVersionPack, begin, count);
    }
}

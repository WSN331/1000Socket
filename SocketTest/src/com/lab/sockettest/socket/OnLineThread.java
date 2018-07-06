package com.lab.sockettest.socket;

import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.bean.Device;
import com.lab.sockettest.model.biz.DeviceBiz;

import java.util.Date;
import java.util.List;

public class OnLineThread implements Runnable {

    private static int ONLINE_TIME = 120 * 1000;

    private DeviceBiz deviceBiz;

    private boolean flag;

    public OnLineThread() {
        this.deviceBiz = BizFactory.getDeviceBiz();
        this.flag = true;
    }

    @Override
    public void run() {
        while (flag) {
            List<Device> deviceList = deviceBiz.findAll();
            Date now = new Date();
            for (Device device : deviceList) {
                if (device.getLastHeartTime() == null || now.getTime() - device.getLastHeartTime().getTime() > ONLINE_TIME) {
                    device.setOnline(0);
                    deviceBiz.update(device);
                }
                device = null;
            }
            deviceList = null;
            now = null;
            System.gc();
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        flag = false;
    }
}

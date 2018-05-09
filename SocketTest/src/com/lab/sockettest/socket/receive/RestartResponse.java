package com.lab.sockettest.socket.receive;

import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.bean.Device;
import com.lab.sockettest.model.biz.DeviceBiz;
import com.lab.sockettest.websocket.WebEndPoint;
import com.lab.sockettest.websocket.response.DeviceStateChangeResponse;
import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

import java.io.IOException;

public class RestartResponse extends BaseReceivePack {

    /**
     *
     */
    private static final long serialVersionUID = 3139249574149389057L;

    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public void setBody(byte[] body) {
        result = body[0];
    }

    @ReceiveAction
    public void receiveAction(StandardBytesSocketClient socketClient) {

        String deviceId = socketClient.getKey();
        DeviceBiz deviceBiz = BizFactory.getDeviceBiz();
        Device device = deviceBiz.findByDeviceId(deviceId);
        switch (result) {
            case 0:
                break;
            case 1:
                device.setOnline(0);
                device.setSwitch1(0);
                device.setSwitch2(0);
                deviceBiz.update(device);
                DeviceStateChangeResponse response = new DeviceStateChangeResponse();
                response.setDevice(device);
                try {
                    WebEndPoint.sendResponseToAll(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }


    }

}

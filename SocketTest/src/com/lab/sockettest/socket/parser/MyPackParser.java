package com.lab.sockettest.socket.parser;

import com.lab.sockettest.socket.receive.BaseReceivePack;

import shit.helper.ShitReflectException;
import shit.helper.ShitReflectHelper;
import shit.socket.pack.parser.BaseBytesPackParser;

public class MyPackParser extends BaseBytesPackParser {

	public MyPackParser(String packageName) {
		super(packageName);
	}

	@Override
	public Class<?> parseClass(byte[] data) {
		if (data.length > 16) {
			System.out.print("func:" + data[4] + " " + data[5] + ";  ");
			Class<? extends BaseReceivePack> packClass = SocketConfig.getFunction(data[4], data[5]);
			return packClass;
		}
		return null;
	}

	@Override
	public Object parseObject(Class<?> clazz, byte[] data) {
		try {
			BaseReceivePack pack = (BaseReceivePack) ShitReflectHelper.newInstance(clazz);
			
			byte[] id = new byte[4];
			for (int i=0; i<4; i++) {
				id[i] = data[i+6];
			}
			pack.setId(id);
			
			int length = data[10] * 16 * 16 + data[11];
			pack.setLength(length);
			
			byte[] body = new byte[length];
			System.out.println("body.length = " + body.length);
			for (int i=0; i<length; i++) {
				body[i] = data[i+12];
				System.out.print(body[i]);
				System.out.print(" ");
			}
			pack.setBody(body);
			
			return pack;
			
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

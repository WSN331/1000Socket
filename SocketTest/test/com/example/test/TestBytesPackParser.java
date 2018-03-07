package com.example.test;

import shit.helper.ShitReflectException;
import shit.helper.ShitReflectHelper;
import shit.socket.pack.parser.BaseBytesPackParser;

public class TestBytesPackParser extends BaseBytesPackParser {

	public TestBytesPackParser(String packageName) {
		super(packageName);
	}

	@Override
	public Class<?> parseClass(byte[] data) {
		return TestReceiveBytesPack.class;
	}

	@Override
	public Object parseObject(Class<?> clazz, byte[] data) {
		try {
			TestReceiveBytesPack pack = (TestReceiveBytesPack) ShitReflectHelper.newInstance(clazz);
			pack.setParam1(data);
			return pack;
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected class TestClassJudger implements ClassJudger {

		@Override
		public boolean isThisClass(String objKey, String objValue) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}

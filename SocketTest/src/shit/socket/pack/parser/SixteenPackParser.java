package shit.socket.pack.parser;

import shit.helper.ShitJSONHelper;
import shit.socket.pack.PackParser;

public class SixteenPackParser implements PackParser {
	
	static{
		ShitJSONHelper.init("shit.helper.json.netsf.ShitJSONInitByNetSf");
	}
	
	private String packageName;

	public SixteenPackParser(String packageName) {
		super();
		this.packageName = packageName;
	}

	@Override
	public Class<?> parseClass(String message) {
		return null;
	}

	@Override
	public Object parseObject(Class<?> clazz, String message) {
		return null;
	}

	@Override
	public Class<?> parseClass(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object parseObject(Class<?> clazz, byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

}

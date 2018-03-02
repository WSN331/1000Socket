package shit.socket.pack.parser;

import shit.helper.ShitJSONHelper;
import shit.helper.json.ShitJSONObject;
import shit.helper.json.netsf.ShitJSONObjectByNetSf;

public class JSONPackParser extends BasePackParser {

	static {
		ShitJSONHelper.init("shit.helper.json.netsf.ShitJSONInitByNetSf");
	}

	public JSONPackParser(String packageName) {
		super(packageName);
	}

	@Override
	public Class<?> parseClass(String message) {
		if (message == null || message.equals("")) {
			return null;
		}
		ShitJSONObject<?> jobj = parse(message);
		return findClassInPackage(new JSONClassJudger(jobj));
	}

	@Override
	public Object parseObject(Class<?> clazz, String message) {
		ShitJSONObject<?> jobj = parse(message);
		return jobj.toBean(clazz);
	}

	private ShitJSONObject<?> parse(String message) {
		return ShitJSONObjectByNetSf.fromObject(message);
	}

	@Override
	public Class<?> parseClass(byte[] data) {
		return null;
	}

	@Override
	public Object parseObject(Class<?> clazz, byte[] data) {
		return null;
	}

	/**
	 * JSON解析数据的类判断器
	 * 
	 * @author GongTengPangYi
	 *
	 */
	protected class JSONClassJudger implements ClassJudger {

		private ShitJSONObject<?> jobj;

		public JSONClassJudger(ShitJSONObject<?> jobj) {
			this.jobj = jobj;
		}

		@Override
		public boolean isThisClass(String objKey, String objValue) {
			return objValue != null && objValue.equals(jobj.get(objKey));
		}

	}

}

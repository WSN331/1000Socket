package shit.socket.pack.parser;

import java.util.List;

import shit.helper.ShitReflectHelper;
import shit.socket.pack.PackParser;
import shit.socket.pack.Receive;

/**
 * 基本数据包解析器
 * 
 * @author GongTengPangYi
 *
 */
public abstract class BasePackParser implements PackParser {

	protected String packageName;

	public BasePackParser(String packageName) {
		super();
		this.packageName = packageName;
	}

	/**
	 * 根据包名来查找数据对应的类
	 * 
	 * @param classJudger
	 *            类判断器
	 * @return
	 */
	protected Class<?> findClassInPackage(ClassJudger classJudger) {
		List<Class<?>> clazzs = ShitReflectHelper.getClasses(packageName);
		for (Class<?> clazz : clazzs) {
			Receive receive = clazz.getAnnotation(Receive.class);
			if (receive == null) {
				continue;
			}
			String objKey = receive.objKey();
			String objValue = receive.objValue();
			if (classJudger.isThisClass(objKey, objValue)) {
				return clazz;
			}
		}
		return null;
	}

	protected interface ClassJudger {

		/**
		 * 判断此包是否为当前类
		 * 
		 * @param objKey
		 * @param objValue
		 * @return
		 */
		boolean isThisClass(String objKey, String objValue);

	}

}

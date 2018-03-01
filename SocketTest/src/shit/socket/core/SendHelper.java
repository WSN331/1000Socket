package shit.socket.core;

import java.lang.reflect.Method;

import shit.helper.ShitReflectException;
import shit.helper.ShitReflectHelper;
import shit.socket.pack.Send;

public class SendHelper {

	
	public static String getString(Object pack) {
		Class<?> packClass = pack.getClass();
		Method[] methods = ShitReflectHelper.findMethods(packClass, 0);
		try {
			for (Method method : methods) {
				if (method.getAnnotation(Send.class) != null) {
					return (String) ShitReflectHelper.invokeMethod(pack, method);
				}
			}
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
		return "thanks";
	}
	
	public static byte[] getBytes(Object pack) {
		Class<?> packClass = pack.getClass();
		Method[] methods = ShitReflectHelper.findMethods(packClass, 0);
		try {
			for (Method method : methods) {
				if (method.getAnnotation(Send.class) != null) {
					return (byte[]) ShitReflectHelper.invokeMethod(pack, method);
				}
			}
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static Object getSend(Object pack) {
		Class<?> packClass = pack.getClass();
		Method[] methods = ShitReflectHelper.findMethods(packClass, 0);
		try {
			for (Method method : methods) {
				if (method.getAnnotation(Send.class) != null) {
					return ShitReflectHelper.invokeMethod(pack, method);
				}
			}
		} catch (ShitReflectException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package shit.db.cfg;

import java.beans.PropertyVetoException;
import java.lang.reflect.Method;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import shit.helper.ShitReflectException;
import shit.helper.ShitReflectHelper;

/**
 * C3P0的数据源 基于C3P0、JDBC
 * 
 * @author GongTengPangYi
 *
 */
public class ShitDBC3P0DataSource extends ShitDBDataSource {
	/**
	 * 数据源
	 */
	ComboPooledDataSource cpds = new ComboPooledDataSource();

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public ComboPooledDataSource getComboPooledDataSource() {
		return cpds;
	}

	/**
	 * 设置数据源
	 * 
	 * @param cpds
	 */
	public void setComboPooledDataSource(ComboPooledDataSource cpds) {
		this.cpds = cpds;
	}

	@Override
	public void setDataSourceByProperties(Properties props) {
//		//TODO: ??
		try {
//			cpds.setProperties(props);
//			for (Object key : props.keySet()) {
//				String ketStr = key.toString();
//				String methodName = "set" + ketStr.substring(0, 1).toUpperCase() + ketStr.substring(1);
//				try {
//					Method method = ComboPooledDataSource.class.
//				} catch (ShitReflectException e) {
//					e.printStackTrace();
//				}
//			}
			cpds.setDriverClass(props.getProperty("driverClass"));
			cpds.setJdbcUrl(props.getProperty("jdbcUrl"));
			cpds.setUser(props.getProperty("user"));
			cpds.setPassword(props.getProperty("password"));

			cpds.setTestConnectionOnCheckin(Boolean.valueOf(props.getProperty("testConnectionOnCheckin")));
			cpds.setIdleConnectionTestPeriod(Integer.valueOf(props.getProperty("idleConnectionTestPeriod")));
			cpds.setMaxIdleTime(Integer.valueOf(props.getProperty("maxIdleTime")));
			cpds.setTestConnectionOnCheckout(Boolean.valueOf(props.getProperty("testConnectionOnCheckout")));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
	}
}
